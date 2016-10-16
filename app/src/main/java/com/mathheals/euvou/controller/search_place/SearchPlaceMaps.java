package com.mathheals.euvou.controller.search_place;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.show_place.ShowPlaceInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import dao.PlaceDAO;
import exception.PlaceException;
import model.Place;

/*
 * File name: SearchPlaceMaps.
 * File pourpose: This file have the pourpose to search places on a google map
 */

public class SearchPlaceMaps extends FragmentActivity implements GoogleMap.OnMarkerClickListener
{

    private String filter;

    public String getFilter() 
    {
        return filter;
    }

    public void setFilter(String filter) 
    {
        this.filter = filter;
    }

    protected GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        mMap.setOnMarkerClickListener(this);
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
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) 
            {
                setUpMap();
            }
        }
    }

    private JSONObject searchPlaces()
    {
        return new PlaceDAO(this).searchPlaceByPartName(getFilter());
    }

    private JSONObject foundPlaces;

    private void setUpMap() 
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-15.7941454, -47.8825479), 9));
        setFilter(getIntent().getStringExtra("query"));
        foundPlaces = searchPlaces();

        try 
        {
            convertJsonToPlace(foundPlaces);
            addMarkerPlace();
        } catch (JSONException exception)
        {
            exception.printStackTrace();
        } catch (PlaceException exception)
        {
            exception.printStackTrace();
        } catch (ParseException exception)
        {
            exception.printStackTrace();
        }

    }

    private ArrayList<Place> places;

    private void convertJsonToPlace(JSONObject result) throws JSONException, PlaceException, ParseException 
    {
        places = new ArrayList<>();
        if(result == null) 
        {
            Toast.makeText(this, "Sem Resultados", Toast.LENGTH_LONG).show();
            Log.d("SearchPlaceMaps", "No results");
            return;
        }
        for (int i = 0; i < result.length(); i++) 
        {
            Place aux;
            aux = new Place(
                    result.getJSONObject("" + i).getString("namePlace"),
                    result.getJSONObject("" + i).getString("evaluate"),
                    result.getJSONObject("" + i).getString("longitude"),
                    result.getJSONObject("" + i).getString("latitude"),
                    result.getJSONObject("" + i).getString("operation"),
                    result.getJSONObject("" + i).getString("description"),
                    result.getJSONObject("" + i).getString("address"),
                    result.getJSONObject("" + i).getString("phonePlace")
                    );
            places.add(aux);
        }
    }

    private void addMarkerPlace() 
    {
        if(places != null) {
            for (int i = 0; i < places.size(); ++i) 
            {
                mMap.addMarker(
                        new MarkerOptions()
                                .title(places.get(i).getName())
                                .snippet(places.get(i).getAddress())
                                .position(new LatLng(places.get(i).getLatitude(), places.get(i).getLongitude()))
                );
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) 
    {
        String marke = marker.getId().substring(1);
        int id = Integer.parseInt(marke);
        select(id);
        startShowInfoActivity();
        return false;
    }

    private Place clickedPlace;
    private int selectedPlaceId;

    private void select(int id) 
    {
        clickedPlace = places.get(id);
        try 
        {
            selectedPlaceId = foundPlaces.getJSONObject(Integer.toString(id)).getInt("idPlace");
        } catch (JSONException exception)
        {
            exception.printStackTrace();
        }
    }
    private void startShowInfoActivity() 
    {
        Intent intent = new Intent(this, ShowPlaceInfo.class);
        intent.putExtras(getPlaceInfoAsBundle());
        startActivity(intent);
    }
    
    private Bundle getPlaceInfoAsBundle() 
    {
        Bundle placeInfo = new Bundle();
        placeInfo.putString("name", clickedPlace.getName());
        placeInfo.putString("phone", clickedPlace.getPhone());
        placeInfo.putString("address", clickedPlace.getAddress());
        placeInfo.putString("description", clickedPlace.getDescription());
        placeInfo.putDouble("latitude", clickedPlace.getLatitude());
        placeInfo.putDouble("longitude", clickedPlace.getLongitude());
        placeInfo.putString("operation", clickedPlace.getOperation());
        placeInfo.putInt("idPlace", selectedPlaceId);
        return placeInfo;
    }
}

