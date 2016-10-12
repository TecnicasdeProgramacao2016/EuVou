/*
* File name: SearchUser.
* File pourpose: Search user's information.
*/


package com.mathheals.euvou.controller.user_profile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.mathheals.euvou.R;

public class SearchUser extends ActionBarActivity implements View.OnClickListener
{
    @Override
    //Override method that create view
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
    }

    @Override
    //Override method that create menu
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_user, menu);
        return true;
    }

    @Override
    //Override iten's option
    public boolean onOptionsItemSelected(MenuItem item)
    {
        /* Handle action bar item clicks here. The action bar will
         * automatically handle clicks on the Home/Up button, so long
         * as you specify a parent activity in AndroidManifest.xml.
         */
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        else
        {
            //NOTHING TO DO
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    //Override view creation
    public void onClick(View view)
    {

        EditText searchName = (EditText) findViewById(R.id.searchNameField);
        String searchNameStr = searchName.getText().toString();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(view.getId() == R.id.btnSearch)
        {
            fragmentTransaction.replace(R.id.content_frame, new ShowUser());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return;
        }
        else
        {
            //DO NOTHING
        }
    }
}