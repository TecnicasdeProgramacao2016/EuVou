/**
 * file: PlaceDAO.java
 * purpose: conecting the Place class to the database, and make operations with it.
 */
package dao;

import android.app.Activity;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.Place;


public class PlaceDAO extends DAO
{
    private  final static Logger logger = Logger.getLogger(PlaceDAO.class.getName());

    public PlaceDAO(Activity currentActivity)
    {
        super(currentActivity);
    }

    public PlaceDAO()
    {

    };

    public JSONObject searchPlaceByPartName(String name)
    {
        assert(name != null);
        logger.log(Level.INFO,"entered in the method that searches the place by part of its name");
        return this.executeConsult("SELECT * FROM vw_place WHERE namePlace LIKE '%" + name + "%'");
    }
    public JSONObject searchAllPlaces()
    {
        logger.log(Level.INFO,"entered in the method that searches all the places on the database");
        return this.executeConsult("SELECT * FROM vw_place ORDER BY evaluate DESC");
    }
    public JSONObject searchTop5Places()
    {
        logger.log(Level.INFO,"entered in the method that gets the top 5 places by it's eavluation");
        return this.executeConsult("SELECT * FROM vw_place ORDER BY evaluate DESC LIMIT 5");
    }
}
