package com.intigral.assignment.weathermap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Application start point
 * 
 * @author Muhammad Zubair
 *
 */
@SpringBootApplication
@EnableFeignClients
public class WeatherMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherMapApplication.class, args);
	}

}
