package com.sharenow.rest.json;

import java.util.List;

import lombok.Data;

/**
 * It is holds GeoJson for Git url
 * 
 * @author RohitSharma
 *
 */
@Data
public class GeoJson {
    private String _id;

    private String updatedAt;

    private String createdAt;

    private int __v;

    private String name;

    private String cityId;

    private String legacyId;

    private String type;

    private List<GeoFeatures> geoFeatures;

    private Options options;

    private List<TimedOptions> timedOptions;

    private GeometryPolygon geometry;

    private int version;
}
