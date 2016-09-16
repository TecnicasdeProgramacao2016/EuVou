package model;

import exception.EventEvaluationException;
import exception.UserEvaluationException;

public class UserEvaluation
{
    private Float rating = null;
    private Integer userId = null;
    private Integer userEvaluatedId = null;
    public static final String EVALUATION_IS_INVALID = "Avaliação deve estar entre 0 e 5";
    public static final String USER_ID_IS_INVALID = "O identificador do usuário é inválido";
    public static final String USER_EVALUATED_ID_IS_INVALID = "O identificador do usuário avaliado é inválido";

    public UserEvaluation(Float rating, Integer userId,
                          Integer userEvaluatedId) throws UserEvaluationException
    {
        setRating(rating);
        setUserId(userId);
        setUserEvaluatedId(userEvaluatedId);
    }

    public void setRating(Float rating) throws UserEvaluationException
    {
        if(rating >= 0f && rating <= 5f)
        {
            this.rating = rating;
        }
        else
        {
            throw new UserEvaluationException(EVALUATION_IS_INVALID);
        }
    }

    public void setUserId(Integer userId) throws UserEvaluationException
    {
        if(userId <= Integer.MAX_VALUE && userId >= 1)
        {
            this.userId = userId;
        }
        else
        {
            throw new UserEvaluationException(USER_ID_IS_INVALID);
        }
    }

    public void setUserEvaluatedId(Integer userEvaluatedId) throws UserEvaluationException
    {
        if(userEvaluatedId <= Integer.MAX_VALUE && userEvaluatedId >= 1)
        {
            this.userEvaluatedId = userEvaluatedId;
        }
        else
        {
            throw new UserEvaluationException(USER_EVALUATED_ID_IS_INVALID);
        }
    }

    public Float getRating()
    {
        return rating;
    }

    public Integer getUserEvaluatedId()
    {
        return userEvaluatedId;
    }

    public Integer getUserId()
    {
        return userId;
    }
}
