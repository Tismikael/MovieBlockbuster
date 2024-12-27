package com.example.movieblockbuster;

import android.app.DownloadManager;

import okhttp3.*;
import java.io.IOException;

public class ApiCall {

    private static final String BASE_URL = "https://api.themoviedb.org/3";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMmRiOGFiMmIyNTU4ZjZmYjUyODJkNmRhZmRhMTFmNiIsIm5iZiI6MTcxMTk5NzU5My4yNzksInN1YiI6IjY2MGIwMjk5MTVkZWEwMDE2MjMyZjdjYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.nu1rqiiKPokvYjIhc_KFT8lVYfgde238QX5DdvJH53g";

    static OkHttpClient client = new OkHttpClient();

    public static void getMovies(String endpoint, Callback callback){
            String url = BASE_URL + endpoint;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept","application/json")
                .addHeader("Authorization","Bearer " + API_KEY)
                .build();

        client.newCall(request).enqueue(callback);

    }


    public static void getMovies(String path, javax.security.auth.callback.Callback failedToLoadMovies) {
    }
}
