package com.intigral.assignment.weathermap.integration;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * openweathermap API Client
 * 
 * @author Muhammad Zubair
 *
 */
@FeignClient(value = "owmapclient", url = "${owmap.url}", fallback = OpenWeatherMapFallback.class)
public interface OpenWeatherMap {

	
	@RequestMapping(value = "/weather?appid=${owmap.appid}", method = RequestMethod.GET)
	ResponseEntity<Map<String, Object>> getByCity(@RequestParam("q") String cityCountry);

	@RequestMapping(value = "/weather?appid=${owmap.appid}", method = RequestMethod.GET)
	ResponseEntity<Map<String, Object>> getByCoordinates(@RequestParam("lat") Double lat,
			@RequestParam("lon") Double lon);

}
