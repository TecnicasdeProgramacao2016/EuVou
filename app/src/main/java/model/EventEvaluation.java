/*
* File name: EventEvaluation.
* File pourpose: Sets Event Evaluation data.
*/

package model;

import java.util.logging.Logger;
import exception.EventEvaluationException;

public class EventEvaluation
{
    private Float rating = null;
    private Integer userId = null;
    private Integer eventId = null;
    private Logger logger = null;
    public static final String EVALUATION_IS_INVALID = "A avaliação deve estar entre 0 e 5";
    public static final String USER_ID_IS_INVALID = "O identificador do usuário é inválido";
    public static final String EVENT_ID_IS_INVALID = "O identificador do evento é inválido";


    public EventEvaluation(Float rating, Integer userId, Integer eventId) throws EventEvaluationException
    {
        setRating(rating);
        setUserId(userId);
        setEventId(eventId);
    }

    public Float getRating()
    {
        return rating;
    }

    public void setRating(Float rating) throws EventEvaluationException
    {
        if(rating >= 0f && rating <= 5f)
        {
            this.rating = rating;
            logger.info("Rating has been set.");
        }
        else
        {
            throw new EventEvaluationException(EVALUATION_IS_INVALID);
        }
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId) throws EventEvaluationException
    {
        if(userId <= Integer.MAX_VALUE && userId >= 1)
        {
            this.userId = userId;
            logger.info("userId has been set.");
        }
        else
        {
            throw new EventEvaluationException(USER_ID_IS_INVALID);
        }
    }

    public Integer getEventId()
    {
        return eventId;
    }

    public void setEventId(Integer eventId) throws EventEvaluationException
    {
        if(eventId <= Integer.MAX_VALUE && eventId >= 1)
        {
            this.eventId = eventId;
            logger.info("Event Id has been set.");
        }
        else
        {
            throw new EventEvaluationException(EVENT_ID_IS_INVALID);
        }
    }
}
