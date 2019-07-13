package com.example.administrator.mygridview.bean;



public class BookType {

    private int id;
    private String catalog;

    public BookType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "id=" + id +
                ", catalog='" + catalog + '\'' +
                '}';
    }
}
