package com.mathheals.euvou.controller.remove_user;

import android.content.Context;
import android.widget.Toast;

/*
 * File name: RemoveUserViewMessages.
 * File pourpose: This file have the pourpose to remove all user view messages.
 */

public class RemoveUserViewMessages 
{
    
    public static final String WELCOME_BACK_MESSAGE = "Seja bem vindo novamente!";

    public static void showWelcomeBackMessage(Context context) 
    {
        Toast.makeText(context, WELCOME_BACK_MESSAGE, Toast.LENGTH_LONG).show();
    }

    public static final String BYE_BYE_MESSAGE = "Conta desativada :(";

    public static void showAccountDeactivateMessage(Context context) {
        Toast.makeText(context, BYE_BYE_MESSAGE, Toast.LENGTH_LONG).show();
    }
}
