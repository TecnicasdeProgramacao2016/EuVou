/*
* File name: User.
* File pourpose: Set and verify User data.
*/

package model;

import android.util.Log;
import android.util.Patterns;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import dao.UserDAO;
import exception.UserException;

/**
*Class: public class User
*Description: This class validates the user
*/
public class User
{
    public static final String ID_IS_INVALID = "Id inválido";
    public static final String NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos dizer seu nome.";
    public static final String NAME_CANT_BE_HIGHER_THAN_50 = "Hey, acho que você ultrapassou o número de caracteres permitido para o nome, tente novamente.";
    public static final String EMAIL_CANT_BE_EMPTY_EMAIL = "Hey, acho que você está esquecendo de nos dizer seu email.";
    public static final String EMAIL_CANT_BE_HIGHER_THAN_150 = "Hey, acho que você ultrapassou o número de caracteres permitido para email, tente novamente.";
    public static final String INVALID_EMAIL = "Ops, esse e-mail é inválido.";
    public static final String CONFIRM_PASSWORD_CANT_BE_EMPTY = "Hey, confirme sua senha";
    public static final String EMAIL_CONFIRMATION_CANT_BE_EMPTY = "Hey, confirme seu e-mail";
    public static final String USERNAME_CANT_BE_EMPTY_USERNAME = "Hey, acho que você está esquecendo de nos dizer seu login.";
    public static final String USERNAME_CANT_BE_HIGHER_THAN_100 = "Hey, acho que você ultrapassou o número de caracteres permitido para o login, tente novamente.";
    public static final String PASSWORD_CANT_BE_EMPTY_PASSWORD = "Hey, acho que você está esquecendo de nos dizer sua senha.";
    public static final String PASSWORD_CANT_BE_LESS_THAN_6 = "Hey, acho que vocẽ não atingiu o número mínimo de caracteres.";
    public static final String BIRTH_DATE_CANT_BE_EMPTY = "Hey, acho que você está esquecendo de nos dizer um dia muito especial, a data do seu nascimento.";
    public static final String EMAIL_ARE_NOT_EQUALS = "Ops, E-mails não conferem.";
    public static final String PASSWORD_ARE_NOT_EQUALS = "Ops, as senhas não conferem.";
    public static final String INVALID_BIRTH_DATE = "Ops, essa data é inválida";
    public static final String USERNAME_EXISTENT = "Ops, esse login já existe";
    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_EMAIL = 150;
    private static final int MAX_LENGTH_USERNAME = 100;
    private static final int MIN_LENGTH_PASSWORD = 6;
    private int idUser = 0;
    private String name = "name";
    private String username = "username";
    private String email = "email";
    private String password = "password";
    private String birthDate = "birthDate";

    /**
    *Method: public boolean equals(User user)
    *Description: validates if name taked and this name are the same
    *@param user
    */
    public boolean equals(User user)
    {
        return this.name.equals(user.getName()) &&
                  this.username.equals(user.getUsername()) &&
                  this.email.equals(user.getEmail()) &&
                  this.password.equals(user.getPassword()) &&
                  this.birthDate.equals(user.getBirthDate());
    }

    /**
    *Method: public User(int idUser, String name, String username,
    *                             String birthDate, String email, String mailConfirmation,
    *                             String password, String passwordConfirmation) throws UserException, ParseException
    *Description: sets user information
    *@param idUser has to be above 0
    *@param name
    *@param username
    *@param birthDate
    *@param email
    *@param mailConfirmation
    *@param password
    *@param passwordConfirmation
    */
    public User(int idUser, String name, String username,
                    String birthDate, String email, String mailConfirmation,
                    String password, String passwordConfirmation) throws UserException, ParseException
    {
        setIdUser(idUser);
        setName(name);
        setUsername(username);
        setBirthDate(birthDate);
        setEmail(email);
        verifyEmailConfirmation(mailConfirmation);
        setPassword(password);
        verifyPasswordConfirmation(passwordConfirmation);

    }

    /**
    *Method: public User(int idUser, String name, String birthDate,
    *                            String email) throws UserException, ParseException
    *Description: sets user information
    *@param idUser has to be above 0
    *@param name
    *@param birthDate
    *@param email
    */
    public User(int idUser, String name, String birthDate,
                    String email) throws UserException, ParseException
    {
        setName(name);
        setBirthDate(birthDate);
        setIdUser(idUser);
        setEmail(email);
    }

    /**
    *Method: public User(int idUser, String name, String birthDate,
    *                            String email, String mailConfirmation, String password,
    *                            String passwordConfirmation) throws UserException, ParseException
    *Description: sets user information
    *@param idUser has to be above 0
    *@param name
    *@param birthDate
    *@param email
    *@param mailConfirmation
    *@param password
    *@param passwordConfirmation
    */
    public User(int idUser, String name, String birthDate,
                String email, String mailConfirmation, String password,
                String passwordConfirmation) throws UserException, ParseException
    {
        setIdUser(idUser);
        setName(name);
        setBirthDate(birthDate);
        setEmail(email);
        verifyEmailConfirmation(mailConfirmation);
        setPassword(password);
        verifyPasswordConfirmation(passwordConfirmation);

    }

    /**
    *Method:     public User(String name, String username, String email,
    *                                 String password,String birthDate) throws UserException, ParseException
    *Description: sets user information
    *@param name
    *@param username
    *@param email
    *@param password
    *@param birthDate
    */
    public User(String name, String username, String email,
                String password,String birthDate) throws UserException, ParseException
    {
        setName(name);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setBirthDate(birthDate);
    }

    /**
    *Method: public User(String name, String username, String email,
    *                    String mailConfirmation, String password,
    *                    String passwordConfirmation, String birthDate) throws UserException, ParseException
    *Description: sets user information
    * @param name
    * @param username
    * @param email
    * @param mailConfirmation
    * @param password
    * @param passwordConfirmation
    * @param birthDate
    * @throws UserException
    * @throws ParseException
    */
    public User(String name, String username, String email,
                String mailConfirmation, String password,
                String passwordConfirmation, String birthDate) throws UserException, ParseException
    {
        setName(name);
        setBirthDate(birthDate);
        setEmail(email);
        verifyEmailConfirmation(mailConfirmation);
        setUsername(username);
        setPassword(password);
        verifyPasswordConfirmation(passwordConfirmation);
    }

    private void setIdUser(int idUser) throws UserException
    {
        assert(idUser > 0);
        assert(idUser < 2147483647);
        if(idUser <= Integer.MAX_VALUE && idUser >= 1){
            this.idUser = idUser;
        }
        else
        {
            throw new UserException(ID_IS_INVALID);
        }
        Log.d("User", "idUser has been set");
    }

    private void setName(String name) throws UserException
    {
        if(!name.isEmpty() && name != null)
        {
            if(name.length() <= MAX_LENGTH_NAME)
            {
                this.name = name;
            }
            else
            {
                throw new UserException(NAME_CANT_BE_HIGHER_THAN_50);
            }
        }
        else
        {
            throw new UserException(NAME_CANT_BE_EMPTY_NAME);
        }
        Log.d("User", "name has been set");
    }

    private  void  setEmail(String email) throws UserException
    {
        if (!email.isEmpty() && email != null)
        {
            if(email.length() <= MAX_LENGTH_EMAIL)
            {
                CharSequence emailCharSequence = email;
                if(Patterns.EMAIL_ADDRESS.matcher(emailCharSequence).matches())
                {
                    this.email = email;
                }
                else
                {
                    throw new UserException(INVALID_EMAIL);
                }
            }
            else
            {
                throw  new UserException(EMAIL_CANT_BE_HIGHER_THAN_150);
            }
        }
        else
        {
            throw  new UserException(EMAIL_CANT_BE_EMPTY_EMAIL);
        }
        Log.d("User", "email has been set");
    }

    private void verifyEmailConfirmation(String confirmationMail) throws UserException
    {
        if(confirmationMail != null && !confirmationMail.isEmpty())
        {
            if (!email.equals(confirmationMail))
            {
                throw new UserException(EMAIL_ARE_NOT_EQUALS);
            }
            else
            {
                //NOTHING TO DO
            }
        }
        else
        {
            throw new UserException(EMAIL_CONFIRMATION_CANT_BE_EMPTY);
        }
        Log.d("User", "Email has been verified");
    }

    private  void  setUsername (String username) throws UserException
    {
        if (username != null && !username.isEmpty())
        {
            if(username.length() <= MAX_LENGTH_USERNAME)
            {
                this.username = username;
            }
            else if (new UserDAO().searchUserByUsername(username) != null)
            {
                throw new UserException(USERNAME_EXISTENT);
            }
            else
            {
                throw  new UserException(USERNAME_CANT_BE_HIGHER_THAN_100);
            }
        }
        else
        {
            throw  new UserException(USERNAME_CANT_BE_EMPTY_USERNAME);
        }
        Log.d("User", "username has been set");

    }

    private  void  setPassword (String password) throws UserException
    {

        if (!password.isEmpty() && password != null)
        {
            if(password.length() >= MIN_LENGTH_PASSWORD)
            {
                this.password = password;
            }
            else
            {
                throw  new UserException(PASSWORD_CANT_BE_LESS_THAN_6);
            }
        }
        else
        {
            throw  new UserException(PASSWORD_CANT_BE_EMPTY_PASSWORD);
        }
        Log.d("User", "password has been set");
    }

    private void verifyPasswordConfirmation(String confirmationPassword) throws UserException
    {
        if(confirmationPassword != null && !confirmationPassword.isEmpty())
        {
            if (!password.equals(confirmationPassword))
            {
                throw new UserException(PASSWORD_ARE_NOT_EQUALS);
            }
            else
            {
                //NOTHING TO DO
            }
        }
        else
        {
            throw new UserException(CONFIRM_PASSWORD_CANT_BE_EMPTY);
        }
        Log.d("User", "password has been confirmed");
    }

    private void setBirthDate (String birthDate) throws UserException, ParseException
    {
        if(!birthDate.isEmpty() && birthDate != null)
        {
            try
            {
                SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
                format.setLenient(false);
                Date userDate = format.parse(birthDate);

                if (userDate.before(new Date()))
                {
                    this.birthDate = birthDate;
                }
                else
                {
                    throw new UserException(INVALID_BIRTH_DATE);
                }
            }
            catch (ParseException excecao)
            {
                throw new UserException(INVALID_BIRTH_DATE);
            }
        }
        else
        {
            throw new UserException(BIRTH_DATE_CANT_BE_EMPTY);
        }
        Log.d("User", "birthDate has been set");
    }


    /**
    *Method: public int getIdUser()
    *Description: gets id of user
    */
    public int getIdUser()
    {
        return idUser;
    }

    /**
    *Method: public String getName()
    *Description: gets name
    */
    public String getName()
    {
        return name;
    }

    /**
    *Method: public String getUsername()
    *Description: gets username
    */
    public String getUsername()
    {
        return username;
    }

    /**
    *Method: public String getEmail()
    *Description: gets email
    */
    public String getEmail()
    {
        return email;
    }

    /**
    *Method: public String getPassword()
    *Description: gets password
    */
    public String getPassword()
    {
        return password;
    }

    /**
    *Method: public String getBirthDate()
    *Description: gets birth date
    */
    public String getBirthDate()
    {
        return birthDate;
    }
}