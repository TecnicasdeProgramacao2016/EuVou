package com.mathheals.euvou.controller.utility;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/*
 * File name: Mask.
 * File pourpose: This file have the pourpose ... (edit later)
 */

public abstract class Mask 
{
    public static String unmask(String s) 
    {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }

    public static TextWatcher insert(final String mask, final EditText ediTxt) 
    {
        return new TextWatcher() 
        {
            boolean isUpdating;
            String old = "";
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                String str = Mask.unmask(s.toString());
                String mascara = "";

                //check if the information is updating according the user's actions
                if (isUpdating) 
                {
                    old = str;
                    if(old == null)
                    {
                        Log.d("Mask", "older string was not passed right");
                    }
                    else
                    {
                        //NOTHINHG TO DO
                    }
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) 
                {
                    if (m != '#' && str.length() > old.length()) 
                    {
                        mascara += m;
                        continue;
                    }
                    try 
                    {
                        mascara += str.charAt(i);
                    } catch (Exception e) 
                    {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        };
    }

    public static String getDateTimeInBrazilianFormat(String dateTime) 
    {
        String[] dateAndTime = dateTime.split(" ");
        String date = dateAndTime[0];

        String[] dateSplit = date.split("-");

        String brazilianDateFormat = dateSplit[2] + "-" +
                dateSplit[1] + "-" +
                dateSplit[0];

        return brazilianDateFormat + " " + dateAndTime[1];
    }

}

