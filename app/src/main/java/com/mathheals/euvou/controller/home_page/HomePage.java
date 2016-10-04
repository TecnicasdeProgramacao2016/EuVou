/**
 * file: HomePage.java
 * pursose:inflate the initial screen of the application
 */

package com.mathheals.euvou.controller.home_page;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.event_consultation.EventConsultation;
import com.mathheals.euvou.controller.event_registration.RegisterEventFragment;
import com.mathheals.euvou.controller.edit_user.EditUserFragment;
import com.mathheals.euvou.controller.login_user.LoginActivity;
import com.mathheals.euvou.controller.remove_user.RemoveUserFragment;
import com.mathheals.euvou.controller.search_event.ListEvents;
import com.mathheals.euvou.controller.search_place.SearchPlaceMaps;
import com.mathheals.euvou.controller.showPlaceRanking.ShowTop5Rank;
import com.mathheals.euvou.controller.user_registration.RegisterFragment;
import com.mathheals.euvou.controller.utility.ActivityUtility;
import com.mathheals.euvou.controller.utility.LoginUtility;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePage extends ActionBarActivity implements AdapterView.OnItemClickListener
{
    private final static Logger logger = Logger.getLogger(HomePage.class.getName());
    private static final String QUERY = "query";
    private static final String SETTINGS_FRAGMENT = "settings_fragment_tag";
    private DrawerLayout drawerLayout = null;
    private LinearLayout linearLayout = null;
    private ListView drawerList = null;
    private ActionBarDrawerToggle drawerToggle = null;
    private String[] textOptions = null;
    private ActionBar actionBar = null;
    private int USER_STATUS = 0;
    private final int LOGGED_OUT = -1;

    @Override
    /**
     * method that starts when the activity is called
     */
    protected void onCreate(final Bundle savedInstanceState)
    {
        assert(savedInstanceState != null);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_navigation_drawer);
        initViews();

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, textOptions));

        assert(drawerList != null);

        callGoogleMaps();
        onConfigActionBar();
        startPrincipalFragment();
        logger.log(Level.INFO, " The method onCreate has been executed, the maps have been called"+
                               " the actionbar has been configured and the main fragment has been called");
    }

    /**
     * method that shows the option for ther users that are not logged
     * @param item - item of the menu
     * @return
     */
    public boolean userLoggedInOptions(final MenuItem item)
    {
        logger.log(Level.INFO,"entered in the method that shows the options for the user that is logged");
        assert(item != null);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch(item.getItemId())
        {
            case R.id.edit_register:
                fragmentTransaction.replace(R.id.content_frame, new EditUserFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.settings:
                ActivityUtility.clearBackStack(this);
                fragmentTransaction.replace(R.id.content_frame, new RemoveUserFragment(), SETTINGS_FRAGMENT);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.register_event:
                fragmentTransaction.replace(R.id.content_frame, new RegisterEventFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.logout:
                new LoginUtility(HomePage.this).setUserLogOff();
                Intent intent = getIntent();
                assert(intent != null);
                finish();
                startActivity(intent);
                return true;
            case R.id.myEvents:
                try
                {
                    fragmentTransaction.replace(R.id.content_frame, new ListEvents());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
                catch (NullPointerException exception)
                {
                    Toast.makeText(getBaseContext(),"Sem eventos criados",Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return false;
        }
    }

    /**
     * method that shows the options for users that are not logged in
     * @param item - item of  the menu
     * @return
     */
    public boolean userLoggedOutOptions(final MenuItem item)
    {
        logger.log(Level.INFO,"entered in the method that shows the options for the users logged out");
        assert(item != null);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId())
        {
            case R.id.registration:
                fragmentTransaction.replace(R.id.content_frame, new RegisterFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.log_in:
                Intent myIntent = new Intent(HomePage.this, LoginActivity.class);
                assert(myIntent != null);
                HomePage.this.startActivity(myIntent);
                return true;
            default:
                return false;
        }
    }

    /**
     * method that starts the main fragment of the aplication
     */
    private void startPrincipalFragment()
    {
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                                        getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new ShowTop5Rank());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * method that searches a place in that was typed
     * @param view - view that will be searched
     */
    public void searchPlace(final View view)
    {
        assert(view != null);
        final String INVALID_SEARCH = "Pesquisa Invalida";

        String filter = ((EditText)findViewById(R.id.place_search)).getText().toString();
        Intent map = new Intent(HomePage.this, SearchPlaceMaps.class);
        assert(map != null);

        logger.log(Level.INFO,"search place method inicializated");

        if(!(filter.isEmpty()))
        {
            map.putExtra(QUERY, filter);
            HomePage.this.startActivity(map);
            drawerLayout.closeDrawer(linearLayout);
        }
        else
        {
            Toast.makeText(this, INVALID_SEARCH, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * method that calls the maps to the application
     */
    private void callGoogleMaps()
    {
        drawerList.setOnItemClickListener(this);
    }

    /**
     * method that iniciate the views of the application
     */
    private void initViews()
    {
        logger.log(Level.INFO,"views have been initializated");

        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer_list);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer,
                        R.string.drawer_open, R.string.drawer_close)
                {
                    public void onDrawerClosed(View view)
                    {

                        supportInvalidateOptionsMenu();
                    }

                    public void onDrawerOpened(View drawerView)
                    {

                        supportInvalidateOptionsMenu();
                    }
                };

        assert(drawerToggle != null);

        textOptions = getResources().getStringArray(R.array.itens_menu_string);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        actionBar = getSupportActionBar();
    }

    /**
     * method that configurates the actionBar
     */
    private void onConfigActionBar()
    {
        logger.log(Level.INFO,"entered on the method to config the actionBar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00C0C3")));

    }

    /**
     * method that creates the options of the menu
     * @param menu = menu instance
     * @return
     */
    public boolean onCreateOptionsMenu(final Menu menu)
    {
        logger.log(Level.INFO,"entered in the method to create the menus option");
        assert(menu != null);

        MenuInflater inflater = getMenuInflater();
        LoginUtility loginUtility = new LoginUtility(HomePage.this);

        assert(loginUtility != null);
        // Inflating menu for logged users

        USER_STATUS = loginUtility.getUserId();

        if(USER_STATUS != LOGGED_OUT)
        {
            inflater.inflate(R.menu.home_page_logged_in, menu);
        }
        else if(USER_STATUS == LOGGED_OUT)
        {
            inflater.inflate(R.menu.home_page_logged_out, menu);
        }
        else
        {
            return false;
        }

        return super.onCreateOptionsMenu(menu);
    }

    /**
     *Method that changes the configuration
     * @param newConfig - configuration to the tested
     */
    public void onConfigurationChanged(final Configuration newConfig)
    {
        logger.log(Level.INFO,"entered in the method that changes the configuration");
        assert(newConfig != null);
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState)
    {
        logger.log(Level.INFO,"entered in the method that creates the post");
        assert(savedInstanceState != null);
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    @Override
    /**
     * method that checks the selected option on the menu
     * @param - item of the menu
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        logger.log(Level.INFO,"entered in the method that checks the options that were selected");
        assert(item != null);
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        else
        {
            //NOTHING TO DO
        }
        // Handle your other action bar items...

        if(USER_STATUS != LOGGED_OUT)
        {
            userLoggedInOptions(item);
        }
        else if(USER_STATUS == LOGGED_OUT)
        {
            userLoggedOutOptions(item);
        }
        else
        {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * method to get the click on the update informations of the user
     * @param view - current screen
     */
    public void editUserUpdateButtonOnClick(View view)
    {
        logger.log(Level.INFO,"entered in the method that informates the user ");
        assert(view != null);
        final String SUCESS_EDIT_MESSAGE = "Dados alterados com sucesso :)";
        Toast.makeText(getBaseContext(), SUCESS_EDIT_MESSAGE, Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        return;
    }

    /**
     * method that listen to the search button
     * @param item - iten of the menu
     */
    public void searchOnclick(final MenuItem item)
    {
        assert(item != null);
        Intent eventConsultation = new Intent(HomePage.this, EventConsultation.class);
        assert(eventConsultation != null);
        HomePage.this.startActivity(eventConsultation);
        logger.log(Level.INFO,"executed the method searchOnClick with ou error");
    }
        @Override
        /**
         * method that gets the item that the user has clicked
         * @param parent - the adapter of the view to the user
         * @param view - current view
         * @param position - the option of the item
         *
         */
        public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id)
        {
            logger.log(Level.INFO,"entered in the method that recognize the click on the categories");
            assert(parent != null);
            assert(view != null);

            String aux = "";
            switch (position)
            {
                case 1:
                    aux = "Museu";
                    break;
                case 2:
                    aux = "Parque";
                    break;
                case 3:
                    aux = "Teatro";
                    break;
                case 4:
                    aux = "shop";
                    break;
                case 5:
                    aux = "Unidade";
                    break;
                default:
                    //NOTHING TO DO


            }
            Intent map = new Intent(HomePage.this, SearchPlaceMaps.class);
            assert(map != null);
            map.putExtra(QUERY, aux);
            HomePage.this.startActivity(map);
            drawerLayout.closeDrawer(linearLayout);
        }
}
