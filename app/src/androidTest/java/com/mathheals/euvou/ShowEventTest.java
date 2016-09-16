package com.mathheals.euvou;
import android.app.Activity;
import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;
import dao.EventDAO;
import static junit.framework.Assert.assertEquals;
/*
 * File name: ShowEventTest.
 * File pourpose: This file have the pourpose to test the events with true and falses parameters.
 */
public class ShowEventTest extends TestCase 
{

    public void testEventName()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = eventDAO.searchEventById(2);
        try
        {
            String nameEventDB = eventData.getJSONObject("0").getString("nameEvent");
            assertEquals("Tes",nameEventDB);
        }catch(JSONException ex)
        {
            //NOTHING TO DO
        }catch(NullPointerException exception)
        {
            //NOTHING TO DO
        }
    }
    public void testEventDescription()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = eventDAO.searchEventById(2);
        try
        {
            String eventDescription = eventData.getJSONObject("0").getString("description");
            assertEquals("evento Teste",eventDescription);
        }catch(JSONException ex)
        {
            //NOTHING TO DO
        }catch(NullPointerException exception)
        {
            //NOTHING TO DO
        }
    }
    public void testEventDate()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = eventDAO.searchEventById(2);
        try
        {
            String eventDateTime = eventData.getJSONObject("0").getString("dateTimeEvent");
            assertEquals("2016-02-29 00:00:00",eventDateTime.toString());
        }catch(JSONException ex)
        {
            //NOTHING TO DO
        }catch(NullPointerException exception)
        {
            //NOTHING TO DO
        }
    }
    public void testEventLatitude()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = eventDAO.searchEventById(2);
        try
        {
            String eventlatitude = eventData.getJSONObject("0").getString("latitude");
            assertEquals(23.342300,Double.parseDouble(eventlatitude));
        }catch(JSONException ex)
        {
            //NOTHING TO DO
        }catch(NullPointerException exception)
        {
            //NOTHING TO DO
        }
    }
    public void testEventLongitude()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = eventDAO.searchEventById(2);
        try
        {
            String eventlongitude = eventData.getJSONObject("0").getString("longitude");
            assertEquals(12.121300,Double.parseDouble(eventlongitude));
        }catch(JSONException ex)
        {
            //NOTHING TO DO
        }catch(NullPointerException exception)
        {
            //NOTHING TO DO
        }
    }
    public void testEventPrice()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = eventDAO.searchEventById(1);
        try{
            String eventPrice = eventData.getJSONObject("0").getString("price");
            assertEquals(10010,Integer.parseInt(eventPrice));
        }catch(JSONException ex)
        {
            //NOTHING TO DO
        }
    }
}
