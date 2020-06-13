package com.example.myfav;

public class Movie {

    private int id;
    private String title;
    private String director;
    private float rate;
    private byte[] poster;

    public Movie(int id, String title, String director, float rate, byte[] poster) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.rate = rate;
        this.poster = poster;
    }

    public Movie(String title, String director, float rate, byte[] poster) {
        this.title = title;
        this.director = director;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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
