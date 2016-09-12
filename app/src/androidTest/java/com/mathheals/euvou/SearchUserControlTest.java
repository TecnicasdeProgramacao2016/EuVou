/*
* File name: EventRecomendationDAOTest.
* File pourpose: Search for users controller.
* Created by: izabela on 04/11/15.
* Edited by: bernardohrl on 10/09/16
*/

package com.mathheals.euvou;

import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;

import org.junit.Before;


public class SearchUserControlTest  extends ActivityInstrumentationTestCase2<HomePage>
{
    //Constructor
    public SearchUserControlTest()
    {
        super(HomePage.class);
    }

    @Before
    //Set up user
    public void setUp() throws Exception
    {
        super.setUp();
        getActivity();
    }

}
