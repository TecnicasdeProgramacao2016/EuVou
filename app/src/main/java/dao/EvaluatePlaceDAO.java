/**
 * file:EvaluatePlaceDAO.java
 * purpose:class to execute the database conection to the class EvaluatePlace
 */
package dao;

import android.app.Activity;
import android.util.Log;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.Evaluation;


public class EvaluatePlaceDAO extends DAO
{
    private final static Logger logger = Logger.getLogger(EvaluatePlaceDAO.class.getName()); //atribute to use loggin system
    public EvaluatePlaceDAO()
    {

    }

    public EvaluatePlaceDAO(Activity currentActivity)
    {
        super(currentActivity);
    }

    public void evaluatePlace(Evaluation evaluation)
    {
        assert(evaluation != null);
        logger.log(Level.INFO,"entered in the method that saves the place evaluation");



        final String QUERY;

        JSONObject findEvaluation = (JSONObject) executeConsult("SELECT * FROM evaluate_place WHERE idPlace = \"" +
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
        assert(placeId > 0);
        assert(userId > 0);

        logger.log(Level.INFO,"entered in the method that searches the place by the evaluation");
        final String QUERY = "SELECT * FROM evaluate_place WHERE idUser = \"" + userId
                            + "\" AND idPlace = " + placeId;
        return executeConsult(QUERY);
    }
}
