package com.mathheals.euvou.controller.utility;

import android.support.v4.app.Fragment;
import android.widget.EditText;

/*
 * File name: EditAndRegisterUtility.
 * File pourpose: This file have the pourpose to edit and register utilities
 */

public class EditAndRegisterUtility 
{

    public void setMessageError(EditText editText, final String message)
    {
        /**
         * method that show errors message
         * @param editText -
         * @param message - message that show the error
         */

        editText.requestFocus();
        editText.setError(message);
    }
    
}
