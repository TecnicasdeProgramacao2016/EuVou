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
    /**
     * testing if the user name is correct
     */
    private void testShowUserName()
    {

        UserDAO userDAO = new UserDAO();

        assert(userDAO != null);

        JSONObject userData = (JSONObject )userDAO.searchUserByUsername("igodudu");

        try
        {
            String nameUserDB = (String) userData.getJSONObject("0").getString("nameUser");

            assertEquals("Igor Duarte",nameUserDB);
        } catch (JSONException exception)
        {
            exception.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }

    /**
     * testing if the user's birthday is correct
     */
    private void testShowUserBirthDate()
    {
        UserDAO userDAO = new UserDAO();

        assert(userDAO != null);

        JSONObject userData = (JSONObject) userDAO.searchUserByUsername("igodudu");

        try
        {
             String birthDateDB = (String) userData.getJSONObject("0").getString("birthDate");

            assertEquals("1995-11-14",birthDateDB);
        } catch (JSONException e)
        {
            e.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }

    /**
     * testing if the user's e-mail is correct
     */
    private void testShowUserEmail()
    {
        UserDAO userDAO = new UserDAO();
        assert(userDAO != null);

        JSONObject userData = (JSONObject) userDAO.searchUserByUsername("igodudu");

        try
        {
            String mailDB = (String) userData.getJSONObject("0").getString("email");

            assertEquals("igor-ribeiro@hotmail.com",mailDB);
        } catch (JSONException exception)
        {
            exception.printStackTrace();
        } catch(NullPointerException except)
        {

        }

    }
}
