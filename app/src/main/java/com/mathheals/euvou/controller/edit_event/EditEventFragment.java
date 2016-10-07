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
    Vector<String> categories = new Vector<>();
    private EditAndRegisterUtility  editAndRegisterUtility = new EditAndRegisterUtility();


    //Constructor
    public EditEventFragment()
    {
        // Required empty public constructor
    }

    private EditText nameField = null, dateField = null, hourField = null,
            descriptionField = null, addressField = null,
            priceDecimalField = null, priceRealField = null;


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

    private String latitude = null;
    private String longitude = null;

    private int idEvent = 0;
    private CheckBox showCheckBox = null, expositionCheckBox = null, cinemaCheckBox = null,
            museumCheckBox = null, theaterCheckBox = null, educationCheckBox = null,
            othersCheckBox = null,sportsCheckBox = null, partyCheckBox = null;


    @Override
    /*
     * Override View to change event's atributtes values, at firts get the values
     *  from the DB and displays them as the create event screen but filled
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        idEvent = this.getArguments().getInt("idEvent");

        View view = inflater.inflate(R.layout.fragment_edit_event, container, false);

        setingEditText(view);
        dateField.addTextChangedListener(Mask.insert("##/##/####", dateField));
        setingCheckBoxs(view);

        EventDAO eventDAO = new EventDAO(getActivity());
        EventCategoryDAO eventCategoryDAO = new EventCategoryDAO(getActivity());
        CategoryDAO categoryDAO = new CategoryDAO(getActivity());

        //Change the value of idEvent when the consultEvent was finished
        JSONObject jsonEvent = eventDAO.searchEventById(idEvent);
        JSONObject jsonEventCategory = eventCategoryDAO.searchCategoriesByEventId(idEvent);

        setEventsAtributtesButCategory (jsonEvent);
        setEventsCategory(jsonEventCategory, categoryDAO);


        //Adding listener to eventLocal EditText
        Button eventLocal = (Button) view.findViewById(R.id.eventLocal);
        eventLocal.setOnClickListener(this);

        //Adding listener to CheckBoxs to verify if each CheckBox is checked or not
        addCheckBoxListeners(view);

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
            String nameEvent = jsonEvent.getJSONObject("0").getString("nameEvent");
            nameField.setText(nameEvent);

            String descriptionEvent = jsonEvent.getJSONObject("0").getString("description");
            descriptionField.setText(descriptionEvent);

            String addressEvent = jsonEvent.getJSONObject("0").getString("address");
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

            for (int counter = 0; counter < jsonEventCategory.length(); counter++) {
                idCategory = jsonEventCategory.getJSONObject(Integer.toString(counter)).getString("idCategory");
                idCategories.add(Integer.parseInt(idCategory));
            }

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

        String dateHourEvent = jsonEvent.getJSONObject("0").getString("dateTimeEvent");
        String[] dateHourEventSplit = dateHourEvent.split(" ");

        String dateEvent = dateHourEventSplit[0];
        String[] dateEventSplit = dateEvent.split("-");
        dateEvent = dateEventSplit[2] + "/" + dateEventSplit[1] + "/" + dateEventSplit[0];

        String hourEvent = dateHourEventSplit[1];

        this.dateField.setText(dateEvent);
        this.hourField.setText(hourEvent);

        Log.d("EditEventFragment", "Date sucessfuly formated");
    }

    //Format Price
    public void formatPrice(JSONObject jsonEvent) throws JSONException
    {
        Integer priceEvent = jsonEvent.getJSONObject("0").getInt("price");
        this.priceRealField.setText(Integer.toString(priceEvent / 100));
        this.priceDecimalField.setText(Integer.toString(priceEvent - priceEvent / 100 * 100));
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

    private void setAsChecked (CheckBox checkBox)
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
        String message = eventException.getMessage().toString();

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

    private void checksValuesNotNull(String nameEvent, String dateEvent, String hourEvent,
                              String descriptionEvent, String addresEvent, Integer priceEvent)
    {
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
    private void removeEvent(int eventId)
    {
        EventDAO eventDAO = new EventDAO(getActivity());
        if(eventDAO.deleteEvent(eventId).contains("Salvo"))
        {
            Toast.makeText(getActivity(), "Deletado com sucesso", Toast.LENGTH_LONG).show();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().
                    getSupportFragmentManager().beginTransaction();
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
    private void updateEventOnDataBase(Event event)
    {
        EventDAO eventDAO = new EventDAO(getActivity());
        eventDAO.updateEvent(event);

        Log.d("EditEventFragment", "Database updated");
    }
}