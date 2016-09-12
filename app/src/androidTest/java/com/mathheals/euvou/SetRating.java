/*
* File name: EventRecomendationDAOTest.
* File pourpose: Set event Rating.
*/

package com.mathheals.euvou;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.RatingBar;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;


public class SetRating implements ViewAction
{
    //Constructio
    public SetRating(Integer rating)
    {
        setRating(rating);
    }

    private Integer rating;

    @Override
    //Checks if raiting bar is constrint
    public Matcher<View> getConstraints()
    {
        Matcher<View> isRatingBarConstraint = isAssignableFrom(RatingBar.class);
        return isRatingBarConstraint;
    }

    @Override
    //Overrides getDescription, that gets value of discription
    public String getDescription() {
        return "Custom view action to set rating.";
    }

    @Override
    //Sets rating bar
    public void perform(UiController uiController, View view) {
        RatingBar ratingBar = (RatingBar) view;
        ratingBar.setRating(getRating());
    }

    //Gets value os rating
    public Integer getRating() {
        return rating;
    }

    //Sets value of rating
    public void setRating(Integer rating) {
        this.rating = rating;
    }
}