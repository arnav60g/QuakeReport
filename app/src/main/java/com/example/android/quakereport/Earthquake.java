package com.example.android.quakereport;

/**
 * Created by Arnav on 17-01-2017.
 */

public class Earthquake {

    private double mMagnitude;

    private String mPlace;

    private long mTimeInMilliseconds;

    private String mDetail;

    public Earthquake (double magnitude, String place, long timeInMilliseconds, String detail){
        mMagnitude=magnitude;
        mPlace=place;
        mTimeInMilliseconds=timeInMilliseconds;
        mDetail=detail;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mPlace;
    }

    public long getDate(){
        return mTimeInMilliseconds;
    }

    public String getDetail(){
        return mDetail;
    }
}
