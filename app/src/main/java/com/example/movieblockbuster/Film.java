package com.example.movieblockbuster;

import com.google.gson.annotations.SerializedName;


public class Film {

    @SerializedName("title")
    private String title;
//    private String category;
    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("duration")
    private String duration;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("name")
    private String name;

    @SerializedName("first_air_date")
    private String firstAirDate;

    private boolean isSaved;

    //Default constructor
    public Film(){}

    public Film(String title, String releaseDate, String duration, String posterPath, String description, float rating, String name, String firstAirDate){
        this.title = title;
//        this.category = category;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.posterPath = posterPath;
        this.overview = description;
        this.voteAverage = rating;
        this.name = name;
        this.firstAirDate = firstAirDate;
        this.isSaved = false;
    }


    // Setters and Getters

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getReleaseDate() { return releaseDate; }

    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getDuration() { return duration; }

    public void setDuration(String duration) { this.duration = duration; }

    public String getPosterPath() { return posterPath; }

    public void setPosterPath(String posterPath) { this.posterPath = posterPath; }

    public String getOverview() { return overview; }

    public void setOverview(String overview) { this.overview = overview; }

    public double getVoteAverage() { return voteAverage; }

    public void setVoteAverage(double voteAverage) { this.voteAverage = voteAverage; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getFirstAirDate() { return firstAirDate; }

    public void setFirstAirDate(String firstAirDate) { this.firstAirDate = firstAirDate; }

    public boolean isSaved() { return isSaved; }

    public void setSaved(boolean saved) { isSaved = saved; }
}

