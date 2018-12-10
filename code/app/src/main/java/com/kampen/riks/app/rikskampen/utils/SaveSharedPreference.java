package com.kampen.riks.app.rikskampen.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.kampen.riks.app.rikskampen.utils.PreferencesUtility.LOGGED_IN_PREF;
import static com.kampen.riks.app.rikskampen.utils.PreferencesUtility.LOGGED_IN_PREF_Email;
import static com.kampen.riks.app.rikskampen.utils.PreferencesUtility.LOGGED_IN_PREF_pass;

public class SaveSharedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }


    public static void setLoggedIn(Context context, boolean loggedIn,String email,String pass) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.putString(LOGGED_IN_PREF_Email,email);
        editor.putString(LOGGED_IN_PREF_pass,pass);
        editor.apply();
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static String[] getLoggedParams(Context context) {
        String email= getPreferences(context).getString(LOGGED_IN_PREF_Email, "");
        String pass= getPreferences(context).getString(LOGGED_IN_PREF_pass, "");

        return  new String []{email,pass};
    }
}