/**
    file: LocalEventActivity.java
    Purpose: class to get the local of the event on Google Maps
 */


package com.mathheals.euvou.controller.event_registration;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;

public class LocalEventActivity extends FragmentActivity implements GoogleMap.OnMapClickListener
{

    private GoogleMap mMap = null; //instance of the maps and it might be null

    /**
     * main method of an Activity on Android, it creates the Activity.
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        assert(savedInstanceState != null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_event);
        setUpMapIfNeeded();
        mMap.setOnMapClickListener(this);
    }

    /**
     * it inflates the map if it is not inflated yet
     */
    private void setUpMapIfNeeded()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
            {
                setUpMap();
            }
            else
            {
                //NOTHING TO DO
            }
        }else
        {
            //NOTHING TO DO
        }
    }
    /**
     * it sets the latitude and the longitude to mark a point on the map
     * @param latLng - coodenates of the point
     */
    @Override
    public void onMapClick(LatLng latLng)
    {
        assert(latLng != null);
        Intent resultado = new Intent();
        assert(resultado == new Intent());
        resultado.putExtra("longitude", " " + latLng.longitude);
        resultado.putExtra("latitude", " " + latLng.latitude);
        setResult(Activity.RESULT_OK, resultado);
        finish();
    }

    @Override
    /**
     * method that gets a paused screen and continues it.
     */
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMap()
    {

    }

}
