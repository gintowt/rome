package com.roma;

public class Model {
    private int image; //desc;
    private String title;

    public Model(int image, String title){
        this.image = image;
        //this.desc = desc;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    /*public int getDesc() {
        return desc;
    }*/

    public String getTitle() {
        return title;
    }

    public void setImage(int image) {
        this.image = image;
    }

   /* public void setDesc(int desc) {
        this.image = desc;
    }*/

    public void setTitle(String title) {
        this.title = title;
    }
}
