package com.example.movieblockbuster;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class TVShowFragment extends Fragment {

    private TextView currentAiring;
    private TextView onTheAir;
    private TextView popular;
    private TextView topRated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shows_options, container, false);

        currentAiring = view.findViewById(R.id.tv_currently_airing);
        onTheAir = view.findViewById(R.id.tv_on_the_air);
        popular = view.findViewById(R.id.tv_popular);
        topRated = view.findViewById(R.id.tv_top_rated);

        if (savedInstanceState == null) {
            loadFragment(new CurrentShowsFragment());
            setActiveTabColor("current");
        }

        currentAiring.setOnClickListener(v -> {
            loadFragment(new CurrentShowsFragment());
            setActiveTabColor("current");
        });
        onTheAir.setOnClickListener(v -> {
            loadFragment(new OnTheAirShowFragment());
            setActiveTabColor("ontheair");
        });
        popular.setOnClickListener(v -> {
            loadFragment(new PopularShowsFragment());
            setActiveTabColor("popular");
        });
        topRated.setOnClickListener(v -> {
            loadFragment(new TopRatedShowsFragment());
            setActiveTabColor("toprated");
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.shows_fragment_container, fragment);
        transaction.commit();
    }

    private void setActiveTabColor(String activeTab) {
        currentAiring.setTextColor(getResources().getColor(R.color.white));
        onTheAir.setTextColor(getResources().getColor(R.color.white));
        popular.setTextColor(getResources().getColor(R.color.white));
        topRated.setTextColor(getResources().getColor(R.color.white));

        switch (activeTab) {
            case "current":
                currentAiring.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
            case "ontheair":
                onTheAir.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
            case "popular":
                popular.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
            case "toprated":
                topRated.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
        }
    }
}
