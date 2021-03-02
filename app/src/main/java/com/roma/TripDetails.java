package com.roma;


public class TripDetails {

    String trip_name;
    String date;
    String attraction_name;
    String imgUrl;
    Integer avg_time;

    public TripDetails( String imgUrl , String attraction_name, Integer avg_time, String date) {
        this.imgUrl = imgUrl;
        this.attraction_name = attraction_name;
        this.avg_time = avg_time;
        this.date = date;
    }


    public TripDetails(String imgUrl, String attraction_name,  Integer avg_time) {
        this.attraction_name = attraction_name;
        this.imgUrl = imgUrl;
        this.avg_time = avg_time;
    }

    public TripDetails( String trip_name, String date, String imgUrl){
        this.trip_name = trip_name;
        this.date = date;
        this.imgUrl = imgUrl;
    }

    TripDetails(String date){
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

    public String getAttraction_name() {
        return attraction_name;
    }

    public void setAttraction_name(String attraction_name) {
        this.attraction_name = attraction_name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getAvg_time() {
        return avg_time;
    }

    public void setAvg_time(Integer avg_time) {
        this.avg_time = avg_time;
    }
}

