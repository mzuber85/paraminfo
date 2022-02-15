package com.intigral.assignment.weathermap.integration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Fallback functions for the APIs
 * 
 * @author Muhammad Zubair
 *
 */
@Component
public class OpenWeatherMapFallback implements OpenWeatherMap {

	@Override
	public ResponseEntity<Map<String, Object>> getByCity(String cityCountry) {
		System.out.println("fallback::");
		Map<String, Object> fallbackResponse = new LinkedHashMap<>(1);
		fallbackResponse.put("errorMessage", "Request has timeout to get details for:" + cityCountry);
		return new ResponseEntity<>(fallbackResponse, HttpStatus.REQUEST_TIMEOUT);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getByCoordinates(Double lat, Double lon) {
		System.out.println("fallback::");
		Map<String, Object> fallbackResponse = new LinkedHashMap<>(1);
		fallbackResponse.put("errorMessage", "Request has timeout to get details for lat:" + lat + ", long:" + lon);
		return new ResponseEntity<>(fallbackResponse, HttpStatus.REQUEST_TIMEOUT);
	}

}
