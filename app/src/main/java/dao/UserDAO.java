
/**
 * file: UserDAO.java
 * purpose: make operations with the User class and the database
 */package dao;
import android.app.Activity;

import org.json.JSONObject;
import model.User;


public class UserDAO extends DAO
{

    public UserDAO(Activity currentActivity)
    {
        super(currentActivity);
    }

    public UserDAO()
    {

    }

    public String save(User user)
    {
        assert(user != null);
        return this.executeQuery("INSERT INTO tb_user(nameUser, login,passwordUser,birthDate, email)VALUES" +
                "(\"" + user.getName() + "\", \"" + user.getUsername() + "\", \"" + user.getPassword() + "\"," +
                " STR_TO_DATE(\"" + user.getBirthDate() + "\",'%d/%m/%Y'),\"" + user.getEmail() + "\")");
    }

    public String searchUserById(int idUser)
    {
        assert(idUser > 0);
        return this.executeConsult("SELECT * from vw_user WHERE idUser="+idUser+"").toString();
    }

    //This method is just used on the tests
    public String delete(String username)
    {
        assert(username != null);
        return this.executeQuery("DELETE FROM tb_user WHERE login=\"" + username + "\"");
    }

    public String delete(int idUser)
    {
        assert(idUser > 0);
        return this.executeQuery("DELETE FROM tb_user WHERE idUser=\"" +idUser+ "\"");
    }

    public String update(User user)
    {
        assert(user != null);
        return this.executeQuery("UPDATE tb_user SET nameUser=\""+user.getName()+"\", " +
                "birthDate=STR_TO_DATE(\"" + user.getBirthDate() + "\",'%d/%m/%Y'), " +
                "email=\""+user.getEmail()+"\", passwordUser=\"" + user.getPassword() + "\"" +
                " WHERE idUser=\""+user.getIdUser()+"\"");
    }

    public String disableUser(int idUser)
    {
        assert(idUser > 0);
        return this.executeQuery("UPDATE tb_user SET isActivity=\"N\" WHERE idUser=" +idUser+ "");
    }

    public JSONObject searchUserByUsername(String username)
    {
        assert(username != null);
        return this.executeConsult("SELECT * FROM vw_user WHERE login=\"" + username + "\"");
    }

    public JSONObject searchUserByName(String name)
    {
        assert(name != null);
        return this.executeConsult("SELECT * FROM vw_user WHERE nameUser LIKE \"%" + name + "%\"");
    }
}