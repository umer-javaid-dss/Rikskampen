package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.adapter.WeekWorkOutAdapter;

public class WeekWorkOutActivity extends AppCompatActivity {



    RecyclerView workoutWeekRV;

    WeekWorkOutAdapter weekWorkOutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_week_work_out);



        workoutWeekRV=findViewById(R.id.workoutWeekRV);


        weekWorkOutAdapter=new WeekWorkOutAdapter();


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this);
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

        workoutWeekRV.setLayoutManager(mLayoutManager1);

        workoutWeekRV.setAdapter(weekWorkOutAdapter);


    }
}
