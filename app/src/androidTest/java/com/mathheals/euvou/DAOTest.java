/**
 * file:DAOTest.java
 * purpose: testing the limit time of the class DAO
 */
package com.mathheals.euvou;

import junit.framework.TestCase;

import dao.DAO;

public class DAOTest extends TestCase
{
    public void testTimeLimitExceded()
    {
        assertFalse(DAO.limitExceded(10000, 999));
    }
    public void testTimeLimit()
    {
        assertTrue(DAO.limitExceded(10000,10050));
    }
}
