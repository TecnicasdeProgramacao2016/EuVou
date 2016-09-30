/*
* File name: Evaluation.
* File pourpose: Set grade, place id and user id.
*/

package model;

import junit.framework.TestCase;

/**
*Class: public class Evaluation
*Description: class to set evaluation
*/
public class Evaluation
{
    private float grade = 0;
    private int idPlace = 0;
    private int idUser = 0;
    /**
    *Method: public Evaluation(int idPlace, int idUser, float grade)
    *Description: sets values to evaluation
    *@param int idPlace
    *@param int idUser
    *@param float grade
    */
    public Evaluation(int idPlace, int idUser, float grade)
    {
        setIdPlace(idPlace);
        setIdUser(idUser);
        setGrade(grade);
    }

    /**
    *Method: private void setGrade(float grade)
    *Description: sets grade
    *@param float grade
    */
    private void setGrade(float grade)
    {
        this.grade = grade;
    }

    /**
    *Method: private void setIdUser(int idUser)
    *Description: sets if user
    *@param int idUser
    */
    private void setIdUser(int idUser)
    {
        this.idUser = idUser;
    }

    /**
    *Method: private void setIdPlace(int idPlace)
    *Description: sets values to view place
    *@param int idPlace
    */
    private void setIdPlace(int idPlace)
    {
        this.idPlace = idPlace;
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
