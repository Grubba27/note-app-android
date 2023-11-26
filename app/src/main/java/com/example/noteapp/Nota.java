package com.example.noteapp;

public class Nota {
    private String text;
    private long id;

    public Nota(String t, long id) {
        text = t;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }
}
