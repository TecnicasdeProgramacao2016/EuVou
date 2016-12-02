package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import java.text.ParseException;
import java.util.Vector;

import dao.EventDAO;
import exception.EventException;
import model.Event;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;


public class EditEventControlTest extends ActivityInstrumentationTestCase2<HomePage>
{
    private LoginUtility isLoged = null;//check if the user is logged or not
    private EventDAO eventDAO = new EventDAO(); //instance of event to make the tests

    public EditEventControlTest()
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

    private void testIfEditUserOptionIsDisplayedForUserLoggedOut()
    {
        //It checks if the user is logged in, if so, it's necessary to logout
        if (isLoged.hasUserLoggedIn())
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Sair")).perform(click());
        }
        else
        {
            //NOTHING TO DO
        }
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Alterar")).check((doesNotExist()));
    }

    private void testIfEditUserOptionIsDisplayedForUserLoggedIn() throws ParseException, EventException
    {
        //It checks if the user is logged out, if so, it's necessary to make the login
        if (!isLoged.hasUserLoggedIn())
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Entrar")).perform(click());
            onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
            onView(withId(R.id.passwordField)).perform(typeText("123456"));
            onView(withText("Login")).perform(click());
        }
        else
        {
            //NOTHING TO DO
        }

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Meus Eventos")).perform(click());

        onData(hasToString(containsString("Teste")))
                .inAdapterView(withId(R.id.eventList)).atPosition(0)
                .perform(click());
        onView(withId(R.id.editRemoveButton)).perform(click());
        onView(withId(R.id.eventName)).perform(typeText("meuTeste"));
        closeSoftKeyboard();
        onView(withText("Alterar")).perform(scrollTo());
        onView(withText("Alterar")).perform(click());

        Vector<String> category = new Vector<String>();
        category.add("Show");
        Event event = new Event(1,"Teste",10010, "oi","30/11/2015","xablau","0","0",category);

        eventDAO.updateEvent(event);
    }

     private void testSelectCheckBoxCategories() throws ParseException, EventException
     {
         //It checks if the user is logged out, if so, it's necessary to make the login
         if (!isLoged.hasUserLoggedIn())
         {
             openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
             onView(withText("Entrar")).perform(click());
             onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
             onView(withId(R.id.passwordField)).perform(typeText("123456"));
             onView(withText("Login")).perform(click());
         }
         else
         {
             //NOTHING TO DO
         }

         openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
         onView(withText("Meus Eventos")).perform(click());

         onData(hasToString(containsString("Teste")))
                 .inAdapterView(withId(R.id.eventList)).atPosition(0)
                 .perform(click());
         onView(withId(R.id.editRemoveButton)).perform(click());
         onView(withId(R.id.optionShow)).check(matches(isChecked()));
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
         onView(withText("Alterar")).perform(scrollTo());
         onView(withText("Alterar")).perform(click());

         Vector<String> category = new Vector<String>();
         category.add("Show");
         Event event = new Event(1,"Teste",10010, "oi","30/11/2015","xablau","0","0",category);
         eventDAO.updateEvent(event);
     }

     private void testNotSelectCheckBoxCategories() throws ParseException, EventException
     {
         //It checks if the user is logged out, if so, it's necessary to make the login
         if (!isLoged.hasUserLoggedIn())
         {
             openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
             onView(withText("Entrar")).perform(click());
             onView(withId(R.id.usernameField)).perform(typeText("igodudu"));
             onView(withId(R.id.passwordField)).perform(typeText("123456"));
             onView(withText("Login")).perform(click());
         }
         else
         {
             //NOTHING TO DO
         }

         Vector<String> category = new Vector<>();
         category.add("Exposicao");
         category.add("Balada");
         category.add("Educacao");
         category.add("Cinema");
         category.add("Teatro");
         category.add("Outros");
         category.add("Esporte");
         category.add("Museu");
         category.add("Show");
         Event event = new Event(1,"Teste",10010, "oi","30/11/2015","xablau","0","0",category);

         eventDAO.updateEvent(event);

         openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
         onView(withText("Meus Eventos")).perform(click());

         onData(hasToString(containsString("Teste")))
                 .inAdapterView(withId(R.id.eventList)).atPosition(0)
                 .perform(click());
         onView(withId(R.id.editRemoveButton)).perform(click());
         onView(withId(R.id.optionCinema)).perform(click());
         onView(withId(R.id.optionCinema)).check(matches(isNotChecked()));
         onView(withId(R.id.optionEducation)).perform(click());
         onView(withId(R.id.optionEducation)).check(matches(isNotChecked()));
         onView(withId(R.id.optionExposition)).perform(click());
         onView(withId(R.id.optionExposition)).check(matches(isNotChecked()));
         onView(withId(R.id.optionMuseum)).perform(click());
         onView(withId(R.id.optionMuseum)).check(matches(isNotChecked()));
         onView(withId(R.id.optionOthers)).perform(click());
         onView(withId(R.id.optionOthers)).check(matches(isNotChecked()));
         onView(withId(R.id.optionSports)).perform(click());
         onView(withId(R.id.optionSports)).check(matches(isNotChecked()));
         onView(withId(R.id.optionParty)).perform(click());
         onView(withId(R.id.optionParty)).check(matches(isNotChecked()));
         onView(withId(R.id.optionTheater)).perform(click());
         onView(withId(R.id.optionTheater)).check(matches(isNotChecked()));
         onView(withId(R.id.optionShow)).perform(click());
         onView(withId(R.id.optionShow)).check(matches(isNotChecked()));
         onView(withText("Alterar")).perform(scrollTo());
         onView(withText("Alterar")).perform(click());

         category.clear();
         category.add("Show");
         event = new Event(1,"Teste",10010, "oi","30/11/2015","xablau","0","0",category);
         eventDAO.updateEvent(event);
     }
 }
