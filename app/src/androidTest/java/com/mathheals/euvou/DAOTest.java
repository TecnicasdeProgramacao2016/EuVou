/**
 * file:DAOTest.java
 * purpose: testing the limit time of the class DAO
 */
package com.mathheals.euvou;

import junit.framework.TestCase;

import dao.DAO;

public class DAOTest extends TestCase
{
    /**
     * Testing if the the limit time is working ok
     */
    public void testTimeLimitExceded()
    {
        assertFalse(DAO.limitExceded(10000, 999));
    }

    /**
     * testing if the limit time is ok
     */
    public void testTimeLimit()
    {
        assertTrue(DAO.limitExceded(10000,10050));
    }
}
