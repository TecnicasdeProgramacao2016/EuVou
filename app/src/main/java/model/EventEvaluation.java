/*
* File name: EventEvaluation.
* File pourpose: Sets Event Evaluation data.
*/

package model;

import exception.EventEvaluationException;

/**
*Class: public class EventEvaluation
*Description: Class to evaluate event
*/
public class EventEvaluation
{
    private Float rating = null;
    private Integer userId = null;
    private Integer eventId = null;
    public static final String EVALUATION_IS_INVALID = "A avaliação deve estar entre 0 e 5";
    public static final String USER_ID_IS_INVALID = "O identificador do usuário é inválido";
    public static final String EVENT_ID_IS_INVALID = "O identificador do evento é inválido";

    /**
    *Method: public EventEvaluation(Float rating, Integer userId, Integer eventId) throws EventEvaluationException
    *Description: sets fields of evaluate an event
    *@param Float rating
    *@param Integer userId
    *@param Integer eventId
    */
    public EventEvaluation(Float rating, Integer userId, Integer eventId) throws EventEvaluationException
    {
        setRating(rating);
        setUserId(userId);
        setEventId(eventId);
    }

    /**
    *Method: public Float getRating()
    *Description: gets rating
    */
    public Float getRating()
    {
        return rating;
    }

    /**
    *Method: public void setRating(Float rating) throws EventEvaluationException
    *Description: sets rating
    *@param Float rating
    */
    public void setRating(Float rating) throws EventEvaluationException
    {
        if(rating >= 0f && rating <= 5f)
        {
            this.rating = rating;
        }
        else
        {
            throw new EventEvaluationException(EVALUATION_IS_INVALID);
        }
    }

    /**
    *Method: public Integer getUserId()
    *Description: gets uder id
    */
    public Integer getUserId()
    {
        return userId;
    }

    /**
    *Method: public void setUserId(Integer userId) throws EventEvaluationException
    *Description: sets id of user
    *@param Integer userId
    */
    public void setUserId(Integer userId) throws EventEvaluationException
    {
        if(userId <= Integer.MAX_VALUE && userId >= 1)
        {
            this.userId = userId;
        }
        else
        {
            throw new EventEvaluationException(USER_ID_IS_INVALID);
        }
    }

    /**
    *Method: public Integer getEventId()
    *Description: get event by id
    */
    public Integer getEventId()
    {
        return eventId;
    }

    /**
    *Method: public void setEventId(Integer eventId) throws EventEvaluationException
    *Description: sets id of event
    *@param Integer eventId
    */
    public void setEventId(Integer eventId) throws EventEvaluationException
    {
        if(eventId <= Integer.MAX_VALUE && eventId >= 1)
        {
            this.eventId = eventId;
        }
        else
        {
            throw new EventEvaluationException(EVENT_ID_IS_INVALID);
        }
    }
}
