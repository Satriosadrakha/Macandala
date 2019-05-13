package com.example.sprint2.Model;

import java.sql.Blob;

public class Word {
    int id;
    String bahasa;
    String sunda;
    Blob vocal;

    // constructors
    public Word() {
    }

    public Word(String bahasa, String sunda) {
        this.bahasa = bahasa;
        this.sunda = sunda;
    }

    public Word(int id, String bahasa, String sunda) {
        this.id = id;
        this.bahasa = bahasa;
        this.sunda = sunda;
    }

    public Word(String bahasa, String sunda, Blob vocal) {
        this.bahasa = bahasa;
        this.sunda = sunda;
        this.vocal = vocal;
    }

    public Word(int id, String bahasa, String sunda, Blob vocal) {
        this.id = id;
        this.bahasa = bahasa;
        this.sunda = sunda;
        this.vocal = vocal;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    public void setSunda(String sunda) {
        this.sunda = sunda;
    }

    public void setVocal(Blob vocal) {
        this.vocal = vocal;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getBahasa() {
        return this.bahasa;
    }

    public String getSunda() {
        return this.sunda;
    }

    public Blob getVocal() {
        return this.vocal;
    }

}
