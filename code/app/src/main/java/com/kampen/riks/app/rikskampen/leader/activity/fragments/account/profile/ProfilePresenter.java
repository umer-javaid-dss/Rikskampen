package com.kampen.riks.app.rikskampen.leader.activity.fragments.account.profile;


import android.content.Context;
import android.support.annotation.NonNull;


import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riks.app.rikskampen.api.remote_api.ResponseStatus;
import com.kampen.riks.app.rikskampen.data_manager.Repository;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ProfilePresenter implements ProfileContract.Presenter{



    @NonNull
    private final ProfileContract.View  mProfileView;



    public ProfilePresenter(@NonNull ProfileContract.View loginView)
    {

        mProfileView = checkNotNull(loginView);

    }


    @Override
    public void performLogout(String token) {


        Repository.logoutUser(token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mProfileView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mProfileView.setLogoutSuccess(status.getMsg());
                }
                else
                {

                    mProfileView.setLogoutFailed(status.getMsg());
                }

            }
        });


    }



    @Override
    public void getUserProfilePhoto(Context context) {


        String [] params=SaveSharedPreference.getLoggedParams(context);

        DB_User user=Repository.provideUserLocal(params[0],params[1]);


        if(mProfileView!=null && user!=null)
        {

               mProfileView.setProfile(user);
        }


    }



}
