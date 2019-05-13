package com.example.sprint2.Model;

public class Category {
    int id;
    String name;

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

}
