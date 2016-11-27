package com.babyloncodetest.model;

/**
 * Created by maximiliano.ferraiuolo on 23/11/2016.
 */

public class Geo {

    private final String latitude;
    private final String longitude;

    public Geo(String latitude, String longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
