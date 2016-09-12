/*
* File name: EventRecomendationTest.
* File pourpose: Test user's log status and test set list new list of recommended events.
*/


package com.mathheals.euvou;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EventRecomendationTest  extends ActivityInstrumentationTestCase2<HomePage>
{

    private Activity activity;
    private ListView list;
    private static final int USER_LOGGED_OUT = -1;
    private boolean isUserLoggedIn;

    //Constructor Method.
    public EventRecomendationTest()
    {
        super(HomePage.class);
    }


    @Before
    //Set user's status to logged in.
    public void setUp() throws Exception
    {
        super.setUp();
        activity = getActivity();
        setIsUserLoggedIn(new LoginUtility(activity).getUserId() != USER_LOGGED_OUT);
    }

    @Test
    //Test events and evaluate if its recommended.
    public void testIfAnyEventIsRecommended()
    {
        list = (ListView) activity.findViewById(R.id.list_view_event_recomendations);
        assertNotNull("The list was not loaded", list);
        openTopOneEvent();
        onView(withId(R.id.event_name_text)).check(matches(isDisplayed()));
    }

    //Open first event after evaluation.
    private void openTopOneEvent()
    {
        getInstrumentation().runOnMainSync(new Runnable()
            {
                @Override
                public void run()
                {
                    int position = 0;
                    list.performItemClick(list.getAdapter().getView(position, null, null),
                          position, list.getAdapter().getItemId(position));
                }

            });

        getInstrumentation().waitForIdleSync();
    }

    //Test if user can loggin and sets a new list of events.
    public void testUserWithoutRecomendations()
    {
        final String LOGIN = "izabiza";
        final String PASSWORD = "123456";

        if(isUserLoggedIn)
        {
            TestUtility.makeUserLogOut();
        }
        else
        {
            //nothing to do
        }

        TestUtility.makeUserLogIn(LOGIN, PASSWORD);

        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        boolean result = false;
        int numberOfEVentsRecomended = -1;

        list = (ListView) activity.findViewById(R.id.list_view_event_recomendations);

        if(list == null)
        {
            result = true;
        }
        else
        {
            numberOfEVentsRecomended = list.getAdapter().getCount();
        }

        if(numberOfEVentsRecomended == 0)
        {
            result = true;
        }

        TestUtility.makeUserLogOut();
        TestUtility.makeUserLogIn();
        isUserLoggedIn = true;

        assertTrue(result);
    }


    public void setIsUserLoggedIn(boolean isUserLoggedIn)
    {
        this.isUserLoggedIn = isUserLoggedIn;
    }
}
