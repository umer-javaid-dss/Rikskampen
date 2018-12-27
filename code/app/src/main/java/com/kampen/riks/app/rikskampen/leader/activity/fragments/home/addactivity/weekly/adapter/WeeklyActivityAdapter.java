package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.weekly.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.weekly.daily.DailyActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.weekly.model.WeeklyActivityModel;

import java.util.ArrayList;

public class WeeklyActivityAdapter extends  RecyclerView.Adapter<WeeklyActivityAdapter.MyViewHolder>{



    ArrayList<WeeklyActivityModel>  list;




    public  WeeklyActivityAdapter()
    {

        list=getDummyData();

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


      View   view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weekly_activity,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.weeklyPick.setImageResource(Integer.parseInt(list.get(i).getActivityPic()));
        myViewHolder.calValue.setText(list.get(i).getCalBurn());
        myViewHolder.stepsValue.setText(list.get(i).getGoalSteps());
        myViewHolder.todayTime.setText(list.get(i).getActiveTime());


        manageItemClick(myViewHolder.item,i);

    }



    private  void  manageItemClick(final View item, final int pos)
    {
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(item.getContext(), DailyActivity.class);
                intent.putExtra("sampleObject", list.get(pos));
                item.getContext().startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {

        return list.size();
    }




    public static class   MyViewHolder   extends RecyclerView.ViewHolder
    {

       public View  item;
       public ImageView  weeklyPick;
       public TextView stepsValue,calValue,todayTime;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            weeklyPick=itemView.findViewById(R.id.weeklyPick);
            stepsValue=itemView.findViewById(R.id.stepsValue);
            calValue=itemView.findViewById(R.id.calValue);
            todayTime=itemView.findViewById(R.id.todayTime);


            item=itemView;
        }



    }


    private  ArrayList<WeeklyActivityModel>  getDummyData()
    {

        ArrayList<WeeklyActivityModel>  weeklyArrayList=new ArrayList<>();


        WeeklyActivityModel weeklyActivityModel=new WeeklyActivityModel();

        weeklyActivityModel.setActivityPic(R.drawable.profile2+"");
        weeklyActivityModel.setActiveTime("43");
        weeklyActivityModel.setGoalSteps("6000");
        weeklyActivityModel.setId("1");
        weeklyActivityModel.setCalBurn("1500");
        weeklyActivityModel.setStars("3");
        weeklyActivityModel.setSteps("1000");


        WeeklyActivityModel weeklyActivityModel2=new WeeklyActivityModel();
        weeklyActivityModel2.setActivityPic(R.drawable.profile3+"");
        weeklyActivityModel2.setActiveTime("33");
        weeklyActivityModel2.setGoalSteps("6000");
        weeklyActivityModel2.setId("1");
        weeklyActivityModel2.setCalBurn("1500");
        weeklyActivityModel2.setStars("3");
        weeklyActivityModel2.setSteps("1000");


        WeeklyActivityModel weeklyActivityModel3=new WeeklyActivityModel();
        weeklyActivityModel3.setActivityPic(R.drawable.profile5+"");
        weeklyActivityModel3.setActiveTime("33");
        weeklyActivityModel3.setGoalSteps("6000");
        weeklyActivityModel3.setId("1");
        weeklyActivityModel3.setCalBurn("1500");
        weeklyActivityModel3.setStars("3");
        weeklyActivityModel3.setSteps("1000");


        weeklyArrayList.add(weeklyActivityModel);
        weeklyArrayList.add(weeklyActivityModel2);
        weeklyArrayList.add(weeklyActivityModel3);


        return  weeklyArrayList;


    }

}
