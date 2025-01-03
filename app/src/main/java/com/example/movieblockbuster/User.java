package com.example.movieblockbuster;


public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String id;
    private String profilePictureUrl;

    // default constructor
    public User() {}

    public User(String firstName, String lastName, String email, String id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.profilePictureUrl = "drawable/default_profile_picture";

    }

    // Getters and Setters
    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getProfilePictureUrl() { return profilePictureUrl; }

    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
}
