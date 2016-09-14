package com.mathheals.euvou.controller.event_registration;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import dao.EventDAO;
import exception.EventException;
import model.Event;

public class RegisterEventFragment extends android.support.v4.app.Fragment implements View.OnClickListener
{

    private static final String SUCCESSFULL_CADASTRATION_MESSAGE = "Evento cadastrado com sucesso :)";
    private String latitude;
    private String longitude;
    private Vector<String> categories= new Vector<>();

    public RegisterEventFragment()
    {
        //EMPTY CONSTRUCTOR
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
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

    private void addCinemaToCategory(final View view)
    {
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
    private void addToEducationCategory(final View view)
    {
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
    private void addToExpositionCategory(final View view)
    {
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
    private void addToMuseumCategory(final View view)
    {
        CheckBox museumCheckBox = (CheckBox) view;

        if(museumCheckBox.isChecked())
        {
            categories.add(museumCheckBox.getText().toString());
        }else
        {
            categories.remove(museumCheckBox.getText().toString());
        }
    }
    private void addToOthersCategory(final View view)
    {
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
    private void addToPartyCategory(final View view)
    {
        CheckBox partyCheckBox = (CheckBox) view;

        if(partyCheckBox.isChecked())
        {
            categories.add(partyCheckBox.getText().toString());
        }else
        {
            categories.remove(partyCheckBox.getText().toString());
        }
    }
    private void addToSportsCategory(final View view)
    {
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
    private void addToTheatreCategories(final View view)
    {
        CheckBox theaterCheckBox = (CheckBox) view;

        if(theaterCheckBox.isChecked())
        {
            categories.add(theaterCheckBox.getText().toString());
        }
        else
        {
            categories.remove(theaterCheckBox.getText().toString());
        }
    }
    private void addToShowCategory(final View view)
    {
        CheckBox showCheckBOx = (CheckBox) view;
        if(showCheckBOx.isChecked())
        {
            categories.add(showCheckBOx.getText().toString());
        }else
        {
            categories.remove(showCheckBOx.getText().toString());
        }
    }
    private void addEventCategories(final View view)
    {
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


    @Override
    public void onClick(View view)
    {
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

            int idOwner = loginUtility.getUserId();

            try
            {
                Event event = new Event(idOwner, nameEvent, dateEvent, eventHour,
                                        priceEventReal, priceEventDecimal, addressEvent,
                                        descriptionEvent, latitude, longitude, categories);
                //testar criação do evento
                registerEvent(event);

                Toast.makeText(getActivity().getBaseContext(), SUCCESSFULL_CADASTRATION_MESSAGE, Toast.LENGTH_LONG).show();
            } catch (EventException exception)
            {
                String message = exception.getMessage();

                //Verify address field
                if(message.equals(Event.ADDRESS_IS_EMPTY))
                {
                    addressEventField.requestFocus();
                    addressEventField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.INVALID_EVENT_HOUR))
                {
                    hourEventField.requestFocus();
                    hourEventField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.EVENT_HOUR_IS_EMPTY))
                {
                    hourEventField.requestFocus();
                    hourEventField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.DESCRIPTION_CANT_BE_EMPTY))
                {
                    descriptionEventField.requestFocus();
                    descriptionEventField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.DESCRIPTION_CANT_BE_GREATER_THAN))
                {
                    descriptionEventField.requestFocus();
                    descriptionEventField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.EVENT_DATE_IS_EMPTY))
                {
                    dateEventField.requestFocus();
                    dateEventField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.EVENT_NAME_CANT_BE_EMPTY_NAME))
                {
                    nameEventField.requestFocus();
                    nameEventField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.INVALID_EVENT_DATE))
                {
                    dateEventField.requestFocus();
                    dateEventField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.NAME_CANT_BE_GREATER_THAN_50))
                {
                    nameEventField.requestFocus();
                    nameEventField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.PRICE_REAL_IS_EMPTY))
                {
                    priceEventRealField.requestFocus();
                    priceEventRealField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

                if(message.equals(Event.PRICE_DECIMAL_IS_EMPTY))
                {
                    priceEventDecimalField.requestFocus();
                    priceEventDecimalField.setError(message);
                }else
                {
                    //NOTHING TO DO
                }

            } catch (ParseException exception)
            {
                exception.printStackTrace();

            }
        }
        else if(view.getId() == R.id.eventLocal)
        {
            Intent map = new Intent(getActivity(), LocalEventActivity.class);
            startActivityForResult(map, 2);
        }else
        {
            addEventCategories(view);
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
        }
    }

    private void registerEvent(Event event)
    {
        //verificar evento que está em parâmetro
        EventDAO eventDAO = new EventDAO(getActivity());
        eventDAO.saveEvent(event);
    }

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

}
