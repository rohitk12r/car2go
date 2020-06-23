package com.sharenow.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sharenow.exception.VehicleException;
import com.sharenow.rest.api.VehiclesApi;
import com.sharenow.rest.model.Car;
import com.sharenow.service.VehicleService;
import com.sharenow.utils.GeoJsonReaderUtils;
import com.sharenow.utils.ResponseUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(VehiclesApi.class)
public class VehicleApiTest {

	final String POLYGONID = "58a58bf685979b5415f3a398";
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private VehicleService vehicleService;

	@MockBean
	GeoJsonReaderUtils geoJsonReaderUtils;

	@MockBean
	ResponseUtils responseUtils;

	@Test
	public void getCarsTest() throws VehicleException, Exception {
		given(vehicleService.getCars(POLYGONID)).willReturn(getCars());
		mvc.perform(get("/api/cars/58a58bf685979b5415f3a398")).andExpect(status().isOk());
	}

	private List<Car> getCars() {
		String vin = "JS3TEA4179564R44G";
		String numberPlat = "S-TX8287";
		String model = "DELOREAN";
		List<Car> cars = new ArrayList<Car>();
		Car car = new Car(POLYGONID, vin, numberPlat, model);
		cars.add(car);
		return cars;

	}
}
