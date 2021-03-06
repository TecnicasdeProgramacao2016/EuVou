    /*
* File name: EventRegistrationControlTest.
* File pourpose: Test if an event can be registred.
*/

package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import model.Event;
import model.User;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public class EventRegistrationControlTest extends ActivityInstrumentationTestCase2<HomePage>
{
    public EventRegistrationControlTest()
    {
        super(HomePage.class);
    }


    private Event event; //Nem event to be tested
    private UiDevice device; //Instance new UI device that further will become an UIObject
    private static LoginUtility isLoged; //Check if user's is logged in

    @Before
    //Set up user.
    public void setUp() throws Exception
    {
        super.setUp();
        getActivity();
        isLoged = new LoginUtility(getActivity());
        device = UiDevice.getInstance(getInstrumentation());
    }

    private TestUtility setLogin;

    //Test Choosen Place to the event
    public void testChoosePlaceOnMap()
    {

        if(!isLoged.hasUserLoggedIn())
        {
            setLogin.makeUserLogIn();
        }
        else
        {
            //NOTHING TO DO
        }

        openRegisterEvent();
        onView(withId(R.id.eventLocal)).perform(click());
        onView(withId(R.id.map)).perform(click());

        //This try-catch structure forces the application principal thread to stop for a while
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException)
        {
            interruptedException.printStackTrace();
        }

        final String SUCESSFULL_CHOICE_MESSAGE = "Local selecionado com sucesso";

        //On view method displays a message or and object to the user, is this case, the text
        onView(withText(SUCESSFULL_CHOICE_MESSAGE))
                /*checks back-end to see if the decorView, or decoration view, gets user's
                  activities (if clicks or rolls the page) */
                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
                //checks if content is displayed
                .check(matches(isDisplayed()));



        finilizeObject(SUCESSFULL_CHOICE_MESSAGE);
    }

    //Test Categories Checkbox return
    public void testCategoriesCheckBox()
    {
        if(!isLoged.hasUserLoggedIn())
        {
            setLogin.makeUserLogIn();
        }
        else
        {
            //NOTHING TO DO
        }

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).perform(click());
        onView(withId(R.id.optionShow)).perform(click());
        onView(withId(R.id.optionShow)).check(matches(isChecked()));
        onView(withId(R.id.optionCinema)).perform(click());
        onView(withId(R.id.optionCinema)).check(matches(isChecked()));
        onView(withId(R.id.optionEducation)).perform(click());
        onView(withId(R.id.optionEducation)).check(matches(isChecked()));
        onView(withId(R.id.optionExposition)).perform(click());
        onView(withId(R.id.optionExposition)).check(matches(isChecked()));
        onView(withId(R.id.optionMuseum)).perform(click());
        onView(withId(R.id.optionMuseum)).check(matches(isChecked()));
        onView(withId(R.id.optionOthers)).perform(click());
        onView(withId(R.id.optionOthers)).check(matches(isChecked()));
        onView(withId(R.id.optionSports)).perform(click());
        onView(withId(R.id.optionSports)).check(matches(isChecked()));
        onView(withId(R.id.optionParty)).perform(click());
        onView(withId(R.id.optionParty)).check(matches(isChecked()));
        onView(withId(R.id.optionTheater)).perform(click());
        onView(withId(R.id.optionTheater)).check(matches(isChecked()));
    }

    //Test if event can be register passing adress bigger than it should
    public void testEventAdress()
    {
        if(!isLoged.hasUserLoggedIn())
        {
            setLogin.makeUserLogIn();
        }
        else
        {
            //NOTHING TO DO
        }

        final int MAXIMUM_EVENT_ADRESS_SIZE = 300;
        Object event_adress_object = R.id.event_adress_txt;
        String event_adress_string = event_adress_object.toString();
        int event_adress_size = Integer.parseInt(event_adress_string);
        if(event_adress_size > MAXIMUM_EVENT_ADRESS_SIZE)
        {
            assertTrue(false); //Stop the program, this error can cause security problems.
        }


    }

    //Test if can register a event without and adress
    public void testRegisterEventButtonWithEmptyAddress()
    {
        if(!isLoged.hasUserLoggedIn())
        {
            setLogin.makeUserLogIn();
        }
        else
        {
            //NOTHING TO DO
        }

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).perform(click());
        onView(withId(R.id.eventName)).perform(typeText("Cine Drive-In"));
        onView(withId(R.id.eventDate)).perform(typeText("12/12/2015"));
        onView(withId(R.id.eventHour)).perform(typeText("20:00"));
        onView(withId(R.id.optionCinema)).perform(click());
        onView(withId(R.id.eventPriceReal)).perform(typeText("05"));
        onView(withId(R.id.eventPriceDecimal)).perform(typeText("00"));
        onView(withText("Cadastrar")).perform(scrollTo());
        UiObject marker = device.findObject(new UiSelector().textContains("Cadastrar"));
        try
        {
            marker.click();
        } catch (UiObjectNotFoundException uiObjectNotFoundException)
        {
            uiObjectNotFoundException.printStackTrace();
        }
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException)
        {
            interruptedException.printStackTrace();
        }

        //Gets event that os shown on view
        onView(withId(R.id.eventAddress))
                //Compares the event show if it's empty
                .check(matches(hasErrorText(event.ADDRESS_IS_EMPTY)));
    }

    //Test if registred event is funcional
    private void openRegisterEvent()
    {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Cadastrar Evento")).perform(click());
    }

    //Free object's memory to make it easyer to the garbage collector get it
    private void finilizeObject(Object object)
    {
        object = null;
    }
}
