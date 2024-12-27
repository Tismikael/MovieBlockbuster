package com.example.movieblockbuster;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class MovieFragment extends Fragment {

    private TextView movieCurrent;
    private TextView moviePopular;
    private TextView movieTopPlaying;
    private TextView movieUpcoming;
    private TextView searchMovie;
    private EditText movieResult;
    String movieName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_options, container, false);

        movieCurrent = view.findViewById(R.id.movie_current);
        moviePopular = view.findViewById(R.id.movie_popular);
        movieTopPlaying = view.findViewById(R.id.movie_top_playing);
        movieUpcoming = view.findViewById(R.id.movie_upcoming);
        searchMovie = view.findViewById(R.id.search_movie);
        movieResult = view.findViewById(R.id.movie_result);

        if (savedInstanceState == null) {
            loadFragment(new CurrentMovieFragment());
            setActiveTabColor("current");
        }

        movieCurrent.setOnClickListener(v -> {
            loadFragment(new CurrentMovieFragment());
            setActiveTabColor("current");
        });
        moviePopular.setOnClickListener(v -> {
            loadFragment(new PopularMoviesFragment());
            setActiveTabColor("popular");
        });
        movieTopPlaying.setOnClickListener(v -> {
            loadFragment(new TopPlayingMoviesFragment());
            setActiveTabColor("topPlaying");
        });
        movieUpcoming.setOnClickListener(v -> {
            loadFragment(new UpcomingMoviesFragment());
            setActiveTabColor("upcoming");
        });

        searchMovie.setOnClickListener(v -> {
            movieName = movieResult.getText().toString().trim();
            if (!movieName.isEmpty()){
                SearchMoviesFragment fragment = SearchMoviesFragment.newInstance(movieName);
                loadFragment(fragment);
                setActiveTabColor("default");
            }else {
                Toast.makeText(getContext(), "Please enter a movie name", Toast.LENGTH_SHORT);
            }

        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.movie_fragment_container, fragment);
        transaction.commit();
    }

    private void setActiveTabColor(String activeTab) {
        movieCurrent.setTextColor(getResources().getColor(R.color.white));
        moviePopular.setTextColor(getResources().getColor(R.color.white));
        movieTopPlaying.setTextColor(getResources().getColor(R.color.white));
        movieUpcoming.setTextColor(getResources().getColor(R.color.white));

        switch (activeTab) {
            case "current":
                movieCurrent.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
            case "popular":
                moviePopular.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
            case "topPlaying":
                movieTopPlaying.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
            case "upcoming":
                movieUpcoming.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
            case "default":
                break;
        }
    }
}