package com.example.rish.earthquake;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mURL;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String URL){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mURL = URL;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getURL() {
        return mURL;
    }
}
