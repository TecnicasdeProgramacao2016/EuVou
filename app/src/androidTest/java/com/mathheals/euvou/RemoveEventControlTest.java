/**
 * file:RemoveEventControlTest.java
 * purpose: test the class RemoveEvent control
 */
package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import java.text.ParseException;

import dao.EventDAO;
import exception.EventException;
import model.Event;
import model.User;

import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;


public class RemoveEventControlTest extends ActivityInstrumentationTestCase2<HomePage>
{

    private LoginUtility isLoged = null;//reference to instanciate later to check if the user is logged
    private TestUtility setLogin = null;//reference to make login
    private UiDevice device = null; // makes reference to UiDevice to instanciate it later
    private User user = null; //makes referênce of user to later instanciate it


    public RemoveEventControlTest()
    {
        super(HomePage.class);
    }

    /**
     * set the logint to the test class
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        getActivity();
        isLoged = new LoginUtility(getActivity());
        device = UiDevice.getInstance(getInstrumentation());
    }

    /**
     * Test if the options to remove an event is shown for the user that is not
     * logged in the application
     */
    public void testIfRemoveEventOptionIsDisplayedForUserLoggedOut()
    {
        //It checks if the user is already logged in, if so, he/she is logged out to make the test
        if(isLoged.hasUserLoggedIn())
        {
            setLogin.makeUserLogOut();
        }else
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Meus Eventos")).check(doesNotExist());

        }
    }

    /**
     *  testing if the button to remove an event is shown.
     * @throws ParseException
     * @throws EventException
     */
    public void testRemoveEventButton() throws ParseException, EventException
    {
        //it checks if the user is not logged in, if so, makes the login
        if(!isLoged.hasUserLoggedIn())
        {
            setLogin.makeUserLogIn();
        }else
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Meus Eventos")).perform(click());
        }


        onData(hasToString(containsString("Teste"))).inAdapterView(withId(R.id.eventList)).atPosition(0)
                                                    .perform(click());

        onView(withId(R.id.editRemoveButton)).perform(click());
        onView(withId(R.id.removeEvent)).perform(scrollTo());
        onView(withId(R.id.removeEvent)).perform(click());
        onView(withText("Deletado com sucesso")).inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        Event event = new Event(1,3,"Teste","2015-12-20 14:00:00",10010,"oi","xablau","0","0");
        new EventDAO().saveEventWithId(event);
    }
}
