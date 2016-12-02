package com.mathheals.euvou;
import android.app.Activity;
import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;
import dao.EventDAO;
import static junit.framework.Assert.assertEquals;

/**
  * File name: ShowEventTest.
  * File pourpose: This file have the pourpose to test the events with true and falses parameters.
 */

public class ShowEventTest extends TestCase 
{

    public void testEventName()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean ok = true;
        try
        {
            String nameEventDB = eventData.getJSONObject("0").getString("nameEvent");
            assertEquals("Tes",nameEventDB);
        }catch(JSONException ex)
        {
            ok = false;
        }catch(NullPointerException exception)
        {
            ok = false;
        }
        assertTrue(ok);
    }
    public void testEventDescription()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean ok = true;
        try
        {
            String eventDescription = eventData.getJSONObject("0").getString("description");
            assertEquals("evento Teste",eventDescription);
        }catch(JSONException ex)
        {
            ok = false;
        }catch(NullPointerException exception)
        {
            ok = false;
        }
        assertTrue(ok);
    }
    public void testEventDate()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean ok = true;
        try
        {
            String eventDateTime = eventData.getJSONObject("0").getString("dateTimeEvent");
            assertEquals("2016-02-29 00:00:00",eventDateTime.toString());
        }catch(JSONException ex)
        {
            ok = false;
        }catch(NullPointerException exception)
        {
            ok = false;
        }
        assertTrue(ok);
    }
    public void testEventLatitude()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean ok = true;
        try
        {
            String eventlatitude = eventData.getJSONObject("0").getString("latitude");
            assertEquals(23.342300,Double.parseDouble(eventlatitude));
        }catch(JSONException ex)
        {
            ok = false;
        }catch(NullPointerException exception)
        {
            ok = false;
        }
        assertTrue(ok);
    }
    public void testEventLongitude()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean ok = true;
        try
        {
            String eventlongitude = eventData.getJSONObject("0").getString("longitude");
            assertEquals(12.121300,Double.parseDouble(eventlongitude));
        }catch(JSONException ex)
        {
            ok = false;
        }catch(NullPointerException exception)
        {
            ok = false;
        }
        assertTrue(ok);
    }
    public void testEventPrice()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(1);
        boolean ok = true;
        try{
            String eventPrice = eventData.getJSONObject("0").getString("price");
            assertEquals(10010,Integer.parseInt(eventPrice));
        }catch(JSONException ex)
        {
            ok = false;
        }
        assertTrue(ok);
    }
}
