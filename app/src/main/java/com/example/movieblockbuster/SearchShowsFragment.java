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

public class SearchShowsFragment extends Fragment{


    private static final String ARG_SHOW_NAME = "show_name" ;
    private RecyclerView recyclerView;
    private TVAdapter adapter;
    private List<Film> showList;

    public SearchShowsFragment() {}

    public static SearchShowsFragment newInstance(String showName){
        SearchShowsFragment fragment = new SearchShowsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_NAME, showName);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        return inflater.inflate(R.layout.current_movies_layout, container, false);
        View view = inflater.inflate(R.layout.search_shows_layout,container,false);

        recyclerView = view.findViewById(R.id.search_shows_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TVAdapter(showList);
        recyclerView.setAdapter(adapter);

        String showName = getArguments() != null ? getArguments().getString(ARG_SHOW_NAME) : "";
        fetchSearchResults(showName);
        return view;
    }

    private void fetchSearchResults(String showName) {
        String url = "/search/tv?query=" + showName;
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
                    Log.d("Name", "movie name typed in: " + showName);

                    // Update UI on the main thread
                    getActivity().runOnUiThread(() -> {
                        adapter.updateShows(filmResponse.getResults());
                    });
                }
            }

        });
    }
}
