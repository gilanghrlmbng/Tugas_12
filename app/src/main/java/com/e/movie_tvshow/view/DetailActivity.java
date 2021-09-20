package com.e.movie_tvshow.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.movie_tvshow.R;

public class DetailActivity extends AppCompatActivity {
    ImageView imageView;
    RatingBar ratingBar;
    TextView sinopsis_tv,judul_tv,tgl_rilis_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.backdrop);
        sinopsis_tv = findViewById(R.id.sinopsis);
        judul_tv = findViewById(R.id.judul);
        tgl_rilis_tv = findViewById(R.id.tanggal_rilis);
        ratingBar = findViewById(R.id.rate);

        Intent tampil = getIntent();
        float ratevalue = tampil.getFloatExtra("skor_rate",0);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+tampil.getStringExtra("backdrop_movie")).into(imageView);
        sinopsis_tv.setText(": "+tampil.getStringExtra("sinopsis_singkat"));
        judul_tv.setText(": "+tampil.getStringExtra("judul"));
        tgl_rilis_tv.setText(": "+tampil.getStringExtra("rilis"));
        ratingBar.setRating(ratevalue);
        ratingBar.setEnabled(false);
        //tagline_tv.setText(tampil.getStringExtra("tagline_movie"));

    }
}