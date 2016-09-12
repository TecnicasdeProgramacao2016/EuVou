/*
* File name: EventRecomendationDAOTest.
* File pourpose: Tests if map's place is valid.
* Created by: viny on 04/11/15.
* Edited by: bernardohrl on 10/09/16
*/

package com.mathheals.euvou;

import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;

import org.junit.Before;
import org.junit.Rule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasToString;

public class SearchPlaceMapsTest extends ActivityInstrumentationTestCase2<HomePage>
{

    //Constructor
    public SearchPlaceMapsTest()
    {
        super(HomePage.class);
    }

    @Rule
    //Sets activity rule
    public ActivityTestRule<HomePage> activityTestRule
            = new ActivityTestRule<>(HomePage.class);

    @Before
    //Set Up in apllication
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    //Test if first option is valid
    public void testSelectOption0()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(0)
                .perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    //Test is second option is valid
    public void testSelectOption1()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(1)
                .perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    //Test if third option is valid
    public void testSelectOption2()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(2)
                .perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    //Test if fourth option is valid
    public void testSelectOption3()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(3)
                .perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    //Test if fifth option is valid
    public void testSelectOption4()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(4)
                .perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }


    //Test if sixth option is valid
    public void testSelectOption5()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(5)
                .perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    //Test if options can be blanck
    public void testWriteOption()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.place_search)).perform(typeText("catet"));
        //onView(withId(R.id.search_button)).perform(click());
        onView(withId(R.id.search_place_button)).perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    //Test if options can be null
    public void testNullOption()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.place_search)).perform(typeText(""));
        onView(withId(R.id.search_place_button)).perform(click());

        onView(withId(R.id.place_search)).check(matches(isDisplayed()));
        onView(withContentDescription("Navigate up")).perform(click());
    }

    //Test if there is no option
    public void testInexistentOption()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.place_search)).perform(typeText("sfslk"));
        onView(withId(R.id.search_place_button)).perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    //Test markers
    public void testMarker()
    {
        onView(withContentDescription("Navigate up")).perform(click());

        onData(hasToString(containsString("")))
                .inAdapterView(withId(R.id.left_drawer_list)).atPosition(0)
                .perform(click());

        onView(withId(R.id.map)).perform(click());
    }

}
