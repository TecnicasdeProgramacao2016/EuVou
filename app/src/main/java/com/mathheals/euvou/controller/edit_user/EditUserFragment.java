
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
 * A simple {@link Fragment} subclass.
 */
public class EditUserFragment extends Fragment implements View.OnClickListener {
    private int USER_STATUS;
    private final int LOGGED_OUT = -1;
    private EditAndRegisterUtility utilityForEdit = new EditAndRegisterUtility();
    private String name, birthDate, mail, mailConfirm, password, passwordConfirm;
    private EditText nameField, mailField, passwordField, birthDateField, mailConfirmField, passwordConfirmField;

    public EditUserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_user, container, false);

        UserDAO userDAO = new UserDAO(this.getActivity());

        EditText nameField = (EditText) view.findViewById(R.id.nameField);

        EditText dateField = (EditText) view.findViewById(R.id.dateField);
        dateField.addTextChangedListener(Mask.insert("##/##/####", dateField));

        EditText mailField = (EditText) view.findViewById(R.id.mailField);

        LoginUtility loginUtility = new LoginUtility(this.getActivity());
        USER_STATUS = loginUtility.getUserId();

        String str = userDAO.searchUserById(USER_STATUS);
        JSONObject json = null;
        try {
            json = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String nameUser = json.getJSONObject("0").getString("nameUser");
            String birthDate = json.getJSONObject("0").getString("birthDate");
            String mail = json.getJSONObject("0").getString("email");

            String[] birthDateSplit = birthDate.split("-");
            birthDate = birthDateSplit[2]+"/"+birthDateSplit[1]+"/"+birthDateSplit[0];

            nameField.setText(nameUser);
            dateField.setText(birthDate);
            mailField.setText(mail);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button update = (Button)view.findViewById(R.id.updateButton);
        update.setOnClickListener(this);

        return view;
    }

    private void updateUser(User user){
        UserDAO userDAO = new UserDAO(getActivity());
        userDAO.update(user);
    }

    @Override
    public void onClick(View v) {
        name = utilityForEdit.getTextTyped(R.id.nameField, this);
        birthDate = utilityForEdit.getTextTyped(R.id.dateField, this);
        mail = utilityForEdit.getTextTyped(R.id.mailField, this);
        mailConfirm = utilityForEdit.getTextTyped(R.id.confirmMailField, this);
        passwordConfirm = utilityForEdit.getTextTyped(R.id.confirmPasswordField, this);
        password = utilityForEdit.getTextTyped(R.id.passwordField, this);

        nameField = utilityForEdit.getEditTextById(R.id.nameField, this);
        birthDateField = utilityForEdit.getEditTextById(R.id.birthDateField, this);
        mailField = utilityForEdit.getEditTextById(R.id.mailField, this);
        mailConfirmField = utilityForEdit.getEditTextById(R.id.mailConfirmField, this);
        passwordField = utilityForEdit.getEditTextById(R.id.passwordField, this);
        passwordConfirmField = utilityForEdit.getEditTextById(R.id.passwordConfirmField, this);

        LoginUtility loginUtility = new LoginUtility(this.getActivity());
        USER_STATUS = loginUtility.getUserId();

        try {
            User user = new User(USER_STATUS, name, birthDate, mail, mailConfirm, password, passwordConfirm);
            updateUser(user);
            Toast.makeText(this.getActivity().getBaseContext(), "Usuário alterado com sucesso", Toast.LENGTH_LONG).show();

            Activity activity = getActivity();
            Intent intent = activity.getIntent();
            activity.finish();
            startActivity(intent);

        } catch (Exception e) {
            String message = e.getMessage();

            if (message.equals(User.NAME_CANT_BE_EMPTY_NAME)) {
                nameField.requestFocus();
                nameField.setError(message);
            }

            if (message.equals(User.NAME_CANT_BE_HIGHER_THAN_50)) {
                nameField.requestFocus();
                nameField.setError(message);
            }

            if (message.equals(User.EMAIL_CANT_BE_EMPTY_EMAIL)) {
                mailField.requestFocus();
                mailField.setError(message);
            }

            if(message.equals(User.NAME_CANT_BE_EMPTY_NAME)){
                nameField.requestFocus();
                nameField.setError(message);
            }
            if(message.equals(User.NAME_CANT_BE_EMPTY_NAME)){
                nameField.requestFocus();
                nameField.setError(message);
            }

            if(message.equals(User.NAME_CANT_BE_HIGHER_THAN_50)){
                nameField.requestFocus();
                nameField.setError(message);
            }

            if(message.equals(User.NAME_CANT_BE_EMPTY_NAME)){
                nameField.requestFocus();
                nameField.setError(message);
            }

            if(message.equals(User.NAME_CANT_BE_HIGHER_THAN_50)){
                nameField.requestFocus();
                nameField.setError(message);
            }

            if (message.equals(User.EMAIL_CANT_BE_HIGHER_THAN_150)) {
                mailField.requestFocus();
                mailField.setError(message);
            }

            if (message.equals(User.INVALID_EMAIL)) {
                mailField.requestFocus();
                mailField.setError(message);
            }

            if(message.equals(User.EMAIL_ARE_NOT_EQUALS)){
                mailField.requestFocus();
                mailField.setError(message);
            }

            if (message.equals(User.PASSWORD_CANT_BE_EMPTY_PASSWORD)) {
                passwordField.requestFocus();
                passwordField.setError(message);
            }

            if (message.equals(User.PASSWORD_CANT_BE_LESS_THAN_6)) {
                passwordField.requestFocus();
                passwordField.setError(message);
            }

            if(message.equals(User.PASSWORD_ARE_NOT_EQUALS)){
                passwordField.requestFocus();
                passwordField.setError(message);
            }

            if(message.equals(User.PASSWORD_ARE_NOT_EQUALS)){
                passwordField.requestFocus();
                passwordField.requestFocus();
            }

            if (message.equals(User.BIRTH_DATE_CANT_BE_EMPTY)) {
                birthDateField.requestFocus();
                birthDateField.setError(message);
            }

            if (message.equals(User.INVALID_BIRTH_DATE)) {
                birthDateField.requestFocus();
                birthDateField.setError(message);
            }
        }

    }
}