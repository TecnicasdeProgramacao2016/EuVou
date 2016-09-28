/*
* File name: Evaluation.
* File pourpose: Set grade, place id and user id.
*/

package model;

import java.util.logging.Logger;

public class Evaluation
{
    private float grade = 0;
    private int idPlace = 0;
    private int idUser = 0;
    private Logger logger = null;

    public Evaluation(int idPlace, int idUser, float grade)
    {
        setIdPlace(idPlace);
        setIdUser(idUser);
        setGrade(grade);
    }

    private void setGrade(float grade)
    {
        this.grade = grade;
        logger.info("Grade has been set.");
    }

    private  void setIdUser(int idUser)
    {
        this.idUser = idUser;
        logger.info("IdUser has been set.");
    }

    private void setIdPlace(int idPlace)
    {
        this.idPlace = idPlace;
        logger.info("idPlace has been set.");
    }

    public float getgrade()
    {
        return grade;
    }

    public int getIdPlace()
    {
        return idPlace;
    }

    public int getIdUser()
    {
        return idUser;
    }
}
