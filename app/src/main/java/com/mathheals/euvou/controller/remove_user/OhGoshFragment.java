package com.mathheals.euvou.controller.remove_user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mathheals.euvou.R;

/*
 * File name: OhGoshFragment.
 * File pourpose: This file have the pourpose to (don't understand yet, edit this later)).
 */
public class OhGoshFragment extends android.support.v4.app.Fragment 
{


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) 
    {
        /**
         * method inflate the layout for this fragment
         * @param inflater -
         * @param container -
         * @param savedInstanceState -
         */

        return inflater.inflate(R.layout.fragment_oh_gosh, container, false);  // Inflate view when it is created
    }


}
