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

    private EventDAO eventDAO;

    public void testIfEventNameIsBeingShown()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean isNameValid = true;
        try
        {
            String nameEventDB = eventData.getJSONObject("0").getString("nameEvent");
            assertEquals("Tes",nameEventDB);
        }catch(JSONException ex)
        {
            isNameValid = false;
        }catch(NullPointerException exception)
        {
            isNameValid = false;
        }
        assertTrue(isNameValid);
        finalize(isNameValid);
    }

    public void testIfEventDescriptionIsBeingShown()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean isDescriptionValid = true;
        try
        {
            String eventDescription = eventData.getJSONObject("0").getString("description");
            assertEquals("evento Teste",eventDescription);
        }catch(JSONException ex)
        {
            isDescriptionValid = false;
        }catch(NullPointerException exception)
        {
            isDescriptionValid = false;
        }
        assertTrue(isDescriptionValid);
        finalize(isDescriptionValid);
    }

    public void testIfEventDateIsBeingShown()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean isDateValid = true;
        try
        {
            String eventDateTime = eventData.getJSONObject("0").getString("dateTimeEvent");
            assertEquals("2016-02-29 00:00:00",eventDateTime.toString());
        }catch(JSONException ex)
        {
            isDateValid = false;
        }catch(NullPointerException exception)
        {
            isDateValid = false;
        }
        assertTrue(isDateValid);
        finalize(isDateValid);
    }

    public void testIfEventLatitudeIsBeingShown()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean isLatitudeValid = true;
        try
        {
            String eventlatitude = eventData.getJSONObject("0").getString("latitude");
            assertEquals(23.342300,Double.parseDouble(eventlatitude));
        }catch(JSONException ex)
        {
            isLatitudeValid = false;
        }catch(NullPointerException exception)
        {
            isLatitudeValid = false;
        }
        assertTrue(isLatitudeValid);
        finalize(isLatitudeValid);
    }

    public void testIfEventLongitudeIsBeingShown()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(2);
        boolean isEventLongitude = true;
        try
        {
            String eventlongitude = eventData.getJSONObject("0").getString("longitude");
            assertEquals(12.121300,Double.parseDouble(eventlongitude));
        }catch(JSONException ex)
        {
            isEventLongitude = false;
        }catch(NullPointerException exception)
        {
            isEventLongitude = false;
        }
        assertTrue(isEventLongitude);
        finalize(isEventLongitude);
    }

    public void testIfEventPriceIsBeingShown()
    {
        EventDAO eventDAO = new EventDAO();
        JSONObject eventData = (JSONObject) eventDAO.searchEventById(1);
        boolean isPriceValid = true;
        try{
            String eventPrice = eventData.getJSONObject("0").getString("price");
            assertEquals(10010,Integer.parseInt(eventPrice));
        }catch(JSONException ex)
        {
            isPriceValid = false;
        }
        assertTrue(isPriceValid);
        finalize(isPriceValid);
    }

    private void finalize(boolean isValid)
    {
        this.eventDAO = null;
    }
}
