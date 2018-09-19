package com.example.rish.earthquake;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EqLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EqLoader.class.getName();

    /** Query URL */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";


    private Context context;

    public EqLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG,"LOADER: onStartLoading....");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Earthquake> loadInBackground() {
        Log.d(LOG_TAG,"LOADER: loadInBackground....");

        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(getUrl(context));
        return earthquakes;
    }
    public String getUrl(Context context){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String minMagnitude = sharedPrefs.getString(
                context.getString(R.string.settings_min_magnitude_key),
                context.getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                context.getString(R.string.settings_order_by_key),
                context.getString(R.string.settings_order_by_default));

        String numberOfResults = sharedPrefs.getString(
                context.getString(R.string.settings_number_of_results_key),
                context.getString(R.string.settings_number_of_results_default));


        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        Log.d(LOG_TAG, "Query URL 1: "+uriBuilder.toString());
        uriBuilder.appendQueryParameter("format", "geojson");
        Log.d(LOG_TAG, "Query URL 2: "+uriBuilder.toString());
        uriBuilder.appendQueryParameter("limit", numberOfResults);
        Log.d(LOG_TAG, "Query URL 3: "+uriBuilder.toString());
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        Log.d(LOG_TAG, "Query URL 4: "+uriBuilder.toString());
        uriBuilder.appendQueryParameter("orderby", orderBy);
        Log.d(LOG_TAG, "Final Query URL: "+uriBuilder.toString());
        return uriBuilder.toString();
    }
}
