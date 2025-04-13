package com.mohyaseensidd.studynitp;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class book {
    String title;
    String author;
    int bookImg = R.drawable.img;

    String pdfUrl;

    public book() {}

    public book(String bookName, String authName,int bookImg, String pdfUrl) {
        title = bookName;
        author = authName;
        this.bookImg = bookImg;
        this.pdfUrl = pdfUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getBookImg() {
        return bookImg;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookImg(int bookImg) {
        this.bookImg = bookImg;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
