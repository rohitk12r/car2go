package com.sharenow.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharenow.exception.VehicleException;
import com.sharenow.rest.model.Car;
import com.sharenow.service.VehicleService;
import com.sharenow.utils.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * This API is expose the cars which are available in the Polygon ID
 * 
 * @author RohitSharma
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/cars")
public class VehiclesApi {

	/**
	 * The VehicleService
	 */
	@Autowired
	private VehicleService vehicleService;

	/**
	 * The Response utils
	 */
	@Autowired
	private ResponseUtils responseUtils;

	/**
	 * It"s return list of cars which are available in this polygon ID
	 * 
	 * @param polygonId
	 *            used for fetch Cars in this polygon ID
	 * @return {@link List}
	 */
	@GetMapping("/{polygonId}")
	public ResponseEntity<?> getCars(@PathVariable("polygonId") String polygonId) {
		try {
			List<Car> cars = vehicleService.getCars(polygonId);
			return this.responseUtils.buildOk(cars);
		} catch (VehicleException e) {
			log.error("Getting exception for calling Car Service ", e);
			return this.responseUtils.build(e);
		}
	}
}
