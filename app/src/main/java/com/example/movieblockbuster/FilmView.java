package com.example.movieblockbuster;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class FilmView extends LinearLayout {


    public FilmView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.setOrientation(LinearLayout.VERTICAL);
    }

    public void addFilm(Film film){

        // Inflate layout
        View filmItem= LayoutInflater.from(getContext()).inflate(R.layout.movie_info, this, false);

        // create Views
        TextView titleView = findViewById(R.id.film_title);
        TextView categoryView = findViewById(R.id.category);
        TextView releaseDateView = findViewById(R.id.release_date);
        TextView durationView = findViewById(R.id.duration);
        ImageView imageView = findViewById(R.id.poster);
        TextView descriptionView = findViewById(R.id.description);
        TextView ratingView = findViewById(R.id.rating);

        TextView readAllView = findViewById(R.id.readAll);

        // set Views values
        titleView.setText(film.getTitle());
        categoryView.setText(film.getCategory());
        releaseDateView.setText(film.getReleaseDate());
        durationView.setText(film.getDuration());
        Picasso.get().load(film.getImageUrl()).into(imageView);
        descriptionView.setText(film.getDescription());
        ratingView.setText("" + film.getRating());

        // Set up "Read All" functionality
        readAllView.setOnClickListener(v -> {
            if (readAllView.getText().equals("Read All")){
                descriptionView.setMaxLines(Integer.MAX_VALUE);
                readAllView.setText("Read less");
            } else {
                descriptionView.setMaxLines(4);
                readAllView.setText("Read All");
            }
        });

    }
}


