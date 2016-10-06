/**
  * File name: EvaluatePlaceTest.
  * File pourpose: Test class EvaluatePlace
  */

package com.mathheals.euvou;

import android.os.Bundle;
import android.util.Log;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import dao.EvaluatePlaceDAO;
import model.Evaluation;


public class EvaluatePlaceTest extends TestCase
{

    private Bundle placeInfo;

    public void testEvaluatePlace()
    {
        final float GRADE = 5.0F;
        final int ID_USER = 1;
        final int ID_PLACE = 1;

        EvaluatePlaceDAO evaluatePlaceDAO = new EvaluatePlaceDAO();
        evaluatePlaceDAO.evaluatePlace(new Evaluation(ID_PLACE, ID_USER, GRADE));
        JSONObject jsonObject = evaluatePlaceDAO.searchPlaceEvaluation(ID_PLACE, ID_USER);

        try
        {
            float grade = new Float(jsonObject.getJSONObject("0").getString("grade"));
            int userId = jsonObject.getJSONObject("0").getInt("idUser");
            int placeId = jsonObject.getJSONObject("0").getInt("idPlace");

            assertEquals(grade, GRADE);
            assertEquals(ID_USER, userId);
            assertEquals(ID_PLACE, placeId);

        }catch (JSONException exception)
        {
            exception.printStackTrace();
            Log.d("EvaluatePlaceTest","JSONException");
        }
    }
}