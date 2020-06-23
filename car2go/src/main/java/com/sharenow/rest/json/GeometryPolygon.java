package com.sharenow.rest.json;

import java.util.List;

import lombok.Data;

/**
 * It is holds GeometryPolygon form Geo Json
 * 
 * @author RohitSharma
 *
 */
@Data
public class GeometryPolygon {
	private String type;

	private List<List<List<Double>>> coordinates;

}
