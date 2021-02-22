package com.roma;


public class TripDetails {

    String trip_name;
    String date;

    public TripDetails(String date, String trip_name) {
        this.date = date;
        this.trip_name = trip_name;
    }

    public TripDetails(String date){
        this.date = date;
    }


    public TripDetails(){}

    public String getName() {
        return trip_name;
    }

    public void setName(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
