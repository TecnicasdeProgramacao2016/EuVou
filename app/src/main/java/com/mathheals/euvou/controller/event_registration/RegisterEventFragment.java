/**
 *  file: RegisterEventFragment.java
 *  Purpose: Register fragment of a new event in the database
 */

package com.mathheals.euvou.controller.event_registration;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.utility.LoginUtility;
import com.mathheals.euvou.controller.utility.Mask;

import java.text.ParseException;
import java.util.Vector;

import dao.EventDAO;
import exception.EventException;
import model.Event;

public class RegisterEventFragment extends android.support.v4.app.Fragment implements View.OnClickListener
{
    private final String DEFAULT_MESSAGE = " ";
    private static final String SUCCESSFULL_CADASTRATION_MESSAGE = "Evento cadastrado com sucesso :)";
    private String latitude = DEFAULT_MESSAGE;
    private String longitude = DEFAULT_MESSAGE;
    private Vector<String> categories= new Vector<>();

    public RegisterEventFragment()
    {
        //EMPTY CONSTRUCTOR
    }

    /**
     * first thing tha happend when the activity is called
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        assert(inflater != null);
        assert(container != null);
        assert(savedInstanceState != null);
        Log.d("01","started the onCreatView method");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.register_event, container, false);

        //Adding listener to saveEvent Button
        Button registerEvent = (Button) view.findViewById(R.id.saveEvent);
        registerEvent.setOnClickListener(this);

        //Adding listener to eventLocal EditText
        Button eventLocal = (Button) view.findViewById(R.id.eventLocal);
        eventLocal.setOnClickListener(this);

        //Adding mask to eventDate Field
        EditText eventDate = (EditText) view.findViewById(R.id.eventDate);
        eventDate.addTextChangedListener(Mask.insert("##/##/####", eventDate));

        //Adding listener to CheckBoxs to verify if each CheckBox is checked or not
        addCheckBoxListeners(view);

        return view;
    }

    /**
     *add the event to the cinema category
     * @param view
     */
    private void addCinemaToCategory(final View view)
    {
        assert(view != null);
        Log.d("01","started the addCinemaToCategory method");
        CheckBox cinemaCheckBox = (CheckBox) view;

        if(cinemaCheckBox.isChecked())
        {
            categories.add(cinemaCheckBox.getText().toString());
        }
        else
        {
            categories.remove(cinemaCheckBox.getText().toString());
        }
    }

    /**
     * add the event to the category of education
     * @param view
     */
    private void addToEducationCategory(final View view)
    {
        assert(view != null);
        Log.d("01","started the addToEducationCategory method");
        CheckBox educationCheckBox = (CheckBox) view;

        if(educationCheckBox.isChecked())
        {
            categories.add("Educacao");
        }
        else
        {
            categories.remove("Educacao");
        }
    }

    /**
     * add the event to the exposition category
     * @param view
     */
    private void addToExpositionCategory(final View view)
    {
        assert(view != null);
        Log.d("01","started the addToExpositionCategory method");
        CheckBox expositionCheckBox = (CheckBox) view;

        if(expositionCheckBox.isChecked())
        {
            categories.add("Exposicao");
        }
        else
        {
            categories.remove("Exposicao");
        }
    }

    /**
     * add event to the museum category
     * @param view
     */
    private void addToMuseumCategory(final View view)
    {
        assert( view != null);
        Log.d("01","started the addToMuseumCategory method");
        CheckBox museumCheckBox = (CheckBox) view;

        if(museumCheckBox.isChecked())
        {
            categories.add(museumCheckBox.getText().toString());
        }else
        {
            categories.remove(museumCheckBox.getText().toString());
        }
    }

    /**
     * add eventto OTHERS category
     * @param view
     */
    private void addToOthersCategory(final View view)
    {
        assert(view != null);
        Log.d("01","started the addToOthersCategory method");
        CheckBox othersCheckBox = (CheckBox) view;

        if(othersCheckBox.isChecked())
        {
            categories.add(othersCheckBox.getText().toString());
        }
        else
        {
            categories.remove(othersCheckBox.getText().toString());
        }
    }

    /**
     * add event to the party cathegory
     * @param view
     */
    private void addToPartyCategory(final View view)
    {
        assert(view != null);
        Log.d("01","started the addToPartyCategory method");
        CheckBox partyCheckBox = (CheckBox) view;

        if(partyCheckBox.isChecked())
        {
            categories.add(partyCheckBox.getText().toString());
        }else
        {
            categories.remove(partyCheckBox.getText().toString());
        }
    }

    /**
     * add to the sports category
     * @param view
     */
    private void addToSportsCategory(final View view)
    {
        assert(view != null);
        Log.d("01","started the addToSportsCategory method");
        CheckBox sportsCheckBox = (CheckBox) view;

        if(sportsCheckBox.isChecked())
        {
            categories.add(sportsCheckBox.getText().toString());
        }
        else
        {
            categories.remove(sportsCheckBox.getText().toString());
        }
    }

    /**
     * add to the theatre category
     * @param view
     */
    private void addToTheatreCategories(final View view)
    {
        assert(view != null);
        CheckBox theaterCheckBox = (CheckBox) view;
        Log.d("01","started the addToTheatreCategories method");
        if(theaterCheckBox.isChecked())
        {
            categories.add(theaterCheckBox.getText().toString());
        }
        else
        {
            categories.remove(theaterCheckBox.getText().toString());
        }
    }

    /**
     * add the event to the show category
     * @param view
     */
    private void addToShowCategory(final View view)
    {
        assert(view != null);
        Log.d("01","started the addToShowCategory method");
        CheckBox showCheckBOx = (CheckBox) view;
        if(showCheckBOx.isChecked())
        {
            categories.add(showCheckBOx.getText().toString());
        }else
        {
            categories.remove(showCheckBOx.getText().toString());
        }
    }

    /**
     * method to register the category of the event
     * @param view - current view
     */
    private void addEventCategories(final View view)
    {
        assert(view != null);
        Log.d("01","started the addEventCategories method");
        if(view.getId() == R.id.optionCinema)
        {
            addCinemaToCategory(view);

        }else if(view.getId() == R.id.optionEducation)
        {
            addToEducationCategory(view);

        }else if(view.getId() == R.id.optionExposition)
        {
            addToExpositionCategory(view);
        }else if(view.getId() == R.id.optionMuseum)
        {
            addToMuseumCategory(view);
        }else if(view.getId() == R.id.optionOthers)
        {
            addToOthersCategory(view);
        }else if(view.getId() == R.id.optionParty)
        {
            addToPartyCategory(view);
        }else if(view.getId() == R.id.optionShow)
        {
            addToShowCategory(view);
        }else if(view.getId() == R.id.optionSports)
        {
            addToSportsCategory(view);
        }else if(view.getId() == R.id.optionTheater)
        {
            addToTheatreCategories(view);
        }
    }

    /**
     * method that gets the informations and create the event
     * @param view - current view of the app
     */
    @Override
    public void onClick(View view)
    {
        assert(view != null);

        if(view.getId() == R.id.saveEvent)
        {
            EditText nameEventField = (EditText) this.getActivity().findViewById(R.id.eventName);
            String nameEvent = nameEventField.getText().toString();

            EditText dateEventField = (EditText) this.getActivity().findViewById(R.id.eventDate);
            String dateEvent = dateEventField.getText().toString();

            EditText hourEventField = (EditText) this.getActivity().findViewById(R.id.eventHour);
            String eventHour = hourEventField.getText().toString();

            EditText descriptionEventField = (EditText) this.getActivity().findViewById(R.id.eventDescription);
            String descriptionEvent = descriptionEventField.getText().toString();

            EditText addressEventField = (EditText) this.getActivity().findViewById(R.id.eventAddress);
            String addressEvent = addressEventField.getText().toString();

            EditText priceEventRealField = (EditText) this.getActivity().findViewById(R.id.eventPriceReal);
            String priceEventReal = priceEventRealField.getText().toString();

            EditText priceEventDecimalField = (EditText) this.getActivity().findViewById(R.id.eventPriceDecimal);
            String priceEventDecimal = priceEventDecimalField.getText().toString();

            LoginUtility loginUtility = new LoginUtility(getActivity());
            assert(loginUtility != null);

            int idOwner = loginUtility.getUserId();

            assert(idOwner > 0);

            eventCreator(idOwner,
                         nameEvent,
                         dateEvent,
                         eventHour,
                         priceEventReal,
                         priceEventDecimal,
                         addressEvent,
                         descriptionEvent,
                         addressEventField,
                         hourEventField,
                         descriptionEventField,
                         dateEventField,
                         nameEventField,
                         priceEventRealField,
                         priceEventDecimalField);

        }
        else if(view.getId() == R.id.eventLocal)
        {
            Intent map = new Intent(getActivity(), LocalEventActivity.class);
            assert(map != null);
            startActivityForResult(map, 2);
        }else
        {
            addEventCategories(view);
        }

    }

    /**
     * method created just to create the event
     * @param idOwner - id of the owner of the event
     * @param nameEvent - name of the event
     * @param dateEvent - date of the event
     * @param eventHour - hour of the event
     * @param priceEventReal - real price of the event
     * @param priceEventDecimal  price of the event in decimal
     * @param addressEvent - place of the event
     * @param descriptionEvent - description of the event
     * @param addressEventField - field where the addres of the event is
     * @param hourEventField - filed of the hours
     * @param descriptionEventField - field of the description
     * @param dateEventField - field of the date
     * @param nameEventField - field of the name
     * @param priceEventRealField - field of the price
     * @param priceEventDecimalField - field of the price decimal
     */
    public void eventCreator(int idOwner,
                             String nameEvent,
                             String dateEvent,
                             String eventHour,
                             String priceEventReal,
                             String priceEventDecimal,
                             String addressEvent,
                             String descriptionEvent ,
                             EditText addressEventField,
                             EditText hourEventField,
                             EditText descriptionEventField,
                             EditText dateEventField,
                             EditText nameEventField,
                             EditText priceEventRealField,
                             EditText priceEventDecimalField)
    {
        try
        {
            Event event = new Event(idOwner, nameEvent, dateEvent, eventHour,
                    priceEventReal, priceEventDecimal, addressEvent,
                    descriptionEvent, latitude, longitude, categories);

            assert(event != null);

            registerEvent(event);

            Toast.makeText(getActivity().getBaseContext(), SUCCESSFULL_CADASTRATION_MESSAGE, Toast.LENGTH_LONG).show();

        } catch (EventException exception)
        {
            String message = exception.getMessage();

            eventCreatorExceptionMessage(message,addressEventField,hourEventField,
                                         descriptionEventField,dateEventField,
                                         nameEventField,priceEventRealField,
                                         priceEventDecimalField);

        } catch (ParseException exception)
        {
            exception.printStackTrace();

        }
    }

    /**
     * method to identify the error and send a message warning
     * @param message - get the message from the exception
     * @param addressEventField - get the addres filed
     * @param hourEventField get the hours field
     * @param descriptionEventField - get the description field
     * @param dateEventField - get the date field
     * @param nameEventField - get the name field
     * @param priceEventRealField - get the real price
     * @param priceEventDecimalField - get the decimal value of the price
     */
    private void eventCreatorExceptionMessage(String message, EditText addressEventField,EditText hourEventField,
                                              EditText descriptionEventField, EditText dateEventField,EditText nameEventField,
                                              EditText priceEventRealField, EditText priceEventDecimalField)
    {
        //Verify address field
        if(message.equals(Event.ADDRESS_IS_EMPTY))
        {
            addressEventField.requestFocus();
            addressEventField.setError(message);

        }else if(message.equals(Event.INVALID_EVENT_HOUR))
        {
            hourEventField.requestFocus();
            hourEventField.setError(message);

        }else if(message.equals(Event.EVENT_HOUR_IS_EMPTY))
        {
            hourEventField.requestFocus();
            hourEventField.setError(message);

        }else if(message.equals(Event.DESCRIPTION_CANT_BE_EMPTY))
        {
            descriptionEventField.requestFocus();
            descriptionEventField.setError(message);

        }else if(message.equals(Event.DESCRIPTION_CANT_BE_GREATER_THAN))
        {
            descriptionEventField.requestFocus();
            descriptionEventField.setError(message);

        }else if(message.equals(Event.EVENT_DATE_IS_EMPTY))
        {
            dateEventField.requestFocus();
            dateEventField.setError(message);

        }else if(message.equals(Event.EVENT_NAME_CANT_BE_EMPTY_NAME))
        {
            nameEventField.requestFocus();
            nameEventField.setError(message);

        }else if(message.equals(Event.INVALID_EVENT_DATE))
        {
            dateEventField.requestFocus();
            dateEventField.setError(message);

        }else if(message.equals(Event.NAME_CANT_BE_GREATER_THAN_50))
        {
            nameEventField.requestFocus();
            nameEventField.setError(message);

        }else if(message.equals(Event.PRICE_REAL_IS_EMPTY))
        {
            priceEventRealField.requestFocus();
            priceEventRealField.setError(message);

        }else if(message.equals(Event.PRICE_DECIMAL_IS_EMPTY))
        {
            priceEventDecimalField.requestFocus();
            priceEventDecimalField.setError(message);
        }else
        {
            //NOTHING TO DO
        }
    }
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case (2) :
            {
                if (resultCode == Activity.RESULT_OK)
                {
                    Bundle bundle = data.getExtras();
                    latitude = bundle.getString("latitude");
                    longitude = bundle.getString("longitude");

                    Toast.makeText(getContext(), "Local selecionado com sucesso", Toast.LENGTH_LONG).show();
                }
                break;
            }
            default:
                //NOTHING TO DO

        }
    }

    /**
     * it registers an event in the database
     * @param event - event that was created and that will be saved
     */
    private void registerEvent(final Event event)
    {
        assert(event != null);

        EventDAO eventDAO = new EventDAO(getActivity());

        assert(eventDAO != null);

        eventDAO.saveEvent(event);
    }

    /**
     * adding the listeniers to the checkboxess of the categories
     * @param view - the current view
     */
    private void addCheckBoxListeners(final View view)
    {
        assert(view != null);

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

}
