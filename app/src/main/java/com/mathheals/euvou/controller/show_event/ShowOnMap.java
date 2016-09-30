/**
 * File name: ShowOnMap.
 * File pourpose: Present Events in GOOGLE MAPS API.
 */

package com.mathheals.euvou.controller.show_event;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;


public class ShowOnMap extends FragmentActivity
{
    // Might be null if Google Play services APK is not available.
    protected GoogleMap mMap;
    private Double latitude;
    private Double longitude;
    private String filter;

    public String getFilter()
    {
        // private String filter;
        return filter;
    }

    public void setFilter(String filter)
    {
        //  private String filter;
        this.filter = filter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // private Double latitude;
        // private Double longitude;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bundle b=this.getIntent().getExtras();
        String[] array = b.getStringArray("LatitudeAndLongitude");
        latitude = Double.parseDouble(array[0]);
        longitude = Double.parseDouble(array[1]);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded()
    {
        //  protected GoogleMap mMap
        // Do a null check to confirm that we have not already instantiated the map.

        if(mMap != null)
        {
            //NOTHING TO DO
            Log.d("ShowOnMap", "Map is not null");
        }
        else
        {
            Log.d("ShowOnMap", "Map is null");
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            assert(mMap != null);

            // Check if we were successful in obtaining the map.
            if (mMap != null)
            {
                setUpMap();
                Log.d("ShowOnMap", "Map should be not null");
            }
            else
            {
                //NOTHING TO DO
            }

        }

    }

    private void setUpMap()
    {
        // private Double latitude;
        // private Double longitude;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(latitude, longitude), 9));
        setFilter(getIntent().getStringExtra("query"));
        addMarkerPlace(latitude,longitude);

        Log.d("ShowOnMap", "Map has been setted up");

    }

    private void addMarkerPlace(Double latitude, Double longitude)
    {
        mMap.addMarker(
                new MarkerOptions()
                        .title("Nome do Evento")
                        .snippet("Endereço")
                        .position(new LatLng(latitude, longitude))
        );
        Log.d("ShowOnMap", "Add a marker in a specific location");
    }
}