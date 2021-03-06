/*
* File name: EditEventFragment.
* File pourpose: Format fragment text.
*/


package com.mathheals.euvou.controller.edit_event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.event_registration.LocalEventActivity;
import com.mathheals.euvou.controller.showPlaceRanking.ShowTop5Rank;
import com.mathheals.euvou.controller.utility.EditAndRegisterUtility;
import com.mathheals.euvou.controller.utility.Mask;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Vector;

import dao.CategoryDAO;
import dao.EventCategoryDAO;
import dao.EventDAO;
import exception.EventException;
import model.Event;

public class EditEventFragment extends Fragment implements View.OnClickListener
{
    public Vector<String> categories = new Vector<>(); //Contains all possible categories
    private EditAndRegisterUtility  editAndRegisterUtility =
            new EditAndRegisterUtility();  //This variable is responsable for showing error mesages to the user

    //Constructor
    public EditEventFragment()
    {
        // Required empty public constructor
    }

    private EditText nameField = null, dateField = null, hourField = null,
            descriptionField = null, addressField = null,
            priceDecimalField = null, priceRealField = null; //Class to edit event content


    //Sets Text
    private void setingEditText(View view)
    {
        this.nameField = (EditText) view.findViewById(R.id.eventName);
        this.dateField = (EditText) view.findViewById(R.id.eventDate);
        this.hourField = (EditText) view.findViewById(R.id.eventHour);
        this.descriptionField = (EditText) view.findViewById(R.id.eventDescription);
        this.priceRealField = (EditText) view.findViewById(R.id.eventPriceReal);
        this.priceDecimalField = (EditText) view.findViewById(R.id.eventPriceDecimal);
        this.addressField = (EditText) view.findViewById(R.id.eventAddress);

        Log.d("EditEventFragment", "Edit text sucessfuly setted");
    }

    private String latitude = null; //Event's latitude
    private String longitude = null; //Event's longitude

    private int idEvent = 0; //Event's ID
    private CheckBox showCheckBox = null, expositionCheckBox = null, cinemaCheckBox = null,
            museumCheckBox = null, theaterCheckBox = null, educationCheckBox = null,
            othersCheckBox = null,sportsCheckBox = null, partyCheckBox = null; //Event's categorie

    @Override
    /*
     * Override View to change event's atributtes values, at firts get the values
     *  from the DB and displays them as the create event screen but filled
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        //Instancing View Elements
        idEvent = this.getArguments().getInt("idEvent");
        View view = inflater.inflate(R.layout.fragment_edit_event, container, false);
        EventDAO eventDAO = new EventDAO(getActivity());
        EventCategoryDAO eventCategoryDAO = new EventCategoryDAO(getActivity());
        CategoryDAO categoryDAO = new CategoryDAO(getActivity());
        JSONObject jsonEvent = eventDAO.searchEventById(idEvent);
        JSONObject jsonEventCategory = eventCategoryDAO.searchCategoriesByEventId(idEvent);

        //Setting elements on view
        setEventsAtributtesButCategory (jsonEvent);
        setEventsCategory(jsonEventCategory, categoryDAO);
        setingEditText(view);
        dateField.addTextChangedListener(Mask.insert("##/##/####", dateField));
        setingCheckBoxs(view);


        //Adding listener to eventLocal EditText
        Button eventLocal = (Button) view.findViewById(R.id.eventLocal);
        eventLocal.setOnClickListener(this);

        //Adding listener to CheckBoxs to verify if each CheckBox is checked or not
        addCheckBoxListeners(view);

        //Adding buttons to remove and Add events
        Button removeEvent = (Button)view.findViewById(R.id.removeEvent);
        removeEvent.setOnClickListener(this);
        Button updateEvent = (Button)view.findViewById(R.id.updateEvent);
        updateEvent.setOnClickListener(this);


        return view;
    }

    //Sets all atributtes but categorie
    private void setEventsAtributtesButCategory (JSONObject jsonEvent)
    {
        try
        {
            //This block intances event atributtes
            String nameEvent = jsonEvent.getJSONObject("0").getString("nameEvent");
            String descriptionEvent = jsonEvent.getJSONObject("0").getString("description");
            String addressEvent = jsonEvent.getJSONObject("0").getString("address");

            //Atributtes are setted
            nameField.setText(nameEvent);
            descriptionField.setText(descriptionEvent);
            addressField.setText(addressEvent);

            formatDate(jsonEvent);
            formatPrice(jsonEvent);

            latitude = jsonEvent.getJSONObject("0").getString("latitude");
            longitude = jsonEvent.getJSONObject("0").getString("longitude");

            if(latitude == null || longitude == null)
            {
                Log.d("EditEventFragment", "Valores inválidos de longitude e latitude");
            }
            else
            {
                //NOTHINHG TO DO
            }
        } catch (JSONException jsonException)
        {
            jsonException.printStackTrace();
        }
    }

    //Sets categorie
    private void setEventsCategory(JSONObject jsonEventCategory,CategoryDAO categoryDAO)
    {
        try {
            Vector <Integer> idCategories = new Vector<>();
            String idCategory;
            String counterString;
            final String ID_CATEGORY = "idCategory";
            JSONObject vector;

            /*
             * As known, events can have many categories, this structure get all chosed
             * options and save then ad idCategories
             */
            for (int counter = 0; counter < jsonEventCategory.length(); counter++) {

                //Parses Counter do String
                counterString = Integer.toString(counter);
                //Get Categories from DataBase
                vector = jsonEventCategory.getJSONObject(counterString);
                //Get Categorie ID
                idCategory = vector.getString(ID_CATEGORY);
                //Add id Category to the vector of Categories IDs
                idCategories.add(Integer.parseInt(idCategory));
            }

            /*
             * This structure searchs all categories from a certain event using IdCategories,
             * the previous structure, and displays them to the user as he wants to edit an event
             */
            for (int i = 0; i < idCategories.size(); i++) {
                JSONObject jsonCategory = categoryDAO.searchCategoryById(idCategories.get(i));
                String nameCategory = jsonCategory.getJSONObject("0").getString("nameCategory");

                switch (nameCategory) {
                    case "Show":
                        showCheckBox.setChecked(true);
                        categories.add("Show");
                        break;
                    case "Teatro":
                        theaterCheckBox.setChecked(true);
                        categories.add("Teatro");
                        break;
                    case "Cinema":
                        cinemaCheckBox.setChecked(true);
                        categories.add("Cinema");
                        break;
                    case "Balada":
                        partyCheckBox.setChecked(true);
                        categories.add("Balada");
                        break;
                    case "Museu":
                        museumCheckBox.setChecked(true);
                        categories.add("Museu");
                        break;
                    case "Educacao":
                        educationCheckBox.setChecked(true);
                        categories.add("Educacao");
                        break;
                    case "Exposicao":
                        expositionCheckBox.setChecked(true);
                        categories.add("Exposicao");
                        break;
                    case "Esporte":
                        sportsCheckBox.setChecked(true);
                        categories.add("Esporte");
                        break;
                    case "Outros":
                        othersCheckBox.setChecked(true);
                        break;
                    default:
                        //NOTHING TO DO
                }
            }

            Log.d("EditEventFragment", "Check box clicked");
        }
        catch (JSONException jsonException)
        {
            jsonException.printStackTrace();
        }
    }

    //Format Date
    public void formatDate(JSONObject jsonEvent) throws JSONException
    {
        //Atributtes used to format date
        String dateHourEvent = jsonEvent.getJSONObject("0").getString("dateTimeEvent"); //Date and hour content
        String[] dateHourEventSplit = dateHourEvent.split(" "); //Date and hour content splitted in 2
        String dateEvent = dateHourEventSplit[0]; //Date content
        String[] dateEventSplit = dateEvent.split("-"); //Date content splitted in 3: day, mounth and year
        dateEvent = dateEventSplit[2] + "/" + dateEventSplit[1] + "/" + dateEventSplit[0];
        String hourEvent = dateHourEventSplit[1]; //Hour content;

        //Sets date and hour
        this.dateField.setText(dateEvent);
        if(Integer.parseInt(hourEvent) < 0 || Integer.parseInt(hourEvent) > 24)
        {
            Log.d("EditEventFragment", "Invalid Time");
        }
        this.hourField.setText(hourEvent);

        Log.d("EditEventFragment", "Date sucessfuly formated");
    }

    //Format Price
    public void formatPrice(JSONObject jsonEvent) throws JSONException
    {
        Integer priceEvent = jsonEvent.getJSONObject("0").getInt("price");

        //At this line, gets the price of the field, turn into string with the right value
        this.priceDecimalField.setText(Integer.toString(priceEvent - priceEvent / 100 * 100));
        this.priceRealField.setText(Integer.toString(priceEvent / 100));
        Log.d("EditEventFragment", "Price sucessfuly formated");
    }



    //Sets checkbox
    private void setingCheckBoxs(View view)
    {
        this.showCheckBox = (CheckBox) view.findViewById(R.id.optionShow);
        this.expositionCheckBox = (CheckBox) view.findViewById(R.id.optionExposition);
        this.cinemaCheckBox = (CheckBox) view.findViewById(R.id.optionCinema);
        this.theaterCheckBox = (CheckBox) view.findViewById(R.id.optionTheater);
        this.partyCheckBox = (CheckBox) view.findViewById(R.id.optionParty);
        this.educationCheckBox = (CheckBox) view.findViewById(R.id.optionEducation);
        this.museumCheckBox = (CheckBox) view.findViewById(R.id.optionMuseum);
        this.sportsCheckBox = (CheckBox) view.findViewById(R.id.optionSports);
        this.othersCheckBox = (CheckBox) view.findViewById(R.id.optionOthers);

        Log.d("EditEventFragment", "Check box sucessfuly setted");
    }

    private static final String SUCCESSFULL_UPDATE_MESSAGE = "Evento alterado com sucesso :)";


    //Add EventCategories
    private void addEventCategories(View view)
    {

        //This if-else structure sets checked events as event's categorie
        if(view.getId() == R.id.optionCinema)
        {
            CheckBox cinemaCheckBox = (CheckBox) view;

            setAsChecked(cinemaCheckBox);
        }
        else if(view.getId() == R.id.optionEducation)
        {
            CheckBox educationCheckBox = (CheckBox) view;

            setAsChecked(educationCheckBox);
        }
        else if(view.getId() == R.id.optionExposition)
        {
            CheckBox expositionCheckBox = (CheckBox) view;

            setAsChecked(expositionCheckBox);
        }
        else if(view.getId() == R.id.optionMuseum)
        {
            CheckBox museumCheckBox = (CheckBox) view;

            setAsChecked(museumCheckBox);
        }
        else if(view.getId() == R.id.optionOthers)
        {
            CheckBox othersCheckBox = (CheckBox) view;

            setAsChecked(othersCheckBox);
        }
        else if(view.getId() == R.id.optionParty)
        {
            CheckBox partyCheckBox = (CheckBox) view;

            setAsChecked(partyCheckBox);
        }
        else if(view.getId() == R.id.optionShow)
        {
            CheckBox showCheckBox = (CheckBox) view;

            setAsChecked(showCheckBox);
        }
        else if(view.getId() == R.id.optionSports)
        {
            CheckBox sportsCheckBox = (CheckBox) view;

            setAsChecked(sportsCheckBox);
        }
        else if(view.getId() == R.id.optionTheater)
        {
            CheckBox theaterCheckBox = (CheckBox) view;

            setAsChecked(theaterCheckBox);
        }
        else
        {
            //NOTHING TO DO
        }

        Log.d("EditEventFragment", "Event type setted");
    }

    private void setAsChecked (final CheckBox checkBox)
    {
        if(checkBox.isChecked())
        {
            categories.add(checkBox.getText().toString());
        }
        else
        {
            categories.remove(checkBox.getText().toString());
        }
    }

    /**
     * Method: public void onClick
     * Overrides are used to rewrite methods.
     * This override sets or removes events as user's will.
     * @param view
     */

    @Override
    //Override ClickAction
    public void onClick(View view)
    {
        //This if-else structure checks user's option and displays an event information
        if(view.getId() == R.id.updateEvent)
        {
            updateEvent();
        }
        else if(view.getId() == R.id.removeEvent)
        {
            removeEvent(idEvent);
        }
        else if(view.getId() == R.id.eventLocal)
        {
            Intent map = new Intent(getActivity(), LocalEventActivity.class);
            startActivityForResult(map, 2);
        }
        else
        {
            addEventCategories(view);
        }
    }



    /**
     * Method: private void addCheckBoxListeners
     * Listeners are used to check user's actions.
     * This checks the category option
     * @param view
     */

    private void addCheckBoxListeners(View view)
    {

        CheckBox showCategory = (CheckBox) view.findViewById(R.id.optionShow);
        showCategory.setOnClickListener(this);

        CheckBox expositionCategory = (CheckBox) view.findViewById(R.id.optionExposition);
        expositionCategory.setOnClickListener(this);

        CheckBox museumCategory = (CheckBox) view.findViewById(R.id.optionMuseum);
        museumCategory.setOnClickListener(this);

        CheckBox cinemaCategory = (CheckBox) view.findViewById(R.id.optionCinema);
        cinemaCategory.setOnClickListener(this);

        CheckBox theaterCategory = (CheckBox) view.findViewById(R.id.optionTheater);
        theaterCategory.setOnClickListener(this);

        CheckBox partyCategory = (CheckBox) view.findViewById(R.id.optionParty);
        partyCategory.setOnClickListener(this);

        CheckBox educationCategory = (CheckBox) view.findViewById(R.id.optionEducation);
        educationCategory.setOnClickListener(this);

        CheckBox sportsCategory = (CheckBox) view.findViewById(R.id.optionSports);
        sportsCategory.setOnClickListener(this);

        CheckBox othersCategory = (CheckBox) view.findViewById(R.id.optionOthers);
        othersCategory.setOnClickListener(this);

    }

    //Update event
    public void updateEvent()
    {
        //Create fields for atributtes
        String nameEvent = nameField.getText().toString();
        String dateEvent = dateField.getText().toString();
        String[] dateEventSplit = dateEvent.split("/");
        dateEvent = dateEventSplit[2] + "-" + dateEventSplit[1] + "-" + dateEventSplit[0];
        String hourEvent = hourField.getText().toString();
        String dateHourEvent = dateEvent + " " + hourEvent;
        String descriptionEvent = descriptionField.getText().toString();
        String addresEvent = addressField.getText().toString();
        Integer eventPriceReal = Integer.parseInt(priceRealField.getText().toString());
        Integer eventPriceDecimal = Integer.parseInt(priceDecimalField.getText().toString());
        Integer priceEvent = eventPriceReal * 100 + eventPriceDecimal;


        checksValuesNotNull(nameEvent, dateEvent, hourEvent, descriptionEvent, addresEvent, priceEvent);

        try
        {
            Event event = new Event(idEvent, nameEvent, priceEvent, addresEvent, dateHourEvent, descriptionEvent,
                    latitude, longitude, categories);

            updateEventOnDataBase(event);

            Toast.makeText(getActivity().getBaseContext(), SUCCESSFULL_UPDATE_MESSAGE, Toast.LENGTH_LONG).show();

            Log.d("EditEventFragment", "Event Updated");

        } catch (EventException eventException)
        {
            warnIfValuesAreInvalid(eventException);

        } catch (ParseException parseException)
        {
            parseException.printStackTrace();

        }
    }

    private void warnIfValuesAreInvalid (EventException eventException)
    {
        final String message = eventException.getMessage().toString();

        //This if-else structure tests if the information given is correct
        if(message.equals(Event.ADDRESS_IS_EMPTY))
        {
            editAndRegisterUtility.setMessageError(addressField, message);
        }
        else
        {
            //NOTING TO DO
        }

        if(message.equals(Event.DESCRIPTION_CANT_BE_EMPTY))
        {
            editAndRegisterUtility.setMessageError(descriptionField, message);
        }
        else
        {
            //NOTING TO DO
        }

        if(message.equals(Event.DESCRIPTION_CANT_BE_GREATER_THAN))
        {
            editAndRegisterUtility.setMessageError(descriptionField, message);
        }
        else
        {
            //NOTING TO DO
        }

        if(message.equals(Event.EVENT_DATE_IS_EMPTY))
        {
            editAndRegisterUtility.setMessageError(dateField, message);
        }
        else
        {
            //NOTING TO DO
        }

        if(message.equals(Event.EVENT_NAME_CANT_BE_EMPTY_NAME))
        {
            editAndRegisterUtility.setMessageError(nameField, message);
        }
        else
        {
            //NOTING TO DO
        }

        if(message.equals(Event.INVALID_EVENT_DATE))
        {
            editAndRegisterUtility.setMessageError(dateField, message);
        }
        else
        {
            //NOTING TO DO
        }

        if(message.equals(Event.NAME_CANT_BE_GREATER_THAN_50))
        {
            editAndRegisterUtility.setMessageError(nameField, message);
        }
        else
        {
            //NOTING TO DO
        }
    }

    private void checksValuesNotNull(final String nameEvent, final String dateEvent, final String hourEvent,
                              final String descriptionEvent, final String addresEvent, final Integer priceEvent)
    {
        //This structure checks if atributte's values are null
        if(nameEvent == null)
        {
            Log.d("EditEventFragment", "Event name null");
        }
        else
        {
            //NOTHING TO DO
        }
        if (dateEvent == null)
        {
            Log.d("EditEventFragment", "Event date null");
        }
        else
        {
            //NOTHING TO DO
        }
        if (hourEvent == null)
        {
            Log.d("EditEventFragment", "Event hour null");
        }
        else
        {
            //NOTHING TO DO
        }
        if (descriptionEvent == null)
        {
            Log.d("EditEventFragment", "Event description null");
        }
        else
        {
            //NOTHING TO DO
        }
        if (addresEvent == null)
        {
            Log.d("EditEventFragment", "Event adress null");
        }
        else
        {
            //NOTHING TO DO
        }
        if (priceEvent == null)
        {
            Log.d("EditEventFragment", "Event price null");
        }
        else
        {
            //NOTHINHG TO DO
        }

    }

    //Remove Event
    private void removeEvent(final int eventId)
    {
        EventDAO eventDAO = new EventDAO(getActivity());
        if(eventDAO.deleteEvent(eventId).contains("Salvo"))
        {
            Toast.makeText(getActivity(), "Deletado com sucesso", Toast.LENGTH_LONG).show();

            //Andoid Suport libary contains a manegment libary for applications
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    /* Get this management libary to rearrange events top five if an event
                       is deleted */
                    getActivity().getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.content_frame, new ShowTop5Rank());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else
        {
            Toast.makeText(getActivity(),"Houve um erro",Toast.LENGTH_LONG).show();
        }

        Log.d("EditEventFragment", "Event Removed");
    }

    //Uptades database
    private void updateEventOnDataBase(final Event event)
    {
        EventDAO eventDAO = new EventDAO(getActivity());
        eventDAO.updateEvent(event);

        finilize();

        Log.d("EditEventFragment", "Database updated");
    }

    //Free the memory that the categories vector use
    private void finilize()
    {
        for(String category : categories)
        {
            category = null;
        }
        categories = null;
    }
}