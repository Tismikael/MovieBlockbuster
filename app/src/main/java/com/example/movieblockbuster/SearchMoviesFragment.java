package com.example.movieblockbuster;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.Response;


public class SearchMoviesFragment extends Fragment {

    private static final String ARG_MOVIE_NAME = "movie_name" ;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Film> movielist;

    public SearchMoviesFragment() {}

    public static SearchMoviesFragment newInstance(String movieName){
        SearchMoviesFragment fragment = new SearchMoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MOVIE_NAME, movieName);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        return inflater.inflate(R.layout.current_movies_layout, container, false);
        View view = inflater.inflate(R.layout.search_movies_layout,container,false);

        recyclerView = view.findViewById(R.id.search_movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MovieAdapter(movielist);
        recyclerView.setAdapter(adapter);

        String movieName = getArguments() != null ? getArguments().getString(ARG_MOVIE_NAME) : "";
        fetchSearchResults(movieName);
        return view;
    }

    private void fetchSearchResults(String movieName) {
        String url = "/search/movie?query=" + movieName;
        ApiCall.getMovies(url , new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Failed to load movies", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String jsonResponse = response.body().string();

                    // Log the JSON response
                    Log.d("SearchMoviesFragment", "JSON Response: " + jsonResponse);

                    FilmResponse filmResponse = new Gson().fromJson(jsonResponse, FilmResponse.class);

                    // Log the parsed data
                    Log.d("Parsed","Parsed Movies: " + filmResponse.getResults());
                    Log.d("Name", "movie name typed in: " + movieName);

                    // Update UI on the main thread
                    getActivity().runOnUiThread(() -> {
                        adapter.updateMovies(filmResponse.getResults());
                    });
                }
            }

        });
    }
}
