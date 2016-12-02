/*
* File name: Place.
* File pourpose: Set Place data.
*/

package model;

import android.util.Log;
import java.text.ParseException;
import java.util.ArrayList;
import exception.PlaceException;

/**
*Class: public class Place
*Description: Class to place
*/
public class Place
{

    private final String INVALID_NAME = "Nome invalido";
    private final String INVALID_LATITUDE = "Sem a latitude não é possível encontrar o lugar";
    private final String INVALID_LONGITUDE= "Sem a longitude não é possível encontrar o lugar";
    private final String INVALID_COMMENT= "O comentario não pode ser vazio";
    private int id = 0; //has to be above 0
    private String name = null;
    private ArrayList<String> comment = null;
    private Float evaluate = null;
    private Double longitude = 0.0;
    private Double latitude = 0.0;
    private String phone = "phonr";
    private String operation = "operation";
    private String description = "description";
    private String address = "address";

    /**
    *Method: public Place(String name, String evaluate, String longitude,
    *                     String latitude, String operation, String description,
    *                     String address, String phone) throws PlaceException, ParseException
    *Description: sets Place variables
    * @param name
    * @param evaluate
    * @param longitude
    * @param latitude
    * @param operation
    * @param description
    * @param address
    * @param phone
    * @throws PlaceException
    * @throws ParseException
    */
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

    /**
    *Method: public Place(int id, String name, String evaluate,
    *                     String longitude, String latitude, String operation,
    *                     String description, String address, String phone) throws PlaceException, ParseException
    *Description: sets id of event
    * @param id has to be above 0
    * @param name
    * @param evaluate
    * @param longitude
    * @param latitude
    * @param operation
    * @param description
    * @param address
    * @param phone
    * @throws PlaceException
    * @throws ParseException
    */
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
        Log.d("Place", "address has been set");
    }

    /**
    *Method: public String getName()
    *Description: gets name
    */
    public String getName()
    {
        return name;
    }

    /**
    *Method: private void setName(String name) throws PlaceException
    *Description: sets id of event
    *@param name
    */
    private void setName(String name) throws PlaceException
    {
        if(name.isEmpty())
        {
            throw new PlaceException(INVALID_NAME);
        }
        else
        {
            //NOTHING TO DO
        }

        this.name = name;
        Log.d("Place", "name has been set");
    }

    /**
    *Method: public ArrayList<String> getComment()
    *Description: gets comment
    */
    public ArrayList<String> getComment()
    {
        return comment;
    }

    /**
    *Method: public void addComment(String comment) throws PlaceException
    *Description: add comments
    *@param comment
    */
    public void addComment(String comment) throws PlaceException
    {
        if(comment == null)
        {
            throw new PlaceException(INVALID_COMMENT);
        }
        else if(comment.isEmpty())
        {
            throw new PlaceException(INVALID_COMMENT);
        }
        else
        {
            //NOTHING TO DO
        }

        this.comment.add(comment);
        Log.d("Place", "comment has been set");
    }

    /**
    *Method: public Double getLongitude()
    *Description: gets longitude
    */
    public Double getLongitude()
    {
        return longitude;
    }

    /**
    *Method: Double getLatitude()
    *Description: gets latitude
    */
    public Double getLatitude()
    {
        return latitude;
    }

    /**
    *Method: public String getAddress()
    *Description: gets adress
    */
    public String getAddress()
    {
        return address;
    }

    private void setLatitude(String latitude) throws ParseException, PlaceException
    {
        if(latitude.isEmpty())
        {
            throw new PlaceException(INVALID_LATITUDE);
        }
        else
        {
            //NOTHING TO DO
        }
        this.latitude = Double.parseDouble(latitude);
        Log.d("Place", "latitude has been set");
    }

    private void setLongitude(String longitude) throws ParseException, PlaceException
    {
        if(longitude.isEmpty())
        {
            throw new PlaceException(INVALID_LONGITUDE);
        }
        else
        {
            //NOTHING TO DO
        }
        this.longitude = Double.parseDouble(longitude);
        Log.d("Place", "longitude has been set");
    }

    private void setEvaluate(String evaluate) throws NumberFormatException
    {
        if(evaluate.equals("null"))
        {
            this.evaluate = 0.0F;
        }
        else
        {
            this.evaluate = Float.parseFloat(evaluate);
        }
        Log.d("Place", "evaluate has been set");
    }

    private void setOperation(String operation)
    {
        this.operation = operation;
        Log.d("Place", "operation has been set");
    }

    private void setDescription(String description)
    {
        this.description = description;
        Log.d("Place", "description has been set");
    }

    /**
    *Method: public String getOperation()
    *Description: gets operation
    */
    public String getOperation()
    {
        return operation;
    }

    /**
    *Method: public Float getEvaluate()
    *Description: gets evaluate
    */
    public Float getEvaluate()
    {
        return evaluate;
    }

    /**
    *Method: public String getDescription()
    *Description: gets description
    */
    public String getDescription()
    {
        return description;
    }

    /**
    *Method: public String getPhone()
    *Description: gets phone
    */
    public String getPhone()
    {
        return phone;
    }

    private void setPhone(String phone)
    {
        this.phone = phone;
        Log.d("Place", "phone has been set");
    }

    /**
    *Method: public int getId()
    *Description: gets id
    */
    public int getId()
    {
        return id;
    }

    private void setId(final int id)
    {
        assert( id > 0);
        assert( id < 2147483647);
        this.id = id;
        Log.d("Place", "id has been set");
    }
}
