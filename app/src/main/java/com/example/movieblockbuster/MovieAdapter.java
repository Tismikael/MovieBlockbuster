package com.example.movieblockbuster;

import android.graphics.Movie;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Film> films;
    private ImageView saveImage;

    private User user;

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

//        holder.presaveImage.setOnClickListener(this::saveMovie);
//        holder.saveImage.setOnClickListener(this::unsaveMovie);
        // Set visibility based on saved state
        if (film.isSaved()) {
            holder.presaveImage.setVisibility(View.GONE);
            holder.saveImage.setVisibility(View.VISIBLE);
        } else {
            holder.presaveImage.setVisibility(View.VISIBLE);
            holder.saveImage.setVisibility(View.GONE);
        }

        // Set the tag for identifying the position in saveMovie
        holder.presaveImage.setTag(position);

        // Attach click listener for presaveImage
        holder.presaveImage.setOnClickListener(this::saveMovie);

    }

    private void unsaveMovie(View view) {
        user = UserSession.getCurrentUser();
        View parent = (View) view.getParent();
        ImageView presaveImage = parent.findViewById(R.id.pre_save_movie);
        ImageView saveImage = parent.findViewById(R.id.save_movie);

        // Change visibility
        if (presaveImage != null && saveImage != null){
            presaveImage.setVisibility(View.VISIBLE);
            saveImage.setVisibility(View.GONE);
        }else {
            Log.e("unsaveMovie","presaveImage or saveImage is null");
        }

        Toast.makeText(view.getContext(), "Movie unsaved successfully", Toast.LENGTH_SHORT).show();
    }

    private void saveMovie(View view) {
        user = UserSession.getCurrentUser();
        if (user == null){
            Toast.makeText(view.getContext(), "you must be signed in to save this movie", Toast.LENGTH_SHORT).show();
        }
        else {
            // Get the position of the clicked item
            int position = (int) view.getTag();
            Film film = films.get(position);

            // Update save state
            film.setSaved(true);

            // Refresh only the affected item
            notifyItemChanged(position);

            Toast.makeText(view.getContext(), "Movie saved successfully", Toast.LENGTH_SHORT).show();
//            View parent = (View) view.getParent();
//            ImageView presaveImage = parent.findViewById(R.id.pre_save_movie);
//            ImageView saveImage = parent.findViewById(R.id.save_movie);
//
//            // Change visibility
//            if (presaveImage != null && saveImage != null) {
//                presaveImage.setVisibility(View.GONE);
//                saveImage.setVisibility(View.VISIBLE);
//            } else {
//                Log.e("saveMovie", "presaveImage or saveImage is null");
//            }
//
//            Toast.makeText(view.getContext(), "Movie saved successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return films == null ? 0 : films.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, releaseDate, duration, overview, voteAverage, readMore;
        ImageView poster, presaveImage, saveImage;

        MovieViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            releaseDate = itemView.findViewById(R.id.movie_release_date);
            duration = itemView.findViewById(R.id.duration);
            overview = itemView.findViewById(R.id.movie_description);
            voteAverage = itemView.findViewById(R.id.movie_rating);
            poster = itemView.findViewById(R.id.movie_poster);
            readMore = itemView.findViewById(R.id.movie_readAll);
            presaveImage = itemView.findViewById(R.id.pre_save_movie);
            saveImage = itemView.findViewById(R.id.save_movie);
        }
    }
}

