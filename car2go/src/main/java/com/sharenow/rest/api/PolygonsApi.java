package com.sharenow.rest.api;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharenow.exception.PolygonException;
import com.sharenow.rest.model.Polygon;
import com.sharenow.service.PolygonService;
import com.sharenow.utils.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * This API is expose the Polygon ID where Car is available.
 * 
 * @author RohitSharma
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/polygons")
public class PolygonsApi {

    /**
     * The PolygonService
     */
    @Autowired
    private PolygonService polygonService;

    /**
     * The ResponseUtils
     */
    @Autowired
    private ResponseUtils responseUtils;

    /**
     * This is return the Polygon ID where VIN is available.
     * 
     * @param vin
     *            this is used for fetching Polygon ID
     * @return {@link Polygon}
     */
    @GetMapping("/{vin}")
    public ResponseEntity<?> getPolygon(@PathVariable("vin") String vin) {
        try {
            Polygon polygon = polygonService.getPolygen(vin);
            if (!Objects.isNull(polygon)) {
                return this.responseUtils.buildOk(polygon);
            } else
                return this.responseUtils.buildOk(vin + " This VIN is not found any Polygon");
        } catch (PolygonException e) {
            log.error("Getting exception for calling Car Service ", e);
            return this.responseUtils.build(e);
        }
    }
}
