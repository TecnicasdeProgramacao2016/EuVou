/**
 *  file: RegisterFragment.java
 *  purpose: fragment to register a new user to the database
 */
package com.mathheals.euvou.controller.user_registration;
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
import com.mathheals.euvou.controller.login_user.LoginActivity;
import com.mathheals.euvou.controller.utility.EditAndRegisterUtility;
import com.mathheals.euvou.controller.utility.Mask;

import dao.UserDAO;
import model.User;

public class RegisterFragment extends Fragment implements View.OnClickListener
{
    private final String DEFAULT_STRING_MESSAGE = " ";
    private static final String SUCCESSFULL_CADASTRATION_MESSAGE = "Bem vindo ao #EuVou :)";
    private EditText nameField = null;
    private EditText birthDateField = null;
    private EditText mailField = null;
    private EditText mailConfirmationField = null;
    private EditText usernameField = null;
    private EditText passwordField = null;
    private EditText passwordConfirmField = null;
    private String name = DEFAULT_STRING_MESSAGE;
    private String birthDate = DEFAULT_STRING_MESSAGE;
    private String username = DEFAULT_STRING_MESSAGE;
    private String mail = DEFAULT_STRING_MESSAGE;
    private String password = DEFAULT_STRING_MESSAGE;
    private String passwordConfirm = DEFAULT_STRING_MESSAGE;
    private String mailConfirm = DEFAULT_STRING_MESSAGE;
    private EditAndRegisterUtility editAndRegisterUtility = new EditAndRegisterUtility();


    public RegisterFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
                             Bundle savedInstanceState)
    {
        assert(inflater != null);
        View view = inflater.inflate(R.layout.register_user, container, false);
        Button register = (Button) view.findViewById(R.id.saveButton);
        register.setOnClickListener(this);
        setingEditText(view);
        birthDateField.addTextChangedListener(Mask.insert("##/##/####", birthDateField));

        return view;
    }

    private void registerUser(final User user)//aplicar assertiva "não confie em ningúem"
    {
        assert(user != null);
        UserDAO userDAO = new UserDAO(getActivity());
        userDAO.save(user);
    }

    private void startLoginActivity()
    {
        Activity activity = getActivity();
        Intent myIntent = new Intent(activity, LoginActivity.class);
        activity.startActivity(myIntent);

    }

    private void setingEditText(View view)
    {
        assert(view != null);
        this.nameField = (EditText) view.findViewById(R.id.nameField);
        this.birthDateField = (EditText) view.findViewById(R.id.dateField);
        this.mailField = (EditText) view.findViewById(R.id.mailField);
        this.usernameField = (EditText) view.findViewById(R.id.loginField);
        this.passwordField = (EditText) view.findViewById(R.id.passwordField);
        this.mailConfirmationField = (EditText) view.findViewById(R.id.confirmMailField);
        this.passwordConfirmField = (EditText) view.findViewById(R.id.confirmPasswordField);
        this.birthDateField = (EditText) view.findViewById(R.id.dateField);
    }

    private void setingTextTyped()
    {
        this.name = nameField.getText().toString();
        this.username = usernameField.getText().toString();
        this.mail = mailField.getText().toString();
        this.mailConfirm = mailConfirmationField.getText().toString();
        this.password = passwordField.getText().toString();
        this.passwordConfirm = passwordConfirmField.getText().toString();
        this.birthDate = birthDateField.getText().toString();
    }

    @Override
    public void onClick(View view)
    {
        assert(view != null);
        setingTextTyped();

        try
        {
            User user = new User(name,
                                 username,
                                 mail,
                                 mailConfirm,
                                 password,
                                 passwordConfirm,
                                 birthDate);
            assert(user != null);
            registerUser(user);

            Toast.makeText(getActivity().getBaseContext(), SUCCESSFULL_CADASTRATION_MESSAGE,
                           Toast.LENGTH_LONG).show();
            startLoginActivity();

        } catch (Exception exception)
        {

            String message = exception.getMessage();
            UserRegisterErrorMessage(message);

        }
    }

    public void UserRegisterErrorMessage(final String message)
    {
        assert(message != null);
        if (message.equals(User.NAME_CANT_BE_EMPTY_NAME))
        {
            editAndRegisterUtility.setMessageError(nameField, message);

        }else if (message.equals(User.NAME_CANT_BE_HIGHER_THAN_50))
        {
            editAndRegisterUtility.setMessageError(nameField, message);

        }else if (message.equals(User.EMAIL_CANT_BE_EMPTY_EMAIL))
        {
            editAndRegisterUtility.setMessageError(mailField, message);

        }else if (message.equals(User.EMAIL_CANT_BE_HIGHER_THAN_150))
        {
            editAndRegisterUtility.setMessageError(mailField, message);

        }else if (message.equals(User.INVALID_EMAIL))
        {
            editAndRegisterUtility.setMessageError(mailField, message);

        }else if (message.equals(User.EMAIL_ARE_NOT_EQUALS))
        {
            editAndRegisterUtility.setMessageError(mailField, message);

        }else if (message.equals(User.USERNAME_CANT_BE_EMPTY_USERNAME))
        {
            editAndRegisterUtility.setMessageError(usernameField, message);

        }else if (message.equals(User.USERNAME_CANT_BE_HIGHER_THAN_100))
        {
            editAndRegisterUtility.setMessageError(usernameField, message);

        }else if (message.equals(User.PASSWORD_CANT_BE_EMPTY_PASSWORD))
        {
            editAndRegisterUtility.setMessageError(passwordField, message);

        }else if (message.equals(User.PASSWORD_CANT_BE_LESS_THAN_6))
        {
            editAndRegisterUtility.setMessageError(passwordField, message);

        }else if (message.equals(User.PASSWORD_ARE_NOT_EQUALS))
        {
            editAndRegisterUtility.setMessageError(passwordField, message);

        }else if (message.equals(User.BIRTH_DATE_CANT_BE_EMPTY))
        {
            editAndRegisterUtility.setMessageError(birthDateField, message);

        }else if (message.equals(User.INVALID_BIRTH_DATE))
        {
            editAndRegisterUtility.setMessageError(birthDateField, message);

        }else if (message.equals(User.USERNAME_EXISTENT))
        {
            editAndRegisterUtility.setMessageError(usernameField, message);

        }else if (message.equals(User.CONFIRM_PASSWORD_CANT_BE_EMPTY))
        {
            editAndRegisterUtility.setMessageError(passwordConfirmField, message);

        }else if(message.equals(User.EMAIL_CONFIRMATION_CANT_BE_EMPTY))
        {
            editAndRegisterUtility.setMessageError(mailConfirmationField, message);

        }else
        {
            //NOTHING TO DO
        }
    }
}
