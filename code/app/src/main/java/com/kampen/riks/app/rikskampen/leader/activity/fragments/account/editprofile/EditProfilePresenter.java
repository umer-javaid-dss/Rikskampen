package com.kampen.riks.app.rikskampen.leader.activity.fragments.account.editprofile;

import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riks.app.rikskampen.api.remote_api.ResponseStatus;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUser;
import com.kampen.riks.app.rikskampen.data_manager.Repository;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;


import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class EditProfilePresenter implements EditProfileContract.Presenter{



    @NonNull
    private final EditProfileContract.View  mEditProfileView;



    public EditProfilePresenter(@NonNull EditProfileContract.View loginView)
    {

                  mEditProfileView = checkNotNull(loginView);

    }






    @Override
    public void performEditProfile(String f_name, String l_name, String user_pass, String email) {


        Repository.editUser(f_name,  l_name,user_pass,email, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mEditProfileView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                     mEditProfileView.setEditProfile(status.getMsg());
                }
                else
                {

                    mEditProfileView.setEditProfileFailed(status.getMsg());
                }


            }
        });

    }

        public DB_User provideUserLocal(Context context)
        {

            String [] params=SaveSharedPreference.getLoggedParams(context);

            DB_User user=Repository.provideUserLocal(params[0],params[1]);

            return  user;
        }



        public void  updateUserLocalWithRemoteUser(RemoteUser rUser,DB_User user)
        {

            Repository.updateUserLocalWithRemoteUser(rUser,user);

        }

}
