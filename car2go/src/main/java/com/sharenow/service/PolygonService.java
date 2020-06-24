package com.sharenow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sharenow.exception.PolygonException;
import com.sharenow.is.VehiclesIntegratedService;
import com.sharenow.rest.json.GeoJson;
import com.sharenow.rest.json.GeometryPolygon;
import com.sharenow.rest.json.Vehicles;
import com.sharenow.rest.model.Polygon;
import com.sharenow.utils.GeoJsonReaderUtils;
import com.sharenow.utils.PolygonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * This class using for business logics of Polygons.
 * 
 * @author RohitSharma
 *
 */
@Slf4j
@Service
public class PolygonService {

    /**
     * The VehiclesIntegratedService
     */
    @Autowired
    private VehiclesIntegratedService vehiclesIntegratedService;

    /**
     * The GeoJsonReaderUtils
     */
    @Autowired
    private GeoJsonReaderUtils geoJsonReaderUtils;

    /**
     * It"s return the Polygon where car is available polygons.
     * 
     * @param vin
     *            used for fetch Polygon ID form polygons
     * @return {@link Polygon}
     * @throws PolygonException
     */
    public Polygon getPolygen(String vin) throws PolygonException {
        return applyVinToSearchPolygon(vin);
    }

    private Polygon applyVinToSearchPolygon(String vin) throws PolygonException {
        try {
            List<GeoJson> geoJsons = geoJsonReaderUtils.getGeoJSON();
            List<Vehicles> vehiclesList = vehiclesIntegratedService.getVehicleJSON();
            if (!CollectionUtils.isEmpty(geoJsons) && !CollectionUtils.isEmpty(vehiclesList)) {
                Optional<Vehicles> vehicles = vehiclesList.stream().filter(vehicle -> vin.equals(vehicle.getVin()))
                        .findFirst();
                if (vehicles.isPresent()) {
                    log.info("VIN is availabe in Vehicle Service API : " + vehicles.get().getVin());
                    return getPolygonForCheckLatitudeAndLongitudeCondition(geoJsons, vehicles.get());
                }
            } else
                throw new PolygonException("Please Check GEOJSON and Vehicles Service are Running");
        } catch (Exception e) {
            log.error("Getting execption in manipulating Polygon Json ", e);
            throw new PolygonException(e.getLocalizedMessage(), e.getMessage(), e);
        }
        throw new PolygonException(vin + " : VIN is not availabe in Stuttgart location API ");
    }

    private Polygon getPolygonForCheckLatitudeAndLongitudeCondition(List<GeoJson> geoJsons, Vehicles vehicle)
            throws PolygonException {
        for (GeoJson geoJson : geoJsons) {
            GeometryPolygon geometryPolygon = geoJson.getGeometry();
            List<List<List<Double>>> coordinates = geometryPolygon.getCoordinates();
            List<List<Double>> exteriorRingPolygons = coordinates.get(0);

            Optional<Polygon> polygonValues = exteriorRingPolygons.stream()
                    .filter(polygon -> PolygonUtils.isLatitudeAndLongitudeAreTrue(polygon, vehicle))
                    .map(x -> new Polygon(geoJson.get_id(), vehicle.getVin())).findFirst();
            if (polygonValues.isPresent()) {
                return polygonValues.get();
            }
        }
        throw new PolygonException(vehicle.getVin() + " : VIN is not availabe in Stuttgart Polygon");
    }
}
