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
    private final  static Logger logger = Logger.getLogger(EventDAO.class.getName());//atribute to use loggin system
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

        String initialQuery = "insert into tb_event(nameEvent, idOwner, price, address, dateTimeEvent,description," +
                       "longitude,latitude) VALUES('" + event.getNameEvent() + "', '" + event.getIdOwner() +
                                                   "', '" + event.getPrice() + "', '" + event.getAddress() +
                                                   "','" + event.getDateTimeEvent() + "','" + event.getDescription() +
                                                  "'," + "" + event.getLongitude() + "," + event.getLatitude() + ")";

        executeQuery(initialQuery);




        int idEvent = 0;
        JSONObject jsonObject = (JSONObject) executeConsult("SELECT idEvent FROM tb_event WHERE nameEvent = \"" +
                                                            event.getNameEvent() + "\"");

        try
        {
            idEvent = (int)jsonObject.getJSONObject("0").getInt("idEvent");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        Vector<String> categories = event.getCategory();

        /*
            executes the query for the expecific categories of the application
         */

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

        String query = "SELECT * FROM vw_event WHERE nameEvent LIKE'%"+eventName+"%'";

        JSONObject consultQuery = this.executeConsult(query);

        return consultQuery;
    }

    public void updateEvent(Event event)
    {
        assert(event != null);
        logger.log(Level.INFO,"entered in the method that updates an event");
        String queryUpdate = "UPDATE tb_event SET price=\"" + event.getPrice() + "\", address=\"" + event.getAddress() +
                                                        "\", " + "nameEvent=\""+event.getNameEvent()+"\", "+
                                                        "dateTimeEvent=\""+event.getDateTimeEvent()+ "\", "+"description=\""+
                                                        event.getDescription()+"\", "+"longitude=\""+event.getLongitude()+"\", " +
                                                        " "+" latitude=\""+event.getLatitude()+ "\" WHERE idEvent = " +
                                                        event.getIdEvent();

        executeQuery(queryUpdate);

        executeQuery("delete from event_category where idEvent ="+event.getIdEvent());

        /*
            execute the query for each event in the category.
         */
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
        String query = "DELETE FROM tb_event WHERE idEvent ="+idEvent;

        return this.executeQuery(query);
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

        //checks if the result of the consult of the database is null or not
        if(json == null)
        {
            return null;
        }
        else
        {
            //NOTHING TO DO
        }
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

        JSONObject jsonObject = (JSONObject) executeConsult("SELECT idEvent FROM tb_event WHERE nameEvent = \"" + event.getNameEvent() + "\"");

    }

}
