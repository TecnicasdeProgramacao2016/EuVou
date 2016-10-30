/*
* File name: EventConsultation.
* File pourpose: Consults Event.
*/

package com.mathheals.euvou.controller.event_consultation;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.RadioGroup;
import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.show_event.ShowEvent;
import com.mathheals.euvou.controller.user_profile.ShowUser;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import dao.EventDAO;
import dao.UserDAO;

/**
*Class: public class EventConsultation extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener
*Description: Class to consult an event
*/
public class EventConsultation extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener
{
    private JSONObject eventDATA = null;
    private SearchView searchView = null;
    private JSONObject peopleDATA = null;
    private String option = "option";
    private static final String PEOPLE_NOT_FOUND_MESSAGE = "Nenhum usuário foi encontrado.";

    //sets text on search bar
    private void setSearchBar(Menu menuSearchBar)
    {
        final String SEARCH_VIEW_HINT = "Pesquisar";

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menuSearchBar.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(SEARCH_VIEW_HINT);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            /**
             *Method: public boolean onQueryTextSubmit(String query)
             *Description: serachs by name an event
             *@param query
             */
            public boolean onQueryTextSubmit(String query)
            {

                int checkedButton = (int) radioGroup.getCheckedRadioButtonId();
                switch (checkedButton)
                {
                    case R.id.radio_events:
                        ifCheckButtonIsEvents(query);
                        break;
                    case R.id.radio_people:
                        ifCheckButtonIsPeople(query);
                        break;
                    default:
                        //NOTHING TO DO
                }
                return true;
            }

            //If option selected on menu is event
            private void ifCheckButtonIsEvents(String query){
                option="event";
                EventDAO eventDAO = new EventDAO(getParent());

                ArrayList<String> eventsFound = new ArrayList<String>();
                eventDATA = eventDAO.searchEventByNameGroup(query);
                final String EVENT_COLUMN = "nameEvent";

                if (eventDATA != null)
                {
                    event_not_found_text.setVisibility(View.GONE);
                    try
                    {
                        //Find events
                        for(int i = 0; i < eventDATA.length(); ++i)
                        {
                            eventsFound.add(eventDATA.getJSONObject(new Integer(i).toString()).getString(EVENT_COLUMN));
                        }

                        String[] eventsFoundArray = eventsFound.toArray(new String[eventsFound.size()]);
                        showEventsAsList(eventsFoundArray);
                    }catch (JSONException exceptionJSON)
                    {
                        exceptionJSON.printStackTrace();
                    }
                }
                else
                {
                    listView.setAdapter(null);
                    event_not_found_text.setVisibility(View.VISIBLE);
                }
            }

            //If option selected on menu is people
            private void ifCheckButtonIsPeople(String query){
                option="people";
                UserDAO userDAO = new UserDAO(getParent());

                ArrayList<String> peopleFound = new ArrayList<String>();
                peopleDATA = userDAO.searchUserByName(query);
                final String NAME_USER_COLUMN = "nameUser";

                if (peopleDATA != null)
                {
                    event_not_found_text.setVisibility(View.GONE);
                    try
                    {
                        //Finds People
                        for(int i = 0; i < peopleDATA.length(); i++)
                        {
                            peopleFound.add(peopleDATA.getJSONObject(new Integer(i).toString()).getString(NAME_USER_COLUMN));
                        }

                        String[] peopleFoundArray = peopleFound.toArray(new String[peopleFound.size()]);
                        showPeopleAsList(peopleFoundArray);
                    }catch (JSONException exceptionOfJSON)
                    {
                        exceptionOfJSON.printStackTrace();
                    }
                }
                else
                {
                    listView.setAdapter(null);
                    event_not_found_text.setText(PEOPLE_NOT_FOUND_MESSAGE);
                    event_not_found_text.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return true;
            }

        });
    }

    private ListView listView = null;
    private TextView event_not_found_text = null;
    @Override
    /**
    *Method: protected void onCreate(Bundle savedInstanceState)
    *Description: sets content of event
    *@param Bundle savedInstanceState
    */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_consultation);
        listView = (ListView) findViewById(R.id.events_list);
        event_not_found_text = (TextView) findViewById(R.id.event_not_found_text);
        setListViewListener();
    }

    private ActionBar actionBar = null;
    private RadioGroup radioGroup = null;
    @Override
    /**
    *Method: public boolean onCreateOptionsMenu(Menu menu)
    *Description: set search bar on menu
    *@param Menu menu
    */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_event_consultation, menu); //This line takes the view from menu of consultation

        actionBar = (ActionBar) getSupportActionBar();
        setSearchBar(menu);
        configActionBar();

        radioGroup = (RadioGroup) findViewById(R.id.search_radio_group); //This line sets button selected by field of menu selected
        radioGroup.setOnCheckedChangeListener(this);
        return true;
    }

    private Integer idItem = 0;
    //sets view
    private void setListViewListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            final Bundle bundle = new Bundle();
            final ShowEvent event = new ShowEvent();
            final ShowUser user = new ShowUser();

            public void onItemClick(AdapterView<?> parent, View clickView, int position, long id) {
                final String ID_COLUMN = option=="event" ? "idEvent" : (option=="people" ? "idUser" : "idPlace");
                try
                {
                    final android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    idItem = new Integer((option=="event" ? eventDATA : peopleDATA).getJSONObject(Integer.toString(position)).getString(ID_COLUMN));
                    bundle.putString("id", Integer.toString(idItem));
                    event.setArguments(bundle);
                    user.setArguments(bundle);

                    fragmentTransaction.replace(R.id.content, option == "event" ? event : user);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                catch (JSONException exceptionOfJSON)
                {
                    exceptionOfJSON.printStackTrace();
                }
            }
        });
    }

    //shows an event in a list
    private void showEventsAsList(String[] eventNames)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EventConsultation.this,
                R.layout.event_consultation_list_view, eventNames);
        listView.setAdapter(adapter);

    }

    //shows people in a list
    private void showPeopleAsList(String[] peopleNames)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EventConsultation.this,
                R.layout.event_consultation_list_view, peopleNames);
        listView.setAdapter(adapter);
    }

    //Configures color of action bar
    private void configActionBar()
    {
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00C0C3")));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    /**
    *Method: public boolean onOptionsItemSelected(MenuItem item)
    *Description: gets an iten by id and starts activity according itens selected on menu
    *@param MenuItem item
    */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //Starts action selected on menu
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, HomePage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }

    }

    /**
    *Method: public void onCheckedChanged(RadioGroup group, int checkedId)
    *Description: check changes of event
    *@param group
    *@param checkedId
    */
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch(checkedId)
        {
            case R.id.radio_events:
                break;
            case R.id.radio_people:
                break;
            default:
                //NOTHING TO DO
        }
    }
}