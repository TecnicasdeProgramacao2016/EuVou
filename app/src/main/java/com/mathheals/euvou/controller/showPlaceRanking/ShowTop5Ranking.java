/**
 * File name: ShowTop5Ranking.
 * File pourpose: Presents top 5 positions.
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

public class ShowTop5Ranking extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener
{

    private ArrayList<Place> places;
    private ListView listView;


    /**
     *
     */
    public ShowTop5Ranking()
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_top5_ranking, container, false);
        // Inflate the layout for this fragment
        listView = (ListView) view.findViewById(R.id.listViewPlaces5);
        listView.setOnItemClickListener(this);
        fillList();

        Log.d("ShowTop5Ranking", "The view has been setted");

        return  view;
    }

    private void fillList()
    {
        JSONObject result;

        try
        {
            int id = (new LoginUtility(getActivity())).getUserId();
            result = new PlaceDAO(getActivity()).searchTop5Places();
            places = new ArrayList<>();
            for (int i = 0; i < result.length(); i++)
            {
                int idPlace = result.getJSONObject("" + i).getInt("idPlace");

                assert(idPlace < 0);

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
                places.add(aux);

            }
            PlaceAdapter placeAdapter = new PlaceAdapter(getActivity(),places);

            listView.setAdapter(placeAdapter);

        }catch (JSONException exception)
        {
            exception.printStackTrace();
            Log.d("ShowTop5Ranking", "JSONException in fillList");
        }catch (PlaceException exception)
        {
            exception.printStackTrace();
            Log.d("Showtop5Ranking", "PlaceException in fillList");
        }catch (ParseException exception)
        {
            exception.printStackTrace();
            Log.d("Showtop5Ranking", "ParseExeception in fillList");
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
        assert(id < 0);

        Intent intent = new Intent(getActivity(), ShowPlaceInfo.class);
        intent.putExtras(getPlaceInfoAsBundle(id));
        startActivity(intent);

        Log.d("ShowTop5Ranking", "Show info has been inserted");


    }

    private Bundle getPlaceInfoAsBundle(int id)
    {
        Bundle placeInfo = new Bundle();
        Toast.makeText(getActivity(), "" + id, Toast.LENGTH_LONG);
        placeInfo.putString("name", places.get(id).getName());
        placeInfo.putString("phone", places.get(id).getPhone());
        placeInfo.putString("address", places.get(id).getAddress());
        placeInfo.putString("description", places.get(id).getDescription());
        placeInfo.putDouble("latitude", places.get(id).getLatitude());
        placeInfo.putDouble("longitude", places.get(id).getLongitude());
        placeInfo.putString("operation", places.get(id).getOperation());
        placeInfo.putInt("idPlace", places.get(id).getId());

        Log.d("ShowTop5Ranking", "Inserting info in PlaceInfo");


        return placeInfo;
    }

}