package com.example.movieblockbuster;

import android.os.Parcel;
import android.os.Parcelable;
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

    //Default constructor
    public Film(){}

    public Film(String title, String releaseDate, String duration, String posterPath, String description, float rating){
        this.title = title;
//        this.category = category;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.posterPath = posterPath;
        this.overview = description;
        this.voteAverage = rating;
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
}


//    protected Film(Parcel in){
//        title = in.readString();
////        category = in.readString();
//        releaseDate = in.readString();
//        duration = in.readString();
//        imageUrl = in.readString();
//        description = in.readString();
//        rating = in.readFloat();
//    }

//    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
//        @Override
//        public Film createFromParcel(Parcel in) { return new Film(in); }
//
//        @Override
//        public Film[] newArray(int i) {
//            return new Film[0];
//        }
//
//    };
//
//    public void writeToParcel(Parcel dest){
//        dest.writeString(title);
////        dest.writeString(category);
//        dest.writeString(releaseDate);
//        dest.writeString(duration);
//        dest.writeString(imageUrl);
//        dest.writeString(description);
//        dest.writeFloat(rating);
//    }