package com.example.movieblockbuster;

import android.util.Log;

public class UserSession {
    private static User currentUser;

    public static void login(User user) {

        if (user != null){
            currentUser = user;
            Log.d("session", "successful login");
        }else{
            Log.e("UserSession", "Attempted to login with null user");
        }
    }

    public static void logout() {
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
