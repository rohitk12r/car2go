package com.sharenow.rest.json;

import lombok.Data;

/**
 * It is holds Options form Geo Json
 * 
 * @author RohitSharma
 *
 */
@Data
public class Options {
	private boolean active;

	private boolean is_excluded;

	private double area;

}