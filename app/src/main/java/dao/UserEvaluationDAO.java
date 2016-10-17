package dao;

import android.app.Activity;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.UserEvaluation;


public class UserEvaluationDAO extends DAO
{
    private final static Logger logger = Logger.getLogger(UserEvaluationDAO.class.getName());// atribute to use loggin system
    public UserEvaluationDAO()
    {

    }

    public UserEvaluationDAO(Activity activity)
    {
        super(activity);
    }

    public void evaluateUser(UserEvaluation evaluation)
    {
        assert(evaluation != null);
        logger.log(Level.INFO,"entered in the method that evaluates the user and saves in the database");
        JSONObject findEvaluation = (JSONObject) searchUserEvaluation(evaluation.getUserEvaluatedId(), evaluation.getUserId());

        String QUERY = " ";
        if(findEvaluation==null)
        {
            QUERY = "INSERT INTO evaluate_user(grade, idUser, idUserEvaluated) VALUES (\"" + evaluation.getRating() +
                                                                                      "\"," + "\"" + evaluation.getUserId() + "\"," +
                                                                                      "\"" + evaluation.getUserEvaluatedId() + "\")";
        }else
        {
            QUERY = "UPDATE evaluate_user SET grade = \"" +evaluation.getRating() + "\" " +
                    "WHERE idUserEvaluated = \"" + evaluation.getUserEvaluatedId() + "\" " +
                    "AND idUser = \"" + evaluation.getUserId() + "\"";
        }

        executeQuery(QUERY);
    }

    public JSONObject searchUserEvaluation(final int userEvaluatedtId, final int userId)
    {
        assert(userId > 0);
        assert(userEvaluatedtId > 0);
        logger.log(Level.INFO,"entered in the method that searches the user evaluation");
        final String QUERY = "SELECT * FROM evaluate_user WHERE idUser = \"" + userId
                + "\" AND idUserEvaluated = " + userEvaluatedtId;
        return executeConsult(QUERY);
    }
}
