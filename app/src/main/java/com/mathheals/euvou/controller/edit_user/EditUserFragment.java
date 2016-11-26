/*
* File name: EditUserFragment.
* File pourpose: Edit User and validate.
*/

package com.mathheals.euvou.controller.edit_user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.utility.EditAndRegisterUtility;
import com.mathheals.euvou.controller.utility.LoginUtility;
import com.mathheals.euvou.controller.utility.Mask;
import org.json.JSONException;
import org.json.JSONObject;
import dao.UserDAO;
import model.User;

import static junit.framework.Assert.assertTrue;


/**
*Class: EditUserFragment extends Fragment implements View.OnClickListener
*Description: Class to Edit an User
*/
public class EditUserFragment extends Fragment implements View.OnClickListener
{

    private EditText nameField = null;
    private EditText birthDateField = null;
    private EditText mailField = null;
    private EditText mailConfirmationField = null;
    private EditText passwordField = null;
    private EditText passwordConfirmField = null;
    private int USER_STATUS = 0;

    @Override
    /**
    *Method: public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    *Description: Creates view of user
    *@param LayoutInflater inflater
    *@param ViewGroup container
    *@param Bundle savedInstanceState
    */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //takes the view and user status
        View viewOfEditUser = inflater.inflate(R.layout.fragment_edit_user, container, false);//This line Returns view from main layout
        UserDAO userDAO = new UserDAO(this.getActivity());
        setingEditText(viewOfEditUser);
        birthDateField.addTextChangedListener(Mask.insert("##/##/####", birthDateField));
        LoginUtility loginUtility = new LoginUtility(this.getActivity());
        USER_STATUS = loginUtility.getUserId();

        final String stringUserStatus = userDAO.searchUserById(USER_STATUS);//This line Searchs user by id on database
        JSONObject json = null;
        try
        {
            json = new JSONObject(stringUserStatus);
        } catch (JSONException exceptionJSON)
        {
            exceptionJSON.printStackTrace();
        }

        try
        {
            //gets info of user and set them
            final String nameUser = (String)json.getJSONObject("0").getString("nameUser");
            String birthDate = (String)json.getJSONObject("0").getString("birthDate");
            final String mail = (String)json.getJSONObject("0").getString("email");
            final String[] birthDateSplit = birthDate.split("-");
            final int fieldZeroOfDate = 0;
            final int fieldOneOfDate = 1;
            final int fieldTwoOfDate = 2;

            birthDate = birthDateSplit[fieldTwoOfDate]+"/"+birthDateSplit[fieldOneOfDate]+"/"+birthDateSplit[fieldZeroOfDate];//This information in this line is on Format of date mm/dd/yyyy
            testIfNameAreValid(nameUser);
            nameField.setText(nameUser);
            birthDateField.setText(birthDate);
            mailField.setText(mail);

        }
        catch (JSONException exceptionJSON)
        {
            exceptionJSON.printStackTrace();
        }

        final int idToUpdateButton = R.id.updateButton;
        Button update = (Button)viewOfEditUser.findViewById(idToUpdateButton);
        update.setOnClickListener(this);

        return viewOfEditUser;
    }

    //Check if name of user are valid to be setted, if are not, there is a problem with data, program must shut down
    private void testIfNameAreValid(String nameUser){
        final int userNameMaxLength = 300;

        int nameSize = nameUser.length();

        assertTrue(nameSize <= userNameMaxLength);

    }

    private EditAndRegisterUtility editAndRegisterUtility = new EditAndRegisterUtility();
    private String name = "name";
    private String birthDate = "brithDate";
    private String mail = "mail";
    private String mailConfirm = "mailConfirm";
    private String password = "password";
    private String passwordConfirm = "passwordConfirm";

    @Override
    /**
    *Method: public void onClick(View viewOnClick)
    *Description: Validates and Updates
    *@param View viewOnClick
    */
    public void onClick(View viewOnClick)
    {
        setingTextTyped();

        LoginUtility loginUtility = new LoginUtility(this.getActivity());
        USER_STATUS = loginUtility.getUserId();

        //Validates and set user alteration
        try
        {
            //Updates user
            User userUpdate = new User(USER_STATUS, name, birthDate, mail, mailConfirm, password, passwordConfirm);
            updateUser(userUpdate);
            Toast.makeText(this.getActivity().getBaseContext(),"Usuário alterado com sucesso", Toast.LENGTH_LONG).show();//This line shows a quick message with Toast of alteration of user

            //Start activity based on witch activity has been selected
            Activity activity = getActivity();
            Intent intent = activity.getIntent();
            activity.finish();
            startActivity(intent);

        } catch (Exception messageOfError)
        {
            String message = (String) messageOfError.getMessage();
            seeErrorOnAnyMessage(message);
            finalizeObject(message);
        }
    }

    //See if has an error on input of some information
    private void seeErrorOnAnyMessage(String message){
        if(message.equals(User.EMAIL_CANT_BE_EMPTY_EMAIL))
        {
            editAndRegisterUtility.setMessageError(mailField, message);
        }
        else if(message.equals(User.NAME_CANT_BE_EMPTY_NAME))
        {
            editAndRegisterUtility.setMessageError(nameField, message);
        }
        else if(message.equals(User.NAME_CANT_BE_HIGHER_THAN_50))
        {
            editAndRegisterUtility.setMessageError(nameField, message);
        }
        else if(message.equals(User.EMAIL_CANT_BE_HIGHER_THAN_150))
        {
            editAndRegisterUtility.setMessageError(mailField, message);
        }
        else if(message.equals(User.INVALID_EMAIL))
        {
            editAndRegisterUtility.setMessageError(mailField, message);
        }
        else if(message.equals(User.EMAIL_ARE_NOT_EQUALS))
        {
            editAndRegisterUtility.setMessageError(mailField, message);
        }
        else if(message.equals(User.PASSWORD_CANT_BE_EMPTY_PASSWORD))
        {
            editAndRegisterUtility.setMessageError(passwordField, message);
        }
        else if(message.equals(User.PASSWORD_CANT_BE_LESS_THAN_6))
        {
            editAndRegisterUtility.setMessageError(passwordField, message);
        }
        else if(message.equals(User.PASSWORD_ARE_NOT_EQUALS))
        {
            editAndRegisterUtility.setMessageError(passwordField, message);
        }
        else if(message.equals(User.BIRTH_DATE_CANT_BE_EMPTY))
        {
            editAndRegisterUtility.setMessageError(birthDateField, message);
        }
        else if(message.equals(User.INVALID_BIRTH_DATE))
        {
            editAndRegisterUtility.setMessageError(birthDateField, message);
        }
        else if(message.equals(User.EMAIL_CONFIRMATION_CANT_BE_EMPTY))
        {
            editAndRegisterUtility.setMessageError(mailConfirmationField, message);
        }
        else if(message.equals(User.CONFIRM_PASSWORD_CANT_BE_EMPTY))
        {
            editAndRegisterUtility.setMessageError(passwordConfirmField, message);
        }
        else
        {
            //NOTHING TO DO
        }
    }

    //Updates user
    private void updateUser(User userUpdate)
    {
        UserDAO userDAO = new UserDAO(getActivity());
        userDAO.update(userUpdate);
    }

    //Sets user data
    private void setingEditText(View viewOfSettingEditText)
    {
        final int IdNameField = R.id.nameField;
        final int IdDateField = R.id.dateField;
        final int IdMailField = R.id.mailField;
        final int IdMailConfirmationField = R.id.confirmMailField;
        final int IdPasswordField = R.id.passwordField;
        final int IdPasswordConfirmField = R.id.confirmPasswordField;

        this.nameField = (EditText) viewOfSettingEditText.findViewById(IdNameField);
        this.mailField = (EditText) viewOfSettingEditText.findViewById(IdMailField);
        this.passwordField = (EditText) viewOfSettingEditText.findViewById(IdPasswordField);
        this.mailConfirmationField = (EditText) viewOfSettingEditText.findViewById(IdMailConfirmationField);
        this.passwordConfirmField = (EditText) viewOfSettingEditText.findViewById(IdPasswordConfirmField);
        this.birthDateField = (EditText) viewOfSettingEditText.findViewById(IdDateField);
    }

    //sets the text typed
    private void setingTextTyped()
    {
        this.name = nameField.getText().toString();
        this.mail = mailField.getText().toString();
        this.mailConfirm = mailConfirmationField.getText().toString();
        this.password = passwordField.getText().toString();
        this.passwordConfirm = passwordConfirmField.getText().toString();
        this.birthDate = birthDateField.getText().toString();
    }

    /**
    *Method: public EditUserFragment()
    *Description: Empty method to create edit user fragment
    */
    public EditUserFragment()
    {
        //Requires a empty public constructor
    }

    //Free objects to garbage collector take it easyer
    private void finalizeObject(Object object)
    {
        object = null;
    }

}