package com.intigral.assignment.weathermap.vo;

import java.util.Map;

/**
 * Cache Value Object
 * 
 * @author Muhammad Zubair
 *
 */
public class WeatherCacheVO {

	private String cityName;
	private long lastLoaded = -1;
	private Double lat = 0.0;
	private Double lng = 0.0;
	private Map<String, Object> fullData;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public long getLastLoaded() {
		return lastLoaded;
	}

	public void setLastLoaded(long lastLoaded) {
		this.lastLoaded = lastLoaded;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Map<String, Object> getFullData() {
		return fullData;
	}

	public void setFullData(Map<String, Object> fullData) {
		this.fullData = fullData;
	}

}
