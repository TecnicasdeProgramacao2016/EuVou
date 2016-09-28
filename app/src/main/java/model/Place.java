/*
* File name: Place.
* File pourpose: Set Place data.
*/

package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Logger;
import exception.PlaceException;

public class Place
{

    private final String INVALID_NAME = "Nome invalido";
    private final String INVALID_LATITUDE = "Sem a latitude não é possível encontrar o lugar";
    private final String INVALID_LONGITUDE= "Sem a longitude não é possível encontrar o lugar";
    private final String INVALID_COMMENT= "O comentario não pode ser vazio";
    private Logger logger = null;
    private int id = 0;
    private String name = null;
    private ArrayList<String> comment = null;
    private Float evaluate = null;
    private Double longitude = 0.0;
    private Double latitude = 0.0;
    private String phone = "phonr";
    private String operation = "operation";
    private String description = "description";
    private String address = "address";

    public Place(String name, String evaluate, String longitude,
                 String latitude, String operation, String description,
                 String address, String phone) throws PlaceException, ParseException
    {
        setName(name);
        setEvaluate(evaluate);
        setLongitude(longitude);
        setLatitude(latitude);
        setOperation(operation);
        setDescription(description);
        setAddress(address);
        setPhone(phone);

        comment = new ArrayList<>();
    }

    public Place(int id, String name, String evaluate,
                 String longitude, String latitude, String operation,
                 String description, String address, String phone) throws PlaceException, ParseException
    {
        setId(id);
        setName(name);
        setEvaluate(evaluate);
        setLongitude(longitude);
        setLatitude(latitude);
        setOperation(operation);
        setDescription(description);
        setAddress(address);
        setPhone(phone);
    }

    private void setAddress(String address)
    {
        this.address = address;
        logger.info("Adress has been set.");
    }

    public String getName()
    {
        return name;
    }

    private void setName(String name) throws PlaceException
    {
        if(name.isEmpty())
            throw new PlaceException(INVALID_NAME);
        else
            //NOTHING TO DO

        this.name = name;
        logger.info("Name has been set.");
    }

    public ArrayList<String> getComment()
    {
        return comment;
    }

    public void addComment(String comment) throws PlaceException
    {
        if(comment == null)
            throw new PlaceException(INVALID_COMMENT);
        else if(comment.isEmpty())
            throw new PlaceException(INVALID_COMMENT);
        else
            //NOTHING TO DO

        this.comment.add(comment);
        logger.info("Comment has been adicionated.");
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public String getAddress()
    {
        return address;
    }

    private void setLatitude(String latitude) throws ParseException, PlaceException
    {
        if(latitude.isEmpty())
            throw new PlaceException(INVALID_LATITUDE);
        else
            //NOTHING TO DO
        this.latitude = Double.parseDouble(latitude);
        logger.info("Latitude has been set.");
    }

    private void setLongitude(String longitude) throws ParseException, PlaceException
    {
        if(longitude.isEmpty())
            throw new PlaceException(INVALID_LONGITUDE);
        else
            //NOTHING TO DO
        this.longitude = Double.parseDouble(longitude);
        logger.info("Longitude has been set.");
    }

    private void setEvaluate(String evaluate) throws NumberFormatException
    {
        if(evaluate.equals("null")) {
            this.evaluate = 0.0F;
        }
        else
        {
            this.evaluate = Float.parseFloat(evaluate);
            logger.info("Evaluate has been set.");
        }
    }

    private void setOperation(String operation)
    {
        this.operation = operation;
        logger.info("Operation has been set.");
    }

    private void setDescription(String description)
    {
        this.description = description;
        logger.info("Description has been set.");
    }

    public String getOperation()
    {
        return operation;
    }

    public Float getEvaluate()
    {
        return evaluate;
    }

    public String getDescription()
    {
        return description;
    }

    public String getPhone()
    {
        return phone;
    }

    private void setPhone(String phone)
    {
        this.phone = phone;
        logger.info("Phone has been set.");
    }

    public int getId()
    {
        return id;
    }

    private void setId(int id)
    {
        this.id = id;
        logger.info("Id has been set.");
    }
}
