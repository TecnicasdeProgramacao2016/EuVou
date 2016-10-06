/*
* File name: EventDAOTest.
* File pourpose: Test Event search delete and update.
*/

package com.mathheals.euvou;

import junit.framework.TestCase;
import org.json.JSONException;
import java.text.ParseException;
import java.util.Vector;
import dao.EventDAO;
import exception.EventException;
import model.Event;

/**
*Class: public class EventDAOTest extends TestCase
*Description: Class to test Event DAO
*/
public class EventDAOTest extends TestCase
{
    /**
    *Method: public void testSearchEventByOwner() throws ParseException, EventException, JSONException
    *Description: tests search event by owner
    */
    public void testSearchEventByOwner() throws ParseException, EventException, JSONException
    {
        EventDAO eventDAO = new EventDAO();
        assertEquals(eventDAO.searchEventByOwner(-1), null);
    }

    /**
    *Method: public void testUpdateEvent() throws ParseException, EventException, JSONException
    *Description: tests an updated event
    */
    public void testUpdateEvent() throws ParseException, EventException, JSONException
    {
        EventDAO eventDAO = new EventDAO();
        Vector<String> category = new Vector<>();
        category.add("Show");
        Event event = new Event(1, "Teste Abacate", 10010, "oi", "00/00/0000", "xablau", "0", "0", category);
        eventDAO.updateEvent(event);
        assertEquals(eventDAO.searchEventById(1).getJSONObject("0").getString("nameEvent"), "Teste Abacate");
        event = new Event(1, "Teste", 10010, "oi", "00/00/0000", "xablau", "0", "0", category);
        eventDAO.updateEvent(event);
    }

    /**
    *Method: public void testSearchEventByOwner2() throws ParseException, EventException, JSONException
    *Description: tests search event by second owner
    */
    public void testSearchEventByOwner2() throws ParseException, EventException, JSONException
    {
        EventDAO eventDAO = new EventDAO();
        assertFalse(eventDAO.searchEventByOwner(3).isEmpty());
    }

    /**
    *Method: public void testEventDelet() throws ParseException, EventException, JSONException
    *Description: tests if event has been deleted
    */
   public void testEventDelet() throws ParseException, EventException, JSONException
   {
        EventDAO eventDAO = new EventDAO();
        Vector<String> category = new Vector<>();
        category.add("Outros");

        Event event = new Event(3,"Evento Delete",10010, "FGA","14/02/2017","DESCRICAO", "12.20","78.41520",category);

        eventDAO.saveEvent(event);
        eventDAO.deleteEvent(3);

        assertNull(eventDAO.searchEventByName("Evento Delete"));
    }
}
