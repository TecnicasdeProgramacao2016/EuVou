/**
 * file:CategoryDAO.java
 * purpose:class to execute the database conection to the class Category
 */
package dao;

import android.app.Activity;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO extends DAO
{
    private final static Logger logger = Logger.getLogger(CategoryDAO.class.getName());
    public CategoryDAO(Activity currentActivity)
    {
        super(currentActivity);
    }

    public JSONObject searchCategoryById(final int idCategory)
    {
        assert(idCategory > 0);
        logger.log(Level.INFO,"entered in the method that searches the category by it's id");

        String query = "SELECT nameCategory FROM tb_category WHERE idCategory = " + idCategory;
        JSONObject consult = this.executeConsult(query);

        return consult;
    }
}