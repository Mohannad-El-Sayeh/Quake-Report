package com.msaye7.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EarthquakeAdapter.OnInteractListener, LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerViewParent;
    TextView emptyView;
    ProgressBar loadingPB;
    TextView noInternet;
    private static final String SAMPLE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&limit=50";

    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new EarthquakeAdapter(new ArrayList<Earthquake>(), this, this);

        recyclerViewParent = findViewById(R.id.recycler_parent);
        loadingPB = findViewById(R.id.pb_loading);
        emptyView = findViewById(R.id.tv_empty);
        noInternet = findViewById(R.id.tv_no_internet);

        if(mAdapter.isEmpty()) {
            Log.e(LOG_TAG, "getting the loader manager.");
            LoaderManager loaderManager = LoaderManager.getInstance(this);
            Log.e(LOG_TAG, "Initializing a new loader.");
            loaderManager.initLoader(1, null, this);
        }

        recyclerViewParent.setAdapter(mAdapter);
        recyclerViewParent.setLayoutManager(linearLayoutManager);
    }

    boolean isConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void onEarthquakeClicked(Earthquake earthquake) {
        String url = earthquake.getUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @NonNull
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.e(LOG_TAG, "onCreate loader called.");
        loadingPB.setVisibility(View.VISIBLE);
        return new EarthquakeLoader(this, SAMPLE_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Earthquake>> loader, List<Earthquake> data) {
        Log.e(LOG_TAG, "onLoadFinished called.");

        mAdapter.clear();

        loadingPB.setVisibility(View.GONE);
        noInternet.setVisibility(View.GONE);

        if(data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
        if(mAdapter.isEmpty()){
            emptyView.setVisibility(View.VISIBLE);
        }else {
            emptyView.setVisibility(View.GONE);
        }
        if(!isConnected()){
            emptyView.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
            loadingPB.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Earthquake>> loader) {
        Log.e(LOG_TAG, "onLoaderReset called.");
        mAdapter.clear();
    }

}