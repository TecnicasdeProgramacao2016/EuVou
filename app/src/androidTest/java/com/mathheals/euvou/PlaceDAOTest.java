/*
* File name: EventTest.
* File pourpose: Test Place DAO's.
* Created by: viny on 20/10/15.
* Edited by: bernardohrl on 10/09/16
*/


package com.mathheals.euvou;

import junit.framework.TestCase;

import dao.PlaceDAO;


public class PlaceDAOTest extends TestCase
{
    //Test search by name
    public void testSearchPlaceByPartName() throws Exception
    {
        PlaceDAO place = new PlaceDAO();
        if(place.searchPlaceByPartName("") != null)
            assertTrue(true);
        else
            fail();
    }
}
