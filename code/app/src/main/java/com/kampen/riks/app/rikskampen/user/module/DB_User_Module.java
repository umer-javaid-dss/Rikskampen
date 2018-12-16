package com.kampen.riks.app.rikskampen.user.module;


import com.kampen.riks.app.rikskampen.user.model.DB_DailyFitnessPic;
import com.kampen.riks.app.rikskampen.user.model.DB_User;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {DB_User.class,DB_DailyFitnessPic.class})
public class DB_User_Module {

}
