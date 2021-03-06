package com.mathheals.euvou;

import android.app.Activity;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.uiautomator.UiDevice;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;


 /**
  * File name: UserEvolationControlTest.
  * File pourpose: Test user's evaluation with true and falses parameters.
 */


public class UserEvaluationControlTest extends ActivityInstrumentationTestCase2<HomePage> 
{

    LoginUtility isLoged;

    public UserEvaluationControlTest()
    {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception 
    {
        super.setUp();
        getActivity();
        isLoged = new LoginUtility(getActivity());
    }

    public void testIfRatingBarIsAvailableForLoggedInUser()
    {
        //check if user is logged
        if(!isLoged.hasUserLoggedIn())
        {
            TestUtility.makeUserLogIn();
        } else
        {
            //NOTHING TO DO
        }
        searchForUserUsedForTest();
        onView(withId(R.id.ratingBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    public void testIfRatingBarIsAvailableForLoggedOutUser() 
    {
        //check if user is logged
        if(isLoged.hasUserLoggedIn()) 
        {
            TestUtility.makeUserLogOut();
        } else
        {
            //NOTHING TO DO
        }
        searchForUserUsedForTest();
        onView(withId(R.id.ratingBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

    }

    public void testIfRatingBarIsSetingEvaluation() 
    {
        boolean result;
        //check if user is logged
        if(!isLoged.hasUserLoggedIn()) 
        {
            TestUtility.makeUserLogIn();
        } else
        {
            //NOTHING TO DO
        }
        searchForUserUsedForTest();
        try 
        {
            int[] ratingNumbersForTest = new int[]{1, 3, 5};

            //inserting test numbers in SetRating
            for(Integer ratingNumber : ratingNumbersForTest)
                onView(withId(R.id.ratingBar)).perform(new SetRating(ratingNumber));
            try 
            {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException)
            {
                interruptedException.printStackTrace();
            }
            result = true;
        } catch (PerformException performException) 
        {
            result = false;
        }
        assertTrue(result);
    }

    public void searchForUserUsedForTest()
    {

        try
        {
            onView(withId(R.id.search)).perform(click());
            onView(withId(R.id.radio_people)).perform(click());
            onView(withId(R.id.search_src_text)).perform(typeText("t"), pressKey(66));
            onData(hasToString(containsString("Igor Duarte")))
                    .inAdapterView(withId(R.id.events_list)).atPosition(0)
                    .perform(click());
            closeSoftKeyboard();
        } catch (UiObjectNotFoundException uiObjectNotFoundException)
        {
            uiObjectNotFoundException.printStackTrace();
        }

    }
}
