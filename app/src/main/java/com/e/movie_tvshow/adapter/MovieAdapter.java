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

import com.bumptech.glide.Glide;
import com.e.movie_tvshow.R;
import com.e.movie_tvshow.model.MovieModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    private Context context;
    private List<MovieModel> modelList;
    private itemCLickListener itemCLickListener;

    public MovieAdapter(Context context, List<MovieModel> modelList, MovieAdapter.itemCLickListener itemCLickListener) {
        this.context = context;
        this.modelList = modelList;
        this.itemCLickListener = itemCLickListener;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.judul.setText(modelList.get(position).getTitle());
        holder.rilisdate.setText(modelList.get(position).getRelease_date());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+modelList.get(position).getPoster_path()).into(holder.poster);

        holder.itemView.setOnClickListener(v -> itemCLickListener.onItemCLick(modelList.get(position)));
        holder.skor.setRating(Float.parseFloat(String.valueOf(modelList.get(position).getVote_avarage()))/2);
        holder.skor.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return (modelList != null) ? modelList.size() : 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView judul;
        ImageView poster;
        RatingBar skor;
        TextView rilisdate;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.title);
            poster = itemView.findViewById(R.id.image);
            rilisdate = itemView.findViewById(R.id.rilis);
            skor = itemView.findViewById(R.id.rate);
        }
    }
    public interface itemCLickListener {

        void onItemCLick(MovieModel dataModel);
    }
}
