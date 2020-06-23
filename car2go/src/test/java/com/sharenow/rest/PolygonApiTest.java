package com.sharenow.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sharenow.exception.PolygonException;
import com.sharenow.is.VehiclesIntegratedService;
import com.sharenow.rest.api.PolygonsApi;
import com.sharenow.rest.model.Polygon;
import com.sharenow.service.PolygonService;
import com.sharenow.utils.GeoJsonReaderUtils;
import com.sharenow.utils.ResponseUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(PolygonsApi.class)
public class PolygonApiTest {

	final String VIN = "JS3TEA4179564R44G";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PolygonService polygonService;

	@MockBean
	VehiclesIntegratedService vehiclesIntegratedService;
	
	@MockBean
	GeoJsonReaderUtils geoJsonReaderUtils;

	@MockBean
	ResponseUtils responseUtils;

	@Test
	public void getPolygonTest() throws Exception, PolygonException {
		given(polygonService.getPolygen(VIN)).willReturn(getPolygon());
		mvc.perform(get("/api/polygons/JS3TEA4179564R44G")).andExpect(status().isOk());
	}

	private Polygon getPolygon() {
		String polygonId = "58a58bf685979b5415f3a398";
		Polygon polygon = new Polygon(polygonId, VIN);
		return polygon;
	}
}
