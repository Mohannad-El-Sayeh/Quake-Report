package com.msaye7.quakereport;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private final String LOG_TAG = EarthquakeLoader.class.getSimpleName();

    String url;

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        Log.e(LOG_TAG, "Created EarthquakeLoader object.");
        this.url = url;
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        if(url == null){
            return null;
        }
        Log.e(LOG_TAG, "Used load in background. and going to query utils.");
        return QueryUtils.getEarthquakes(url);
    }

    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG, "Used start loading.");
        forceLoad();
    }
}
