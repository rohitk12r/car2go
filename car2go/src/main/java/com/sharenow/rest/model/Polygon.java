package com.sharenow.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * It's holds for Polygon details
 * 
 * @author RohitSharma
 *
 */
@Data
@AllArgsConstructor
public class Polygon {
    /**
     * This is Polygon ID for GeoJson
     */
    private String polygonId;
    /**
     * This is for VIN number for car.
     */
    private String vin;
}
