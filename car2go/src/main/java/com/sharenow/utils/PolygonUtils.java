package com.sharenow.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sharenow.rest.json.Position;
import com.sharenow.rest.json.Vehicles;

import lombok.extern.slf4j.Slf4j;

/**
 * It is utils class for checking latitude and longitude polygon
 * 
 * @author RohitSharma
 *
 */
@Slf4j
@Component
public class PolygonUtils {

    /**
     * It is return the true if Vehicle and Polygon latitude and longitude are
     * match if not then return false.
     * 
     * @param polygons
     *            It stores polygon if coordinates
     * @param vehicle
     *            It stores vehicles details
     * @return {@link Boolean}
     */
    public static Boolean isLatitudeAndLongitudeAreTrue(List<Double> polygons, Vehicles vehicle) {
        Boolean flag = Boolean.FALSE;
        try {
            Position position = vehicle.getPosition();
            BigDecimal latitudePolygon = new BigDecimal(polygons.get(1)).setScale(2, RoundingMode.FLOOR); // 48.79
            BigDecimal latitudeCar = new BigDecimal(position.getLatitude()).setScale(2, RoundingMode.FLOOR); // 48.80
            BigDecimal longitudePolygon = new BigDecimal(polygons.get(0)).setScale(2, RoundingMode.FLOOR); // 9.137248
            BigDecimal longitudeCar = new BigDecimal(position.getLongitude()).setScale(2, RoundingMode.FLOOR); // 9.13
            flag = latitudePolygon.equals(latitudeCar) && longitudePolygon.equals(longitudeCar);
        } catch (Exception e) {
            log.error("Getting exception for checking latitude and longitude condition ", e);
        }
        return flag;
    }
}
