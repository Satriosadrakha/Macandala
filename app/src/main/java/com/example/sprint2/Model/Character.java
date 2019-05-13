package com.example.sprint2.Model;

import java.sql.Blob;

public class Character {
    int id;
    String bahasa;
    String sunda;
    String aksara;
    String vocal;

    // constructors
    public Character() {
    }

    public Character(String sunda) {
        this.sunda = sunda;
    }

    public Character(int id, String sunda) {
        this.id = id;
        this.sunda = sunda;
    }

    public Character(String sunda, String aksara, String vocal) {
        this.sunda = sunda;
        this.aksara = aksara;
        this.vocal = vocal;
    }

    public Character(int id, String sunda, String aksara, String vocal) {
        this.id = id;
        this.sunda = sunda;
        this.aksara = aksara;
        this.vocal = vocal;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSunda(String sunda) {
        this.sunda = sunda;
    }

    public void setAksara(String aksara) {
        this.aksara = aksara;
    }

    public void setVocal(String vocal) {
        this.vocal = vocal;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getSunda() {
        return this.sunda;
    }

    public String getAksara() {
        return this.aksara;
    }

    public String getVocal() {
        return this.vocal;
    }

}
