package com.mathheals.euvou.controller.utility;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/*
 * File name: ActivityUtility.
 * File pourpose: This file have the pourpose to disable the account login confirmation
 */

public class ActivityUtility 
{

    public static void restartActivity(Activity activity) 
    {
        Intent intent = activity.getIntent();
        activity.finish();
        activity.startActivity(intent);
    }

    public static void clearBackStack(FragmentActivity fragmentActivity) 
    {
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) 
        {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        else
        {
            //NOTHING TO DO
        }
    }
}
