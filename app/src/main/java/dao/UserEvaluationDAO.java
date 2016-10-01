package dao;

import android.app.Activity;

import org.json.JSONObject;

import model.UserEvaluation;


public class UserEvaluationDAO extends DAO
{
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

        JSONObject findEvaluation = searchUserEvaluation(evaluation.getUserEvaluatedId(), evaluation.getUserId());

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

    public JSONObject searchUserEvaluation(int userEvaluatedtId, int userId)
    {
        assert(userId > 0);
        assert(userEvaluatedtId > 0);

        final String QUERY = "SELECT * FROM evaluate_user WHERE idUser = \"" + userId
                + "\" AND idUserEvaluated = " + userEvaluatedtId;
        return executeConsult(QUERY);
    }
}
