package com.roma;

public class RecommendedModel {

    String attraction_name;
    Integer avg_time;
    String imgUrl;

    public RecommendedModel(String image, String atr_name, Integer time) {
        attraction_name = atr_name;
        avg_time = time;
        imgUrl = image;
    }

    public RecommendedModel(){}

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
