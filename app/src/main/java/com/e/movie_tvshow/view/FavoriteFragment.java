package com.e.movie_tvshow.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.movie_tvshow.R;
import com.e.movie_tvshow.adapter.FavoriteAdapter;
import com.e.movie_tvshow.adapter.MovieAdapter;
import com.e.movie_tvshow.database.MovieDB;
import com.e.movie_tvshow.database.MyAppDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment implements FavoriteAdapter.itemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static MyAppDatabase myAppDatabase;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private List<MovieDB> movieDBS;
    private RecyclerView recyclerView;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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

        View v = inflater.inflate(R.layout.fragment_favorite, container, false);
        Stetho.initializeWithDefaults(getActivity());
        new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();
        myAppDatabase = Room.databaseBuilder(getActivity(), MyAppDatabase.class, "moviedb_favorite").allowMainThreadQueries().build();
        movieDBS = myAppDatabase.myDao().getMovie_favorite();
        initRecycleview(movieDBS,v);
        //movieDBS.addAll(myAppDatabase.myDao().getMovie_favorite());
        /* recyclerView = v.findViewById(R.id.rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        getData();
        */return v;
    }
/*    private void getData() {
        class GetData extends AsyncTask<Void,Void,List<MovieDB>> {

            @Override
            protected List<MovieDB> doInBackground(Void... voids) {
                List<MovieDB>myDataLists=myAppDatabase.myDao().getMovie_favorite();
                return myDataLists;

            }

            @Override
            protected void onPostExecute(List<MovieDB> myDataList) {
                FavoriteAdapter adapter=new FavoriteAdapter(getActivity(),myDataList);
                recyclerView.setAdapter(adapter);
                super.onPostExecute(myDataList);
            }
        }
        GetData gd=new GetData();
        gd.execute();
    }*/ private void initRecycleview(List<MovieDB> movieDBS, View v) {
        RecyclerView recyclerView = v.findViewById(R.id.rcv_favorite);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getActivity(),movieDBS,this);
        recyclerView.setAdapter(favoriteAdapter);
    }

    @Override
    public void onItemClicklistener(MovieDB movieDB) {
        Intent intent = new Intent(getActivity(), FavoriteDetailActivity.class);
        intent.putExtra("judul", movieDB.getJudul());
        intent.putExtra("sinopsis_singkat", movieDB.getSinopsis());
        intent.putExtra("backdrop_movie", movieDB.getBackdrop_path());
        intent.putExtra("poster_movie", movieDB.getPoster_path());
        intent.putExtra("rilis", movieDB.getTgl_rilis());
        intent.putExtra("skor_rate", Float.parseFloat(String.valueOf(movieDB.getRating())));
        startActivity(intent);
    }
}