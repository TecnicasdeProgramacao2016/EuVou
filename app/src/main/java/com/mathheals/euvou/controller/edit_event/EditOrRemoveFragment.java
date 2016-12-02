/*
* File name: EditOrRemoveFragment.
* File pourpose: Edit or remove fragments.
*/


package com.mathheals.euvou.controller.edit_event;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.show_event.ShowEvent;
import com.mathheals.euvou.controller.utility.Mask;

import model.Event;

public class EditOrRemoveFragment extends android.support.v4.app.Fragment  implements View.OnClickListener
{
    //Public Constructor
    public EditOrRemoveFragment()
    {
        // Required empty public constructor
    }

    public Event evento = null;
    private TextView eventCategoriesText = null, eventPriceText = null;
    private ShowEvent showEvent = new ShowEvent();

    /**
     * Method: public View onCreateView
     * Overrides are used to rewrite methods.
     * This override shows to user an event.
     * @param inflater
     * @param container
     * @param savedInstanceState
     */

    @Override
    //Override view
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Instance of atributte's values
        View view = inflater.inflate(R.layout.edit_or_remove_event_fragment, container, false); //App view
        Button editOrRemoveButton = (Button) view.findViewById(R.id.editRemoveButton); //Edit button
        String eventName = evento.getNameEvent(); //Event Name content
        String eventDescription = evento.getDescription(); //Event description content
        String eventDateTime = evento.getDateTimeEvent(); //Event dateTime content
        Integer eventPrice = evento.getPrice(); //Event price content
        String eventAddress = evento.getAddress(); //Event adress content
        TextView name1Event = (TextView) view.findViewById(R.id.nameEventShow);  //Event name display
        TextView dateEvent = (TextView) view.findViewById(R.id.dateEvent); //Event date display
        TextView description = (TextView) view.findViewById(R.id.descriptionEvent); //Event description  display
        TextView eventAddres = (TextView) view.findViewById(R.id.eventPlaces); //Event adress  display
        TextView eventPriceText = (TextView) view.findViewById(R.id.eventPrice); //Event price  display
        eventCategoriesText = (TextView) view.findViewById(R.id.eventCategories);


        //Sets atributte's values
        editOrRemoveButton.setOnClickListener(this);
        name1Event.setText(eventName);
        description.setText(eventDescription);
        dateEvent.setText(Mask.getDateTimeInBrazilianFormat(eventDateTime));
        eventAddres.setText(eventAddress);
        setShowEvent(eventPrice);

        return view;
    }

    //Sets atributtes for event's show
    private void setShowEvent(Integer eventPrice)
    {
        showEvent.setPriceText(eventPriceText, eventPrice+"");
        showEvent.setCategoriesText(new Integer(evento.getIdEvent()), eventCategoriesText);
    }

    @Override
    /*
     * Override onClick method to relace the content previouslly filled by
     * the content that is shown
     */
    public void onClick(View view)
    {
        if(view.getId()==R.id.editRemoveButton)
        {
            EditEventFragment editEventFragment = new EditEventFragment();
            Bundle bundle = new Bundle();

            /*
             * As default, the fragment transaction is gotten by the system, but in this line it is
             * gotten from the support libary. Both of them achieve the same purpose, but they cannot be
             * replaced with one another in code.
             */
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

            bundle.putInt("idEvent", evento.getIdEvent());
            editEventFragment.setArguments(bundle);

            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.content_frame, editEventFragment);

            fragmentTransaction.commit();
        }
        else
        {
            //NOTHING TO DO
        }

    }
}