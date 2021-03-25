package com.msaye7.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeViewHolder> {

    private final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public interface OnInteractListener{
        void onEarthquakeClicked(Earthquake earthquake);
    }

    ArrayList<Earthquake> earthquakes;
    Context context;
    OnInteractListener onInteractListener;

    public EarthquakeAdapter(List<Earthquake> earthquakes, Context context, OnInteractListener onInteractListener) {
        this.earthquakes = (ArrayList<Earthquake>) earthquakes;
        this.context = context;
        this.onInteractListener = onInteractListener;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EarthquakeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_view, parent, false), context, onInteractListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        holder.bindData(earthquakes.get(position));
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }

    public void clear(){
        for (int i = 0; i < earthquakes.size(); i++)
            earthquakes.set(i, null);
        notifyDataSetChanged();
    }

    public void addAll(List<Earthquake> earthquakes1){
        earthquakes.addAll(earthquakes1);
        notifyDataSetChanged();
    }
}
