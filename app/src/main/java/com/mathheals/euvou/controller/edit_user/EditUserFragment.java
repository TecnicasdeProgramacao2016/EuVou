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
        View viewOfEditUser = inflater.inflate(R.layout.fragment_edit_user, container, false);//Returns view from main layout

        UserDAO userDAO = new UserDAO(this.getActivity());

        setingEditText(viewOfEditUser);
        birthDateField.addTextChangedListener(Mask.insert("##/##/####", birthDateField));

        LoginUtility loginUtility = new LoginUtility(this.getActivity());
        USER_STATUS = loginUtility.getUserId();

        String stringUserStatus = userDAO.searchUserById(USER_STATUS);//Searchs user by id on database
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
            String nameUser = (String)json.getJSONObject("0").getString("nameUser");
            String birthDate = (String)json.getJSONObject("0").getString("birthDate");
            String mail = (String)json.getJSONObject("0").getString("email");

            String[] birthDateSplit = birthDate.split("-");
            birthDate = birthDateSplit[2]+"/"+birthDateSplit[1]+"/"+birthDateSplit[0];//Format of date mm/dd/yyyy

            nameField.setText(nameUser);
            birthDateField.setText(birthDate);
            mailField.setText(mail);

        }
        catch (JSONException exceptionJSON)
        {
            exceptionJSON.printStackTrace();
        }

        Button update = (Button)viewOfEditUser.findViewById(R.id.updateButton);
        update.setOnClickListener(this);

        return viewOfEditUser;
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
            User userUpdate = new User(USER_STATUS, name, birthDate, mail, mailConfirm, password, passwordConfirm);
            updateUser(userUpdate);
            Toast.makeText(this.getActivity().getBaseContext(),
                           "Usuário alterado com sucesso", Toast.LENGTH_LONG).show();//Quick message with Toast of alteration of user

            Activity activity = getActivity();
            Intent intent = activity.getIntent();
            activity.finish();
            startActivity(intent);

        } catch (Exception messageOfError)
        {
            String message = (String) messageOfError.getMessage();
            seeErrorOnAnyMessage(message);
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
        this.nameField = (EditText) viewOfSettingEditText.findViewById(R.id.nameField);
        this.birthDateField = (EditText) viewOfSettingEditText.findViewById(R.id.dateField);
        this.mailField = (EditText) viewOfSettingEditText.findViewById(R.id.mailField);
        this.passwordField = (EditText) viewOfSettingEditText.findViewById(R.id.passwordField);
        this.mailConfirmationField = (EditText) viewOfSettingEditText.findViewById(R.id.confirmMailField);
        this.passwordConfirmField = (EditText) viewOfSettingEditText.findViewById(R.id.confirmPasswordField);
        this.birthDateField = (EditText) viewOfSettingEditText.findViewById(R.id.dateField);
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

}