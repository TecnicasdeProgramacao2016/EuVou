package model;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
//confirmar restrições de tamanho
import exception.EventException;

public class Event {

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
    private static final String CATEGORY_IS_INVALID = "Hey, você esqueceu de informar a categoria do evento, preenche ela aí vai!";


    private int idEvent;
    private String nameEvent;
    private String dateTimeEvent;
    private String description;
    private Double latitude;
    private Double longitude;
    private String adress;
    private Integer evaluation;
    private Integer price;
    private Vector<String> category;

    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_DESCRIPTION = 500;
    private int idOwner;

    /*new Event(nameEvent, dateHourEvent, priceEvent, addressEvent, descriptionEvent,
              latitude, longitude, categories);*/
    public Event(int idOwner, String nameEvent, String dateTimeEvent, Integer price, String address, String description, String latitude, String longitude, Vector<String> category) throws EventException, ParseException{
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

    public Event(int idEvent, int idOwner, String nameEvent, String dateTimeEvent, Integer price, String address, String description, String latitude, String longitude) throws EventException, ParseException{
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

    public Event(int idEvent, String nameEvent, Integer price, String address, String dateTimeEvent, String description,String latitude, String longitude, Vector<String> category) throws EventException, ParseException {
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

    public String getDateTimeEvent() {
        return dateTimeEvent;
    }

    public void setDateTimeEvent(String dateTimeEvent) throws ParseException, EventException {
        /*if(!dateTimeEvent.isEmpty() && dateTimeEvent !=null)
        {
            try{
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                format.setLenient(false);
                Date eventDate = format.parse(dateTimeEvent);

                if(eventDate.after(new Date()))
                {*/
                    this.dateTimeEvent = dateTimeEvent;
               /* }else
                {
                    throw new EventException(INVALID_EVENT_DATE);
                }
            }catch(ParseException exception)
            {
                throw new EventException(INVALID_EVENT_DATE);
            }


        }else
        {
            throw  new EventException(EVENT_DATE_IS_EMPTY);
        }*/

    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) throws  EventException{
        if(evaluation >=1 && evaluation<=5)
        {
            this.evaluation = evaluation;
        }
        else
        {
            throw new EventException(INVALID_EVALUATION);
        }

    }

    public void setPrice(Integer price) throws EventException{
        this.price = price;
    }

    public Integer getPrice(){
        return price;
    }

    public String getAddress() {
        return adress;
    }

    public void setAddress(String adress) throws EventException{
        if(!(adress.isEmpty()) && adress!=null)
        {
            this.adress = adress;
        }else{
            throw new EventException(ADDRESS_IS_EMPTY);
        }

    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) throws EventException{
        if(!(longitude.toString().isEmpty()) && longitude!=null)
        {
            Double longitudeDouble = Double.parseDouble(longitude);
            if(longitudeDouble >= -180 && longitudeDouble <= 180) {
                this.longitude = longitudeDouble;

            }else
            {
                throw  new EventException(LONGITUDE_IS_INVALID);
            }
        }else
        {
            throw new EventException(LONGITUDE_IS_EMPTY);
        }

    }

    public String getNameEvent(){
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) throws EventException {
        if(!nameEvent.isEmpty() && nameEvent!= null) {

            if(nameEvent.length() <= MAX_LENGTH_NAME)
            {
                this.nameEvent = nameEvent;
            }else
            {
                throw new EventException(NAME_CANT_BE_GREATER_THAN_50);
            }
        }else{
            throw new EventException(EVENT_NAME_CANT_BE_EMPTY_NAME);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws EventException{
        if(!description.isEmpty() && description !=null)
        {
            if(description.length() < MAX_LENGTH_DESCRIPTION)
            {
                this.description = description;
            }else
            {
                throw new EventException(DESCRIPTION_CANT_BE_GREATER_THAN);
            }

        }else
        {
            throw new EventException(DESCRIPTION_CANT_BE_EMPTY);
        }

    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) throws EventException{
        if(!(latitude.toString().isEmpty()) && latitude!=null)
        {
            Double latitudeDouble = Double.parseDouble(latitude);
            if(latitudeDouble >= -90 && latitudeDouble <= 90)
            {
                this.latitude = latitudeDouble;
            }else
            {
                throw  new EventException(LATITUDE_IS_INVALID);
            }
        }else
        {
            throw  new EventException(LANTITUDE_IS_EMPTY);
        }
    }

    public void setCategory(Vector<String> category) throws EventException{
        if(category!=null && !category.isEmpty()){
            this.category = category;
        }else{
            throw  new EventException(CATEGORY_IS_INVALID);
        }
    }

    public Vector<String> getCategory()
    {
        return category;
    }


    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }
}