package com.kampen.riks.app.rikskampen.login;

import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riks.app.rikskampen.api.remote_api.ResponseStatus;
import com.kampen.riks.app.rikskampen.data_manager.Repository;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter{



    @NonNull
    private final LoginContract.View  mLoginView;



    public LoginPresenter(@NonNull LoginContract.View loginView)
    {
        mLoginView = checkNotNull(loginView);

    }


    @Override
    public void performLogin(String email, String pass) {


               Repository.getUser(email, pass, new ResponseStatus() {
                   @Override
                   public void onResult(ResponseStatus status) {


                       if(mLoginView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                       {
                           mLoginView.setLogin(status.getMsg());
                       }
                       else
                       {
                           mLoginView.setLoginFailed(status.getMsg());
                       }


                   }
               });

    }


    public void  updateUserLocal(DB_User user)
    {

        Repository.updateUserLocal(user);

    }

    public DB_User provideUserLocal(Context context)
    {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        DB_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }



}
