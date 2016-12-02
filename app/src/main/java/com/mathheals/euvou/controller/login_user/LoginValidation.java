package com.mathheals.euvou.controller.login_user;
import android.util.Log;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import dao.UserDAO;

/*
 * File name: LoginValidation.
 * File pourpose: This file have the pourpose to do the validation of the login.
 */

public class LoginValidation 
{
    
    
    Activity activity;

    public LoginValidation(Activity activity)
    {
        this.activity=activity;
    }

    private boolean checkUsernameCharacters(String username)
    {
        /*
            * method check if the username is valid
            * @param username - user's username
        */

        //return false case username is empty
        if(username.isEmpty()){
            return false;
        } else
        {
            //NOTHING TO DO
        }

        //return false case username have space
        if(username.contains(" ")){
            return false;
        } else
        {
            //NOTHING TO DO
        }
        return true;
    }

    private static final String COLUMN_USER_STATE = "isActivity";

    public boolean isActivity(String username)
    {

        /*
            * method check if the username is ative
            * @param username - user's username
        */

        UserDAO userDAO = new UserDAO(this.activity);

        JSONObject json = null;
        String isActivity = null;
        try 
        {
            json = userDAO.searchUserByUsername(username);
            isActivity = json.getJSONObject(JSON_FORMAT).getString(COLUMN_USER_STATE);
        } catch (JSONException exception)
        {
            exception.printStackTrace();
        }

        // case json and activities are null, return false in function
        if(json!=null && isActivity.equals("Y"))
        {
            return true;
        } else
        {
            return false;
        }

    }

    public boolean isUsernameRegistred(String username)
    {
        /*
            * method check if username is registred
            * @param username - user's username
        */
        UserDAO userDAO = new UserDAO(this.activity);

        JSONObject json = (JSONObject) userDAO.searchUserByUsername(username);

        //check if json is prepared to be used
        if(json!=null)
        {
            Log.d("LoginValidation", "Username is already registraded");
            return true;
        }else
        {
            return false;
        }

    }

    public boolean isUsernameValid(String username)
    {
        /*
            * method check if the username is valid
            * @param username - user's username
        */

        return checkUsernameCharacters(username) && isUsernameRegistred(username);
    }

    private final String JSON_FORMAT = "0";

    private final String PASSWORD_USER = "passwordUser";

    public boolean checkPassword(String validUsername, String passwordTyped)
    {
        UserDAO userDAO = new UserDAO(this.activity);

        JSONObject json = (JSONObject) userDAO.searchUserByUsername(validUsername);

        try 
        {
            String password = json.getJSONObject(JSON_FORMAT).getString(PASSWORD_USER);

            // check if password is the same of the database
            if(password.equals(passwordTyped))
            {
                return true;
            }else
            {
                return false;
            }

        } catch (JSONException exception)
        {
            exception.printStackTrace();
            return false;
        }

    }

    private final String INVALID_USERNAME_MESSAGE = "Ops, acho que você digitou o login errado";

    public String getInvalidUsernameMessage() 
    {
        return INVALID_USERNAME_MESSAGE;
    }

    private final String INVALID_PASSWORD_MESSAGE = "Ops, acho que você digitou a senha errada";

    public String getInvalidPasswordMessage() 
    {
        return INVALID_PASSWORD_MESSAGE;
    }

}