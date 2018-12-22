package com.kampen.riks.app.rikskampen;

import android.content.Context;

import com.kampen.riks.app.rikskampen.data_manager.Repository;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

public class AppPresenter implements BasePresenter{



    public DB_User provideUserLocal(Context context)
    {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        DB_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }



    public void  updateUserLocal(DB_User  user)
    {

            Repository.updateUserLocal(user);

    }

}
