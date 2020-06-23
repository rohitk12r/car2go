package com.sharenow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharenow.exception.VehicleException;
import com.sharenow.is.VehiclesIntegratedService;
import com.sharenow.rest.json.GeoJson;
import com.sharenow.rest.json.GeometryPolygon;
import com.sharenow.rest.json.Vehicles;
import com.sharenow.rest.model.Car;
import com.sharenow.utils.GeoJsonReaderUtils;
import com.sharenow.utils.PolygonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * This class using for business logics of cars.
 * 
 * @author RohitSharma
 *
 */
@Slf4j
@Service
public class VehicleService {
	
	
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
	 * It"s return list of cars which are available in this polygon ID
	 * 
	 * @param polygonId
	 *            used for fetch Cars in this polygon ID
	 * @return {@link List}
	 * @throws VehicleException
	 */
	public List<Car> getCars(String polygonId) throws VehicleException {
		return applyPolygonsIdInGeoJsonForGetCars(polygonId);

	}

	private List<Car> applyPolygonsIdInGeoJsonForGetCars(String polygonId) throws VehicleException {
		try {
			List<GeoJson> geoJsons = geoJsonReaderUtils.getGeoJSON();
			List<Vehicles> vehicles = vehiclesIntegratedService.getVehicleJSON();
			Optional<GeoJson> geoJson = geoJsons.stream().parallel()
					.filter(geojson -> geojson.get_id().equals(polygonId)).findAny();
			if (geoJson.isPresent()) {
				GeoJson json = geoJson.get();
				return getCarsForCheckLatitudeAndLongitudeCondition(vehicles, json);
			}
		} catch (Exception e) {
			log.error("Getting execption in manipulating Vehicle Json ", e);
			throw new VehicleException(e.getLocalizedMessage(), e.getMessage(), e);
		}
		throw new VehicleException("Polygon ID : "+polygonId+" is not available in the GEOJSON");
	}

	private List<Car> getCarsForCheckLatitudeAndLongitudeCondition(List<Vehicles> vehicles, GeoJson json) {
		List<Car> cars = new ArrayList<Car>();
		GeometryPolygon geometryPolygon = json.getGeometry();
		List<List<List<Double>>> coordinates = geometryPolygon.getCoordinates();
		List<List<Double>> exteriorRingPolygons = coordinates.get(0);
		vehicles.stream().forEach(vehicle -> {
			Optional<Car> car = exteriorRingPolygons.stream()
					.filter(polygons -> PolygonUtils.isLatitudeAndLongitudeAreTrue(polygons, vehicle))
					.map(polygon -> new Car(json.get_id(), vehicle.getVin(), vehicle.getNumberPlate(),
							vehicle.getModel()))
					.findAny();
			if (car.isPresent()) {
				cars.add(car.get());
			}
		});
		return cars;
	}
}
