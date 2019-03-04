package com.example.telassessment.model;

import java.io.Serializable;

public class Rows implements Serializable {

    private String title;
    private String description;
    private String imageHref;
    private boolean imgLoadFailed;

    public boolean isImgLoadFailed() {
        return imgLoadFailed;
    }

    public void setImgLoadFailed(boolean imgLoadFailed) {
        this.imgLoadFailed = imgLoadFailed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
