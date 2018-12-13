package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.models.HabitModel;

import java.util.ArrayList;
import java.util.Random;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.ViewHolder>  {


    ArrayList<HabitModel> mHabitsArrayList;


    public NutritionAdapter()
    {
        mHabitsArrayList=generateDummyData();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_habits, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.habitNameTV.setText(mHabitsArrayList.get(i).getHabitName());
        viewHolder.habitJoinTV.setText(mHabitsArrayList.get(i).getJoinedPersons());
        viewHolder.habitStartFromTV.setText(mHabitsArrayList.get(i).getWorkingFrom());
    }

    @Override
    public int getItemCount() {
        return mHabitsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView habitNameTV,habitJoinTV,habitStartFromTV;

        public ViewHolder(View v) {
            super(v);
            habitNameTV=v.findViewById(R.id.habitNameTV);
            habitJoinTV=v.findViewById(R.id.habitJoinTV);
            habitStartFromTV=v.findViewById(R.id.habitTimeTV);
        }


    }


    private   ArrayList<HabitModel> generateDummyData()
    {
        ArrayList<HabitModel> hmArray=new ArrayList<>();
        String [] habitNames=new String[4];
        habitNames[0]="No Fats";
        habitNames[1]="No Drinks";
        habitNames[2]="Vegetable";
        habitNames[3]="Fish eat";

        Random  random=new Random();

          for(int i=0; i<4; i++)
          {
              HabitModel hm=new HabitModel();
              hm.setHabitId(i+"");
              hm.setHabitName(habitNames[i]);
              int joinedPersons=random.nextInt(100);
              hm.setJoinedPersons(joinedPersons+" joined");
              hm.setWorkingFrom(i+" weeks");
              hmArray.add(hm);
          }


          return  hmArray;
    }
}
