/**
 * file:DAO.java
 * purpose:main class related o the database conection
 */
package dao;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {

    private final String URLQUERY = "http://euvou.esy.es/query.php";//link to the database to execute queries
    private final String URLCONSULT = "http://euvou.esy.es/consult.php";; //link to the database to use the consult
    private final static Logger logger = Logger.getLogger(DAO.class.getName()); //atribute to use loggin system
    private final int LIMITCONECTIONTIME = 15000;//maximum time to try to connect to the database
    protected Activity currentActivity = null;

    public DAO(Activity currentActivity)
    {
        this.currentActivity = currentActivity;
    }

    public DAO()
    {

    }

    protected JSONObject executeConsult(String query)
    {
        assert(query != null);
        logger.log(Level.INFO,"entered in the method that execute the consult on the database");

        JSONObject jObject = null;
        try
        {
            String json = query(query,URLCONSULT);
            jObject  = new JSONObject(json);
            assert(jObject != null);
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return jObject;
    }

    private String query(final String query,final String urlQuery)
    {
        assert(query != null);
        assert(urlQuery != null);
        logger.log(Level.INFO,"entered in the method that makes the query");

        long currentTime = (long) Calendar.getInstance().getTime().getTime();

        Consult consult = new Consult(query,urlQuery);
        consult.exec();

        long timeLimit = currentTime + LIMITCONECTIONTIME;

        while(!consult.getIsDoing() && currentTime < timeLimit)
        {
            currentTime = Calendar.getInstance().getTime().getTime();
        }

        //verification of the current time used
        if(limitExceded(timeLimit,currentTime))
        {
            Toast.makeText(currentActivity,"Problema de conexão com o servidor (verifique se esta "+
                                            "conectado a internet)", Toast.LENGTH_LONG).show();
            return null;
        }
        else
        {
            //NOTHING TO DO
        }


        return consult.getResult();
    }
    public static boolean limitExceded(final long timeLimit, long currentTime)
    {
        assert(timeLimit > 0);
        assert(currentTime > 0);
        logger.log(Level.INFO,"entered in the method that checks the limit of the time conection");

        boolean resultLimitTimeCheck =  (currentTime >= timeLimit);

        return resultLimitTimeCheck;
    }
    protected String executeQuery(String query)
    {
        assert(query != null);
        logger.log(Level.INFO,"entered in the method that executes the query");

        return query(query, URLQUERY);
    }



}