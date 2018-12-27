package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.adapter;

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
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.detail.model.DailyVideo;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.detail.VideoPlayActivity;

import java.util.ArrayList;

public class DayOneWorkoutAdapter extends RecyclerView.Adapter<DayOneWorkoutAdapter.ViewHolder>  {


    ArrayList<DailyVideo> mDailyVideoArrayList;

    private  Context mContext;


    public DayOneWorkoutAdapter(Context context)
    {
        mDailyVideoArrayList =generateDummyData();
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_daily_video, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {




        try {
            viewHolder.headingTV.setText(mDailyVideoArrayList.get(i).getTitle());
            viewHolder.joinTV.setText(mDailyVideoArrayList.get(i).getShortDes());
            viewHolder.timeTV.setText(mDailyVideoArrayList.get(i).getDateTime());
            manageClick(i,viewHolder.view);
        }catch (Exception ex)
        {
            ex.toString();
        }




    }

    @Override
    public int getItemCount() {
        return mDailyVideoArrayList.size();
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


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                mContext.startActivity(intent);

            }
        });
    }


    private   ArrayList<DailyVideo> generateDummyData()
    {
        ArrayList<DailyVideo> hmArray=new ArrayList<>();
        String [][] habitNames=new String[5][2];
        habitNames[0][0]="Shoulder";
        habitNames[1][0]="Your Legs";
        habitNames[2][0]="Neck";
        habitNames[3][0]="Push ups";
        habitNames[4][0]="Running";


        habitNames[0][1]=(R.drawable.ic_sardines)+"";
        habitNames[1][1]=(R.drawable.ic_patatoes)+"";
        habitNames[2][1]=(R.drawable.ic_lentils)+"";
        habitNames[3][1]=(R.drawable.ic_wheat_germ)+"";
        habitNames[4][1]=(R.drawable.ic_kale)+"";


          for(int i=0; i<habitNames.length; i++)
          {
              DailyVideo hm=new DailyVideo();
              hm.setId(i+"");
              hm.setTitle(habitNames[i][0]);
              hm.setDateTime("12-08-2018");
              hm.setShortDes("This is test des");

              hmArray.add(hm);
          }


          return  hmArray;
    }
}
