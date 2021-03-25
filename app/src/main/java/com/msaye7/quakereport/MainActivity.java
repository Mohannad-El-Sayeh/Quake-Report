package com.msaye7.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EarthquakeAdapter.OnInteractListener {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerViewParent;
    private static final String SAMPLE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&limit=50";

    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewParent = findViewById(R.id.recycler_parent);

        Background background = new Background();
        background.execute(SAMPLE_URL);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new EarthquakeAdapter(new ArrayList<Earthquake>(), this, this::onEarthquakeClicked);

        recyclerViewParent.setAdapter(mAdapter);
        recyclerViewParent.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onEarthquakeClicked(Earthquake earthquake) {
        String url = earthquake.getUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private class Background extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if(urls.length == 0 || urls[0] == null){
                return null;
            }
            return QueryUtils.getEarthquakes(urls[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {

            mAdapter.clear();

            if(!earthquakes.isEmpty() && earthquakes != null){
                mAdapter.addAll(earthquakes);
            }


        }
    }

}