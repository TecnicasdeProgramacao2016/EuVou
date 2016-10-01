/**
 * file:DAO.java
 * purpose:main class related o the database conection
 */
package dao;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;

public abstract class DAO {

    private final String URLQUERY = "http://euvou.esy.es/query.php";
    private final String URLCONSULT = "http://euvou.esy.es/consult.php";;

    private final int LIMITCONECTIONTIME = 15000;
    protected Activity currentActivity = null;

    public DAO(Activity currentActivity)
    {
        this.currentActivity = currentActivity;
    }

    public DAO()
    {

    }

    private String query(final String query,final String urlQuery)
    {
        assert(query != null);
        assert(urlQuery != null);

        Consult consult = new Consult(query,urlQuery);
        consult.exec();

        long currentTime = Calendar.getInstance().getTime().getTime();
        long timeLimit = currentTime + LIMITCONECTIONTIME;
        while(!consult.getIsDoing() && currentTime < timeLimit)
        {
            currentTime = Calendar.getInstance().getTime().getTime();
        }

        if(limitExceded(timeLimit,currentTime))
        {
            Toast.makeText(currentActivity,"Problema de conexão com o servidor (verifique se esta "+
                                            "conectado a internet)", Toast.LENGTH_LONG).show();
            return null;
        }


        return consult.getResult();
    }
    public static boolean limitExceded(final long timeLimit, long currentTime)
    {
        assert(timeLimit > 0);
        assert(currentTime > 0);

        return (currentTime >= timeLimit);
    }
    protected String executeQuery(String query)
    {
        assert(query != null);
        return query(query, URLQUERY);
    }

    protected JSONObject executeConsult(String query)
    {
        assert(query != null);
        String json;
        JSONObject jObject = null;
        try
        {
            json = query(query,URLCONSULT);
            jObject  = new JSONObject(json);
            assert(jObject != null);
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return jObject;
    }

}