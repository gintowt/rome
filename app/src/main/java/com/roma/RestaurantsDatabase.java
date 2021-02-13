package com.roma;

public class RestaurantsDatabase {

    String Name;
    String Address;
    Integer Stars;
    String ImgUrl;
    String Cuisines;
    String Price;

    RestaurantsDatabase(String Address, String Cuisines, Integer Stars, String Price, String ImgUrl, String Name) {
        this.Cuisines = Cuisines;
        this.Stars = Stars;
        this.Price = Price;
        this.ImgUrl = ImgUrl;
        this.Name = Name;
        this.Address = Address;

    }

    RestaurantsDatabase(String ImgUrl, String Name) {
        this.ImgUrl = ImgUrl;
        this.Name = Name;
    }

    RestaurantsDatabase() {}


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAvg_time() {
        return Stars;
    }

    public void setAvg_time(Integer avg_time) {
        Stars = avg_time;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getDescription() {
        return Cuisines;
    }

    public void setDescription(String description) {
        Cuisines = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getStars() {
        return Stars;
    }

    public void setStars(Integer stars) {
        Stars = stars;
    }

    public String getCuisines() {
        return Cuisines;
    }

    public void setCuisines(String cuisines) {
        Cuisines = cuisines;
    }
}
