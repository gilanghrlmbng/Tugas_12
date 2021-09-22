package com.e.movie_tvshow.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_detail")
public class MovieDB {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "judul")
    private String judul;

    @ColumnInfo(name = "sinopsis")
    private String sinopsis;
    @ColumnInfo(name = "tanggal_rilis")
    private String tgl_rilis;
    @ColumnInfo(name = "rating")
    private float rating;
    @ColumnInfo(name = "backdrop")
    private String backdrop_path;
    @ColumnInfo(name = "poster")
    private String poster_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTgl_rilis() {
        return tgl_rilis;
    }

    public void setTgl_rilis(String tgl_rilis) {
        this.tgl_rilis = tgl_rilis;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
