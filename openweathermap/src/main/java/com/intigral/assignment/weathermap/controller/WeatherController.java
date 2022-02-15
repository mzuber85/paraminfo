package com.intigral.assignment.weathermap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intigral.assignment.weathermap.service.WeatherMapServiceImpl;

/**
 * Application Rest End Points
 * @author Muhammad Zubair
 *
 */
@RestController
@RequestMapping("/weather/")
public class WeatherController {

	@Autowired
	WeatherMapServiceImpl service;

	/**
	 * Retrieves the data for given city, country (optional)
	 * @param cityName
	 * @param countryName
	 * @return
	 */
	@GetMapping(value = "city", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> weatherByCity(@RequestParam(required = true, name = "cityName") String cityName,
			@RequestParam(required = false, name = "countryName") String countryName) {
		return service.getByCity(cityName, countryName);
	}

	/**
	 * Retrieves the data for given coordinates
	 * @param lat
	 * @param lng
	 * @return
	 */
	@GetMapping(value = "coordinates", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> weatherByCoorinates(@RequestParam(required = true, name = "lat") Double lat,
			@RequestParam(required = true, name = "long") Double lng) {

		return service.getByCoordinates(lat, lng);
	}
}
