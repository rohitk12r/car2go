package com.sharenow.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharenow.exception.VehicleException;
import com.sharenow.is.VehiclesIntegratedService;
import com.sharenow.rest.json.GeoJson;
import com.sharenow.rest.json.Position;
import com.sharenow.rest.json.Vehicles;
import com.sharenow.rest.model.Car;
import com.sharenow.utils.GeoJsonReaderUtils;

@RunWith(SpringRunner.class)
public class VehicleServiceUnitTest {

	@TestConfiguration
	static class VehicleServiceTestContextConfiguration {

		@Bean
		public VehicleService vehicleService() {
			return new VehicleService();
		}
	}

	@Autowired
	VehicleService vehicleService;

	@MockBean
	VehiclesIntegratedService vehiclesIntegratedService;

	@MockBean
	GeoJsonReaderUtils geoJsonReaderUtils;

	@Before
	public void setUp() {
		Mockito.when(vehiclesIntegratedService.getVehicleJSON()).thenReturn(getVehicle());
		Mockito.when(geoJsonReaderUtils.getGeoJSON()).thenReturn(getGeoJson());
	}

	@Test
	public void succesfullyGetPolygenTest() throws VehicleException {
		String vin = "2G1WM16N26J58041U";
		String polygonId = "58a58bf685979b5415f3a398";
		vehiclesIntegratedService.accessVehicleService();
		geoJsonReaderUtils.readGeoJson();
		List<Car> cars = vehicleService.getCars(polygonId);
		cars.stream().forEach(car -> {
			assertThat(car.getVin()).isEqualTo(vin);
			assertThat(car.getPolygonId()).isEqualTo(polygonId);
		});
	}
	private List<Vehicles> getVehicle() {
		List<Vehicles> vehicles = new ArrayList<Vehicles>();
		Vehicles vehicle = new Vehicles();
		vehicle.setVin("2G1WM16N26J58041U");
		Position position = new Position();
		position.setLatitude(48.821281814140164);
		position.setLongitude(9.237898326155673);
		vehicle.setPosition(position);
		vehicle.setLocationId(3);
		vehicle.setId(1);
		vehicle.setFuel(0.6);
		vehicle.setNumberPlate("S-IJ8862");
		vehicle.setModel("SMART_42_ELECTRIC");
		vehicles.add(vehicle);
		return vehicles;
	}

	private List<GeoJson> getGeoJson() {
		String json = "[{\"_id\":\"58a58bf685979b5415f3a398\",\"updatedAt\":\"2017-03-27T14:04:34.470Z\",\"createdAt\":\"2017-02-16T11:24:38.375Z\",\"__v\":0,\"name\":\"0\",\"cityId\":\"548876e13a9424d55af738b5\",\"legacyId\":\"18_92\",\"type\":\"relocationzone\",\"geoFeatures\":[{\"name\":\"opticalCenter\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[9.1371735,48.790337]}},{\"name\":\"center\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[9.137148666666667,48.79031233333333]}}],\"options\":{\"active\":true,\"is_excluded\":false,\"area\":0.4},\"timedOptions\":[{\"key\":\"min\",\"changesOverTime\":[[0,0]]},{\"key\":\"max\",\"changesOverTime\":[[0,200]]},{\"key\":\"idle_time\",\"changesOverTime\":[[0,2000]]},{\"key\":\"revenue\",\"changesOverTime\":[[0,0]]},{\"key\":\"walking_range1\",\"changesOverTime\":[[0,0]]},{\"key\":\"walking_range2\",\"changesOverTime\":[[0,0]]}],\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[9.237848,48.8212811],[9.137248,48.790263],[9.13695,48.790263],[9.137248,48.790411]]]},\"version\":1,\"$computed\":{\"activeTimedOptions\":{\"min\":0,\"max\":200,\"idle_time\":2000,\"revenue\":0,\"walking_range1\":0,\"walking_range2\":0}}}]";
		List<GeoJson> geoJsons = new ArrayList<>();
		Gson gson = new Gson();
		Type listTypeGeoJson = new TypeToken<List<GeoJson>>() {
		}.getType();
		geoJsons = gson.fromJson(json, listTypeGeoJson);
		return geoJsons;
	}
}
