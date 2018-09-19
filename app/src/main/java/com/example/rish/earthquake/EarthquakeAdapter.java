package com.example.rish.earthquake;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.eq_list_item, parent, false);
        }

        final Earthquake currentEq = getItem(position);
        TextView textView = listItemView.findViewById(R.id.magnitude);
        textView.setText(formatMagnitude(currentEq.getMagnitude()));
        GradientDrawable magnitudeCircle = (GradientDrawable) textView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEq.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        textView = listItemView.findViewById(R.id.date);
        Date dateObject = new Date(currentEq.getTimeInMilliseconds());
        String formattedDate = formatDate(dateObject);
        textView.setText(formattedDate);


        textView = listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        textView.setText(formattedTime);

        String location = currentEq.getLocation();
        String locationOffset;
        String primaryLocation;
        if (location.contains(LOCATION_SEPARATOR)) {
            String[] parts = location.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = location;
        }
        textView = listItemView.findViewById(R.id.location_offset);
        textView.setText(locationOffset);
        textView = listItemView.findViewById(R.id.primary_location);
        textView.setText(primaryLocation);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {

        if(magnitude>=0&&magnitude<2)
            return ContextCompat.getColor(getContext(), R.color.magnitude1);
        else if(magnitude>=2&&magnitude<3)
            return ContextCompat.getColor(getContext(), R.color.magnitude2);
        else if(magnitude>=3&&magnitude<4)
            return ContextCompat.getColor(getContext(), R.color.magnitude3);
        else if(magnitude>=4&&magnitude<5)
            return ContextCompat.getColor(getContext(), R.color.magnitude4);
        else if(magnitude>=5&&magnitude<6)
            return ContextCompat.getColor(getContext(), R.color.magnitude5);
        else if(magnitude>=6&&magnitude<7)
            return ContextCompat.getColor(getContext(), R.color.magnitude6);
        else if(magnitude>=7&&magnitude<8)
            return ContextCompat.getColor(getContext(), R.color.magnitude7);
        else if(magnitude>=8&&magnitude<9)
            return ContextCompat.getColor(getContext(), R.color.magnitude8);
        else if(magnitude>=9&&magnitude<10)
            return ContextCompat.getColor(getContext(), R.color.magnitude9);
        else
            return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
