package com.mathheals.euvou;

import android.test.ActivityInstrumentationTestCase2;
import com.mathheals.euvou.controller.home_page.HomePage;
import org.junit.Before;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;


/**
  * File name: ShowPlaceRankingTest.
  * File pourpose: This file have the pourpose to test the ranking of the show places with true and falses parameters.
 */


public class ShowPlaceRankingTest extends ActivityInstrumentationTestCase2<HomePage> 
{

    public ShowPlaceRankingTest()  
    {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception 
    {
        super.setUp();
        getActivity();
    }

    public void testIfTopFiveIsDisplayed()
    {
        onView(withId(R.id.rateEvent)).check(matches(isDisplayed()));
        onView(withId(R.id.more)).check(matches(isDisplayed()));
        onView(withId(R.id.labelRankingPlace)).check(matches(isDisplayed()));

    }
    public void testIfPlaceRakingIsDisplayed()
    {
        testIfTopFiveIsDisplayed();
        onView(withId(R.id.more)).perform(click());
        onView(withId(R.id.rateEvent)).check(matches(isDisplayed()));

    }

    public void testclickPlaceRakingIsDisplayed()
    {
        testIfTopFiveIsDisplayed();
        onView(withId(R.id.rateEvent)).perform(click());

    }


}
