package com.samyotech.testofindia.bean;

import java.io.Serializable;

/**
 * Created by varun on 7/23/2016.
 */
public class Book implements Serializable {
    String name, img;
    int categoryid;


    public Book() {
    }

    public Book(String name, String img, String dis, int categoryid) {
        this.name = name;
        this.img = img;
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }
}
