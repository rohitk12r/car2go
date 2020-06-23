package com.sharenow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sharenow.utils.GeoJsonReaderUtils;

@SpringBootApplication
@EnableScheduling
public class Car2goApplication implements CommandLineRunner {

	@Autowired
	private GeoJsonReaderUtils geoJsonReaderUtils;

	public static void main(String[] args) {
		SpringApplication.run(Car2goApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		geoJsonReaderUtils.readGeoJson();
	}
}
