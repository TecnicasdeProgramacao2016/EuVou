package com.mathheals.euvou.controller.utility;

import android.app.Activity;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import dao.UserDAO;
import exception.UserException;
import model.User;

/*
 * File name: LoginUtility.
 * File pourpose: This file have the pourpose to catch and operate some important informations about the login
 */

public class LoginUtility 
{
    
    private Activity activity = null;
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor = null;

    public LoginUtility(Activity activity) 
    {
        this.activity = activity;
        sharedPreferences = activity.getSharedPreferences(ID_FIELD, activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public LoginUtility(){}
    
    private static final int LOGGED_OUT = -1;

    public boolean hasUserLoggedIn() 
    {
        return getUserId() != LOGGED_OUT;
    }

    private static final String COLUMN_USER_ID = "idUser";

    // gets user's ID by username. OBS: IT'S ASSUMED THAT USERNAME DOES EXIST
    public int getUserId(String username) throws org.json.JSONException
    {
        UserDAO userDAO = new UserDAO(this.activity);
        JSONObject jsonObject = (JSONObject) userDAO.searchUserByUsername(username);
        return Integer.parseInt(jsonObject.getJSONObject("0").getString(COLUMN_USER_ID));
    }

    private static final String COLUMN_USER_NAME = "nameUser";
    private static final String COLUMN_USER_LOGIN = "login";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "passwordUser";
    private static final String COLUMN_USER_BIRTHDATE = "birthDate";

    public User getUser(String username)
    {
        User user=null;
        try 
        {
            UserDAO userDAO = new UserDAO(this.activity);
            JSONObject jsonObject = (JSONObject) userDAO.searchUserByUsername(username);

            user = new User(jsonObject.getJSONObject("0").getString(COLUMN_USER_NAME),
                    jsonObject.getJSONObject("0").getString(COLUMN_USER_LOGIN),
                    jsonObject.getJSONObject("0").getString(COLUMN_USER_EMAIL),
                    jsonObject.getJSONObject("0").getString(COLUMN_USER_PASSWORD),
                    formatDateToBr(jsonObject.getJSONObject("0").getString(COLUMN_USER_BIRTHDATE)));
            return user;

        } catch (ParseException exception)
        {
            exception.printStackTrace();
        } catch (UserException exception)
        {
            exception.printStackTrace();
        } catch (JSONException exception)
        {
            exception.printStackTrace();
        }

        return user;
    }

    // gets current user's ID
    public int getUserId() 
    {
        SharedPreferences sharedId = activity.getSharedPreferences(ID_FIELD, activity.MODE_PRIVATE);
        return sharedId.getInt(ID_FIELD, LOGGED_OUT);
    }

    public void setUserLogIn(int userId) 
    {
        editor.putInt(ID_FIELD, userId);
        editor.commit();
    }

    private static final String ID_FIELD =  "idField";

    public void setUserLogOff() 
    {
        editor.putInt(ID_FIELD, LOGGED_OUT);
        editor.commit();
    }

    private static final String COLUMN_USER_STATE = "isActivity";

    public boolean isUserActive(String username) 
    {
        UserDAO userDAO = new UserDAO(this.activity);
        JSONObject jsonObject = userDAO.searchUserByUsername(username);
        String userState = null;
        try 
        {
            userState = jsonObject.getJSONObject("0").getString(COLUMN_USER_STATE);
            return userState == "Y";
        } catch (JSONException e) 
        {
            e.printStackTrace();
        }
        return false;
    }

    public String formatDateToBr(String birthDate)
    {
        String[] birthDateSplit = birthDate.split("-");
        return birthDateSplit[2]+"/"+birthDateSplit[1]+"/"+birthDateSplit[0];
    }

}

