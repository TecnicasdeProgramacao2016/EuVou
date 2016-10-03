/*
* File name: RecommendEvent.
* File pourpose: Recommends events.
*/

package com.mathheals.euvou.controller.event_recommendation;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.show_event.ShowEvent;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import dao.EventRecommendationDAO;
import exception.EventException;
import model.Event;

/**
*Class: public class RecommendEvent extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener
*Description: Class to recommend event
*/
public class RecommendEvent extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener
{
    ArrayList<Event> events;
    private JSONObject eventDATA;
    private void fillList()
    {
        EventRecommendationDAO eventRecommendationDAO = new EventRecommendationDAO();

        events = new ArrayList<>();

        try
        {
            eventDATA = eventRecommendationDAO.recommendEvents(idUser);

            for(int i=0 ; i < eventDATA.length() ; i++)
            {
                int idEvent = eventDATA.getJSONObject(Integer.toString(i)).getInt("idEvent");
                String nameEvent = eventDATA.getJSONObject(Integer.toString(i)).getString("nameEvent");
                int eventEvaluation = 4;

                Event event = new Event(idEvent, nameEvent, eventEvaluation);

                events.add(event);
            }
        }
        catch (JSONException exceptionOfJSON)
        {
            exceptionOfJSON.printStackTrace();
        }
        catch (ParseException exceptionOfParse)
        {
            exceptionOfParse.printStackTrace();
        }
        catch (EventException exceptionOfEvent)
        {
            exceptionOfEvent.printStackTrace();
        }
        catch (NullPointerException exceptionOfNull)
        {
            Toast.makeText(getActivity().getBaseContext(), "Sem eventos recomendados!", Toast.LENGTH_LONG).show();
        }

        EventAdapter eventAdapter = new EventAdapter(getActivity(),events);

        listView.setAdapter(eventAdapter);
    }

    private int idUser = 0;
    private ListView listView = null;
    @Override
    /**
    *Method: public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    *Description: fills list of events if user are logged
    *@param inflater
    *@param container
    *@param savedInstanceState
    */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View viewOfList = inflater.inflate(R.layout.fragment_recommend_event, container, false);
        listView = (ListView) viewOfList.findViewById(R.id.list_view_event_recomendations);
        listView.setOnItemClickListener(this);

        LoginUtility loginUtility = new LoginUtility(getActivity());
        idUser = loginUtility.getUserId();

        if(idUser != -1)
        {
            fillList();
        }else
        {
            Toast.makeText(getActivity().getBaseContext(), "Sem eventos recomendados!", Toast.LENGTH_LONG).show();
        }
        return  viewOfList;
    }

    @Override
    /**
    *Method: public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    *Description:
    *@param parent
    *@param view
    *@param position
    *@param id
    */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        final String ID_COLUMN = "idEvent";

        int eventId = 0;
        final Bundle bundle = new Bundle();
        final ShowEvent event = new ShowEvent();
        try
        {
            final android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            eventId = new Integer(eventDATA.getJSONObject(Integer.toString(position)).getString(ID_COLUMN));
            bundle.putString("idEventSearch", Integer.toString(eventId));

            event.setArguments(bundle);
            fragmentTransaction.add(R.id.content_frame, event);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } catch (JSONException exceptionOnItemClick)
        {
            exceptionOnItemClick.printStackTrace();
        }

    }

    /**
    *Method: public RecommendEvent()
    *Description: Empty method to construct recommend event
    */
    public RecommendEvent()
    {
        //Required empty public constructor
    }
}
