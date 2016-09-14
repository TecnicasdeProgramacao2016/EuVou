package com.mathheals.euvou;

import junit.framework.TestCase;

import dao.Consult;


public class ConsultTest extends TestCase
{

    public void testSetResult() throws Exception
    {
        Consult consult = new Consult("","");
        consult.setResult("teste");
        assertTrue(consult.getResult().equals("teste"));
    }
}
