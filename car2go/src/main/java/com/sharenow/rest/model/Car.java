package com.sharenow.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * It's holds Car data
 * 
 * @author RohitSharma
 */
@Data
@AllArgsConstructor
public class Car {

	/**
	 * This is Polygon ID for GeoJson
	 */
	private String polygonId;
	/**
	 * This is for VIN number for car.
	 */
	private String vin;
	/**
	 * This is for number plate of car.
	 */
	private String numberPlat;
	/**
	 * This is for model of Car.
	 */
	private String model;
}
