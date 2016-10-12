/*
* File name: Evaluation.
* File pourpose: Set grade, place id and user id.
*/

package model;

import android.util.Log;

/**
*Class: public class Evaluation
*Description: class to set evaluation
*/
public class Evaluation
{
    private float grade = 0;
    private int idPlace = 0;//has to be above 0
    private int idUser = 0;//has to be above 0
    /**
    *Method: public Evaluation(int idPlace, int idUser, float grade)
    *Description: sets values to evaluation
    *@param idPlace
    *@param idUser
    *@param grade
    */
    public Evaluation(final int idPlace, final int idUser, final float grade)
    {
        setIdPlace(idPlace);
        setIdUser(idUser);
        setGrade(grade);
    }

    private void setGrade(final float grade)
    {
        this.grade = grade;
        Log.d("Evaluation", "Grade has been setted");
    }

    private void setIdUser(final int idUser)
    {
        assert( idUser > 0);
        this.idUser = idUser;
        Log.d("Evaluation", "idUser has been setted");
    }

    private void setIdPlace(final int idPlace)
    {
        assert( idPlace > 0);
        this.idPlace = idPlace;
        Log.d("Evaluation", "idPlace has been setted");
    }

    /**
    *Method: public float getgrade()
    *Description: get grade
    */
    public float getgrade()
    {
        return grade;
    }

    /**
    *Method: public int getIdPlace()
    *Description: get id of place
    */
    public int getIdPlace()
    {
        return idPlace;
    }

    /**
    *Method: public int getIdUser()
    *Description: get id of user
    */
    public int getIdUser()
    {
        return idUser;
    }
}
