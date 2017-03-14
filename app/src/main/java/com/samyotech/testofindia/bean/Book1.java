package com.samyotech.testofindia.bean;

import java.io.Serializable;

/**
 * Created by varun on 7/23/2016.
 */
public class Book1 implements Serializable {

    String que, ans,img;
    int categoryid;

    public Book1() {
    }

    public Book1(String que, String ans, String img, int categoryid) {
        this.que = que;
        this.ans = ans;
        this.img = img;
        this.categoryid = categoryid;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getAns() {
        return ans;
    }

    public String setAns(String ans) {
        this.ans = ans;
        return ans;
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