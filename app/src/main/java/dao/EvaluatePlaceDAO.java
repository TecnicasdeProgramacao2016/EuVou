/**
 * file:EvaluatePlaceDAO.java
 * purpose:class to execute the database conection to the class EvaluatePlace
 */
package dao;

import android.app.Activity;

import org.json.JSONObject;

import model.Evaluation;


public class EvaluatePlaceDAO extends DAO
{

    public EvaluatePlaceDAO()
    {

    }

    public EvaluatePlaceDAO(Activity currentActivity)
    {
        super(currentActivity);
    }

    public void evaluatePlace(Evaluation evaluation)
    {
        final String QUERY;

        JSONObject findEvaluation = executeConsult("SELECT * FROM evaluate_place WHERE idPlace = \"" +
                                                    evaluation.getIdPlace() + "\" " +
                                                    "AND idUser = \"" + evaluation.getIdUser() + "\"");

        if(findEvaluation==null)
        {
            QUERY = "INSERT INTO evaluate_place(grade, idUser, idPlace) VALUES (\"" + evaluation.getgrade() +
                                                                                "\"," + "\"" + evaluation.getIdUser() +
                                                                                "\"," + "\"" + evaluation.getIdPlace() +
                                                                                "\")";
        }else
        {
            QUERY = "UPDATE evaluate_place SET grade = \"" +evaluation.getgrade() + "\" " +
                    "WHERE idPlace = \"" + evaluation.getIdPlace() + "\" AND idUser = \"" +
                    evaluation.getIdUser() + "\"";
        }

        executeQuery(QUERY);
    }


    public JSONObject searchPlaceEvaluation(int placeId, int userId)
    {
        final String QUERY = "SELECT * FROM evaluate_place WHERE idUser = \"" + userId
                            + "\" AND idPlace = " + placeId;
        return executeConsult(QUERY);
    }
}
