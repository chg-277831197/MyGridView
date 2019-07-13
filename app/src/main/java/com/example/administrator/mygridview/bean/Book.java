package com.example.administrator.mygridview.bean;

public class Book {
    private String title;
    private int code;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Data{" +
                "title='" + title + '\'' +
                ", code=" + code +
                '}';
    }
}
