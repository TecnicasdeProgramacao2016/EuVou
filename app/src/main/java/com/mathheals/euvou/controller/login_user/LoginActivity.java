package com.mathheals.euvou.controller.login_user;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;

import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.home_page.HomePage;
import com.mathheals.euvou.controller.utility.LoginUtility;

import org.json.JSONException;

 /*
 * File name: LoginActivity.
 * File pourpose: This file have the pourpose to active the login.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener 
{

    private ActionBar actionBar;

    /**
        * Overrides are used to rewrite methods.
        * This override saves the state of writh instances
        * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button doLogin = (Button) findViewById(R.id.doLogin);
        doLogin.setOnClickListener(this);
        initViews();
        onConfigActionBar();
    }

    private void initViews()
    {
        actionBar = getSupportActionBar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        /**
             * This method inflate the menu and add items to the action bar if it is present.
             * @param menu -
         */

        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int userId = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (userId == R.id.action_settings)
        {
            return true;
        } else 
        {
            //NOTHING TO DO
        }

        return super.onOptionsItemSelected(item);
    }

    private void onConfigActionBar()
    {

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#008B8B"))); //Set the color blue onde parse

    }

    //flags to charge when the User can not log
    private boolean isUsernameValid = false;
    private boolean isPasswordValid = false;

    @Override
    public void onClick(View view)
    {
        /**
         * method that gets the informations and create the event
         * @param view
         */

        EditText usernameField = (EditText) findViewById(R.id.usernameField);
        String typedUsername = usernameField.getText().toString();

        EditText passwordField = (EditText) findViewById(R.id.passwordField);
        String typedPassword = passwordField.getText().toString();

        LoginValidation loginValidation = new LoginValidation(LoginActivity.this);

        isUsernameValid = loginValidation.isUsernameValid(typedUsername);

        checkIfUsernameAndPasswordAreValid(isUsernameValid, isPasswordValid, typedUsername);
        checkIfUsernameAndloginValidationAreTrue(isUsernameValid, isPasswordValid, typedUsername, loginValidation, typedPassword, passwordField, usernameField);
        checkIfUsernameAndloginValidationAreFalse(isUsernameValid, isPasswordValid, typedUsername, loginValidation, typedPassword, passwordField, usernameField);

    }

    public void checkIfUsernameAndPasswordAreValid (boolean isUsernameValid, boolean isPasswordValid, String typedUsername)
    {
        if(isUsernameValid && isPasswordValid)
        {

            LoginUtility loginUtility = new LoginUtility(LoginActivity.this);

            try
            {
                int idUser = loginUtility.getUserId(typedUsername);
                loginUtility.setUserLogIn(idUser);
                Intent i = new Intent(this, HomePage.class);
                finish();
                startActivityForResult(i, 1);
            } catch (JSONException exception)
            {
                exception.printStackTrace();
            }
            Log.d("LoginActivity", "User is logged");

        } else
        {
            //NOTHING TO DO
        }
    }

    public void checkIfUsernameAndloginValidationAreTrue (boolean isUsernameValid, boolean isPasswordValid,
                                                    String typedUsername, LoginValidation loginValidation,
                                                    String typedPassword, EditText passwordField,
                                                    EditText usernameField)
    {
        if(isUsernameValid==true || loginValidation.isActivity(typedUsername))
        {
            isPasswordValid=loginValidation.checkPassword(typedUsername, typedPassword);

            if(isPasswordValid==false)
            {
                passwordField.requestFocus();
                passwordField.setError(loginValidation.getInvalidPasswordMessage());
            } else
            {
                //NOTHING TO DO
            }

        } else
        {

            usernameField.requestFocus();
            usernameField.setError(loginValidation.getInvalidUsernameMessage());

        }
    }

    public void checkIfUsernameAndloginValidationAreFalse (boolean isUsernameValid, boolean isPasswordValid,
                                                                 String typedUsername, LoginValidation loginValidation,
                                                                 String typedPassword, EditText passwordField,
                                                                 EditText usernameField)
    {
        if (isUsernameValid == false || !loginValidation.isActivity(typedUsername))
        {
            usernameField.requestFocus();
            usernameField.setError(loginValidation.getInvalidUsernameMessage());
        } else
        {
            isPasswordValid = loginValidation.checkPassword(typedUsername, typedPassword);

            if (isPasswordValid == false)
            {
                passwordField.requestFocus();
                passwordField.setError(loginValidation.getInvalidPasswordMessage());
            } else {
                //NOTHING TO DO
            }
        }
    }
}
