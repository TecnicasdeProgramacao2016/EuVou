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
    public PlaceAdapter(Context context, List<Place> places)
    {

        super(context, 0,places);
    }

    private static class ViewHolder
    {
        TextView placeName;
        TextView placeEvaluation;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        Place place = getItem(position);
        ViewHolder viewHolder;

        if(convertView != null)
        {
            viewHolder = (ViewHolder) convertView.getTag();
            Log.d("PlaceAdapter", "Getiing TAG for Converting view");
        }
        else
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_show_place_rank, parent, false);
            viewHolder.placeName = (TextView) convertView.findViewById(R.id.placeName);
            viewHolder.placeEvaluation = (TextView) convertView.findViewById(R.id.placeEvaluation);
            convertView.setTag(viewHolder);
            Log.d("PlaceAdapter", "Setting ViewHolder");
        }


        viewHolder.placeName.setText(
                ((place.getName().length() > 40) ? place.getName().substring(0, 39).concat("...") : place.getName()));
        viewHolder.placeEvaluation.setText(place.getEvaluate().toString());
        return convertView;


    }
}
