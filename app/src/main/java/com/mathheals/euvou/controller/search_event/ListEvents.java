/**
 * File name: ListEvents.java
 * File pourpose: List events in a search
 */


package com.mathheals.euvou.controller.search_event;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.edit_event.EditOrRemoveFragment;
import com.mathheals.euvou.controller.showPlaceRanking.ShowTop5Rank;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import dao.EventDAO;
import exception.EventException;
import model.Event;

public class ListEvents extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener
{

    private ListView listView;
    private Vector<Event> events;



    /**
     * Method: public void onCreate(Bundle savedInstanceState)
     * Description: Conctruct method to instaciate a state
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    /**
     * Method:  public View onCreateView(LayoutInflater inflater, ViewGroup container,
       Bundle savedInstanceState)
     * Description: Join views to present to user
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        assert(inflater != null);
        assert(container != null);
        assert(savedInstanceState != null);

        View view =(View) inflater.inflate(R.layout.fragment_list_events, container, false); // Inflate view when it is created
        assert(view != null);

        // Inflate the layout for this fragment
        listView = (ListView) view.findViewById(R.id.eventList);
        assert(listView != null);

        listView.setOnItemClickListener(this);
        populaList();

        return view;
    }

    /**
     * Method:  public void onItemClick(AdapterView<?> parent, View view, int position, long id)
     * Description: Present content from a click
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        assert(view != null);
        assert(id > 0);

        Event clicked;

        final android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        clicked = events.get(position);
        assert(clicked != null);

        EditOrRemoveFragment editOrRemoveFragment = new EditOrRemoveFragment();
        editOrRemoveFragment.evento = clicked;

        fragmentTransaction.replace(R.id.content_frame, editOrRemoveFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        Log.d("ListEvents", "Apllying action when is clicked");


    }

    /**
     * Method: public ListEvents()
     * Description: public constructor
     */
    public ListEvents()
    {
        // Required empty public constructor
    }


    //Add event to event list
    private void populaList()
    {
        try
        {
            int id = (new LoginUtility(getActivity())).getUserId(); // id must be greater than zero

            assert(id > 0);

            events = new EventDAO(getActivity()).searchEventByOwner(id);

            //Add a new a event in a event list
            if(events != null)
            {
                List<Map<String, String>> eventList = new ArrayList<Map<String, String>>();

                for (Event e : events)
                    eventList.add(createEvent("Nome", e.getNameEvent()));

                SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), eventList,
                        android.R.layout.simple_list_item_1,
                        new String[]{"Nome"}, new int[]{android.R.id.text1}); // Adapt an event to list layout

                listView.setAdapter(simpleAdapter);

                Log.d("ListEvents", "Event is not NULL");
            }
            //If there is not any event created it add a new event to a event list
            else
            {
                Toast.makeText(getContext(), "Você ainda não criou nenhum evento, que tal criar um agora?", Toast.LENGTH_LONG).show();

                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction(); //Andoroid functionality to support a transaction

                fragmentTransaction.replace(R.id.content_frame, new ShowTop5Rank());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                Log.d("ListEvents", "Exception NullPointerException");
            }

        }catch (JSONException exception)
        {
            exception.printStackTrace();
            Log.d("ListEvents", "JSONException in populaList");
        }catch (ParseException exception)
        {
            exception.printStackTrace();
            Log.d("ListEvents", "ParseExeception in populaList");
        }catch (EventException exception)
        {
            exception.printStackTrace();
            Log.d("ListEvents", "EventException in populaList");
        }catch( NullPointerException exception)
        {
            exception.printStackTrace();
            Toast.makeText(getContext(),"Sem eventos criados",Toast.LENGTH_SHORT).show();
            Log.d("ListEvents", "NullPointerException in populaList");

        }
    }


    //Create an event acord to a hash position
    private HashMap<String, String> createEvent(String name, String number)
    {
        HashMap<String, String> eventName = new HashMap<String, String>();//Create a hash to event

        eventName.put(name, number);

        Log.d("ListEvents", "A event has been created");


        return eventName;
    }

}