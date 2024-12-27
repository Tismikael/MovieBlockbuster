package com.example.movieblockbuster;

import android.media.Image;
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

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.TvViewHolder> {

    private List<Film> films;

    public TVAdapter(List<Film> films) { this.films = films; }

    public void updateShows(List<Film> newFilms) {
        films = newFilms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_info, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVAdapter.TvViewHolder holder, int position) {

        Film film = films.get(position);

        holder.name.setText(film.getName());

        holder.firstAirDate.setText(film.getFirstAirDate());

        holder.overview.setText(film.getOverview());

        String voteAverageValue = String.valueOf(film.getVoteAverage());
        double vavDouble = Double.parseDouble(voteAverageValue);
        DecimalFormat df = new DecimalFormat("#.00");
        double rounded = Double.parseDouble(df.format(vavDouble));

        holder.voteAverage.setText(String.valueOf(rounded));

         if (film.getPosterPath() != null && !film.getPosterPath().isEmpty()){

             String imageUrl = "https://image.tmdb.org/t/p/w185" + film.getPosterPath();

             Picasso.get()
                     .load(imageUrl)
                     .resize(170, 225)
                     .placeholder(R.drawable.one_piece_logo)
                     .error(R.drawable.one_piece_logo)
                     .into(holder.poster);
         }

    }

    @Override
    public int getItemCount() { return films == null ? 0 : films.size(); }

    static class TvViewHolder extends  RecyclerView.ViewHolder {

        TextView name, overview, voteAverage, firstAirDate;
        ImageView poster;

        TvViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.show_name);
            overview = itemView.findViewById(R.id.show_description);
            voteAverage = itemView.findViewById(R.id.show_rating);
            firstAirDate = itemView.findViewById(R.id.show_first_aired);
            poster = itemView.findViewById(R.id.show_poster);

        }
    }


}
