package com.mathheals.euvou.controller.remove_user;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mathheals.euvou.R;

/*
 * File name: DisableAccountFragment.
 * File pourpose: This file have the pourpose disable a account fragment.
 */

public class DisableAccountFragment extends android.support.v4.app.Fragment implements View.OnClickListener 
{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) 
    {
        assert(inflater != null);
        assert(container != null);
        assert(savedInstanceState != null);
        /**
         * method inflate the layout for this fragment
         * @param inflater
         * @param container
         * @param savedInstanceState
         */

        View view = inflater.inflate(R.layout.fragment_disable_account, container, false); // Inflate view when it is created


        //Init buttons functions
        Button yesButton = (Button)view.findViewById(R.id.button_yes_id);
        yesButton.setOnClickListener(this);

        Button noButton = (Button)view.findViewById(R.id.button_no_id);
        noButton.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View view) 
    {
        assert(view != null);

        /**
         * method that gets the informations and create the event
         * @param view
         */

        FragmentActivity activity = this.getActivity();
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Context homePageContext = activity.getBaseContext();

        //check if button was selected
        switch(view.getId())
        {
            //Check if disable button was pressionaded
            case R.id.button_yes_id:
                fragmentManager.popBackStack();
                RemoveUserViewMessages.showWelcomeBackMessage(homePageContext);
                return;

            case R.id.button_no_id:
                android.support.v4.app.Fragment disableAccountFragment = activity.getSupportFragmentManager().findFragmentByTag(String.valueOf(R.string.DISABLE_ACCOUNT_FRAGMENT_TAG)); //starts process of deactivation when request
                fragmentTransaction.remove(disableAccountFragment);
                fragmentTransaction.add(R.id.content_frame, new DisableAccountLoginConfirmation());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return;
            default:
                //NOTHING TO DO
        }

    }

}
