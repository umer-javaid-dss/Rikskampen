package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.models.NutritionModel;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.models.TrainingModel;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.WeekWorkOutActivity;

import java.util.ArrayList;
import java.util.Random;

public class TrainingAndFitnessAdapter extends RecyclerView.Adapter<TrainingAndFitnessAdapter.ViewHolder>  {


    ArrayList<TrainingModel> mTrainingArrayList;


    private Context mContext;



    public TrainingAndFitnessAdapter(Context context)
    {

        mContext=context;

        this.mTrainingArrayList = new ArrayList<>();

        this.mTrainingArrayList=generateDummyData();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_stress_relief, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.stressNameTV.setText(mTrainingArrayList.get(i).getTitle());
        viewHolder.JoinTV.setText(mTrainingArrayList.get(i).getJoinedPeople());
        viewHolder.StartFromTV.setText(mTrainingArrayList.get(i).getWeek());
        viewHolder.nutritionImage.setImageResource(mTrainingArrayList.get(i).getTemPic());


        manageClick(i,viewHolder.view);

    }



    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(mContext, ""+mTrainingArrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, WeekWorkOutActivity.class);
                mContext.startActivity(intent);


            }
        });
    }


    @Override
    public int getItemCount() {
        return mTrainingArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView stressNameTV;
        public  TextView JoinTV,StartFromTV;

        public ImageView nutritionImage;

        public View view;

        public ViewHolder(View v) {
            super(v);

            stressNameTV=v.findViewById(R.id.NameTV);
            JoinTV=v.findViewById(R.id.JoinTV);
            StartFromTV=v.findViewById(R.id.timeTV);
            nutritionImage=v.findViewById(R.id.workoutImage);
            view=v;
        }


    }


    private ArrayList<TrainingModel> generateDummyData()
    {
        ArrayList<TrainingModel> hmArray=new ArrayList<>();
        String [][] habitNames=new String[10][2];
        habitNames[0][0]="Barbell Bench";
        habitNames[1][0]="Incline Bench";
        habitNames[2][0]="Decline Bench";
        habitNames[3][0]="Dumbbell Flys";
        habitNames[4][0]="Dumbbell Pullover";
        habitNames[5][0]="Tricep Extension";
        habitNames[6][0]="Tricep Dip";
        habitNames[7][0]="Tricep Bench";
        habitNames[8][0]="Upright Row";
        habitNames[9][0]="Squat";

        habitNames[0][1]=(R.drawable.ic_barbell_bench)+"";
        habitNames[1][1]=(R.drawable.ic_incline_bench)+"";
        habitNames[2][1]=(R.drawable.ic_declinebench)+"";
        habitNames[3][1]=(R.drawable.ic_sumbbel_flys)+"";
        habitNames[4][1]=(R.drawable.ic_tricep_extension)+"";
        habitNames[5][1]=(R.drawable.ic_barbell_bench)+"";
        habitNames[6][1]=(R.drawable.ic_incline_bench)+"";
        habitNames[7][1]=(R.drawable.ic_declinebench)+"";
        habitNames[8][1]=(R.drawable.ic_sumbbel_flys)+"";
        habitNames[9][1]=(R.drawable.ic_tricep_extension)+"";

        Random random=new Random();

        for(int i=0; i<10; i++)
        {
            TrainingModel hm=new TrainingModel();
            hm.setId(i+"");
            hm.setTitle(habitNames[i][0]);
            hm.setTemPic(Integer.parseInt(habitNames[i][1]));
            int joinedPersons=random.nextInt(100);
            hm.setJoinedPeople(joinedPersons+" joined");

            if(i==0) {
                hm.setWeek((i+1) + "st weeks");
            }
            else if(i==1)
            {
                hm.setWeek((i+1) + "nd weeks");
            }
            else if(i==2)
            {
                hm.setWeek((i+1) + "rd weeks");
            }
            else
            {
                hm.setWeek((i+1) + "th weeks");
            }
            hmArray.add(hm);
        }


        return  hmArray;
    }
}
