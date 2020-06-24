package com.sharenow.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharenow.rest.json.GeoJson;

import lombok.extern.slf4j.Slf4j;

/**
 * It is utility class for reading GeoJSON.
 * 
 * @author RohitSharma
 *
 */
@Slf4j
@Component
public class GeoJsonReaderUtils {

    /**
     * It is dump GeoJson in array list.
     */
    private List<GeoJson> geoJsonCollection = new ArrayList<GeoJson>();

    @Value("${geoJsonUrl}")
    private String geoJsonUrl;

    /**
     * It is reading GeoJson form URL.
     */
    public void readGeoJson() {
        try (InputStream is = new URL(geoJsonUrl).openStream()) {
            Gson gson = new Gson();
            String str = IOUtils.toString(is, StandardCharsets.UTF_8);
            Type listTypeGeoJson = new TypeToken<List<GeoJson>>() {
            }.getType();
            geoJsonCollection = gson.fromJson(str, listTypeGeoJson);
            log.info("Successfully dump GEO JSON in List ");
        } catch (IOException e) {
            log.error("Getting exception for reading GEO JSON form URL : " + geoJsonUrl, e);
        }
    }

    /**
     * It is return GeoJson object
     * 
     * @return {@link List}
     */
    public List<GeoJson> getGeoJSON() {
        return geoJsonCollection;
    }
}
