package com.example.movieblockbuster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    private TextView moviesCategory;
    private TextView tvshowCategory;
    private AppCompatButton signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize category tabs
        moviesCategory = findViewById(R.id.movies_category);
        tvshowCategory = findViewById(R.id.tvshow_category);
        signIn = findViewById(R.id.sign_in_main);

        // Initially load the MoviesFragment
        if (savedInstanceState == null) {
            loadFragment(new MovieFragment());
            setActiveCategoryColor("movies");
        }

        // Set click listeners for category tabs (Movies, TV Shows)
        moviesCategory.setOnClickListener(v -> {
            loadFragment(new MovieFragment());
            setActiveCategoryColor("movies");
        });

        tvshowCategory.setOnClickListener(v -> {
            loadFragment(new TVShowFragment());
            setActiveCategoryColor("tvshows");
        });

        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });
    }

    // Method to load a fragment into the container
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);  // fragment_container is your FrameLayout in activity_main.xml
        transaction.commit();
    }

    // Method to change the active category tab color
    private void setActiveCategoryColor(String activeCategory) {
        // Reset both categories to default color (white or any other default color)
        moviesCategory.setTextColor(getResources().getColor(R.color.white));
        tvshowCategory.setTextColor(getResources().getColor(R.color.white));

        // Set the selected category tab color to yellow
        switch (activeCategory) {
            case "movies":
                moviesCategory.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
            case "tvshows":
                tvshowCategory.setTextColor(getResources().getColor(R.color.star_yellow));
                break;
        }
    }
}


