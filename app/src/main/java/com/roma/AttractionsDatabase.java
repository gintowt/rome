package com.roma;

public class AttractionsDatabase {
    String Name;
    Integer Avg_time;
    String ImgUrl;
    String Description;
    String Price;

    AttractionsDatabase(String Description, Integer Avg_time, String Price, String ImgUrl, String Name) {
        this.Description = Description;
        this.Avg_time = Avg_time;
        this.Price = Price;
        this.ImgUrl = ImgUrl;
        this.Name = Name;

    }

    AttractionsDatabase(String ImgUrl, String Name) {
        this.ImgUrl = ImgUrl;
        this.Name = Name;
    }

    AttractionsDatabase() {}


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAvg_time() {
        return Avg_time;
    }

    public void setAvg_time(Integer avg_time) {
        Avg_time = avg_time;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
