/*
* File name: EventConsultationTest.
* File pourpose: Test class Event Consultation.
*/

package com.mathheals.euvou;

import android.app.Activity;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import com.mathheals.euvou.controller.home_page.HomePage;
import org.junit.Before;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import android.support.test.InstrumentationRegistry;
import com.mathheals.euvou.controller.utility.LoginUtility;
import android.widget.EditText;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.IsNot.not;

/**
*Class: public class EventConsultationTest extends ActivityInstrumentationTestCase2<HomePage>
*Description: Test to consultation of event
*/
public class EventConsultationTest extends ActivityInstrumentationTestCase2<HomePage>
{
    private static final int USER_LOGGED_OUT = -1;

    private boolean isUserLoggedIn = false;
    private Activity activity = null;
    private Integer userId = null;
    /**
    *Method: public EventConsultationTest()
    *Description: has super with homepage class
    */
    public EventConsultationTest()
    {
        super(HomePage.class);
    }

    @Before
    /**
    *Method: public void setUp() throws Exception
    *Description: set user activity
    */
    public void setUp() throws Exception
    {
        super.setUp();
        activity = getActivity();
        setUserId(new LoginUtility(activity).getUserId());
        setIsUserLoggedIn(getUserId() != USER_LOGGED_OUT);
    }

    /**
    *Method: public void testIfEventConsultationIsOpened()
    *Description: Test if event consultation is opened
    */
    public void testIfEventConsultationIsOpened()
    {
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.radio_events)).check(matches(isDisplayed()));
        onView(withId(R.id.radio_people)).check(matches(isDisplayed()));
    }

    /**
    *Method: public void testIfAnyEventWasFound()
    *Description: test if any event has been founded
    */
    public void testIfAnyEventWasFound()
    {
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
        onView(withId(R.id.event_name_text)).check(matches(isDisplayed()));
    }

    /**
    *Method: public void testIfEventConsultationReturnsToHomePage()
    *Description: test if event consultation return to homepage
    */
    public void testIfEventConsultationReturnsToHomePage()
    {
        onView(withId(R.id.search)).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withText("EuVou")).check(matches(isDisplayed()));
    }

    /**
    *Method: public void testConsultationByCategory()
    *Description: tests consultation by category
    */
    public void testConsultationByCategory()
    {
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.radio_people)).perform(click());
        onView(withId(R.id.radio_people)).check(matches(isChecked()));
        onView(withId(R.id.radio_events)).perform(click());
        onView(withId(R.id.radio_events)).check(matches(isChecked()));
    }

    /**
    *Method: public void testButtonToMap()
    *Description: test button to map
    */
    public void testButtonToMap()
    {
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
        onView(withId(R.id.showEventOnMapButton)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    /**
    *Method: public void testIfRatingBarIsAvailableForLoggedOutUser()
    *Description: tests if rating bar is avaible to not logged user
    */
    public void testIfRatingBarIsAvailableForLoggedOutUser()
    {
        if(isUserLoggedIn)
        {
            assertTrue(isUserLoggedIn);
            TestUtility.makeUserLogOut();
            isUserLoggedIn = false;
        }
        openShowEventFragment();
        try
        {
            Thread.sleep(1000);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        onView(withId(R.id.ratingBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    /**
    *Method: public void testIfRatingBarIsAvailableForLoggedInUser()
    *Description: tests if rating bar is avaible to logged user
    */
    public void testIfRatingBarIsAvailableForLoggedInUser()
    {
        if(!isUserLoggedIn)
        {
            TestUtility.makeUserLogIn();
            isUserLoggedIn = true;
        }
        else
        {
            assertTrue(isUserLoggedIn);
        }

        boolean result = false;
        openShowEventFragment();

        try
        {
            int[] ratingNumbersForTest = new int[]{1, 3, 5};

            //sets rating numbers
            for(Integer ratingNumber : ratingNumbersForTest)
            {
                onView(withId(R.id.ratingBar)).perform(new SetRating(ratingNumber));
            }
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            result = true;
        }catch (PerformException performException)
        {
            result = false;
        }
        assertTrue(result);
    }

    private void openShowEventFragment()
    {
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
    }

    /**
    *Method: public Integer getUserId()
    *Description: gets user id
    */
    public Integer getUserId()
    {
        return userId;
    }

    /**
    *Method: public void setUserId(Integer userId)
    *Description: sets user id
    *@param userId
    */
    public void setUserId(Integer userId)
    {
        assertNotNull("userId is null.",userId);
        this.userId = userId;
    }

    /**
    *Method: public void setIsUserLoggedIn(boolean isUserLoggedIn)
    *Description:sets that user are logged in
    *@param isUserLoggedIn
    */
    public void setIsUserLoggedIn(boolean isUserLoggedIn)
    {
        this.isUserLoggedIn = isUserLoggedIn;
    }

    /**
    *Method: public void testMarkParticipateNotLoged()
    *Description: tests marker to participace if are not logged
    */
    public void testMarkParticipateNotLoged()
    {
        if(isUserLoggedIn)
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Sair")).perform(click());
        }
        else
        {
            assertFalse(isUserLoggedIn);
        }

        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
        onView(withId(R.id.EuVou)).check(matches(not(isDisplayed())));
    }

    private void markClick()
    {
        onView(withId(R.id.search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("t"), pressKey(66));
        onData(hasToString(containsString("t")))
                .inAdapterView(withId(R.id.events_list)).atPosition(0)
                .perform(click());
    }

    /**
    *Method: public void testMarkParticipateTwoTimeLoged()
    *Description: tests marks
    */
    public void testMarkParticipateTwoTimeLogged()
    {
        if (!isUserLoggedIn)
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        else
        {
            assertTrue(isUserLoggedIn);
        }

        markClick();
        onView(withId(R.id.EuVou)).perform(click());
        onView(withId(R.id.EuVou)).perform(click());

        onView(withId(R.id.search)).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withText("EuVou")).check(matches(isDisplayed()));
        markClick();
        onView(withId(R.id.EuVou)).perform(click());
        onView(withId(R.id.EuVou)).perform(click());
    }

    /**
    *Method: public void testMarkOffParticipateTwoTimeLoged()
    *Description: tests maks off
    */
    public void testMarkOffParticipateTwoTimeLoged()
    {
        if (!isUserLoggedIn)
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        else
        {
            assertTrue(isUserLoggedIn);
        }
            markClick();
            onView(withId(R.id.EuVou)).perform(click());
            onView(withId(R.id.EuVou)).perform(click());

            onView(withId(R.id.search)).perform(click());
            onView(withContentDescription("Navigate up")).perform(click());
            onView(withText("EuVou")).check(matches(isDisplayed()));
            markClick();
            onView(withId(R.id.EuVou)).perform(click());
            onView(withId(R.id.EuVou)).perform(click());
        }
    }
