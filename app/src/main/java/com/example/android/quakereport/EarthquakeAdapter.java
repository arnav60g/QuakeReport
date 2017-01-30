package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
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

import static com.example.android.quakereport.R.id.date;
import static com.example.android.quakereport.R.id.time;

/**
 * Created by Arnav on 17-01-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        final Earthquake currentEarthquake= getItem(position);

        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitude = formatter.format(currentEarthquake.getMagnitude());

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(magnitude);

        String place,direction;
        if (currentEarthquake.getLocation().contains("of")){
            place=getPlace(currentEarthquake.getLocation());
            direction=getDirection(currentEarthquake.getLocation());
        }else {
            place=currentEarthquake.getLocation();
            direction=getContext().getString(R.string.near_the);
        }

        TextView locationView = (TextView) listItemView.findViewById(R.id.location);
        locationView.setText(place);

        TextView directionView = (TextView) listItemView.findViewById(R.id.direction);
        directionView.setText(direction);

        Date dateObject= new Date(currentEarthquake.getDate());
        SimpleDateFormat date1= new SimpleDateFormat("MMM DD, yyyy");
        SimpleDateFormat time1= new SimpleDateFormat("h:mm a");
        String dateToDisplay=date1.format(dateObject);
        String timeToDisplay=time1.format(dateObject);

        TextView timeView = (TextView) listItemView.findViewById(time);
        timeView.setText(timeToDisplay);

        TextView dateView = (TextView) listItemView.findViewById(date);
        dateView.setText(dateToDisplay);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        final View textContainer = listItemView.findViewById(R.id.text_container);

        textContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = currentEarthquake.getDetail();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                getContext().startActivity(intent);
            }
        });

        return listItemView;
    }

    public String getPlace(String loc1){
        String loc3[],loc2;
        loc3= loc1.split(" of ");
        loc2=loc3[1];
        return loc2;
    }

    public String getDirection(String loc1){
        String loc3[],loc2;
        loc3= loc1.split(" of ");
        loc2=loc3[0]+" of";
        return loc2;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


}
