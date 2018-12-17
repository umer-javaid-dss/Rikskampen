package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.adapters.HealthFitnessAdapter;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.model.WeekWorkOutModel;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.workout_video.VideoPlayActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.webshop.WebShopActivity;

import java.util.ArrayList;
import java.util.List;


public class WeekWorkOutAdapter  extends RecyclerView.Adapter<WeekWorkOutAdapter.ViewHolder>  {


    List<WeekWorkOutModel> list;


    public  WeekWorkOutAdapter()
    {
        list=getWeekRoteen();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_week_workout, viewGroup, false);

        return new WeekWorkOutAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.image.setImageResource(list.get(i).getTempImage());
        viewHolder.name.setText(list.get(i).getWorkOutName());



        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(viewHolder.view.getContext(), VideoPlayActivity.class);
                viewHolder.view.getContext().startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView name;

        public ImageView image;

        public    View view;

        public ViewHolder(View v) {
            super(v);

            image= v.findViewById(R.id.weekImage);
            name= v.findViewById(R.id.videoItemId);

            view=v;

        }


    }


    public List<WeekWorkOutModel> getWeekRoteen()
    {

        List<WeekWorkOutModel>  list=new ArrayList<>();

        {
            WeekWorkOutModel weekWorkOutModel=new WeekWorkOutModel();
            weekWorkOutModel.setWorkOutName("Crunches day "+(1));
            weekWorkOutModel.setTempImage(R.drawable.ic_workout);
            list.add(weekWorkOutModel);

            WeekWorkOutModel weekWorkOutModel1=new WeekWorkOutModel();
            weekWorkOutModel1.setWorkOutName("Push ups day "+(2));
            weekWorkOutModel1.setTempImage(R.mipmap.ic_w_22);
            list.add(weekWorkOutModel1);

            WeekWorkOutModel weekWorkOutModel2=new WeekWorkOutModel();
            weekWorkOutModel2.setWorkOutName("Running day "+(3));
            weekWorkOutModel2.setTempImage(R.mipmap.ic_w_3);
            list.add(weekWorkOutModel2);

            WeekWorkOutModel weekWorkOutModel3=new WeekWorkOutModel();
            weekWorkOutModel3.setWorkOutName("Stress out day "+(3));
            weekWorkOutModel3.setTempImage(R.mipmap.ic_w_4);
            list.add(weekWorkOutModel3);




            WeekWorkOutModel weekWorkOutMode5=new WeekWorkOutModel();
            weekWorkOutMode5.setWorkOutName("Crunches day "+(1));
            weekWorkOutMode5.setTempImage(R.drawable.ic_workout);
            list.add(weekWorkOutMode5);

            WeekWorkOutModel weekWorkOutModel6=new WeekWorkOutModel();
            weekWorkOutModel6.setWorkOutName("Push ups day "+(2));
            weekWorkOutModel6.setTempImage(R.mipmap.ic_w_22);
            list.add(weekWorkOutModel6);

            WeekWorkOutModel weekWorkOutModel7=new WeekWorkOutModel();
            weekWorkOutModel7.setWorkOutName("Running day "+(3));
            weekWorkOutModel7.setTempImage(R.mipmap.ic_w_3);
            list.add(weekWorkOutModel7);
        }

        return list;
    }

}
