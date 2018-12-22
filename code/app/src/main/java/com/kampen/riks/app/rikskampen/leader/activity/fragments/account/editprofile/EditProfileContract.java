package com.kampen.riks.app.rikskampen.leader.activity.fragments.account.editprofile;

import com.kampen.riks.app.rikskampen.BasePresenter;
import com.kampen.riks.app.rikskampen.BaseView;

public class EditProfileContract {

    interface View extends BaseView<Presenter> {

        void setEditProfile(String message);
        void setEditProfileFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void performEditProfile(String f_name, String l_name, String user_pass, String email);

    }

}
