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
import android.widget.Toast;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.utility.LoginUtility;

import dao.EvaluatePlaceDAO;
import model.Evaluation;
import model.Place;


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
    private float grade = 0;
    /**
    *Method: private void setPlaceInfo()
    *Description: sets place informations
    */
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

    private SupportMapFragment mMapFragment = null;
    private Integer userId = 0;
    private boolean isUserLoggedIn;
    private final Integer LOGGED_OUT = -1;
    @Override
    /**
    *Method: protected void onCreate(Bundle savedInstanceState)
    *Description: sets values to view place
    *@param Bundle savedInstanceState
    */
    protected void onCreate(Bundle savedInstanceState)
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


    /**
    *Method: private void setRatingBarIfNeeded()
    *Description: If user are logged sets rating bar
    */
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

    private int idPlace = 0;
    private RatingBar ratingBar = null;
    private Evaluation ratingEvaluation = null;
    /**
    *Method: private void setRatingBar()
    *Description: sets rating bar
    */
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

    /**
    *Method: private void setRatingBarStyle()
    *Description: sets style to rate bar
    */
    private void setRatingBarStyle()
    {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.turquesa_app), PorterDuff.Mode.SRC_ATOP);
    }

    protected GoogleMap mMap = null;
    /**
    *Method: private void setUpMapIfNeeded()
    *Description: set map if does not exist
    */
    private void setUpMapIfNeeded()
    {
        if (mMap == null)
        {
            mMapFragment = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_show_place_info_map));
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

    /**
    *Method: private void setUpMap()
    *Description: sets map
    */
    private void setUpMap()
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(getLatitude(), getLongitude()), 9));
        markPlaceOnMap();
    }

    /**
    *Method: private void markPlaceOnMap()
    *Description: marks a place on map
    */
    private void markPlaceOnMap()
    {

        mMap.addMarker(
                new MarkerOptions()
                        .title(getName())
                        .snippet(getAddress())
                        .position(new LatLng(getLatitude(), getLongitude()))
        );
    }


    private Button showMapButton = null;
    private Button hideMapButton = null;
    /**
    *Method: public void showPlaceInfoOnClick(View view)
    *Description: shows informations about place
    *@param View view
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

    /**
    *Method: private void setGrade(float grade)
    *Description: sets grade
    *@param float grade
    */
    private void setGrade(float grade)
    {
        this.grade = grade;
        Log.d("ShowPlaceInfo", "grade has been set");
    }

    /**
    *Method: private void setAddress(String address)
    *Description: sets adress
    *@param String address
    */
    private void setAddress(String address)
    {
        this.address = address;
        Log.d("ShowPlaceInfo", "adress has been set");
    }

    /**
    *Method: private String getAddress()
    *Description: get adress
    */
    private String getAddress()
    {
        return address;
    }

    /**
    *Method: private double getLongitude()
    *Description: get longitude
    */
    private double getLongitude()
    {
        return longitude;
    }

    /**
    *Method: private void setLongitude(double longitude)
    *Description: set longitude
    *@param double longitude
    */
    private void setLongitude(double longitude)
    {
        this.longitude = longitude;
        Log.d("ShowPlaceInfo", "longitude has been set");
    }

    /**
    *Method: private void setDescription(String description)
    *Description: set description
    *@param String description
    */
    private void setDescription(String description)
    {
        this.description = description;
        Log.d("ShowPlaceInfo", "description been set");
    }

    private String operation = "operation";
    /**
    *Method: private void setOperation(String operation)
    *Description: set operation
    *@param String operation
    */
    private void setOperation(String operation)
    {
        this.operation = operation;
        Log.d("ShowPlaceInfo", "operation has been set");
    }

    private String phone = "phone";
    /**
    *Method: private void setPhone(String phone)
    *Description: set phone
    *@param String phone
    */
    private void setPhone(String phone)
    {
        this.phone = phone;
        Log.d("ShowPlaceInfo", "phone has been set");
    }

    private String name = "name";
    /**
    *Method: private String getName()
    *Description: get name
    */
    private String getName()
    {
        return name;
    }

    /**
    *Method: private void setName(String name)
    *Description: set name
    *@param String name
    */
    private void setName(String name)
    {
        this.name = name;
        Log.d("ShowPlaceInfo", "name has been set");
    }

    /**
    *Method: private double getLatitude()
    *Description: get latitude
    */
    private double getLatitude()
    {
        return latitude;
    }

    /**
    *Method: private void setLatitude(double latitude)
    *Description: set Latitude
    */
    private void setLatitude(double latitude)
    {
        this.latitude = latitude;
        Log.d("ShowPlaceInfo", "latitude has been set");
    }

    private TextView addressText = null;
    /**
    *Method: private void setAddressText(String adressText)
    *Description: set text on adress
    *@param String adressText
    */
    private void setAddressText(String adressText)
    {
        this.addressText = (TextView) findViewById(R.id.address_text);
        this.addressText.setText(adressText);
        this.addressText.setMovementMethod(new ScrollingMovementMethod());
    }

    private TextView operationText = null;
    /**
    *Method: private void setOperationText(String operationText)
    *Description: set operations text
    *@param String operationText
    */
    private void setOperationText(String operationText)
    {
        this.operationText = (TextView) findViewById(R.id.operation_text);
        this.operationText.setText(operationText);
        this.operationText.setMovementMethod(new ScrollingMovementMethod());
    }

    private TextView phoneText = null;
    /**
    *Method: private void setPhoneText(String phoneText)
    *Description: set text of phone
    *@param String phoneText
    */
    private void setPhoneText(String phoneText)
    {
        this.phoneText = (TextView) findViewById(R.id.phone_text);
        this.phoneText.setText(phoneText);
        Log.d("ShowPlaceInfo", "phone text has been set");
    }

    private TextView gradeText = null;
    /**
    *Method: private void setGradeText(String gradeText)
    *Description: set text on grade
    *@param String gradeText
    */
    private void setGradeText(String gradeText)
    {
        this.gradeText = (TextView) findViewById(R.id.grade_text);
        this.gradeText.setText(gradeText);
        Log.d("ShowPlaceInfo", "grade text has been set");
    }

    private TextView descriptionText = null;
    /**
    *Method: private void setDescriptionText(String descriptionText)
    *Description: set text on description
    *@param String descriptionText
    */
    private void setDescriptionText(String descriptionText)
    {
        this.descriptionText = (TextView) findViewById(R.id.description_text);
        this.descriptionText.setText(descriptionText);
        this.descriptionText.setMovementMethod(new ScrollingMovementMethod());
        Log.d("ShowPlaceInfo", "description text been set");
    }

    /**
    *Method: private void setAllTextViews()
    *Description: set all texts
    */
    private void setAllTextViews()
    {
        setAddressText(address);
        setOperationText(operation);
        setPhoneText(phone);
        setGradeText(Float.toString(grade));
        setDescriptionText(description);
    }

    /**
    *Method: private void setShowMapButton(Button showMapButton)
    *Description: sets show map button
    *@param Button showMapButton
    */
    private void setShowMapButton(Button showMapButton)
    {
        this.showMapButton = showMapButton;
        Log.d("ShowPlaceInfo", "button of show map has been set");
    }

    /**
    *Method: private void setHideMapButton(Button hideMapButton)
    *Description: sets hide map button
    *@param Button hideMapButton
    */
    private void setHideMapButton(Button hideMapButton)
    {
        this.hideMapButton = hideMapButton;
        Log.d("ShowPlaceInfo", "hide map button has been set");
    }

    private TextView ratingMessage = null;
    /**
    *Method: private void setRatingMessage(boolean isUserLoggedIn)
    *Description: sets rating message
    *@param boolean isUserLoggedIn
    */
    private void setRatingMessage(boolean isUserLoggedIn)
    {
        String message = isUserLoggedIn ? "Sua avaliação:" : "Faça login para avaliar!";
        ratingMessage = (TextView) findViewById(R.id.rate_it_text);
        ratingMessage.setText(message);
    }

    /**
    *Method: private void setUserId(int userId)
    *Description: sets user id
    *@param int userId
    */
    private void setUserId(int userId)
    {
        assert(userId > 0);
        this.userId = userId;
        Log.d("ShowPlaceInfo", "userId has been set");
    }

    /**
    *Method: public void setIsUserLoggedIn(boolean isUserLoggedIn)
    *Description: sets user logged in
    *@param boolean isUserLoggedIn
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
    *@param int idPlace
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
    *@param int idPlace
    *@param int idUser
    *@param float grade
    */
    public void setRatingEvaluation(int idPlace, int idUser, float grade)
    {
        assert(idPlace > 0);
        assert(idUser > 0);
        this.ratingEvaluation = new Evaluation(idPlace, idUser, grade);
        Log.d("ShowPlaceInfo", "rating evaluation has been set");
    }
}