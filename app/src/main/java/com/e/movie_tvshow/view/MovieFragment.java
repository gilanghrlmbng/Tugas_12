package com.e.movie_tvshow.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.movie_tvshow.R;
import com.e.movie_tvshow.adapter.MovieAdapter;
import com.e.movie_tvshow.api.ApiMovie;
import com.e.movie_tvshow.model.MovieArray;
import com.e.movie_tvshow.model.MovieModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragment extends Fragment implements MovieAdapter.itemCLickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private List<MovieModel> movieModelList;

    public MovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieFragment newInstance(String param1, String param2) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        /*       recyclerView = v.findViewById(R.id.rcv);*/

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiMovie apiMovie = retrofit.create(ApiMovie.class);

        Call<MovieArray> call = apiMovie.getMovieData();

        call.enqueue(new Callback<MovieArray>() {
            @Override
            public void onResponse(Call<MovieArray> call, Response<MovieArray> response) {
                MovieArray movieArray = response.body();
                movieModelList = new ArrayList<>(Arrays.asList(movieArray.getResults()));
/*

                if(response.code()!=200){
                    return;
                }
                List<MovieModel>movieModels = response.body();*/


                    /*String responseTest = "";

                    responseTest+= moviesModel.getId();*/
/*                    String title = moviesModel.getTitle();
                    String img = moviesModel.getPoster_path();
                    double skore = moviesModel.getVote_avarage();*/

                /*movieModelList.add(moviesModel);*/

                initRecyclerView(movieModelList, v);

            }


            @Override
            public void onFailure(Call<MovieArray> call, Throwable t) {

            }
        });
        return v;
    }

    private void initRecyclerView(List<MovieModel> movieList, View v) {
        RecyclerView recyclerView = v.findViewById(R.id.rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), movieList, this);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void onItemCLick(MovieModel dataModel) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("judul", dataModel.getTitle());
        intent.putExtra("sinopsis_singkat", dataModel.getOverview());
        intent.putExtra("backdrop_movie", dataModel.getBackdrop_path());
        intent.putExtra("poster_movie", dataModel.getPoster_path());
        intent.putExtra("rilis", dataModel.getRelease_date());
        intent.putExtra("skor_rate", Float.parseFloat(String.valueOf(dataModel.getVote_avarage())) / 2);
        /*intent.putExtra("tagline_movie", dataModel.getTag());*/
        startActivity(intent);
    }
}