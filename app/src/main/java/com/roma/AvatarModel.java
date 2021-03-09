package com.roma;

public class AvatarModel {
    String name;
    String imgUrl;

    AvatarModel(){}

    AvatarModel(String imgUrl, String name) {
        this.imgUrl = imgUrl;
        this.name = name;
    }

    AvatarModel(String imgUrl) { this.imgUrl = imgUrl; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
