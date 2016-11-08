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

    //Instance user's atributtes values
    private UserEvaluation userEvaluation = null; //User evaluation
    private final String SUCCESSFULL_EVALUATION_MESSAGE = "Avaliação cadastrada com sucesso"; //Constant do indicate success
    private RatingBar ratingBar = null; //User rate ba
    private View showUserView = null; //User view
    private String userEvaluatedId = null; //Id of user's evaluation
    private int currentUserId = 0;  //This variable has the pourpose to check if current user is logged, as it compares with LOGGED_OUT
    private static boolean isUserLoggedIn = true; //Constant to indicate if user is logged in
    private TextView ratingMessage = null;  //Rate text display
    private final Integer LOGGED_OUT = -1; //Constant to indicate if user is logged out



    public ShowUser()
    {
        //Required Empty Constructor
    }


    /*
     * Creates a view for user's information, using user's current ID gets the necessary
     * data from DB and displays it
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Sets User's values
        setShowUserView(inflater.inflate(R.layout.show_user, container, false));

        //Tests User's Id
        if(currentUserId < 0)
            Log.d("ShowUser", "Invalid User ID");

        setCurrentUserId(new LoginUtility(getActivity()).getUserId());
        setIsUserLoggedIn(currentUserId != LOGGED_OUT);
        setRatingMessage(isUserLoggedIn);
        setRatingBarIfNeeded();


        //Gets values to create the view
        userEvaluatedId=this.getArguments().getString("id");
        getDBValuesToSetView();

        Log.d("ShowUser", "User logged in");

        return showUserView;
    }

    private void getDBValuesToSetView()
    {
        UserDAO userDAO = new UserDAO(getActivity());
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
            //Gets atributte's values
            String nameUserDB = userData.getJSONObject("0").getString("nameUser");
            String birthDateDB = userData.getJSONObject("0").getString("birthDate");
            String mailDB = userData.getJSONObject("0").getString("email");

            //Instance texts values for the view
            TextView name = (TextView) showUserView.findViewById(R.id.labelName);
            TextView date = (TextView) showUserView.findViewById(R.id.labelBirthDate);
            TextView mail = (TextView) showUserView.findViewById(R.id.labelMail);

            //Sets texts values
            name.setText(nameUserDB);
            date.setText(birthDateDB);
            mail.setText(mailDB);

        } catch (JSONException jsonException)
        {
            jsonException.printStackTrace();
        }
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

        checkRateBar(ratingBar);

        UserEvaluationDAO userEvaluationDAO = new UserEvaluationDAO();

        JSONObject evaluationJSON = (JSONObject) userEvaluationDAO.searchUserEvaluation(Integer.
                                                    parseInt(userEvaluatedId), currentUserId);

        settingRateBarEvaluation(evaluationJSON, ratingBar);

        //This line ckecks if the status are different to change them
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

    //Sets evaluation bar
    private void settingRateBarEvaluation(final JSONObject evaluationJSON, final RatingBar ratingBar)
    {
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
    }

    private void checkRateBar(final RatingBar ratingBar)
    {
        if(ratingBar == null)
        {
            Log.d("ShowUser", "Rating bar is null");
        }
        else
        {
            //NOTHING TO DO
        }
    }

    //Set atribute Rating bar Style
    private void setRatingBarStyle()
    {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getContext(), R.color.turquesa_app),
                                                                    PorterDuff.Mode.SRC_ATOP);
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
        finilizeObject(message);

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
            //This if-else structure tests if the information given is correct
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

    //Free object's memory to make it easyer to the garbage collector get it
    private void finilizeObject(Object object)
    {
        object = null;
    }
}
