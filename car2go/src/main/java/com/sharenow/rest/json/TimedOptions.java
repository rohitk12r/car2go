package com.sharenow.rest.json;

import java.util.List;

import lombok.Data;

/**
 * It is holds TimedOptions form Geo Json
 * 
 * @author RohitSharma
 *
 */
@Data
public class TimedOptions {
	private String key;

	private List<List<Integer>> changesOverTime;

}
