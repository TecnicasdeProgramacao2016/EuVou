package model;

public class Evaluation
{
    private float grade = 0;
    private int idPlace = 0;
    private int idUser = 0;

    public Evaluation(int idPlace, int idUser, float grade)
    {
        setIdPlace(idPlace);
        setIdUser(idUser);
        setGrade(grade);
    }

    private void setGrade(float grade)
    {
        this.grade = grade;
    }

    private  void setIdUser(int idUser)
    {
        this.idUser = idUser;
    }

    private void setIdPlace(int idPlace)
    {
        this.idPlace = idPlace;
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
