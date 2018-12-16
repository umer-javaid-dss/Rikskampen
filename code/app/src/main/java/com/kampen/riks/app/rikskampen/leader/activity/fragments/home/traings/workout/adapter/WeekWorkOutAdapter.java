package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.adapter;

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

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView name;

        public ImageView image;

        public ViewHolder(View v) {
            super(v);

            image= v.findViewById(R.id.weekImage);
            name= v.findViewById(R.id.videoItemId);

        }


    }


    public List<WeekWorkOutModel> getWeekRoteen()
    {

        List<WeekWorkOutModel>  list=new ArrayList<>();
        for(int i=0; i<7; i++)
        {
            WeekWorkOutModel weekWorkOutModel=new WeekWorkOutModel();

            weekWorkOutModel.setWorkOutName("Crunches day "+(i+1));

            weekWorkOutModel.setTempImage(R.drawable.ic_workout);

            list.add(weekWorkOutModel);
        }

        return list;
    }

}
