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

    @Override
    //Override view
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.edit_or_remove_event_fragment, container, false);

        Button editOrRemoveButton = (Button) view.findViewById(R.id.editRemoveButton);
        editOrRemoveButton.setOnClickListener(this);

        String eventName = evento.getNameEvent();
        String eventDescription = evento.getDescription();
        String eventDateTime = evento.getDateTimeEvent();
        Integer eventPrice = evento.getPrice();
        String eventAddress = evento.getAddress();

        TextView name1Event = (TextView) view.findViewById(R.id.nameEventShow);
        TextView dateEvent = (TextView) view.findViewById(R.id.dateEvent);
        TextView description = (TextView) view.findViewById(R.id.descriptionEvent);
        TextView eventAddres = (TextView) view.findViewById(R.id.eventPlaces);
        TextView eventPriceText = (TextView) view.findViewById(R.id.eventPrice);
        eventCategoriesText = (TextView) view.findViewById(R.id.eventCategories);
        name1Event.setText(eventName);
        description.setText(eventDescription);
        dateEvent.setText(Mask.getDateTimeInBrazilianFormat(eventDateTime));
        eventAddres.setText(eventAddress);
        showEvent.setPriceText(eventPriceText, eventPrice+"");
        showEvent.setCategoriesText(new Integer(evento.getIdEvent()), eventCategoriesText);

        return view;
    }

    @Override
    //Override onClick Method
    public void onClick(View view)
    {
        if(view.getId()==R.id.editRemoveButton)
        {
            EditEventFragment editEventFragment = new EditEventFragment();
            Bundle bundle = new Bundle();

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
