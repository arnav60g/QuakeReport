package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

/**
 * Created by Arnav on 23-01-2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {
    String mUrl;
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl=url;
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        ArrayList<Earthquake> quake = QueryUtils.fetchEarthquakeData(mUrl);
        return quake;
    }
}
