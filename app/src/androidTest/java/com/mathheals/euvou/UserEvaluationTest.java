package com.mathheals.euvou;

import junit.framework.TestCase;

import java.text.ParseException;

import exception.UserEvaluationException;
import exception.UserException;
import model.User;
import model.UserEvaluation;

 /**
  * File name: UserEvaluationTest.
  * File pourpose: Test user's evaluation with true and falses parameters.
 */

public class UserEvaluationTest extends TestCase
{

    private UserEvaluation userEvaluation;

    // Tests with valids Parameters
    public void testUserEvaluationWithValidParameters()
    {
        boolean isUserEvaluationValid;

        try
        {
            userEvaluation = new UserEvaluation(3f, 3, 1);
            isUserEvaluationValid = true;
        } catch (UserEvaluationException userEvaluationException)
        {
            isUserEvaluationValid = false;
        }

        assertTrue(isUserEvaluationValid);
        finalizeUserEvaluation(isUserEvaluationValid);

    }


    // Tests with invalids Id's
    public void testUserEvaluationWithInvalidUserId()
    {
        boolean isUserEvaluationValid;

        try
        {
            userEvaluation = new UserEvaluation(3f, -3, 1);
            isUserEvaluationValid = true;
        } catch (UserEvaluationException userEvaluationException)
        {
            isUserEvaluationValid = false;
        }

        assertFalse(isUserEvaluationValid);
        finalizeUserEvaluation(isUserEvaluationValid);

    }

    public void testUserEvaluationWithInvalidUserEvaluatedId()
    {
        boolean isUserEvaluationValid;

        try
        {
            userEvaluation = new UserEvaluation(3f, 3, -1);
            isUserEvaluationValid = true;
        } catch (UserEvaluationException userEvaluationException)
        {
            isUserEvaluationValid = false;
        }

        assertFalse(isUserEvaluationValid);
        finalizeUserEvaluation(isUserEvaluationValid);

    }

    //Tests with values ​​outside the range

    public void testUserEvaluationWithEvaluationBiggerThanFive() 
    {
        boolean isUserEvaluationValid;

        try 
        {
            userEvaluation = new UserEvaluation(6f, 3, 1);
            isUserEvaluationValid = true;
        } catch (UserEvaluationException userEvaluationException)
        {
            isUserEvaluationValid = false;
        }

        assertFalse(isUserEvaluationValid);
        finalizeUserEvaluation(isUserEvaluationValid);
    }

    public void testUserEvaluationWithNegativeRating()
    {
        boolean isUserEvaluationValid;

        try
        {
            userEvaluation = new UserEvaluation(3f, -3, 1);
            isUserEvaluationValid = true;
        } catch (UserEvaluationException userEvaluationException)
        {
            isUserEvaluationValid = false;
        }

        assertFalse(isUserEvaluationValid);
        finalizeUserEvaluation(isUserEvaluationValid);

    }

    private void finalizeUserEvaluation(boolean isUserEvaluationValid) {
        this.userEvaluation = null;
    }

}
