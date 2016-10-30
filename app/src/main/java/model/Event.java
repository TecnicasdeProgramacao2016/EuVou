/*
* File name: Event.
* File pourpose: Model of Event.
*/

package model;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import exception.EventException;

/**
*Class: public class Event
*Description: Class of event
*/
public class Event
{
    public static final String EVENT_NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos informar o nome do evento.";
    public static final String NAME_CANT_BE_GREATER_THAN_50 = "Hey, você ultrapassou o número de caracteres permitido para o nome do evento, tente novamente.";
    public static final String DESCRIPTION_CANT_BE_EMPTY = "Hey, acho que você esqueu de informar a descrição do evento.";
    public static final String DESCRIPTION_CANT_BE_GREATER_THAN = "Hey, o máximo de caractéres para descrever um evento é de 500 caracteres";
    public static final String LATITUDE_IS_INVALID = "Hey, você inseriu um número inválido, a latitude deve ser maior que -90 e menor que 90!";
    public static final String LONGITUDE_IS_INVALID = "Hey, você inseriu um número inválido, a longitude deve ser maior que -180 e menor que 180";
    public static final String LONGITUDE_IS_EMPTY = "Hey, você deixou a longitude em branco... preenche ela aí vai!";
    public static final String LANTITUDE_IS_EMPTY = "Hey, você deixou a longitude em branco... preenche ela aí vai!";
    public static final String INVALID_EVALUATION = "Hey, você deve avaliar um evento com notas de 1 a 5!";
    public static final String ADDRESS_IS_EMPTY = "Hey, você esqueceu de nos informar o endereço do evento!";
    public static final String INVALID_EVENT_DATE = "Hey, você informou uma data errada, pay attention guy!";
    public static final String EVENT_DATE_IS_EMPTY = "Hey, você esqueceu de informar a data do evento, cuidado!";
    public static final String PRICE_REAL_IS_EMPTY = "Hey, você esqueceu de informar a parte real do preço";
    public static final String PRICE_DECIMAL_IS_EMPTY = "Hey, você esqueceu de informar a parte decimal do preço";
    public static final String INVALID_EVENT_HOUR = "Hey, você informou uma hora inválida";
    public static final String EVENT_HOUR_IS_EMPTY = "Hey, você esqueceu de informar a hora";
    public static final String CATEGORY_IS_INVALID = "Hey, você esqueceu de informar a categoria do evento, preenche ela aí vai!";

    private int idEvent = 0;//has to be above 0
    private String nameEvent = "nameEvent";
    private String dateTimeEvent = "dateTimeEvent";
    private String description = "description";
    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private String adress = "adress";
    private Integer evaluation = null;
    private Integer price = null;
    private Vector<String> category = null;
    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_DESCRIPTION = 500;
    private int idOwner = 0;

    /**
    * Method: public Event(int idOwner, String nameEvent, String date,
    *                      String hour, String priceReal, String priceDecimal,
    *                      String address, String description, String latitude,
    *                      String longitude, Vector<String> category) throws EventException, ParseException
    *Description: sets fields of event
    * @param idOwner has to be above 0
    * @param nameEvent
    * @param date
    * @param hour
    * @param priceReal
    * @param priceDecimal
    * @param address
    * @param description
    * @param latitude
    * @param longitude
    * @param category
    * @throws EventException
    * @throws ParseException
    */
    public Event(int idOwner, String nameEvent, String date,
                      String hour, String priceReal, String priceDecimal,
                      String address, String description, String latitude,
                      String longitude, Vector<String> category) throws EventException, ParseException
    {
        setIdOwner(idOwner);
        setNameEvent(nameEvent);
        setDateTimeEvent(date, hour);
        setPrice(priceReal, priceDecimal);
        setAddress(address);
        setDescription(description);
        setLatitude(latitude);
        setLongitude(longitude);
        setCategory(category);
    }

    /**
     *Method: public Event(int idOwner, String nameEvent, String dateTimeEvent,
     *                     Integer price, String address, String description,
     *                     String latitude, String longitude,
     *                     Vector<String> category) throws EventException, ParseException
     *Description: sets fields of event
     * @param idOwner has to be above 0
     * @param nameEvent
     * @param dateTimeEvent
     * @param price
     * @param address
     * @param description
     * @param latitude
     * @param longitude
     * @param category
     * @throws EventException
     * @throws ParseException
     */
    public Event(int idOwner, String nameEvent, String dateTimeEvent,
                      Integer price, String address, String description,
                      String latitude, String longitude,
                      Vector<String> category) throws EventException, ParseException
    {
        setIdOwner(idOwner);
        setNameEvent(nameEvent);
        setDateTimeEvent(dateTimeEvent);
        setPrice(price);
        setAddress(address);
        setDescription(description);
        setLatitude(latitude);
        setLongitude(longitude);
        setCategory(category);
    }

    /**
    *Method: public Event(int idOwner, String nameEvent,
    *                              int eventEvaluation) throws EventException, ParseException
    *Description: sets fields of event
    *@param idOwner has to be above 0
    *@param nameEvent
    *@param eventEvaluation
    */
    public Event(int idOwner, String nameEvent,
                      int eventEvaluation) throws EventException, ParseException
    {
        setIdOwner(idOwner);
        setNameEvent(nameEvent);
        setEvaluation(eventEvaluation);
    }

    /**
    *Method: public Event(int idEvent, int idOwner, String nameEvent,
    *                              String dateTimeEvent, Integer price,
    *                              String address, String description,
    *                              String latitude, String longitude) throws EventException, ParseException
    *Description: sets fields of event
    *@param idEvent has to be above 0
    *@param idOwner has to be above 0
    *@param nameEvent
    *@param dateTimeEvent
    *@param price
    *@param address
    *@param description
    *@param latitude
    *@param longitude
    */
    public Event(int idEvent, int idOwner, String nameEvent,
                 String dateTimeEvent, Integer price,
                 String address, String description,
                 String latitude, String longitude) throws EventException, ParseException
    {
        setIdEvent(idEvent);
        setIdOwner(idOwner);
        setNameEvent(nameEvent);
        setDateTimeEvent(dateTimeEvent);
        setPrice(price);
        setAddress(address);
        setDescription(description);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    /**
    *Method: public Event(int idEvent, int idOwner, String nameEvent,
    *                              String dateTimeEvent, Integer price,
    *                              String address, String description,
    *                              String latitude, String longitude) throws EventException, ParseException
    *Description: sets fields of event
    *@param idEvent has to be above 0
    *@param nameEvent
    *@param price
    *@param address
    *@param dateTimeEvent
    *@param description
    *@param latitude
    *@param longitude
    *@param category
    */
    public Event(int idEvent, String nameEvent, Integer price,
                      String address, String dateTimeEvent,
                      String description,String latitude,
                      String longitude, Vector<String> category) throws EventException, ParseException
    {
        setIdEvent(idEvent);
        setNameEvent(nameEvent);
        setAddress(address);
        setPrice(price);
        setDateTimeEvent(dateTimeEvent);
        setDescription(description);
        setLatitude(latitude);
        setLongitude(longitude);
        setCategory(category);
    }

    /**
    *Method: public String getDateTimeEvent()
    *Description: get date time event
    */
    public String getDateTimeEvent()
    {
        return dateTimeEvent;
    }

    /**
    *Method: public void setDateTimeEvent(String dateTimeEvent)
    *Description: sets date time of event
    *@param dateTimeEvent
    */
    public void setDateTimeEvent(String dateTimeEvent)
    {
        this.dateTimeEvent=dateTimeEvent;
        Log.d("Event", "dateTimeEvent has been setted");
    }

    /**
    *Method: public void setDateTimeEvent(String date, String hour) throws ParseException, EventException
    *Description: sets date time of event
    *@param date
    *@param hour
    */
    public void setDateTimeEvent(String date, String hour) throws ParseException, EventException
    {
        if(!date.isEmpty() && date != null && !hour.isEmpty() && hour!=null)
        {
            try
            {
                SimpleDateFormat formatDate = new SimpleDateFormat("dd/mm/yyyy");
                formatDate.setLenient(false);
                Date eventDate = formatDate.parse(date);

                if(eventDate.after(new Date()))
                {
                    String[] dateEventSplit = date.split("/");
                    date = dateEventSplit[2]+"-"+dateEventSplit[1]+"-"+dateEventSplit[0];
                }
                else
                {
                    throw new EventException(INVALID_EVENT_DATE);
                }
            } catch (ParseException exception)
            {
                throw new EventException(INVALID_EVENT_DATE);
            }

            try
            {
                SimpleDateFormat formatHour = new SimpleDateFormat("hh:mm:ss");
                formatHour.setLenient(false);
                formatHour.parse(hour);

                this.dateTimeEvent = date + " " + hour;
            } catch (ParseException exception)
            {
                throw new EventException(INVALID_EVENT_HOUR);
            }
        }
        else
        {
            if(date.isEmpty() || date==null)
            {
                throw new EventException(EVENT_DATE_IS_EMPTY);
            }
            else if(hour.isEmpty() || hour==null)
            {
                throw new EventException(EVENT_HOUR_IS_EMPTY);
            }
            else
            {
                //NOTHING TO DO
            }
        }
        Log.d("Event", "dateTimeEvent has been setted");
    }

    /**
    *Method: public Integer getEvaluation()
    *Description: get evaluation
    */
    public Integer getEvaluation()
    {
        return evaluation;
    }

    /**
    *Method: public void setEvaluation(Integer evaluation) throws  EventException
    *Description: sets evaluation
    *@param evaluation
    */
    public void setEvaluation(Integer evaluation) throws  EventException
    {
        final int minEvaluation = 1;
        final int maxEvaluation = 5;
        if(evaluation >= minEvaluation && evaluation <= maxEvaluation)
        {
            this.evaluation = evaluation;
        }
        else
        {
            throw new EventException(INVALID_EVALUATION);
        }
        Log.d("Event", "evaluation has been set");
    }

    /**
    *Method: public void setPrice(String priceReal, String priceDecimal) throws EventException
    *Description: sets price
    *@param priceReal
    *@param priceDecimal
    */
    public void setPrice(String priceReal, String priceDecimal) throws EventException
    {
        if(priceReal != null && priceDecimal != null && !priceReal.isEmpty() && !priceDecimal.isEmpty())
        {
            Integer priceEventReal = Integer.parseInt(priceReal);
            Integer priceEventDecimal = Integer.parseInt(priceDecimal);

            Integer price = priceEventReal * 100 + priceEventDecimal;

            this.price=price;
        }
        else
        {
            if(priceReal == null || priceReal.isEmpty())
            {
                throw new EventException(PRICE_REAL_IS_EMPTY);
            }
            else if(priceDecimal == null || priceDecimal.isEmpty())
            {
                throw new EventException(PRICE_DECIMAL_IS_EMPTY);
            }
            else
            {
                //NOTHING TO DO
            }
        }
        Log.d("Event", "price has been set");
    }

    /**
    *Method: public void setPrice(Integer price)
    *Description: sets price
    *@param price
    */
    public void setPrice(Integer price)
    {
        this.price=price;
        Log.d("Event", "price has been set");
    }

    /**
    *Method: public Integer getPrice()
    *Description: gets price
    */
    public Integer getPrice()
    {
        return price;
    }

    /**
    *Method: public String getAddress()
    *Description: get adress
    */
    public String getAddress()
    {
        return adress;
    }

    /**
    *Method: public void setAddress(String adress) throws EventException
    *Description: sets adress
    *@param adress
    */
    public void setAddress(String adress) throws EventException
    {
        if(!(adress.isEmpty()) && adress != null)
        {
            this.adress = adress;
        }
        else
        {
            throw new EventException(ADDRESS_IS_EMPTY);
        }
        Log.d("Event", "address has been set");

    }

    /**
    *Method: public int getIdEvent()
    *Description: gets id of event
    */
    public int getIdEvent()
    {
        return idEvent;
    }

    /**
    *Method: public void setIdEvent(int idEvent)
    *Description: set id of event
    *@param idEvent has to be above 0
    */
    public void setIdEvent(final int idEvent)
    {
        assert( idEvent > 0);
        assert( idEvent < 2147483647);
        this.idEvent = idEvent;
        Log.d("Event", "idEvent has been set");
    }

    /**
    *Method: Double getLongitude()
    *Description: get longitude
    */
    public Double getLongitude()
    {
        return longitude;
    }

    /**
    *Method: public void setLongitude(String longitude) throws EventException
    *Description: sets longitude
    *@param longitude
    */
    public void setLongitude(String longitude) throws EventException
    {
        final int minLongitude = -180;
        final int maxLongitude = 180;
        if(!(longitude.toString().isEmpty()) && longitude != null)
        {
            Double longitudeDouble = Double.parseDouble(longitude);
            if(longitudeDouble >= minLongitude && longitudeDouble <= maxLongitude)
            {
                this.longitude = longitudeDouble;

            }
            else
            {
                throw  new EventException(LONGITUDE_IS_INVALID);
            }
        }
        else
        {
            throw new EventException(LONGITUDE_IS_EMPTY);
        }
        Log.d("Event", "longitude has been set");
    }

    /**
    *Method: public String getNameEvent()
    *Description: get name of event
    */
    public String getNameEvent()
    {
        return nameEvent;
    }

    /**
    *Method: public void setNameEvent(String nameEvent) throws EventException
    *Description: sets name of event
    *@param nameEvent
    */
    public void setNameEvent(String nameEvent) throws EventException
    {
        if(!nameEvent.isEmpty() && nameEvent != null)
        {

            if(nameEvent.length() <= MAX_LENGTH_NAME)
            {
                this.nameEvent = nameEvent;
            }
            else
            {
                throw new EventException(NAME_CANT_BE_GREATER_THAN_50);
            }
        }
        else
        {
            throw new EventException(EVENT_NAME_CANT_BE_EMPTY_NAME);
        }
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
    *Method: public void setDescription(String description) throws EventException
    *Description: sets description
    *@param description
    */
    public void setDescription(String description) throws EventException
    {
        if(!description.isEmpty() && description != null)
        {
            if(description.length() < MAX_LENGTH_DESCRIPTION)
            {
                this.description = description;
            }
            else
            {
                throw new EventException(DESCRIPTION_CANT_BE_GREATER_THAN);
            }

        }
        else
        {
            throw new EventException(DESCRIPTION_CANT_BE_EMPTY);
        }

    }

    /**
    *Method: public Double getLatitude()
    *Description: gets latitude
    */
    public Double getLatitude()
    {
        return latitude;
    }

    /**
    *Method: public void setLatitude(String latitude) throws EventException
    *Description: sets latitude
    *@param latitude
    */
    public void setLatitude(String latitude) throws EventException
    {
        final int minLatitude = -90;
        final int maxLatitude = 90;
        if(!(latitude.toString().isEmpty()) && latitude!=null)
        {
            Double latitudeDouble = Double.parseDouble(latitude);
            if(latitudeDouble >= minLatitude && latitudeDouble <= maxLatitude)
            {
                this.latitude = latitudeDouble;
            }
            else
            {
                throw  new EventException(LATITUDE_IS_INVALID);
            }
        }
        else
        {
            throw  new EventException(LANTITUDE_IS_EMPTY);
        }
    }

    /**
    *Method: public void setCategory(Vector<String> category) throws EventException
    *Description: sets category
    *@param category
    */
    public void setCategory(Vector<String> category) throws EventException
    {
        if(category != null && !category.isEmpty())
        {
            this.category = category;
        }
        else
        {
            throw  new EventException(CATEGORY_IS_INVALID);
        }
    }

    /**
    *Method: public Vector<String> getCategory()
    *Description: gets category
    */
    public Vector<String> getCategory()
    {
        return category;
    }


    /**
    *Method: public int getIdOwner()
    *Description: gets id of owner
    */
    public int getIdOwner()
    {
        return idOwner;
    }

    /**
    *Method: public void setIdOwner(int idOwner)
    *Description: sets id of owner
    *@param idOwner has to be above 0
    */
    public void setIdOwner(final int idOwner)
    {
        assert( idOwner > 0);
        assert( idOwner < 2147483647);
        this.idOwner = idOwner;
        Log.d("Event", "idOwner has been set");
    }
}