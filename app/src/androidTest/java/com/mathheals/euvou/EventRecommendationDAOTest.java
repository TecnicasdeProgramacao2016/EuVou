/*
* File name: EventRecomendationDAOTest.
* File pourpose: Test eventRecommendation's DOA.
* Created by: igor on 29/11/15.
* Edited by: bernardohrl on 10/09/16
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
        EventRecommendationDAO eventRecommendationDAO = new EventRecommendationDAO();

        JSONObject jsonObject = eventRecommendationDAO.recommendEvents(3);

        boolean check;
        try
        {
            jsonObject.getJSONObject("0").getString("nameEvent");

            check = true;

        } catch (JSONException e) {
            check = false;

            e.printStackTrace();
        }

        assertTrue(check);
    }
}
