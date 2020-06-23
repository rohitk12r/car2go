package com.sharenow.rest.json;

import lombok.Data;

/**
 * It is holds cars details from API
 * 
 * @author RohitSharma
 *
 */
@Data
public class Vehicles {
	private int id;

	private int locationId;

	private String vin;

	private String numberPlate;

	private Position position;

	private double fuel;

	private String model;

}
