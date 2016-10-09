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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Event event = getItem(position);
        ViewHolder viewHolder;

        //Sets informations to event
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_recommend_event, parent, false);
            viewHolder.eventName = (TextView) convertView.findViewById(R.id.eventName);
            viewHolder.eventEvaluation = (TextView) convertView.findViewById(R.id.eventEvaluation);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.eventName.setText(
                ((event.getNameEvent().length() > 40) ? event.getNameEvent().substring(0, 39).concat("...") : event.getNameEvent()));
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
}
