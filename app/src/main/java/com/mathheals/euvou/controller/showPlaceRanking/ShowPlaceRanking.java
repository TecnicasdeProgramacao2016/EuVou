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
     * Method: public void onCreate(Bundle savedInstanceState)
     * Description: Save a instanciated state
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        assert(savedInstanceState != null);

        super.onCreate(savedInstanceState);
        Log.d("ShowPLaceRanking", "The instance state has been saved");
    }

    /**
     * Method:public View onCreateView(LayoutInflater inflater, ViewGroup container,
     * Bundle savedInstanceState)
     * Description: Join resources to a specific view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Verifying params
        verifyingParamsOnCreateView( inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_place_ranking, container, false); // inflate a view when it is created
    }

    /**
     * Method: public ShowPlaceRanking()
     * Description: Present a place in rank
     */
    public ShowPlaceRanking()
    {
        // Required empty public constructor
    }


    // Verify params to method OnCreateView
    private void verifyingParamsOnCreateView(LayoutInflater inflater, ViewGroup container,
                                             Bundle savedInstanceState)
    {
        assert(inflater != null);
        assert(container != null);
        assert(savedInstanceState != null);

    }

    protected void finalize() throws Throwable
    {
        try
        {
            close(); // close open files
        }finally
        {
            super.finalize();
        }
    }

}
