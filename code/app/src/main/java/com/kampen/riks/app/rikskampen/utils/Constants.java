package com.kampen.riks.app.rikskampen.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUser;
import com.kampen.riks.app.rikskampen.user.model.DB_User;

import java.util.regex.Pattern;

public class Constants {

    public final static  String MAP_KEY="AIzaSyAy11QPMQf14buf7SlIvjRuWWMiRHQSVb0";//"AIzaSyDZb6POB0XTX0m2eNMbgXruT56dyRsTDXQ";                //"AIzaSyDpGipfKQXGO1JU9Z4A1dMD3BZxjh5dJDg";
    public final static  int   MAP_RADIUS=1500;                //"AIzaSyDpGipfKQXGO1JU9Z4A1dMD3BZxjh5dJDg";


    public final static   String USER_EMAIL_TAG="_email";
    public final static   String USER_PASS_TAG="_pass";


    public static boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static void hideSoftKeyboard(View view,Context context) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public  static   int convertDpToPx(int dp,Context context){
        return Math.round(dp*(context.getResources().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));

    }




    public  static RemoteUser   DB_To_R_USER(DB_User user)
    {
        RemoteUser  rUser=new RemoteUser();

        rUser.setFirstname(user.getF_name());
        rUser.setLastname(user.getL_name());
        rUser.setEmail(user.getEmail());
        rUser.setPassword(user.getPass());

        return rUser;

    }



    public   static    class  Converter
    {


        public  static int parseStrInt(String value)
        {
           try {

             return   Integer.parseInt(value);
           }catch (NumberFormatException ex)
           {

           }


           return  0;
        }

    }
}
