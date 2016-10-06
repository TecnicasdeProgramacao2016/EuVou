/**
 * File name: ShowPlaceRanking.
 * File pourpose: Present Ranking.
 */

package com.mathheals.euvou.controller.showPlaceRanking;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mathheals.euvou.R;


public class ShowPlaceRanking extends android.support.v4.app.Fragment
{

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        Log.d("ShowPLaceRanking", "The instance state has been saved");
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
        return inflater.inflate(R.layout.fragment_show_place_ranking, container, false);
    }

    /**
     *
     */
    public ShowPlaceRanking()
    {
        // Required empty public constructor
    }
}
