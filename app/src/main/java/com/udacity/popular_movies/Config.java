package com.udacity.popular_movies;

import android.os.AsyncTask;
import android.util.Log;

import com.udacity.popular_movies.task.BaseUrlTask;

import java.util.concurrent.ExecutionException;

public class Config {


    private Config(){}

    private static String baseUrl = null;
    private static final String resolution = "w185/";
    private static final String highResolution = "w500/";
    private static final String language = "en";

    public static String getLanguage() {
        return language;
    }

    public static String getBaseUrl() {
        if(baseUrl == null){
            baseUrl = loadBaseUrl();
        }
        return baseUrl;
    }

    public static String getResolution() {
        return resolution;
    }

    public static String getHighResolution() {
        return highResolution;
    }

    private static String loadBaseUrl(){
        AsyncTask<Void, Void, String> baseUrlTask = new BaseUrlTask().execute();
        try {
            return baseUrlTask.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e("Configuration", "Error loading BaseUrl");
        }
        return null;
    }
}
