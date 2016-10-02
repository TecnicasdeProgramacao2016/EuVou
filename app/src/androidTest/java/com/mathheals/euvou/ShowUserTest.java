package com.mathheals.euvou;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;


public class ShowUserTest extends TestCase
{

    public void testShowUserName()
    {

        UserDAO userDAO = new UserDAO();

        JSONObject userData = userDAO.searchUserByUsername("igodudu");

        try
        {
            String nameUserDB = userData.getJSONObject("0").getString("nameUser");

            assertEquals("Igor Duarte",nameUserDB);
        } catch (JSONException e)
        {
            e.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }
    public void testShowUserBirthDate()
    {
        UserDAO userDAO = new UserDAO();

        JSONObject userData = userDAO.searchUserByUsername("igodudu");

        try
        {
             String birthDateDB = userData.getJSONObject("0").getString("birthDate");

            assertEquals("1995-11-14",birthDateDB);
        } catch (JSONException e)
        {
            e.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }
    public void testShowUserEmail()
    {
        UserDAO userDAO = new UserDAO();

        JSONObject userData = userDAO.searchUserByUsername("igodudu");

        try
        {
            String mailDB = userData.getJSONObject("0").getString("email");

            assertEquals("igor-ribeiro@hotmail.com",mailDB);
        } catch (JSONException exception)
        {
            exception.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }
}
