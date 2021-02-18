package com.roma;


public class TripDetails {

    String trip_name;
    Integer date;

    public TripDetails(String trip_name, Integer date) {
        this.trip_name = trip_name;
        this.date = date;
    }

    public TripDetails(){}

    public String getName() {
        return trip_name;
    }

    public void setName(String trip_name) {
        this.trip_name = trip_name;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
}
