/*
* File name: EventTest.
* File pourpose: Test Place DAO's.
*/


package com.mathheals.euvou;

import junit.framework.TestCase;

import dao.PlaceDAO;


public class PlaceDAOTest extends TestCase
{
    //Test place search by name
    public void testSearchPlaceByPartName() throws Exception
    {
        PlaceDAO place = new PlaceDAO();
        if(place.searchPlaceByPartName("") != null)
        {
            assertTrue(true);
        }
        else
        {
            fail();
            assertFalse(false);
        }
    }
}
