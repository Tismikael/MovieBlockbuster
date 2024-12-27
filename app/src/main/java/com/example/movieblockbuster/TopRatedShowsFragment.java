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
import java.util.List;

import okhttp3.Callback;
import okhttp3.Response;

public class TopRatedShowsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TVAdapter adapter;
    private List<Film> showList;

    public TopRatedShowsFragment() {}

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_rated_shows_layout, container, false);

        recyclerView = view.findViewById(R.id.top_rated_shows_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TVAdapter(showList);
        recyclerView.setAdapter(adapter);

        fetchTopRatedShows();
        return view;
    }

    private void fetchTopRatedShows() {
        ApiCall.getMovies("/tv/top_rated", new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Failed to load shows", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String jsonResponse = response.body().string();

                    // Log the JSON response
                    Log.d("TopRatedFragment", "JSON Response: " + jsonResponse);

                    FilmResponse filmResponse = new Gson().fromJson(jsonResponse, FilmResponse.class);

                    // Log the parsed data
                    Log.d("Parsed","Parsed Shows: " + filmResponse.getResults());

                    // Update UI on the main thread
                    getActivity().runOnUiThread(() -> {
                        adapter.updateShows(filmResponse.getResults());
                    });
                }
            }

        });
    }
}
