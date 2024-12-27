package com.example.movieblockbuster;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Film> films;

    public MovieAdapter(List<Film> films) {
        this.films = films;
    }

    public void updateMovies(List<Film> newFilms) {
        films = newFilms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_info, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Film film = films.get(position);

        // Set title
        holder.title.setText(film.getTitle());

        // Set release date
        holder.releaseDate.setText(film.getReleaseDate());

        // Set duration
        holder.duration.setText(film.getDuration());

        // Set description (trim if needed)
        holder.overview.setText(film.getOverview());

        // Set rating

        // round rating to one decimal place
        String voteAverageValue = String.valueOf(film.getVoteAverage());
        double vavDouble = Double.parseDouble(voteAverageValue);
        DecimalFormat df = new DecimalFormat("#.00");
        double rounded = Double.parseDouble(df.format(vavDouble));


//        holder.voteAverage.setText(String.valueOf(film.getVoteAverage()));
        holder.voteAverage.setText(String.valueOf(rounded));

        // Set poster path
        // Load image using Picasso
        if (film.getPosterPath() != null && !film.getPosterPath().isEmpty()){

            String imageUrl = "https://image.tmdb.org/t/p/w185" + film.getPosterPath();

            Picasso.get()
                    .load(imageUrl)
                    .resize(170, 225)
                    .placeholder(R.drawable.one_piece_logo)
                    .error(R.drawable.one_piece_logo)
                    .into(holder.poster);

//            Glide.with(holder.itemView.getContext())
//                            .load(imageUrl)
//                            .placeholder(R.drawable.one_piece_logo)
//                            .error(R.drawable.one_piece_logo)
//                            .override(170,225)
//                            .into(holder.poster);

            Log.d("imageurl", "Loading image url: " + imageUrl);
        }

        holder.readMore.setOnClickListener(v -> {
            if (holder.overview.getMaxLines() == 5) {
                holder.overview.setMaxLines(100);
                holder.readMore.setText("Read Less");
            } else {
                holder.overview.setMaxLines(5);
                holder.readMore.setText("Read More");
            }
        });


    }

    @Override
    public int getItemCount() {
        return films == null ? 0 : films.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, releaseDate, duration, overview, voteAverage, readMore;
        ImageView poster;

        MovieViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            releaseDate = itemView.findViewById(R.id.movie_release_date);
            duration = itemView.findViewById(R.id.duration);
            overview = itemView.findViewById(R.id.movie_description);
            voteAverage = itemView.findViewById(R.id.movie_rating);
            poster = itemView.findViewById(R.id.movie_poster);
            readMore = itemView.findViewById(R.id.movie_readAll);

        }
    }
}

