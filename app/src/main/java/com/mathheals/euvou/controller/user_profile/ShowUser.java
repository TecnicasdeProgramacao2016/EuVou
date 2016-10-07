/*
* File name: Show User.
* File pourpose: Show user's information.
*/


package com.mathheals.euvou.controller.user_profile;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;
import dao.UserEvaluationDAO;
import exception.UserEvaluationException;
import model.UserEvaluation;

public class ShowUser extends android.support.v4.app.Fragment {
    private UserEvaluation userEvaluation = null;
    private final String SUCCESSFULL_EVALUATION_MESSAGE = "Avaliação cadastrada com sucesso";
    private RatingBar ratingBar = null;
    private View showUserView = null;
    private String userEvaluatedId = null;
    private int currentUserId = 0;
    private boolean isUserLoggedIn = true;
    private TextView ratingMessage = null;
    private final Integer LOGGED_OUT = -1;


    public ShowUser()
    {
        //Required Empty Constructor
    }


    //Create user's view
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        setShowUserView(inflater.inflate(R.layout.show_user, container, false));
        UserDAO userDAO = new UserDAO(getActivity());

        userEvaluatedId=this.getArguments().getString("id");
        setCurrentUserId(new LoginUtility(getActivity()).getUserId());
        JSONObject userData = null;
        try
        {
            userData = new JSONObject(userDAO.searchUserById(Integer.parseInt(userEvaluatedId)));
        } catch (JSONException jsonException)
        {
            jsonException.printStackTrace();
        }

        try
        {
            String nameUserDB = userData.getJSONObject("0").getString("nameUser");
            String birthDateDB = userData.getJSONObject("0").getString("birthDate");
            String mailDB = userData.getJSONObject("0").getString("email");

            TextView name= (TextView) showUserView.findViewById(R.id.labelName);
            TextView date = (TextView) showUserView.findViewById(R.id.labelBirthDate);
            TextView mail = (TextView) showUserView.findViewById(R.id.labelMail);
            name.setText(nameUserDB);
            date.setText(birthDateDB);
            mail.setText(mailDB);

        } catch (JSONException jsonException)
        {
            jsonException.printStackTrace();
        }

        setIsUserLoggedIn(currentUserId != LOGGED_OUT);
        setRatingMessage(isUserLoggedIn);
        setRatingBarIfNeeded();

        Log.d("ShowUser", "User logged in");

        return showUserView;
    }


    //Set user's loggin status
    public void setIsUserLoggedIn(boolean isUserLoggedIn)
    {
        this.isUserLoggedIn = isUserLoggedIn;
    }


    /**
     * Method:  private void setRatingBar()
     * Rating bar is setted as user's wish using user's evaluation
     * @param
     */

    //Set Atribute Rate
    private void setRatingBar()
    {
        ratingBar = (RatingBar) showUserView.findViewById(R.id.ratingBar);
        ratingBar.setVisibility(View.VISIBLE);

        if(ratingBar == null)
        {
            Log.d("ShowUser", "Rating bar is null");
        }
        else
        {
            //NOTHING TO DO
        }

        UserEvaluationDAO userEvaluationDAO = new UserEvaluationDAO();

        JSONObject evaluationJSON = (JSONObject) userEvaluationDAO.searchUserEvaluation(Integer.parseInt(userEvaluatedId), currentUserId);

        if(evaluationJSON!=null)
        {
            Float evaluation = null;
            try
            {
                evaluation = new Float(evaluationJSON.getJSONObject("0").getDouble("grade"));
            } catch (JSONException e)
            {
                e.printStackTrace();
            }

            ratingBar.setRating(evaluation);
        }
        else
        {
            //NOTHING TO DO
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.
                OnRatingBarChangeListener()
                {
                     @Override
                     public void onRatingChanged(RatingBar arg0, float rateValue, boolean arg2)
                     {
                        setUserEvaluation(rateValue, currentUserId, new Integer(userEvaluatedId));

                         UserEvaluationDAO userEvaluationDAO = new UserEvaluationDAO();

                       userEvaluationDAO.evaluateUser(getUserEvaluation());
                     }
                }
        );
        setRatingBarStyle();
        Log.d("ShowUser", "Rating bar setted");
    }

    //Set atribute Rating bar Style
    private void setRatingBarStyle()
    {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getContext(), R.color.turquesa_app), PorterDuff.Mode.SRC_ATOP);
    }

    public UserEvaluation getUserEvaluation()
    {
        return userEvaluation;
    }

    //Set rate messages
    private void setRatingMessage(boolean isUserLoggedIn)
    {
        final String LOGGED_IN_MESSAGE = "Sua avaliação:";
        final String LOGGED_OUT_MESSAGE = "Faça login para avaliar este usuário!";
        String message = isUserLoggedIn ? LOGGED_IN_MESSAGE : LOGGED_OUT_MESSAGE;

        ratingMessage = (TextView) showUserView.findViewById(R.id.rate_user_text);
        ratingMessage.setText(message);

        Log.d("ShowUser", "Setted user status as logged in");
    }


    public void setShowUserView(View showUserView)
    {
        this.showUserView = showUserView;
    }


    //Set rate bar
    private void setRatingBarIfNeeded()
    {
        if(isUserLoggedIn)
        {
            setRatingBar();
        }
        else
        {
            //NOTING TO DO
        }
    }

    public void setCurrentUserId(int currentUserId)
    {
        this.currentUserId = currentUserId;
    }


    //Set atributte User Evaluation
    public void setUserEvaluation(Float rating, Integer userId, Integer userEvaluatedId)
    {
        try
        {
            this.userEvaluation = new UserEvaluation(rating, userId, userEvaluatedId);
            Toast.makeText(getActivity().getBaseContext(), SUCCESSFULL_EVALUATION_MESSAGE, Toast.LENGTH_LONG).show();
            Log.d("ShowUser", "Users evaluation setted");
        }
        catch (UserEvaluationException exception)
        {
            if(exception.getMessage()==UserEvaluation.EVALUATION_IS_INVALID)
            {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
            else
            {
                //NOTING TO DO
            }
            if(exception.getMessage()==UserEvaluation.USER_EVALUATED_ID_IS_INVALID)
            {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
            else
            {
                //NOTING TO DO
            }

            if(exception.getMessage()==UserEvaluation.USER_ID_IS_INVALID)
            {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
            else
            {
                //NOTING TO DO
            }
        }
    }
}
