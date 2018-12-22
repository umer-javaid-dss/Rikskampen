package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.nutrition.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.nutrition.detail.NutritionActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.nutrition.model.DailyNutrition;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.detail.VideoPlayActivity;

import java.util.ArrayList;


public class WeekNutritionDayOneAdapter extends RecyclerView.Adapter<WeekNutritionDayOneAdapter.ViewHolder>  {


    ArrayList<DailyNutrition> mDailyNutritionArrayList;

    private Context mContext;


    public WeekNutritionDayOneAdapter(Context context)
    {
        mContext=context;
        mDailyNutritionArrayList=generateDummyData();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_week_nutrition, viewGroup, false);

        return new WeekNutritionDayOneAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        try {
            viewHolder.thumbNail.setImageResource(mDailyNutritionArrayList.get(i).getThumbNailPathTemp());
            viewHolder.headingTV.setText(mDailyNutritionArrayList.get(i).getTitle());
            viewHolder.joinTV.setText(mDailyNutritionArrayList.get(i).getShortDes());
            viewHolder.timeTV.setText(mDailyNutritionArrayList.get(i).getDateTime());
            manageClick(i,viewHolder.view);
        }catch (Exception ex)
        {
            ex.toString();
        }

    }



    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, NutritionActivity.class);
                mContext.startActivity(intent);

            }
        });
    }





    @Override
    public int getItemCount() {
        return mDailyNutritionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView headingTV,joinTV,timeTV;

        public ImageView thumbNail;

        public View view;

        public ViewHolder(View v) {
            super(v);
            headingTV=v.findViewById(R.id.headingTV);
            joinTV=v.findViewById(R.id.joinTV);
            timeTV=v.findViewById(R.id.timeTV);
            thumbNail=v.findViewById(R.id.thumbNail);
            view=v;

        }


    }


    private   ArrayList<DailyNutrition> generateDummyData()
    {
        ArrayList<DailyNutrition> hmArray=new ArrayList<>();
        String [][] nutritionNames=new String[3][2];
        nutritionNames[0][0]="Breakfast";
        nutritionNames[1][0]="Lunch";
        nutritionNames[2][0]="Dinner";



        nutritionNames[0][1]=(R.drawable.ic_breakfast)+"";
        nutritionNames[1][1]=(R.drawable.ic_lunch)+"";
        nutritionNames[2][1]=(R.drawable.ic_dinner)+"";


        for(int i=0; i<nutritionNames.length; i++)
        {
            DailyNutrition hm=new DailyNutrition();
            hm.setId(i+"");
            hm.setTitle(nutritionNames[i][0]);
            hm.setThumbNailPathTemp(Integer.parseInt(nutritionNames[i][1]));
            hm.setDateTime("12-08-2018");
            hm.setShortDes("This is test des");

            hmArray.add(hm);
        }


        return  hmArray;
    }

}
