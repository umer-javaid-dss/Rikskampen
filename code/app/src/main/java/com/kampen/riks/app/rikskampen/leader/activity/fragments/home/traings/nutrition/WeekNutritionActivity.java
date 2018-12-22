package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.nutrition;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.nutrition.adapter.WeekNutritionDayOneAdapter;


public class WeekNutritionActivity extends AppCompatActivity {



    RecyclerView nutritionWeekRVDayOne;
    WeekNutritionDayOneAdapter nutritionWeekDayOneAdapter;

    RecyclerView nutritionWeekRVDayTwo;
    WeekNutritionDayOneAdapter nutritionWeekDayTwoAdapter;

    Context    mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_week_nutrition);

        mContext=WeekNutritionActivity.this;

        nutritionWeekRVDayOne=findViewById(R.id.nutritionWeekRVDayOne);
        nutritionWeekDayOneAdapter=new WeekNutritionDayOneAdapter(mContext);

        nutritionWeekRVDayTwo=findViewById(R.id.nutritionWeekRVDayTwo);
        nutritionWeekDayTwoAdapter=new WeekNutritionDayOneAdapter(mContext);


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this);
        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);


        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        nutritionWeekRVDayOne.setLayoutManager(mLayoutManager1);
        nutritionWeekRVDayOne.setAdapter(nutritionWeekDayOneAdapter);

        nutritionWeekRVDayTwo.setLayoutManager(mLayoutManager2);
        nutritionWeekRVDayTwo.setAdapter(nutritionWeekDayTwoAdapter);


    }


    public void onBackClick(View view) {

        finish();

    }
}
