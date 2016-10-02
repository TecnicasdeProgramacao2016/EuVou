/**
 * file: EventRecommendationDAO.java
 * purpose: make the conection of the class EventRecommendation to the database
 */
package dao;

import android.app.Activity;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;


public class EventRecommendationDAO extends DAO
{
    private final static Logger logger = Logger.getLogger(EventRecommendationDAO.class.getName());
    public EventRecommendationDAO()
    {

    }

    public EventRecommendationDAO(Activity activity)
    {
        super(activity);
    }

    public JSONObject recommendEvents(int idUser)
    {
        logger.log(Level.INFO,"entered in the method that gets the events that is recomended for an user");
        assert(idUser > 0);
        String QUERY =
                "SELECT DISTINCT V.idEvent, V.nameEvent,\n" +
                "(SELECT AVG(v.evaluate) FROM participate p \n" +
                "INNER JOIN vw_event v on v.idEvent = p.idEvent \n" +
                "WHERE v.nameCategory = V.nameCategory AND p.idUser = " + idUser + "  GROUP BY v.nameCategory) grade,\n" +
                "SUM((SELECT COUNT(v.nameCategory) FROM participate p \n" +
                "INNER JOIN vw_event v on v.idEvent = p.idEvent \n" +
                "WHERE v.nameCategory = V.nameCategory AND p.idUser = " + idUser + " GROUP BY v.nameCategory)) preference\n" +
                " FROM vw_event V \n" +
                "WHERE \n" +
                "V.dateTimeEvent BETWEEN now() and DATE_ADD(now(), INTERVAL 1 MONTH)\n" +
                "AND\n" +
                "nameCategory in(SELECT nameCategory FROM participate p \n" +
                "INNER JOIN vw_event v on v.idEvent = p.idEvent AND p.idUser = " + idUser + ")\n" +
                "GROUP BY V.idEvent\n" +
                "ORDER BY V.dateTimeEvent DESC,preference DESC, grade DESC\n" +
                "LIMIT 10";


        return executeConsult(QUERY);
    }


}
