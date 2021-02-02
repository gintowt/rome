package com.roma;

import android.content.Intent;

public class AttractionLocation {

    Float location_distance;
    String attraction_name;
    Integer avg_time;

    AttractionLocation (String attraction_name, Float location_distance) {
        this.attraction_name = attraction_name;
        this.location_distance = location_distance;
    }
    AttractionLocation (String attraction_name, Integer avg_time) {
        this.attraction_name = attraction_name;
        this.avg_time = avg_time;
    }

    AttractionLocation() {}

    public Float getLocation_distance() {
        return location_distance;
    }

    public void setLocation_distance(Float location_distance) {
        this.location_distance = location_distance;
    }

    public String getAttraction_name() {
        return attraction_name;
    }

    public void setAttraction_name(String attraction_name) {
        this.attraction_name = attraction_name;
    }

    public Integer getAvg_time() {
        return avg_time;
    }

    public void setAvg_time(Integer avg_time) {
        this.avg_time = avg_time;
    }
}
