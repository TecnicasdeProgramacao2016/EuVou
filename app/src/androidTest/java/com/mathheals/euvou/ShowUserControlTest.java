/**
 * file:ShowUserControlTest.java
 * purpose: test the controller of the ShowUser class
 */
package com.mathheals.euvou;

import android.test.ActivityInstrumentationTestCase2;

import com.mathheals.euvou.controller.home_page.HomePage;

import org.junit.Before;


public class ShowUserControlTest  extends ActivityInstrumentationTestCase2<HomePage>
{

    public ShowUserControlTest()
    {
        super(HomePage.class);
    }

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        getActivity();
    }
}
