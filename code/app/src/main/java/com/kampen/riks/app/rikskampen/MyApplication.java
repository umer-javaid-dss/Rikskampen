package com.kampen.riks.app.rikskampen;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.stetho.Stetho;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication  extends MultiDexApplication {


    public  static DB_User  tempUser;

    @Override
    public void onCreate() {
        super.onCreate();

        try
        {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/MontserratAlternates-Bold.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()
            );
        }catch (Exception ex)
        {
            ex.toString();
        }


        try
        {
            Realm.init(this);

        }catch (Exception ex)
        {
            ex.toString();
        }

        Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException (Thread thread, Throwable e)
            {
                handleUncaughtException (thread, e);
            }
        });
    }


    public void handleUncaughtException (Thread thread, Throwable e)
    {
        e.printStackTrace(); // not all Android versions will print the stack trace automatically

        Intent intent = new Intent ();
        intent.setAction ("com.mydomain.SEND_LOG"); // see step 5.
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK); // required when starting from Application
        startActivity (intent);

        System.exit(1); // kill off the crashed app
    }

    public static  void showSimpleSnackBar(Context mContext, String message)
    {

        View rootView=((AppCompatActivity)mContext).findViewById(android.R.id.content);

        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(Color.parseColor("#cc0000"));
        snackbar.show();
    }


}
