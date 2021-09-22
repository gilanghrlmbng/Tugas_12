package com.e.movie_tvshow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.e.movie_tvshow.R;
import com.e.movie_tvshow.database.MovieDB;
import com.e.movie_tvshow.database.MyAppDatabase;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    Context context;
    List<MovieDB> movieDBList;
    MyAppDatabase myAppDatabase;
    itemClickListener itemClickListener;

    public FavoriteAdapter(Context context, List<MovieDB> movieDBList, FavoriteAdapter.itemClickListener itemClickListener) {
        this.context = context;
        this.movieDBList = movieDBList;
        myAppDatabase = Room.databaseBuilder(context.getApplicationContext(), MyAppDatabase.class, "moviedb_favorite").allowMainThreadQueries().build();
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_favorite,parent,false);
        return new FavoriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        movieDBList =  myAppDatabase.myDao().getMovie_favorite();
        holder.judul.setText(movieDBList.get(position).getJudul());
        holder.rilisdate.setText(movieDBList.get(position).getTgl_rilis());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+movieDBList.get(position).getPoster_path()).into(holder.poster);

        holder.itemView.setOnClickListener(v -> itemClickListener.onItemClicklistener(movieDBList.get(position)));
        holder.skor.setRating(Float.parseFloat(String.valueOf(movieDBList.get(position).getRating())));
        holder.skor.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return movieDBList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView judul;
        ImageView poster;
        RatingBar skor;
        TextView rilisdate;
        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.title_favorite);
            poster = itemView.findViewById(R.id.image_favorite);
            rilisdate = itemView.findViewById(R.id.rilis_favorite);
            skor = itemView.findViewById(R.id.rate_favorite);
        }
    }
    public interface itemClickListener{
        void onItemClicklistener(MovieDB movieDB);
    }
}
