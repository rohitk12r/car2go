package com.sharenow.rest.json;

import lombok.Data;

/**
 * It is holds GeoFeatures from GeoJson
 * 
 * @author RohitSharma
 *
 */
@Data
public class GeoFeatures {
	private String name;

	private Geometry geometry;

}
