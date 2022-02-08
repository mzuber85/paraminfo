# paraminfo
Pre Interview Assignment 

====================
Download .zip files:
====================


File 1: PROBLEM1_openweatherMap.zip
------------------------------------

1) extract and import maven project in eclipse
2) Run com.intigral.assignment.weathermap.WeatherController.java file as java application
3) use postman collection provided in the package to test the APIs

API Details:
1) 
	URI: {{protocol}}://{{host}}:{{port}}/weather/city (e.g. http://localhost:8080/weather/city)
	method: GET
	Params: 
		1) cityName (e.g. Dubai) (Required)
		2) countryName (e.g AE) (Optional)
	
2)

	URI: {{protocol}}://{{host}}:{{port}}/weather/coordinates (e.g. http://localhost:8080/weather/coordinates)
	method: GET
	Params: 
		1) lat (e.g 25.2582) (Required), 
		2) long (e.g 55.3047) (Required)	

The Information is loaded from openWeatherMap APIs and cached locally for 2 hours (can be modified with 'wmap.api.response.cache.time.hours' property in application.properties)
After request each subisquent requests are served from cache
Fallback is used to return a timeout response back to user if openWeatherMap APIs are not responding back in 3 seconds

File 2: PROBLEM2_merge-sorted.zip
---------------------------------
Merge sorted arrays

1) extract and import maven project in eclipse
2) Run com.intigral.assignment.Application.java file