/*
* File name: Event Adapter.
* File pourpose: Adapts the event.
*/

package com.mathheals.euvou.controller.event_recommendation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.mathheals.euvou.R;
import java.util.List;
import model.Event;
import static junit.framework.Assert.assertTrue;

/**
*Class: public class EventAdapter extends ArrayAdapter<Event>
*Description: adapts event
*/
public class EventAdapter extends ArrayAdapter<Event>
{
    @Override
    /**
    *Method: public View getView(int position, View convertView, ViewGroup parent)
    *Description: method to get of event
    *@param position
    *@param convertView
    *@param parent
    */
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        Event event = getItem(position);
        ViewHolder viewHolder;
        final int eventNameMaxLength = 40;
        final int eventNameMinLenghtPossible = 0;
        final int eventNameMaxLengthPossible = 39;

        //Sets informations to event
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout
                    .fragment_recommend_event, parent, false);//This line takes view from recommended event
            viewHolder.eventName = (TextView) convertView.findViewById(R.id.eventName);
            viewHolder.eventEvaluation = (TextView) convertView.findViewById(R.id.eventEvaluation);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Name length of event bigger than maximun length of name are reduced and finished with ...
        if(event.getNameEvent().length() > eventNameMaxLength)
        {
            viewHolder.eventName.setText(event.getNameEvent().substring(eventNameMinLenghtPossible,
                    eventNameMaxLengthPossible).concat("..."));
        }
        else
        {
            event.getNameEvent();
        }
        testSizeName();
        viewHolder.eventEvaluation.setText(event.getEvaluation().toString());

        return convertView;
    }

    /**
    *Method: public EventAdapter(Context context, List<Event> events)
    *Description:
    *@param context
    *@param events
    */
    public EventAdapter(Context context, List<Event> events)
    {
        super(context, 0, events);
    }

    private static class ViewHolder
    {
        TextView eventName;
        TextView eventEvaluation;
    }


    //If Name passed with size more than allowed, causes error on program
    private void testSizeName()
    {
        final int eventNameMaxLength = 45;

        Object eventName = R.id.eventName;
        String eventNameString = eventName.toString();

        int nameSize = Integer.parseInt((eventNameString));

        if(nameSize > eventNameMaxLength)
        {
            assertTrue(false); //Stop the program, this error can cause security problems.
        }

    }
}
