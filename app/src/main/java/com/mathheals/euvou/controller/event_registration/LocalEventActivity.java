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

    private GoogleMap mMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_event);
        setUpMapIfNeeded();
        mMap.setOnMapClickListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }

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
        }
    }


    private void setUpMap()
    {

    }

    @Override
    public void onMapClick(LatLng latLng)
    {
        //verificar parâmetro
        Intent resultado = new Intent();
        resultado.putExtra("longitude", " " + latLng.longitude);
        resultado.putExtra("latitude", " " + latLng.latitude);
        setResult(Activity.RESULT_OK, resultado);
        finish();
    }
}
