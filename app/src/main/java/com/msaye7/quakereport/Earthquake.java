package com.msaye7.quakereport;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Earthquake {

    private double magnitude;
    private String location;
    private long time;
    private String url;

    private final String LOCATION_SPLITTER = " of ";

    public Earthquake(double magnitude, String location, long time, String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.time = time;
        this.url = url;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMagnitude() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    public int getMagnitudeColor(){
        int magnitudeColor;
        switch ((int) Math.floor(magnitude)){
            case 0:
            case 1:
                magnitudeColor = R.color.magnitude1;
                break;
            case 2:
                magnitudeColor = R.color.magnitude2;
                break;
            case 3:
                magnitudeColor = R.color.magnitude3;
                break;
            case 4:
                magnitudeColor = R.color.magnitude4;
                break;
            case 5:
                magnitudeColor = R.color.magnitude5;
                break;
            case 6:
                magnitudeColor = R.color.magnitude6;
                break;
            case 7:
                magnitudeColor = R.color.magnitude7;
                break;
            case 8:
                magnitudeColor = R.color.magnitude8;
                break;
            case 9:
                magnitudeColor = R.color.magnitude9;
                break;
            default:
                magnitudeColor = R.color.magnitude10plus;
        }

        return magnitudeColor;
    }

    public String getLocation() {
        return location;
    }

    public long getTime() {
        return time;
    }

    public String getPrimaryLocation(){
        String primaryLocation;
        if(location.contains(LOCATION_SPLITTER)){
            primaryLocation = location.substring(location.indexOf(LOCATION_SPLITTER) + LOCATION_SPLITTER.length() + 1);
        }else{
            primaryLocation = location;
        }
        return primaryLocation;
    }

    public String getLocationOffset(){
        String locationOffset;
        if(location.contains(LOCATION_SPLITTER)){
            locationOffset = location.substring(0, location.indexOf(LOCATION_SPLITTER));
            locationOffset = locationOffset + LOCATION_SPLITTER;
        }else{
            locationOffset = "Near the ";
        }
        return locationOffset;
    }

    public String getDateString(){
        final String DATE_FORMAT_PATTERN = "LLL dd, yyyy";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        return dateFormatter.format(time);
    }

    public String getTimeString(){
        final String TIME_FORMAT_PATTERN = "h:mm a";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormatter = new SimpleDateFormat(TIME_FORMAT_PATTERN);
        return timeFormatter.format(time);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
