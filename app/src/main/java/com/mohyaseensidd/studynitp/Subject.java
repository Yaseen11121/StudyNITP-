package com.mohyaseensidd.studynitp;

public class Subject {
    private String name;
    private int subImg;

    public Subject(String name, int subImg) {
        this.name = name;
        this.subImg = subImg;
    }

    public int getSubImg() {
        return subImg;
    }

    public void setSubImg(int subImg) {
        this.subImg = subImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
