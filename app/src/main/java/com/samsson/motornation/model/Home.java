package com.samsson.motornation.model;

public class Home {
    int id;
    String Image,Autonum,Phonenum;

    public Home() {
    }

    public Home(int id, String image, String autonum, String phonenum) {
        this.id = id;
        Image = image;
        Autonum = autonum;
        Phonenum = phonenum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getAutonum() {
        return Autonum;
    }

    public void setAutonum(String autonum) {
        Autonum = autonum;
    }

    public String getPhonenum() {
        return Phonenum;
    }

    public void setPhonenum(String phonenum) {
        Phonenum = phonenum;
    }
}
