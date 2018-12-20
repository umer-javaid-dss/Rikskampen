package com.kampen.riks.app.rikskampen.signup;

import com.kampen.riks.app.rikskampen.BasePresenter;
import com.kampen.riks.app.rikskampen.BaseView;

public class SignupContract {

    interface View extends BaseView<Presenter> {

        void setSignup(String message);
        void setSignupFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void performSign_up(String f_name, String l_name, String user_pass,String email);

    }

}
