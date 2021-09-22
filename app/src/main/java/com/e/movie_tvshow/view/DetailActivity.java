package com.e.movie_tvshow.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.e.movie_tvshow.R;
import com.e.movie_tvshow.database.MovieDB;
import com.e.movie_tvshow.database.MyAppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import okhttp3.OkHttpClient;

public class DetailActivity extends AppCompatActivity {
    ImageView imageView;
    RatingBar ratingBar;
    TextView sinopsis_tv, judul_tv, tgl_rilis_tv;
    FloatingActionButton floatingActionButton;
    MovieDB movieDB;
    MyAppDatabase myAppDatabase;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.backdrop);
        sinopsis_tv = findViewById(R.id.sinopsis);
        judul_tv = findViewById(R.id.judul);
        tgl_rilis_tv = findViewById(R.id.tanggal_rilis);
        ratingBar = findViewById(R.id.rate);
        floatingActionButton = findViewById(R.id.floatingbtn);
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "moviedb_favorite").allowMainThreadQueries().build();
        Intent tampil = getIntent();
        String sinopsis = tampil.getStringExtra("sinopsis_singkat");
        String judul = tampil.getStringExtra("judul");
        String rilis = tampil.getStringExtra("rilis");
        String bd = tampil.getStringExtra("backdrop_movie");
        String pp = tampil.getStringExtra("poster_movie");

        float ratevalue = tampil.getFloatExtra("skor_rate", 0);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tampil.getStringExtra("backdrop_movie")).into(imageView);
        sinopsis_tv.setText(": " + tampil.getStringExtra("sinopsis_singkat"));
        judul_tv.setText(": " + tampil.getStringExtra("judul"));
        tgl_rilis_tv.setText(": " + tampil.getStringExtra("rilis"));
        ratingBar.setRating(ratevalue);
        ratingBar.setEnabled(false);
        movieDB = new MovieDB();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieDB.setJudul(judul);
                movieDB.setSinopsis(sinopsis);
                movieDB.setTgl_rilis(rilis);
                movieDB.setRating(ratevalue);
                movieDB.setBackdrop_path(bd);
                movieDB.setPoster_path(pp);
                myAppDatabase.myDao().addMovie_favorite(movieDB);
                //myAppDatabase.myDao().deleteMovie_favorite(movieDB);
            }
        });
    }
}