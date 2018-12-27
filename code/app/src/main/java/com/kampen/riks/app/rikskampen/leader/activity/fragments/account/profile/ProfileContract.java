package com.kampen.riks.app.rikskampen.leader.activity.fragments.account.profile;

import android.content.Context;

import com.kampen.riks.app.rikskampen.BasePresenter;
import com.kampen.riks.app.rikskampen.BaseView;
import com.kampen.riks.app.rikskampen.user.model.DB_User;

public class ProfileContract {

    interface View extends BaseView<Presenter> {

        void setProfile(DB_User user);

        void setLogoutSuccess(String message);
        void setLogoutFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void performLogout(String token);

        void getUserProfilePhoto(Context context);

    }

}
