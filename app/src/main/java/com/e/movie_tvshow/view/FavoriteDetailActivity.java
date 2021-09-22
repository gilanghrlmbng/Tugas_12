package com.e.movie_tvshow.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.e.movie_tvshow.R;

public class FavoriteDetailActivity extends AppCompatActivity {
    TextView tanggal_rilis,overview,rate_view;
    ImageView poster_path_movie,backdrop_movie;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tanggal_rilis = findViewById(R.id.tgl_rilis);
        overview = findViewById(R.id.sinopsis);
        poster_path_movie = findViewById(R.id.poster);
        ratingBar = findViewById(R.id.rate);
        backdrop_movie = findViewById(R.id.backdrop);
        rate_view = findViewById(R.id.rate_view);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Intent tampil = getIntent();
        String judul_film = tampil.getStringExtra("judul");
        tanggal_rilis.setText(tampil.getStringExtra("rilis"));
        overview.setText(tampil.getStringExtra("sinopsis_singkat"));
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tampil.getStringExtra("poster_movie")).into(poster_path_movie);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tampil.getStringExtra("backdrop_movie")).into(backdrop_movie);
        float ratevalue = tampil.getFloatExtra("skor_rate", 0);
        ratingBar.setRating(ratevalue);
        ratingBar.setEnabled(false);
        rate_view.setText(String.valueOf(ratevalue));
        toolBarLayout.setTitle(judul_film);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());*/


    }
}