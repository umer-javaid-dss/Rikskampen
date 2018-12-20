package com.kampen.riks.app.rikskampen.signup;

import android.support.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riks.app.rikskampen.api.remote_api.ResponseStatus;
import com.kampen.riks.app.rikskampen.data_manager.Repository;
import com.kampen.riks.app.rikskampen.login.LoginContract;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class SignupPresenter implements SignupContract.Presenter{



    @NonNull
    private final SignupContract.View  mSignupView;



    public SignupPresenter(@NonNull SignupContract.View loginView)
    {
        mSignupView = checkNotNull(loginView);

    }



    @Override
    public void performSign_up(String f_name, String l_name, String user_pass, String email) {


        Repository.signUpUser(f_name,  l_name,user_pass,email, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mSignupView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {

                    mSignupView.setSignup(status.getMsg());
                }
                else
                {

                    mSignupView.setSignupFailed(status.getMsg());
                }


            }
        });

    }
}
