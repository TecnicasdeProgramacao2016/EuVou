/**
 * file: EventEvaluationDAO.java
 * purpose: conecting the Event Evaluation class to the database, and make operations with it.
 */
package dao;

import android.app.Activity;

import org.json.JSONObject;

import model.Evaluation;
import model.EventEvaluation;


public class EventEvaluationDAO extends DAO
{
    public EventEvaluationDAO()
    {

    }

    public EventEvaluationDAO(Activity activity)
    {
        super(activity);
    }

    public void evaluateEvent(EventEvaluation evaluation)
    {
        assert(evaluation != null);


        JSONObject findEvaluation = searchEventEvaluation(evaluation.getEventId(), evaluation.getUserId());

        String QUERY = " defaultValue";

        if(findEvaluation==null)
        {
            QUERY = "INSERT INTO participate(grade, idUser, idEvent) VALUES (\"" + evaluation.getRating() + "\"," +
                    "\"" + evaluation.getUserId() + "\"," +
                    "\"" + evaluation.getEventId() + "\")";
        }else
        {
            QUERY = "UPDATE participate SET grade = \"" +evaluation.getRating() + "\" " +
                    "WHERE idEvent = \"" + evaluation.getEventId() + "\" AND idUser = \"" + evaluation.getUserId() + "\"";
        }

        executeQuery(QUERY);
    }

    public JSONObject searchEventEvaluation(int eventId, int userId)
    {
        assert( eventId > 0);
        assert( userId > 0);

        final String QUERY = "SELECT * FROM participate WHERE idUser = \"" + userId
                            + "\" AND idEvent = " + eventId;
        return executeConsult(QUERY);
    }
}
