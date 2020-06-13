package com.example.myfav;

public class Book {

    private int id;
    private String title;
    private String author;
    private float rate;
    private byte[] poster;

    public Book(int id, String title, String author, float rate, byte[] poster) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rate = rate;
        this.poster = poster;
    }

    public Book(String title, String author, float rate, byte[] poster) {
        this.title = title;
        this.author = author;
        this.rate = rate;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String director) {
        this.author = author;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }
}
