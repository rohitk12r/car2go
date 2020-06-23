package com.sharenow.rest.json;

import java.util.List;

import lombok.Data;
/**
 * It is holds Geometry form Geo Json
 * 
 * @author RohitSharma
 *
 */
@Data
public class Geometry {
	private String type;

	private List<Double> coordinates;

}
