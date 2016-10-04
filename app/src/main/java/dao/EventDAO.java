/**
 * file:EventDAO.java
 * purpose:class to execute the database conection to the Event class
 */
package dao;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import exception.EventException;
import model.Event;

public class EventDAO extends DAO
{
    private final  static Logger logger = Logger.getLogger(EventDAO.class.getName());
    public EventDAO(Activity currentActivity)
    {
        super(currentActivity);
    }
    
    public EventDAO()
    {

    }

    public void saveEvent(Event event)
    {
        assert(event != null);
        logger.log(Level.INFO,"entered in the method that saves an event");
        executeQuery("insert into tb_event(nameEvent, idOwner, price, address, dateTimeEvent,description,longitude,latitude) VALUES('" +
                event.getNameEvent() + "', '" + event.getIdOwner() + "', '" + event.getPrice() + "', '" + event.getAddress() + "','" + event.getDateTimeEvent() + "','" + event.getDescription() + "'," +
                "" + event.getLongitude() + "," + event.getLatitude() + ")");


        JSONObject jsonObject = executeConsult("SELECT idEvent FROM tb_event WHERE nameEvent = \"" + event.getNameEvent() + "\"");

        int idEvent = 0;

        try
        {
            idEvent = jsonObject.getJSONObject("0").getInt("idEvent");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        Vector<String> categories = event.getCategory();

        for(int i=0; i<categories.size(); i++)
        {
            String query = "INSERT INTO event_category(idEvent, idCategory) VALUES(\"" + idEvent + "\", " +
                    "(SELECT idCategory FROM tb_category WHERE nameCategory = \""+categories.get(i)+"\"))";

            executeQuery(query);
        }

    }
    public JSONObject searchEventByName(String eventName)
    {
        assert(eventName != null);
        logger.log(Level.INFO,"entered in the method that searches an Event by it's name");
        return this.executeConsult("SELECT * FROM vw_event WHERE nameEvent LIKE'%"+eventName+"%'");
    }

    public void updateEvent(Event event)
    {
        assert(event != null);
        logger.log(Level.INFO,"entered in the method that updates an event");
        executeQuery("UPDATE tb_event SET price=\"" + event.getPrice() + "\", address=\"" + event.getAddress() + "\", " +
                "nameEvent=\""+event.getNameEvent()+"\", "+"dateTimeEvent=\""+event.getDateTimeEvent()+
                "\", "+"description=\""+event.getDescription()+"\", "+"longitude=\""+event.getLongitude()+"\", " +
                " "+" latitude=\""+event.getLatitude()+ "\" WHERE idEvent = " + event.getIdEvent());

        executeQuery("delete from event_category where idEvent ="+event.getIdEvent());

        for (String category : event.getCategory())
        {
            String query = "INSERT INTO event_category VALUES("+event.getIdEvent() +","
                    + "(SELECT idCategory FROM tb_category WHERE namecategory = '"+category+"'));";

            executeQuery(query);
        }

    }
    public  String deleteEvent(int idEvent)
    {
        logger.log(Level.INFO,"entered in the method that deletes an event");
        assert(idEvent > 0);
        return this.executeQuery("DELETE FROM tb_event WHERE idEvent ="+idEvent);
    }

    public JSONObject searchEventByNameGroup(String eventName)
    {
        assert(eventName != null);
        logger.log(Level.INFO,"entered in the method that searches an event by the group name");
        return this.executeConsult("SELECT * FROM tb_event WHERE nameEvent LIKE'%"+eventName+"%' GROUP BY idEvent");
    }

    public JSONObject searchEventById(int idEvent)
    {
        assert(idEvent > 0);
        logger.log(Level.INFO,"entered in the method that searches an event by it's id");
        return this.executeConsult("SELECT * FROM tb_event WHERE idEvent = " + idEvent);
    }

    public Vector<Event> searchEventByOwner(int owner) throws JSONException, ParseException, EventException
    {
        assert(owner > 0);
        logger.log(Level.INFO,"entered in the method that searches an event by it's owner");
        JSONObject json = this.executeConsult("SELECT * FROM tb_event WHERE idOwner=" + owner + " GROUP BY idEvent");

        if(json == null)
            return null;

        Vector<Event> events = new Vector<>();

        for (int i = 0; i < json.length(); i++)
        {

            Event event = new Event(json.getJSONObject(""  + i).getInt("idEvent"),
                    json.getJSONObject(""  + i).getInt("idOwner"),
                    json.getJSONObject("" + i).getString("nameEvent"),
                    json.getJSONObject("" + i).getString("dateTimeEvent"),
                    json.getJSONObject("" + i).getInt("price"),
                    json.getJSONObject("" + i).getString("address"),
                    json.getJSONObject(""  + i).getString("description"),
                    json.getJSONObject("" + i).getString("latitude"),
                    json.getJSONObject("" + i).getString("longitude")
            );
            events.add(event);
        }

        return events;
    }

    public String markParticipate(int idUser, int idEvent)
    {
        assert(idEvent > 0);
        assert(idUser > 0);
        logger.log(Level.INFO,"entered in the method that marks presence");
        return this.executeQuery("INSERT INTO participate(idEvent, idUser) VALUES(" + idEvent + "," + idUser + ");");
    }

    public JSONObject verifyParticipate(int idUser, int idEvent)
    {
        assert(idEvent > 0);
        assert(idUser > 0);

        logger.log(Level.INFO,"entered in the method that verifies the participation");
        return this.executeConsult("SELECT * FROM participate WHERE idEvent=" + idEvent + " AND idUser=" + idUser);
    }

    public String markOffParticipate(int idUser, int idEvent)
    {
        assert(idEvent > 0);
        assert(idUser > 0);
        logger.log(Level.INFO,"entered in the method that turns the participation off");
        return this.executeQuery("DELETE FROM participate WHERE idEvent=" + idEvent + " AND idUser=" + idUser);
    }

    public void saveEventWithId(Event event)
    {
        assert(event != null);
        logger.log(Level.INFO,"entered in the method that saves the event whith the id");
        executeQuery("insert into tb_event(idEvent,nameEvent, idOwner, price, address, dateTimeEvent,description,longitude,latitude)"+
                    "VALUES('" + event.getIdEvent() + "', '" + event.getNameEvent() + "', '" + event.getIdOwner() + "', '" + event.getPrice() +
                    "', '" + event.getAddress() + "','" + event.getDateTimeEvent() + "','" + event.getDescription() + "'," +
                    "" + event.getLongitude() + "," + event.getLatitude() + ")");

        Vector<String> categories = event.getCategory();
        JSONObject jsonObject = executeConsult("SELECT idEvent FROM tb_event WHERE nameEvent = \"" + event.getNameEvent() + "\"");
        int idEvent = 0;
        try
        {
            idEvent = jsonObject.getJSONObject("0").getInt("idEvent");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}
