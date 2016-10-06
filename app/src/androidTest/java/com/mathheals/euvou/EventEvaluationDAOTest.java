/*
* File name: EventEvaluationDAOTest.
* File pourpose: Test Evaluation of Event.
*/

package com.mathheals.euvou;

import org.json.JSONException;
import org.json.JSONObject;
import dao.EventEvaluationDAO;
import exception.EventEvaluationException;
import model.EventEvaluation;
import junit.framework.TestCase;

/**
*Class: public class EventEvaluationDAOTest extends TestCase
*Description: Class to test Event Evaluation DAO
*/
public class EventEvaluationDAOTest extends TestCase
{
    /**
    *Method: public void testEvaluatePlace()
    *Description: tests evaluated place
    */
    public void testEvaluatePlace()
    {

        final Float RATING = 3.5F;
        final Integer USER_ID = 1;
        final Integer EVENT_ID = 1;


        EventEvaluationDAO eventEvaluationDAO = new EventEvaluationDAO();
        try
        {

            eventEvaluationDAO.evaluateEvent(new EventEvaluation(RATING, USER_ID, EVENT_ID));
        }catch(EventEvaluationException exception)
        {
            fail();
        }

        JSONObject jsonObject = (JSONObject) eventEvaluationDAO.searchEventEvaluation(EVENT_ID, USER_ID);
        try
        {
            Float rating = new Float(jsonObject.getJSONObject("0").getString("grade"));
            Integer userId = jsonObject.getJSONObject("0").getInt("idUser");
            Integer eventId = jsonObject.getJSONObject("0").getInt("idEvent");

            assertEquals(rating, RATING);
            assertEquals(userId, USER_ID);
            assertEquals(eventId, EVENT_ID);
        }catch (JSONException exceptionJSON)
        {
            exceptionJSON.printStackTrace();
        }
    }
}
