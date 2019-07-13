package com.example.administrator.mygridview.bean;

public class BookDetails {
    private String title;
    private String sub2;
    private String img;

    public BookDetails() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "title='" + title + '\'' +
                ", sub2='" + sub2 + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
