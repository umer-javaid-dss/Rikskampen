package com.kampen.riks.app.rikskampen.user.module;


import com.kampen.riks.app.rikskampen.leader.activity.fragments.LeaderBordTab.models.TopContestant;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.model.DailyPick;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.model.WeekWorkOutModel;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.models.NutritionModel;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.models.PT_Model;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.models.TrainingModel;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.model.DailyVideo;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.map.Modal.PlacesDetails_Modal;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.model.Order;
import com.kampen.riks.app.rikskampen.user.model.DB_DailyFitnessPic;
import com.kampen.riks.app.rikskampen.user.model.DB_User;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {DB_User.class,DB_DailyFitnessPic.class,PlacesDetails_Modal.class,TopContestant.class,Order.class,WeekWorkOutModel.class,NutritionModel.class,PT_Model.class,TrainingModel.class,DailyVideo.class})
public class DB_User_Module {

}
