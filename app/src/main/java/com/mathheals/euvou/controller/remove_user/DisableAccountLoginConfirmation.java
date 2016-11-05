package com.mathheals.euvou.controller.remove_user;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.login_user.LoginValidation;
import com.mathheals.euvou.controller.utility.ActivityUtility;
import com.mathheals.euvou.controller.utility.LoginUtility;

import dao.UserDAO;

/*
 * File name: DisableAccountLoginConfirmation.
 * File pourpose: This file have the pourpose to disable the account login confirmation
 */

public class DisableAccountLoginConfirmation extends android.support.v4.app.Fragment implements View.OnClickListener 
{

    private Activity homePage;


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
         * @param inflater -
         * @param container -
         * @param savedInstanceState -
         */

        homePage = getActivity();
        View view = (View) inflater.inflate(R.layout.fragment_disable_account_login_confirmation, container, false);// Inflate view when it is created

        Button backButton = (Button)view.findViewById(R.id.button_back_id);
        backButton.setOnClickListener(this);

        Button disableButton = (Button)view.findViewById(R.id.button_disable_account_confirmation_id);
        disableButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) 
    {

        /**
         * method that gets the informations and create the event
         * @param view
         */

        FragmentActivity activity = this.getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

       //check if disable account was pressioned to show feedback message
        switch (view.getId())
        {
            case R.id.button_back_id:
                returnToConfigurationOptions(fragmentManager);
                RemoveUserViewMessages.showWelcomeBackMessage(activity.getBaseContext());
                return;
            case R.id.button_disable_account_confirmation_id:
                if(isLoginConfirmationValid()) {
                    LoginUtility loginUtility = new LoginUtility(homePage);
                    UserDAO userDAO = new UserDAO(getActivity());

                    userDAO.disableUser(new LoginUtility(homePage).getUserId());
                    loginUtility.setUserLogOff();

                    ActivityUtility.restartActivity(homePage);
                    RemoveUserViewMessages.showAccountDeactivateMessage(homePage.getBaseContext());
                }
                return;
            default:
                //NOTHING TO DO
        }
    }

    private void returnToConfigurationOptions(FragmentManager fragmentManager) 
    {
        fragmentManager.popBackStack();
        fragmentManager.popBackStack();
    }

    public boolean isLoginConfirmationValid()
    {
        /**
         * method check the confirmation of user login
         */

        View view = (View)getView();

        EditText usernameField = (EditText) view.findViewById(R.id.edit_text_login_id);
        String typedUsername = (String)usernameField.getText().toString();

        EditText passwordField = (EditText) view.findViewById(R.id.edit_text_password_id);
        String typedPassword = (String)passwordField.getText().toString();

        LoginValidation loginValidation = new LoginValidation(homePage);

        boolean isUsernameValid = loginValidation.isUsernameValid(typedUsername);

        //check if username is valid to login

        isUsernameValid = checkIfUserNameIsValid (isUsernameValid, typedUsername, typedPassword, loginValidation, passwordField, usernameField);

        if (isUsernameValid)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public boolean checkIfUserNameIsValid (boolean isUsernameValid, String typedUsername, String typedPassword,
                                           LoginValidation loginValidation, EditText passwordField, EditText usernameField)
    {
        if(isUsernameValid==true)
        {
            boolean isPasswordValid = loginValidation.checkPassword(typedUsername, typedPassword);

            //check if passoword is valid to login
            if(isPasswordValid==true)
            {
                Log.d("checkIfUserNameIsValid", "Login valid");
                return true;
            }
            else
            {
                passwordField.requestFocus();
                passwordField.setError(loginValidation.getInvalidPasswordMessage());
            }

        } else
        {
            usernameField.requestFocus();
            usernameField.setError(loginValidation.getInvalidUsernameMessage());

        }
        return false;
    }
}
