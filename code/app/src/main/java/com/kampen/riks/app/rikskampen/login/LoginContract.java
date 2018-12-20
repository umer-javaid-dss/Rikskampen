package com.kampen.riks.app.rikskampen.login;

import com.kampen.riks.app.rikskampen.BasePresenter;
import com.kampen.riks.app.rikskampen.BaseView;

public class LoginContract {

    interface View extends BaseView<Presenter> {

        void setLogin(String message);
        void setLoginFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void performLogin(String username,String userpass);

    }

}
