/**
 * File name: PlaceAdapter.
 * File pourpose: Present info about Events
 */

package com.mathheals.euvou.controller.showPlaceRanking;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mathheals.euvou.R;

import java.util.List;

import model.Place;

public class PlaceAdapter extends ArrayAdapter<Place>
{
    /**
     * Method: public View getView(int position, View convertView, ViewGroup parent)
     * Description: Set and create a view to information about places
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Validate method params
        assert(position > 0);
        assert(convertView != null);
        assert(parent != null);


        Place place = (Place) getItem(position);
        ViewHolder viewHolder;

        //Convert a view
        if(convertView != null)
        {
            viewHolder = (ViewHolder) convertView.getTag();
            Log.d("PlaceAdapter", "Getting TAG for Converting view");
        }
        //Create a view with informatios about an event ranking
        else
        {
            viewHolder = new ViewHolder();
            assert(viewHolder != null);

            // Convert view to layout that is requested
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_show_place_rank, parent, false);

            viewHolder.placeName = (TextView) convertView.findViewById(R.id.placeName);
            viewHolder.placeEvaluation = (TextView) convertView.findViewById(R.id.placeEvaluation);

            convertView.setTag(viewHolder);
            Log.d("PlaceAdapter", "Setting ViewHolder");
        }


        final int LengthPlaceName = place.getName().length();

        /*
        if(LengthPlaceName > 40)
            viewHolder.placeName.setText(place.getName().substring(0, 39).concat("..."));
        else
            viewHolder.placeName.setText(place.getName());
        */
        
        viewHolder.placeName.setText(
                (LengthPlaceName > 40) ? place.getName()
                        .substring(0, 39).concat("...") : place.getName());

        viewHolder.placeEvaluation.setText(place.getEvaluate().toString());

        return convertView;


    }

    /**
     * Method: public PlaceAdapter(Context context, List<Place> places)
     * Description: Call informations about place and content
     * @param context
     * @param places
     */
    public PlaceAdapter(Context context, List<Place> places)
    {

        super(context, 0,places);
    }


    //Save information aboit name and evaluation about a place
    private static class ViewHolder
    {
        TextView placeName;
        TextView placeEvaluation;
    }


}
