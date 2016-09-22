/**
 * file:CategoryDAO.java
 * purpose:class to execute the database conection to the class Category
 */
package dao;

import android.app.Activity;

import org.json.JSONObject;

public class CategoryDAO extends DAO
{

    public CategoryDAO(Activity currentActivity)
    {
        super(currentActivity);
    }

    public JSONObject searchCategoryById(final int idCategory)
    {
        return this.executeConsult("SELECT nameCategory FROM tb_category WHERE idCategory = " + idCategory);
    }
}