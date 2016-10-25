/*
* File name: ShowPlaceInfo.
* File pourpose: Show informations about place.
*/

package com.mathheals.euvou.controller.show_place;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.*;
import android.widget.TextView;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.utility.LoginUtility;
import dao.EvaluatePlaceDAO;
import exception.EventException;
import model.Evaluation;
import static junit.framework.Assert.assertFalse;

/**
 *Class: public class ShowPlaceInfo extends FragmentActivity
 *Description: Class to show information of place
 */
public class ShowPlaceInfo extends FragmentActivity
{
    private String description = "description";
    private double longitude = 0;
    private double latitude = 0;
    private String address = "nothing";
    private float grade = 0;//Has to be above 0

    //sets place informations
    private void setPlaceInfo()
    {
        Intent intent = getIntent();
        setName(intent.getStringExtra("name"));
        setPhone(intent.getStringExtra("phone"));
        setAddress(intent.getStringExtra("address"));
        setGrade(intent.getFloatExtra("grade", 0.0F));
        setDescription(intent.getStringExtra("description"));
        setLatitude(intent.getDoubleExtra("latitude", 0.0));
        setLongitude(intent.getDoubleExtra("longitude", 0.0));
        setOperation(intent.getStringExtra("operation"));
        setIdPlace(intent.getIntExtra("idPlace", 0));
    }

    private SupportMapFragment mMapFragment = null;//Map to be setted
    private Integer userId = 0;//Has to be above 0
    private boolean isUserLoggedIn;
    private final Integer LOGGED_OUT = -1;
    @Override
    /**
     *Method: protected void onCreate(Bundle savedInstanceState)
     *Description: sets values to view place
     *@param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        boolean runningOK = true;
        try
        {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_place_info);
            setShowMapButton((Button) findViewById(R.id.button_show_map));
            setHideMapButton((Button) findViewById(R.id.button_hide_map));
            setUserId(new LoginUtility(this).getUserId());
            setIsUserLoggedIn(userId != LOGGED_OUT);
            setPlaceInfo();
            setAllTextViews();
            setUpMapIfNeeded();
            mMapFragment.getView().setVisibility(View.INVISIBLE);
            setRatingMessage(isUserLoggedIn);
            setRatingBarIfNeeded();
        }
        catch(Exception exceptionOnCreate)
        {
            runningOK = false;
        }
        assertFalse(runningOK);
    }

    //If user are logged sets rating bar
    private void setRatingBarIfNeeded()
    {
        if(isUserLoggedIn)
        {
            setRatingBar();
        }
        else
        {
            assert(isUserLoggedIn == false);
        }
    }

    private int idPlace = 0;//Has to be above 0
    private RatingBar ratingBar = null;
    private Evaluation ratingEvaluation = null;
    //sets rating bar
    private void setRatingBar()
    {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setVisibility(View.VISIBLE);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar arg0, float rateValue, boolean arg2)
            {
                setRatingEvaluation(idPlace, userId, rateValue);
                EvaluatePlaceDAO evaluatePlaceDAO = new EvaluatePlaceDAO();
                evaluatePlaceDAO.evaluatePlace(ratingEvaluation);
            }
        });
        setRatingBarStyle();
    }
    //sets style to rate bar
    private void setRatingBarStyle()
    {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable(); //draw stars of rating saved before
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.turquesa_app),
                PorterDuff.Mode.SRC_ATOP);//This line puts Color on rating bar
    }

    protected GoogleMap mMap = null;
    //set map if does not exist
    private void setUpMapIfNeeded()
    {
        if (mMap == null)
        {
            mMapFragment = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id
                            .fragment_show_place_info_map));
            mMap = mMapFragment.getMap();
            if (mMap != null)
            {
                setUpMap();
            }
            else
            {
                //NOTHING TO DO
            }
        }
        else
        {
            //NOTHING TO DO
        }
    }

    //sets map
    private void setUpMap()
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(getLatitude(),
                        getLongitude()), 9));
        markPlaceOnMap();
    }

    //marks a place on map
    private void markPlaceOnMap()
    {

        mMap.addMarker(new MarkerOptions().title(getName()).snippet(getAddress())
                       .position(new LatLng(getLatitude(), getLongitude()))
        );
    }


    private Button showMapButton = null;
    private Button hideMapButton = null;
    /**
    *Method: public void showPlaceInfoOnClick(View view)
    *Description: shows informations about place
    *@param view
    */
    public void showPlaceInfoOnClick(View view)
    {
        switch(view.getId()) {
            case R.id.button_show_map:
                setUpMapIfNeeded();
                hideMapButton.setVisibility(View.VISIBLE);
                showMapButton.setVisibility(View.GONE);
                mMapFragment.getView().setVisibility(View.VISIBLE);
                break;
            case R.id.button_hide_map:
                hideMapButton.setVisibility(View.GONE);
                showMapButton.setVisibility(View.VISIBLE);
                mMapFragment.getView().setVisibility(View.GONE);
                break;
            default:
                //NOTHING TO DO
        }
    }

    private void setGrade(float grade)
    {
        this.grade = grade;
        Log.d("ShowPlaceInfo", "grade has been set");
    }

    private void setAddress(String address)
    {
        this.address = address;
        Log.d("ShowPlaceInfo", "adress has been set");
    }

    private String getAddress()
    {
        return address;
    }

    private double getLongitude()
    {
        return longitude;
    }

    private void setLongitude(double longitude)
    {
        this.longitude = longitude;
        Log.d("ShowPlaceInfo", "longitude has been set");
    }

    private void setDescription(String description)
    {
        this.description = description;
        Log.d("ShowPlaceInfo", "description been set");
    }

    private String operation = "operation";
    private void setOperation(String operation)
    {
        this.operation = operation;
        Log.d("ShowPlaceInfo", "operation has been set");
    }

    private String phone = "phone";
    private void setPhone(String phone)
    {
        this.phone = phone;
        Log.d("ShowPlaceInfo", "phone has been set");
    }

    private String name = "name";
    private String getName()
    {
        return name;
    }
    private void setName(String name)
    {
        this.name = name;
        Log.d("ShowPlaceInfo", "name has been set");
    }

    private double getLatitude()
    {
        return latitude;
    }

    private void setLatitude(double latitude)
    {
        this.latitude = latitude;
        Log.d("ShowPlaceInfo", "latitude has been set");
    }

    private TextView addressText = null;
    private void setAddressText(String adressText)
    {
        this.addressText = (TextView) findViewById(R.id.address_text);
        this.addressText.setText(adressText);
        this.addressText.setMovementMethod(new ScrollingMovementMethod());
    }

    private TextView operationText = null;
    private void setOperationText(String operationText)
    {
        this.operationText = (TextView) findViewById(R.id.operation_text);
        this.operationText.setText(operationText);
        this.operationText.setMovementMethod(new ScrollingMovementMethod());
    }

    private TextView phoneText = null;
    private void setPhoneText(String phoneText)
    {
        this.phoneText = (TextView) findViewById(R.id.phone_text);
        this.phoneText.setText(phoneText);
        Log.d("ShowPlaceInfo", "phone text has been set");
    }

    private TextView gradeText = null;
    private void setGradeText(String gradeText)
    {
        this.gradeText = (TextView) findViewById(R.id.grade_text);
        this.gradeText.setText(gradeText);
        Log.d("ShowPlaceInfo", "grade text has been set");
    }

    private TextView descriptionText = null;
    private void setDescriptionText(String descriptionText)
    {
        this.descriptionText = (TextView) findViewById(R.id.description_text);
        this.descriptionText.setText(descriptionText);
        this.descriptionText.setMovementMethod(new ScrollingMovementMethod());
        Log.d("ShowPlaceInfo", "description text been set");
    }

    private void setAllTextViews()
    {
        setAddressText(address);
        setOperationText(operation);
        setPhoneText(phone);
        setGradeText(Float.toString(grade));
        setDescriptionText(description);
    }

    private void setShowMapButton(Button showMapButton)
    {
        this.showMapButton = showMapButton;
        Log.d("ShowPlaceInfo", "button of show map has been set");
    }

    private void setHideMapButton(Button hideMapButton)
    {
        this.hideMapButton = hideMapButton;
        Log.d("ShowPlaceInfo", "hide map button has been set");
    }

    private TextView ratingMessage = null;
    private void setRatingMessage(boolean isUserLoggedIn)
    {
        String message = isUserLoggedIn ? "Sua avaliação:" : "Faça login para avaliar!";
        ratingMessage = (TextView) findViewById(R.id.rate_it_text);
        ratingMessage.setText(message);
        finalizeObject(message);
    }

    private void setUserId(int userId)
    {
        assert(userId > 0);
        this.userId = userId;
        Log.d("ShowPlaceInfo", "userId has been set");
    }

    /**
     *Method: public void setIsUserLoggedIn(boolean isUserLoggedIn)
     *Description: sets user logged in
     *@param isUserLoggedIn
     */
    public void setIsUserLoggedIn(boolean isUserLoggedIn)
    {
        this.isUserLoggedIn = isUserLoggedIn;
        Log.d("ShowPlaceInfo", "user is logged in has been set");
    }

    /**
     *Method: public int getIdPlace()
     *Description: get id place
     */
    public int getIdPlace()
    {
        return idPlace;
    }

    /**
     *Method: public void setIdPlace(int idPlace)
     *Description: sets id of place
     *@param idPlace has to be above 0
     */
    public void setIdPlace(int idPlace)
    {
        assert(idPlace > 0);
        this.idPlace = idPlace;
        Log.d("ShowPlaceInfo", "idPlace has been set");
    }

    /**
     *Method: public void setRatingEvaluation(int idPlace, int idUser, float grade)
     *Description: set rating evaluation
     *@param idPlace has to be above 0
     *@param idUser has to be above 0
     *@param grade
     */
    public void setRatingEvaluation(int idPlace, int idUser, float grade)
    {
        assert(idPlace > 0);
        assert(idUser > 0);
        this.ratingEvaluation = new Evaluation(idPlace, idUser, grade);
        Log.d("ShowPlaceInfo", "rating evaluation has been set");
    }

    //Free objects to garbage collector take it easyer
    private void finalizeObject(Object object)
    {
        object = null;
    }
}