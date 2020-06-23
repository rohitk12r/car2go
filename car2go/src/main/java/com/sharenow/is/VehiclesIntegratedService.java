package com.sharenow.is;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharenow.rest.json.Vehicles;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is used for integrating with Vehicle service API.
 * 
 * @author RohitSharma
 *
 */
@Slf4j
@Component
public class VehiclesIntegratedService {

	/**
	 * It is used for store Vehicles JSON data from Vehicle API
	 * 
	 */
	private List<Vehicles> vehicleJsonCollection = new ArrayList<Vehicles>();

	@Value("${vehicleServiceApi}")
	private String vehicleServiceApi;

	/**
	 * This method is used for access vehicle service API and store JSON data in
	 * {@link ArrayList} and This is a scheduler for every 10 second to call
	 * Vehicle service API for update List to live data of vehicles.
	 * 
	 */
	@Scheduled(fixedDelayString= "${timeInterval}")
	public void accessVehicleService() {
		URL serviceUrl;
		try {
			serviceUrl = new URL(vehicleServiceApi);
			HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
			if (connection.getResponseCode() == 200) {
				log.info("Vehicle Service API is successfully connected with URL : " + vehicleServiceApi);
				convertInVehiclesJson(connection.getInputStream());
			}
		} catch (Exception e) {
			log.error("Vehicle Service API is not able to establish the connection with url : " + vehicleServiceApi, e);
		}
	}

	private List<Vehicles> convertInVehiclesJson(InputStream inputStream) {
		try {
			Gson gson = new Gson();
			String str = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			Type listTypeGeoJson = new TypeToken<List<Vehicles>>() {
			}.getType();
			vehicleJsonCollection = gson.fromJson(str, listTypeGeoJson);
			log.info("Successfully Convert Json to Vehicle Object");
		} catch (IOException e) {
			log.error("Getting error for converting JSON to Vehicles Object", e);
		}
		return vehicleJsonCollection;
	}

	/**
	 * It's return live data of vehicles service
	 * 
	 * @return {@link List}
	 */
	public List<Vehicles> getVehicleJSON() {
		return vehicleJsonCollection;
	}
}
