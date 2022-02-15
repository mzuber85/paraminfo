package com.intigral.assignment.weathermap.service;

import org.springframework.http.ResponseEntity;

/**
 * 
 * @author Muhammad Zubair
 *
 */
public interface IWeatherMapService {

	ResponseEntity<Object> getByCity(String cityName, String countryName);

	ResponseEntity<Object> getByCoordinates(Double lat, Double lng);

}
