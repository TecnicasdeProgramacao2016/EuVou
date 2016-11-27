package com.mathheals.euvou;

 /**
  * File name: UserDAOTest.
  * File pourpose: Test of Data Access Object - DAD  with true and falses parameters.
 */

import com.mathheals.euvou.controller.utility.LoginUtility;

import android.app.Activity;

import com.mathheals.euvou.controller.utility.LoginUtility;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Random;

import dao.UserDAO;
import exception.UserException;
import model.User;

public class UserDAOTest extends TestCase 
{
    public void testSave() throws ParseException, UserException
    {
            UserDAO userDAO = new UserDAO();
            User user;
            user = new User("marceloChavosaao","marceloChavosaao","marceloChavosao@euvou.com",
                            "marceloChavosao@euvou.com","123456","123456","11/09/2015");
            if(user == null)
            {
                throw new UserException("Usuário nulo");
            }
            else
            {
                //NOTHINHG TO
            }

            if (user.getIdUser()<0)
            {
                throw new UserException("Invalid Evaluation");
            } else {
                //NOTHING TO DO
            }

            assertTrue(userDAO.save(user).contains("Salvo"));
            userDAO.delete("marceloChavosaao");
    }

    public void testDisableLogin() throws ParseException, UserException, JSONException
    {
        UserDAO userDAO = new UserDAO();
        User user = new User(1,"Vinicius Pinheiro", "umteste", "14/02/1995",
                "viny-pinheiro@hotmail.com", "viny-pinheiro@hotmail.com", "123456", "123456");

        if(user == null)
        {
            throw new UserException("Usuário nulo");
        }
        else
        {
            //NOTHINHG TO
        }

        if (user.getIdUser()<0)
        {
            throw new UserException("Invalid Evaluation");
        } else {
            //NOTHING TO DO
        }

        //check if user is saved
        if(!userDAO.save(user).contains("Salvo"))
        {
            assertTrue(false);
            userDAO.delete("umteste");
        } else
        {
            //NOTHING TO DO
        }
        int id = userDAO.searchUserByUsername("umteste").getJSONObject("0").getInt("idUser");
        assertTrue(userDAO.disableUser(id).contains("Salvo"));
        userDAO.delete("umteste");

    }

    public void testDeleteByName() throws ParseException, UserException
    {

        UserDAO userDAO = new UserDAO();
        User user = new User("Marcelo", "marceloChavosaoa", "galudo11cm@uol.com", "123456", "24/11/1969");
        if(user == null)
        {
            throw new UserException("Usuário nulo");
        }
        else
        {
            //NOTHINHG TO
        }

        if (user.getIdUser()<0)
        {
            throw new UserException("Invalid Evaluation");
        } else {
            //NOTHING TO DO
        }

        //check if user is saved
        if(!userDAO.save(user).contains("Salvo"))
        {
            assertTrue(false);
        } else
        {
            //NOTHING TO DO
        }
        assertTrue(userDAO.delete("marceloChavosaoa").contains("Salvo"));
    }


    public void testDeleteById() throws ParseException, UserException, JSONException
    {

        UserDAO userDAO = new UserDAO();
        User user = new User("VIny", "viny", "viny@uol.com", "123456", "14/02/1995");
        if(user == null)
        {
            throw new UserException("Usuário nulo");
        }
        else
        {
            //NOTHINHG TO
        }

        if (user.getIdUser()<0)
        {
            throw new UserException("Invalid Evaluation");
        } else {
            //NOTHING TO DO
        }

        //check if user is saved
        if(!userDAO.save(user).contains("Salvo"))
        {
            assertTrue(false);
        } else
        {
            //NOTHING TO DO
        }
        int id = userDAO.searchUserByUsername("viny").getJSONObject("0").getInt("idUser");
        assertTrue(userDAO.delete(id).contains("Salvo"));
        userDAO.delete("viny");
    }



    public void testUpdateUser() throws ParseException, UserException, JSONException
    {
        UserDAO userDAO = new UserDAO();
        User user = new User(1,"Vinicius ppp", "umteste", "14/02/1995", "viny-pinheiro@hotmail.com",
                "viny-pinheiro@hotmail.com", "123456", "123456");
        if(user == null)
        {
            throw new UserException("Usuário nulo");
        }
        else
        {
            //NOTHINHG TO
        }

        if (user.getIdUser()<0)
        {
            throw new UserException("Invalid Evaluation");
        } else {
            //NOTHING TO DO
        }

        //check if user is saved
        if(!userDAO.save(user).contains("Salvo"))
        {
            assertTrue(false);
            userDAO.delete("umteste");
        } else
        {
            //NOTHING TO DO
        }
        assertTrue(userDAO.update(user).contains("Salvo"));
        userDAO.delete("umteste");

    }

    public void testeSearchUserById()
    {
        assertFalse(new UserDAO().searchUserById(3) == null);
    }


}
