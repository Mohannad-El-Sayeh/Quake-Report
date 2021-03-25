package com.msaye7.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EarthquakeViewHolder extends RecyclerView.ViewHolder {

    private final String LOG_TAG = EarthquakeViewHolder.class.getSimpleName();

    LinearLayout parentLinear;
    TextView magnitudeTV;
    TextView locationOffsetTV;
    TextView locationTV;
    TextView dateTV;
    TextView timeTV;
    Context context;
    private EarthquakeAdapter.OnInteractListener onInteractListener;

    public EarthquakeViewHolder(@NonNull View itemView, Context context, EarthquakeAdapter.OnInteractListener onInteractListener) {
        super(itemView);
        magnitudeTV = itemView.findViewById(R.id.tv_mag);
        locationOffsetTV = itemView.findViewById(R.id.tv_loc_offset);
        locationTV = itemView.findViewById(R.id.tv_primary_loc);
        dateTV = itemView.findViewById(R.id.tv_date);
        timeTV = itemView.findViewById(R.id.tv_time);
        parentLinear = itemView.findViewById(R.id.linear_parent);
        this.onInteractListener = onInteractListener;
        this.context = context;
    }

    public void bindData(Earthquake earthquake){
        magnitudeTV.setText(earthquake.getMagnitude());
        locationOffsetTV.setText(earthquake.getLocationOffset());
        locationTV.setText(earthquake.getPrimaryLocation());
        dateTV.setText(earthquake.getDateString());
        timeTV.setText(earthquake.getTimeString());
        GradientDrawable magnitudeBackground = (GradientDrawable) magnitudeTV.getBackground();
        int color = context.getResources().getColor(earthquake.getMagnitudeColor());
        magnitudeBackground.setColor(color);
        parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInteractListener.onEarthquakeClicked(earthquake);
            }
        });
    }

}
