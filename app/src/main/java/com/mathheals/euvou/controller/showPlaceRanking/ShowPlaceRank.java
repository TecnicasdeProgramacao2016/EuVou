/**
 * File name: ShowPlaceRank.
 * File pourpose: Present position in ranking.
 */


package com.mathheals.euvou.controller.showPlaceRanking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.show_place.ShowPlaceInfo;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.PlaceDAO;
import exception.PlaceException;
import model.Place;

public class ShowPlaceRank extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener
{

    private ListView listView;
    private JSONObject result;
    private ArrayList<Place> places;


    /**
     *
     */
    public ShowPlaceRank()
    {
        // Required empty public constructor
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_show_place_rank, container, false);
        // Inflate the layout for this fragment
        listView = (ListView) view.findViewById(R.id.listViewPlacesTotall);
        listView.setOnItemClickListener(this);
        fillList();
        return  view;
    }

    private void fillList()
    {
        int id = (new LoginUtility(getActivity())).getUserId();

        assert(id < 0);

        result = new PlaceDAO(getActivity()).searchAllPlaces();
        places = new ArrayList<>();
        populateArrayOfPlaces(result,places);
        PlaceAdapter placeAdapter = new PlaceAdapter(getActivity(),places);
        listView.setAdapter(placeAdapter);
    }

    /**
     *
     * @param result
     * @param places
     */
    public void populateArrayOfPlaces(JSONObject result,ArrayList<Place> places)
    {
        try
        {
            for (int i = 0; i < result.length(); i++)
            {
                int idPlace = result.getJSONObject("" + i).getInt("idPlace");

                assert (idPlace <  0);

                String namePlace = result.getJSONObject("" + i).getString("namePlace");
                Place aux = new Place(idPlace,
                        namePlace,
                        result.getJSONObject("" + i).getString("evaluate"),
                        result.getJSONObject("" + i).getString("longitude"),
                        result.getJSONObject("" + i).getString("latitude"),
                        result.getJSONObject("" + i).getString("operation"),
                        result.getJSONObject("" + i).getString("description"),
                        result.getJSONObject("" + i).getString("address"),
                        result.getJSONObject("" + i).getString("phonePlace")
                );

                Log.d("ShowPlaceRank", "A new place has been created");

                places.add(aux);
            }
        }catch(JSONException exception)
        {
            exception.printStackTrace();
            Log.d("ShowPlaceRank", "JSONException");
        }
        catch(PlaceException exception)
        {
            exception.printStackTrace();
            Log.d("ShowPlaceRank", "PlaceException");
        }
        catch (ParseException exception)
        {
            exception.printStackTrace();
            Log.d("ShowPlaceRank", "ParseException");
        }
    }

    /**
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        startShowInfoActivity(position);
    }

    private void startShowInfoActivity(int id)
    {
        Intent intent = new Intent(getActivity(), ShowPlaceInfo.class);
        intent.putExtras(getPlaceInfoAsBundle(id));
        startActivity(intent);

        Log.d("ShowPlaceRank", "It has been started to insert in activity");
    }

    private Bundle getPlaceInfoAsBundle(int id)
    {
        assert(id < 0);

        Bundle placeInfo = new Bundle();
        Toast.makeText(getActivity(),"" + id,Toast.LENGTH_LONG);
        placeInfo.putString("name", places.get(id).getName());
        placeInfo.putString("phone", places.get(id).getPhone());
        placeInfo.putString("address", places.get(id).getAddress());
        placeInfo.putString("description", places.get(id).getDescription());
        placeInfo.putDouble("latitude", places.get(id).getLatitude());
        placeInfo.putDouble("longitude", places.get(id).getLongitude());
        placeInfo.putString("operation", places.get(id).getOperation());
        placeInfo.putInt("idPlace", places.get(id).getId());

        Log.d("ShowPlaceRank", "PlaceInfo has been injected");

        return placeInfo;
    }
}