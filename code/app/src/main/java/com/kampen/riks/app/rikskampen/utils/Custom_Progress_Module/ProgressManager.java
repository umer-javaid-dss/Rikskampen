package com.kampen.riks.app.rikskampen.utils.Custom_Progress_Module;

import android.content.Context;

public class ProgressManager {



    public static CustomProgressDialogue progress;


    public static void showProgress(Context activityContex, String message)
    {

        try {
            if (progress != null && progress.isShowing()) {
                progress.dismiss();
            }
        }catch (Exception ex){}
        progress=new CustomProgressDialogue(activityContex,message);

        progress.setCancelable(false);

        progress.show();

    }


    public static void hideProgress()
    {
        try
        {
            //hideProgress();
            progress.hide();

        }catch (Exception ex)
        {


        }
    }
}
