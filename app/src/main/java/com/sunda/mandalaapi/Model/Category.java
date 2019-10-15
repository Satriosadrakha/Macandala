package com.sunda.mandalaapi.Model;

public class Category {
    public static final int BASIC11 = 1;
    public static final int BASIC12 = 2;
    public static final int BASIC21 = 3;
    public static final int BASIC22 = 4;
    public static final int HEWAN1 = 5;
    public static final int HEWAN2 = 6;
    public static final int WARNA1 = 7;
    public static final int WARNA2 = 8;
    public static final int ANGKA1 = 9;
    public static final int ANGKA2 = 10;

    private int id;
    private String name;

    // constructors
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String name) {
        this.name = name;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getNama() {
        return this.name;
    }

    @Override
    public String toString(){
        return getNama();
    }
}
