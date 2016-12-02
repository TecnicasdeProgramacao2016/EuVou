
/**
 * file: UserDAO.java
 * purpose: make operations with the User class and the database
 */package dao;
import android.app.Activity;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;


public class UserDAO extends DAO
{
    private final static Logger logger = Logger.getLogger(UserDAO.class.getName());//atribute to use loggin system

    public UserDAO(Activity currentActivity)
    {
        super(currentActivity);
    }

    public UserDAO()
    {

    }

    public String save(final User user)
    {
        assert(user != null);
        logger.log(Level.INFO,"entered in the method that saves the user in the database");
        return this.executeQuery("INSERT INTO tb_user(nameUser, login,passwordUser,birthDate, email)VALUES" +
                "(\"" + user.getName() + "\", \"" + user.getUsername() + "\", \"" + user.getPassword() + "\"," +
                " STR_TO_DATE(\"" + user.getBirthDate() + "\",'%d/%m/%Y'),\"" + user.getEmail() + "\")");
    }

    public JSONObject searchUserByName(final String name)
    {
        assert(name != null);
        logger.log(Level.INFO,"entered in the method that searches the user by it's name");
        return this.executeConsult("SELECT * FROM vw_user WHERE nameUser LIKE \"%" + name + "%\"");
    }

    public JSONObject searchUserByUsername(final String username)
    {
        assert(username != null);
        logger.log(Level.INFO,"entered in the method that searches an user by it's username");
        return this.executeConsult("SELECT * FROM vw_user WHERE login=\"" + username + "\"");
    }

    public String searchUserById(final int idUser)
    {
        assert(idUser > 0);
        logger.log(Level.INFO,"entered in the method that searches the user in the database by it's id");
        return this.executeConsult("SELECT * from vw_user WHERE idUser="+idUser+"").toString();
    }

    //This method is just used on the tests
    public String delete(final String username)
    {
        assert(username != null);
        logger.log(Level.INFO,"entered in the method that deletes the user from the database");
        return this.executeQuery("DELETE FROM tb_user WHERE login=\"" + username + "\"");
    }

    public String delete(final int idUser)
    {
        assert(idUser > 0);
        logger.log(Level.INFO,"entered in the method that deletes an user from the database by it's id");
        return this.executeQuery("DELETE FROM tb_user WHERE idUser=\"" +idUser+ "\"");
    }

    public String update(final User user)
    {
        assert(user != null);
        logger.log(Level.INFO,"entered in the method that updates user on the database ");
        return this.executeQuery("UPDATE tb_user SET nameUser=\""+user.getName()+"\", " +
                "birthDate=STR_TO_DATE(\"" + user.getBirthDate() + "\",'%d/%m/%Y'), " +
                "email=\""+user.getEmail()+"\", passwordUser=\"" + user.getPassword() + "\"" +
                " WHERE idUser=\""+user.getIdUser()+"\"");
    }

    public String disableUser(final int idUser)
    {
        assert(idUser > 0);
        logger.log(Level.INFO,"entered in the method that disables the user from the database");
        return this.executeQuery("UPDATE tb_user SET isActivity=\"N\" WHERE idUser=" +idUser+ "");
    }
}