package com.mathheals.euvou.controller.show_place;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;

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

public class ShowPlaceInfo extends FragmentActivity
{
    private String description = "no description";
    private double longitude = 0;
    private double latitude = 0;
    private String address = "nothing";
    private float grade = 0;
    private void setPlaceInfo() {
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

    private void setRatingBarIfNeeded()
    {
        if(isUserLoggedIn)
            setRatingBar();
    }

    private int idPlace = 0;
    private RatingBar ratingBar = null;
    private Evaluation ratingEvaluation = null;
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

    private void setRatingBarStyle()
    {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.turquesa_app), PorterDuff.Mode.SRC_ATOP);
    }

    protected GoogleMap mMap = null;
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
        }
    }

    private void setUpMap()
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(getLatitude(), getLongitude()), 9));
        markPlaceOnMap();
    }

    private void markPlaceOnMap() {

        mMap.addMarker(
                new MarkerOptions()
                        .title(getName())
                        .snippet(getAddress())
                        .position(new LatLng(getLatitude(), getLongitude()))
        );
    }


    private Button showMapButton = null;
    private Button hideMapButton = null;
    public void showPlaceInfoOnClick(View view) {
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
                break;
        }
    }

    private void setGrade(float grade) {
        this.grade = grade;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private String getAddress() {
        return address;
    }

    private double getLongitude() {
        return longitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private String operation = "no operation";
    private void setOperation(String operation) {
        this.operation = operation;
    }

    private String phone = "no phone";
    private void setPhone(String phone) {
        this.phone = phone;
    }

    private String name = "no name";
    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private double getLatitude() {
        return latitude;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private TextView addressText = null;
    private void setAddressText(String adressText) {
        this.addressText = (TextView) findViewById(R.id.address_text);
        this.addressText.setText(adressText);
        this.addressText.setMovementMethod(new ScrollingMovementMethod());
    }

    private TextView operationText = null;
    private void setOperationText(String operationText) {
        this.operationText = (TextView) findViewById(R.id.operation_text);
        this.operationText.setText(operationText);
        this.operationText.setMovementMethod(new ScrollingMovementMethod());
    }

    private TextView phoneText = null;
    private void setPhoneText(String phoneText) {
        this.phoneText = (TextView) findViewById(R.id.phone_text);
        this.phoneText.setText(phoneText);
    }

    private TextView gradeText = null;
    private void setGradeText(String gradeText) {
        this.gradeText = (TextView) findViewById(R.id.grade_text);
        this.gradeText.setText(gradeText);
    }

    private TextView descriptionText = null;
    private void setDescriptionText(String descriptionText) {
        this.descriptionText = (TextView) findViewById(R.id.description_text);
        this.descriptionText.setText(descriptionText);
        this.descriptionText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setAllTextViews() {
        setAddressText(address);
        setOperationText(operation);
        setPhoneText(phone);
        setGradeText(Float.toString(grade));
        setDescriptionText(description);
    }

    private void setShowMapButton(Button showMapButton) {
        this.showMapButton = showMapButton;
    }

    private void setHideMapButton(Button hideMapButton) {
        this.hideMapButton = hideMapButton;
    }

    private TextView ratingMessage = null;
    private void setRatingMessage(boolean isUserLoggedIn) {
        String message = isUserLoggedIn ? "Sua avaliação:" : "Faça login para avaliar!";
        ratingMessage = (TextView) findViewById(R.id.rate_it_text);
        ratingMessage.setText(message);
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIsUserLoggedIn(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public void setRatingEvaluation(int idPlace, int idUser, float grade) {
        this.ratingEvaluation = new Evaluation(idPlace, idUser, grade);
    }
}