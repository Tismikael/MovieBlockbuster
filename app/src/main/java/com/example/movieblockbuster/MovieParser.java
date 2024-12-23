package com.example.movieblockbuster;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MovieParser {

    public List<Film> parseJson(String json) {
        List<Film> films = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String title = jsonObject.getString("title");
                String releaseDate = jsonObject.getString("release_date");
                String duration = jsonObject.getString("runtime");
                String posterPath = jsonObject.getString("poster_path");
                String overview = jsonObject.getString("overview");
                float voteAverage = (float) jsonObject.getDouble("vote_average");

                films.add(new Film(title, releaseDate, duration, posterPath, overview, voteAverage));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }
}
