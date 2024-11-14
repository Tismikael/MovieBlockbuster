package com.example.movieblockbuster;

import android.os.Parcel;
import android.os.Parcelable;

public class Film {
    private String title;
    private String category;
    private String releaseDate;
    private String duration;
    private String imageUrl;
    private String description;
    private float rating;

    //Default constructor
    public Film(){}

    public Film(String title, String category, String releaseDate, String duration, String imageUrl, String description, float rating){
        this.title = title;
        this.category = category;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.description = description;
        this.rating = rating;
    }

    protected Film(Parcel in){
        title = in.readString();
        category = in.readString();
        releaseDate = in.readString();
        duration = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        rating = in.readFloat();
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) { return new Film(in); }

        @Override
        public Film[] newArray(int i) {
            return new Film[0];
        }

    };

    public void writeToParcel(Parcel dest){
        dest.writeString(title);
        dest.writeString(category);
        dest.writeString(releaseDate);
        dest.writeString(duration);
        dest.writeString(imageUrl);
        dest.writeString(description);
        dest.writeFloat(rating);
    }

    // Setters and Getters

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getReleaseDate() { return releaseDate; }

    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getDuration() { return duration; }

    public void setDuration(String duration) { this.duration = duration; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public float getRating() { return rating; }

    public void setRating(float rating) { this.rating = rating; }
}
