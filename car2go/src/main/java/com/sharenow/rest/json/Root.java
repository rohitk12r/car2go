package com.sharenow.rest.json;

import java.util.List;

import lombok.Data;

/**
 * It is holds all Geo Json
 * 
 * @author RohitSharma
 *
 */
@Data
public class Root {
    private List<GeoJson> geoJson;
}
