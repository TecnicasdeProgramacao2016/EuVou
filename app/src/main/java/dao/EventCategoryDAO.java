/**
 * file:EventCategoryDAO.java
 * purpose:class to execute the database conection to the class EventCategory
 */
package dao;

import android.app.Activity;

import org.json.JSONObject;

public class EventCategoryDAO extends DAO
{

    public EventCategoryDAO(Activity currentActivity)
    {
        super(currentActivity);
    }

    public JSONObject searchCategoriesByEventId(int idEvent)
    {
        assert(idEvent > 0);
        return this.executeConsult("SELECT idCategory FROM event_category WHERE idEvent = " + idEvent);
    }
}