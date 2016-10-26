/*
* File name: EventRecomendationDAOTest.
* File pourpose: Test eventRecommendation's DOA.
*/


package com.mathheals.euvou;

import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;

import dao.EventRecommendationDAO;

public class EventRecommendationDAOTest extends TestCase
{
    //Test recommended events
    public void testRecommendEvent()
    {
        final EventRecommendationDAO eventRecommendationDAO = new EventRecommendationDAO();

        final JSONObject jsonObject = (JSONObject) eventRecommendationDAO.recommendEvents(3);

        boolean check; //Is used to check if there's an exception

        try
        {
            jsonObject.getJSONObject("0").getString("nameEvent");

            check = true;

        } catch (JSONException JsonException) {
            check = false;

            JsonException.printStackTrace();
        }

        assertTrue(check);
    }
}
