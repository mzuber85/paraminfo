package com.intigral.assignment.weathermap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intigral.assignment.weathermap.integration.OpenWeatherMap;
import com.intigral.assignment.weathermap.vo.WeatherCacheVO;

/**
 * Service loads and caches the weather data for city/coordinates provided. Each
 * request is checked if the data is available in local cache and is < 2 hours
 * old than returns from cache else calls weather map api
 * 
 * @author Muhammad Zubair
 *
 */
@Service
public class WeatherMapServiceImpl implements IWeatherMapService {

	private List<WeatherCacheVO> cache = new ArrayList<>(50);

	@Value("${wmap.api.response.cache.time.hours}")
	private Integer apiResponseCacheTime;

	@Autowired
	OpenWeatherMap openWeatherMap;

	/**
	 * Returns the weather information for the given city,country Can also return
	 * Timeout for slow/down APIs or Internal Server Errors for any other exception
	 */
	public ResponseEntity<Object> getByCity(String cityName, String countryName) {

		ResponseEntity<Object> response = null;
		Object weatherDetails = checkCache(cityName);
		if (weatherDetails != null) {
			// already in cache, serve it
			response = new ResponseEntity<>(weatherDetails, HttpStatus.OK);
			return response;
		}

		String query = cityName;
		if (countryName != null && countryName.length() > 0) {
			query += "," + countryName;
		}

		try {
			ResponseEntity<Map<String, Object>> apiResponse = openWeatherMap.getByCity(query);
			if (apiResponse.getStatusCode() == HttpStatus.OK) {
				Map<String, Object> responseBody = apiResponse.getBody();
				setToCache(responseBody);
				response = new ResponseEntity<>(responseBody, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(apiResponse.getBody(), apiResponse.getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(
					": No information found for city:" + cityName + " please check and resubmit request",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/**
	 * Returns the weather information for the given Coordinates Can also return
	 * Timeout for slow/down APIs or Internal Server Errors for any other exception
	 */
	public ResponseEntity<Object> getByCoordinates(Double lat, Double lng) {

		ResponseEntity<Object> response = null;
		Object weatherDetails = checkCache(lat, lng);
		if (weatherDetails != null) {
			// already in cache, serve it
			response = new ResponseEntity<>(weatherDetails, HttpStatus.OK);
			return response;
		}

		try {
			ResponseEntity<Map<String, Object>> apiResponse = openWeatherMap.getByCoordinates(lat, lng);
			if (apiResponse.getStatusCode() == HttpStatus.OK) {
				Map<String, Object> responseBody = apiResponse.getBody();
				setToCache(responseBody);
				response = new ResponseEntity<>(responseBody, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(apiResponse.getBody(), apiResponse.getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(
					": No information found for lat:" + lat + ",long:" + lng + " please check and resubmit request",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/**
	 * Sets the object in cache for 2 hours to be loaded for subsiquent requests
	 * 
	 * @param responseBody
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private WeatherCacheVO setToCache(Map<String, Object> responseBody) {
		WeatherCacheVO vo = new WeatherCacheVO();
		vo.setCityName(responseBody.get("name") + "");
		Object coord = responseBody.get("coord");
		if (coord != null && coord instanceof Map) {
			Map coordMap = (Map) coord;
			vo.setLat(new Double(coordMap.get("lat") + ""));
			vo.setLng(new Double(coordMap.get("lon") + ""));
			vo.setLastLoaded(System.currentTimeMillis());
			cache.add(vo);
		}
		vo.setFullData(responseBody);
		return vo;
	}

	/**
	 * check if the data is available in cache for the city
	 * 
	 * @param cityName
	 * @return
	 */
	private Map<String, Object> checkCache(String cityName) {

		WeatherCacheVO vo = null;
		if (!cache.isEmpty()) {
			vo = cache.stream()
					.filter(wvo -> wvo.getCityName().equalsIgnoreCase(cityName) || wvo.getCityName().contains(cityName))
					.findAny().orElse(null);
		}
		return checkCacheObject(vo);
	}

	/**
	 * checks cache if data is available for lat, long
	 * 
	 * @param lat
	 * @param lng
	 * @return
	 */
	private Map<String, Object> checkCache(Double lat, Double lng) {
		WeatherCacheVO vo = null;
		if (!cache.isEmpty()) {
			vo = cache.stream().filter(wvo -> wvo.getLat().equals(lat) && wvo.getLng().equals(lng)).findAny()
					.orElse(null);
		}
		return checkCacheObject(vo);
	}

	/**
	 * check and returns object from cache if available and not more than 2 hours
	 * old
	 * 
	 * @param vo
	 * @return
	 */
	private Map<String, Object> checkCacheObject(WeatherCacheVO vo) {
		Map<String, Object> responseObj = null;
		if (vo != null) {
			long diff = System.currentTimeMillis() - vo.getLastLoaded();
			System.out.println("diff:" + diff + ",apiResponseCacheTime:" + apiResponseCacheTime);
			if (diff < (1000 * 60 * 60 * apiResponseCacheTime)) {
				responseObj = vo.getFullData();
				responseObj.put("loadedSince", new Date(vo.getLastLoaded()));
			}
		}
		return responseObj;
	}

}
