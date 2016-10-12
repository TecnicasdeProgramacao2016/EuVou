/*
* File name: UserEvaluation.
* File pourpose: Sets User Evaluation data.
*/

package model;

import android.util.Log;
import exception.UserEvaluationException;

/**
*Class: public class UserEvaluation
*Description: Class of user Evaluation
*/
public class UserEvaluation
{
    private Float rating = null;
    private Integer userId = null; //has to be above 0
    private Integer userEvaluatedId = null; //has to be above 0
    public static final String EVALUATION_IS_INVALID = "Avaliação deve estar entre 0 e 5";
    public static final String USER_ID_IS_INVALID = "O identificador do usuário é inválido";
    public static final String USER_EVALUATED_ID_IS_INVALID = "O identificador do usuário avaliado é inválido";

    /**
    *Method: public UserEvaluation(Float rating, Integer userId,
    *                              Integer userEvaluatedId) throws UserEvaluationException
    *Description: sets data on fields of user evaluation
    *@param rating
    *@param userId has to be above 0
    *@param userEvaluatedId
    */
    public UserEvaluation(Float rating, Integer userId,
                          Integer userEvaluatedId) throws UserEvaluationException
    {
        setRating(rating);
        setUserId(userId);
        setUserEvaluatedId(userEvaluatedId);
    }

    /**
    *Method: public void setRating(Float rating) throws UserEvaluationException
    *Description: sets rating
    *@param rating
    */
    public void setRating(Float rating) throws UserEvaluationException
    {
        final float minRating = 0f;
        final float maxRating = 5f;
        if(rating >= minRating && rating <= maxRating)
        {
            this.rating = rating;
        }
        else
        {
            throw new UserEvaluationException(EVALUATION_IS_INVALID);
        }
        Log.d("UserEvaluation", "rating has been set");
    }

    /**
    *Method: public void setUserId(Integer userId) throws UserEvaluationException
    *Description: sets user if
    *@param userId
    */
    public void setUserId(Integer userId) throws UserEvaluationException
    {
        assert(userId > 0);
        final int validId = 1;
        if(userId <= Integer.MAX_VALUE && userId >= validId)
        {
            this.userId = userId;
        }
        else
        {
            throw new UserEvaluationException(USER_ID_IS_INVALID);
        }
        Log.d("UserEvaluation", "idUser has been set");
    }

    /**
    *Method: public void setUserEvaluatedId(Integer userEvaluatedId) throws UserEvaluationException
    *Description: sets user evaluated id
    *@param userEvaluatedId
    */
    public void setUserEvaluatedId(Integer userEvaluatedId) throws UserEvaluationException
    {
        final int validId = 1;
        if(userEvaluatedId <= Integer.MAX_VALUE && userEvaluatedId >= validId)
        {
            this.userEvaluatedId = userEvaluatedId;
        }
        else
        {
            throw new UserEvaluationException(USER_EVALUATED_ID_IS_INVALID);
        }
        Log.d("UserEvaluation", "userEvaluatedId has been set");
    }

    /**
    *Method: public Float getRating()
    *Description: gets Rating
    */
    public Float getRating()
    {
        return rating;
    }

    /**
    *Method: public public Integer getUserEvaluatedId()
    *Description: gets user evaluated id
    */
    public Integer getUserEvaluatedId()
    {
        return userEvaluatedId;
    }

    /**
    *Method:  public Integer getUserId()
    *Description: gets user id
    */
    public Integer getUserId()
    {
        return userId;
    }
}
