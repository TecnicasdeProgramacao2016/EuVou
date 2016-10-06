/**
 * file:EventCategoryDAO.java
 * purpose:class to execute the database conection to the class EventCategory
 */
package dao;

import android.app.Activity;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EventCategoryDAO extends DAO
{
    private  final static Logger logger = Logger.getLogger(EventCategoryDAO.class.getName());
    public EventCategoryDAO(Activity currentActivity)
    {
        super(currentActivity);
    }

    public JSONObject searchCategoriesByEventId(int idEvent)
    {
        assert(idEvent > 0);
        logger.log(Level.INFO,"entered in the method that searches the category of the event");
        return this.executeConsult("SELECT idCategory FROM event_category WHERE idEvent = " + idEvent);
    }
}