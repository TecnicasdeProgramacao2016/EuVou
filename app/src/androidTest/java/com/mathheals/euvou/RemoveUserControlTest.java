/**
 *  file:RemoveUserControlTest.java
 *  purpose: creating the checklist of testing the control of the RemoveUser class
 */
package com.mathheals.euvou;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class RemoveUserControlTest extends ActivityInstrumentationTestCase2<HomePage>
{

    LoginUtility userIsLoged;
    public RemoveUserControlTest()
    {
        super(HomePage.class);
    }

    /**
     * make the loggin to make the others tests below
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        getActivity();
        userIsLoged = new LoginUtility(getActivity());
    }

    /**
     * Testing if the options in the configure menu is shown for users that aren't
     * logged in
     */
    public void testIfConfigureOptionIsDisplayedForUserLoggedOut()
    {
        if(userIsLoged.hasUserLoggedIn())
        {
            TestUtility setLogin = null;
            setLogin.makeUserLogOut();
        }else
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Configurações")).check(doesNotExist());
        }

    }

    /**
     * Test if the options of the configuration menu is shown for users
     * that are logged in
     */
    public void testIfConfigureOptionIsDisplayedForUserLoggedIn()
    {
        if(!userIsLoged.hasUserLoggedIn())
        {
            TestUtility setLogin = null;
            setLogin.makeUserLogIn();
        }else
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Configurações")).check(matches(isDisplayed()));

        }
    }

    /**
     * testing if the remove button is working well
     */
    public void testRemoveButton()
    {
        if(!userIsLoged.hasUserLoggedIn())
        {
            TestUtility setLogin = null;
            setLogin.makeUserLogIn();
        }else
        {

            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Configurações")).perform(click());
            onView(withText("Desativar conta")).check(matches(isDisplayed()));
            onView(withText("DESATIVAR")).check(matches(isDisplayed()));
            onView(withText("DESATIVAR")).perform(click());
            onView(withText("Sim")).check(matches(isDisplayed()));
            onView(withText("Não")).check(matches(isDisplayed()));
        }

    }

    /**
     * Testing if the button to confirm the removement of an event is working well
     */
    public void testRemoveConfirmationButton()
    {
        if(!userIsLoged.hasUserLoggedIn())
        {
            TestUtility setLogin = null;
            setLogin.makeUserLogIn();
        }else
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Configurações")).perform(click());
            onView(withText("DESATIVAR")).perform(click());
            onView(withText("Não")).perform(click());
            onView(withId(R.id.button_disable_account_confirmation_id)).check(matches(isDisplayed()));
            onView(withId(R.id.button_disable_account_confirmation_id)).check(matches(withText("DESATIVAR")));
        }

    }

    /**
     * testing if it is possible to delete an account with a wrong password
     */
    public void testRemoveWithInvalidPasswordConfirmation()
    {
        if(!userIsLoged.hasUserLoggedIn())
        {
            TestUtility setLogin = null;
            setLogin.makeUserLogIn();
        }else
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Configurações")).perform(click());
            onView(withText("DESATIVAR")).perform(click());
            onView(withText("Não")).perform(click());
            onView(withId(R.id.edit_text_login_id)).perform(typeText("igodudu"));
            onView(withId(R.id.edit_text_password_id)).perform(typeText("1234567"));
            onView(withId(R.id.button_disable_account_confirmation_id)).perform(click());
            onView(withId(R.id.edit_text_password_id)).check(matches(hasErrorText("Ops, acho que você digitou a senha errada")));
        }

    }

    /**
     * Testing if it is possible to remove an account with an invalid login confirmation
     */
    public void testRemoveWithInvalidLoginConfirmation()
    {
        if(!userIsLoged.hasUserLoggedIn())
        {
            TestUtility setLogin = null;
            setLogin.makeUserLogIn();
        }else
        {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withText("Configurações")).perform(click());
            onView(withText("DESATIVAR")).perform(click());
            onView(withText("Não")).perform(click());
            onView(withId(R.id.edit_text_login_id)).perform(typeText("izacris"));
            onView(withId(R.id.edit_text_password_id)).perform(typeText("123456"));
            onView(withId(R.id.button_disable_account_confirmation_id)).perform(click());
            onView(withId(R.id.edit_text_login_id)).check(matches(hasErrorText("Ops, acho que você digitou o login errado")));
        }

    }

}
