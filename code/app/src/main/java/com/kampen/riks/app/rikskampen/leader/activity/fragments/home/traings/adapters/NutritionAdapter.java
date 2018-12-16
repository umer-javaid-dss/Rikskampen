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
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.models.NutritionModel;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.nutrition.WeekNutritionActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.WeekWorkOutActivity;

import java.util.ArrayList;
import java.util.Random;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.ViewHolder>  {


    ArrayList<NutritionModel> mNutritionArrayList;

    private  Context mContext;


    public NutritionAdapter(Context context)
    {
        mNutritionArrayList =generateDummyData();
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_nutrition, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.nutritionImage.setImageResource(mNutritionArrayList.get(i).getTempPicture());
        viewHolder.habitNameTV.setText(mNutritionArrayList.get(i).getHabitName());
        viewHolder.habitJoinTV.setText(mNutritionArrayList.get(i).getJoinedPersons());
        viewHolder.habitStartFromTV.setText(mNutritionArrayList.get(i).getWorkingFrom());

        manageClick(i,viewHolder.view);


    }

    @Override
    public int getItemCount() {
        return mNutritionArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView habitNameTV,habitJoinTV,habitStartFromTV;

        public ImageView nutritionImage;

        public View view;

        public ViewHolder(View v) {
            super(v);
            habitNameTV=v.findViewById(R.id.habitNameTV);
            habitJoinTV=v.findViewById(R.id.habitJoinTV);
            habitStartFromTV=v.findViewById(R.id.habitTimeTV);
            nutritionImage=v.findViewById(R.id.nutrition_image);
            view=v;
        }


    }


    private void  manageClick(final int position,View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, WeekNutritionActivity.class);
                mContext.startActivity(intent);

            }
        });
    }


    private   ArrayList<NutritionModel> generateDummyData()
    {
        ArrayList<NutritionModel> hmArray=new ArrayList<>();
        String [][] habitNames=new String[10][2];
        habitNames[0][0]="Sardines";
        habitNames[1][0]="Potatoes";
        habitNames[2][0]="Lentils";
        habitNames[3][0]="Wheat germ";
        habitNames[4][0]="Kale";
        habitNames[5][0]="Seaweed";
        habitNames[6][0]="Garlic";
        habitNames[7][0]="Egg Yolks";
        habitNames[8][0]="Blueberries";
        habitNames[9][0]="Liver";

        habitNames[0][1]=(R.drawable.ic_sardines)+"";
        habitNames[1][1]=(R.drawable.ic_patatoes)+"";
        habitNames[2][1]=(R.drawable.ic_lentils)+"";
        habitNames[3][1]=(R.drawable.ic_wheat_germ)+"";
        habitNames[4][1]=(R.drawable.ic_kale)+"";
        habitNames[5][1]=(R.drawable.ic_seaweed)+"";
        habitNames[6][1]=(R.drawable.ic_garlic)+"";
        habitNames[7][1]=(R.drawable.ic_eggs_yolk)+"";
        habitNames[8][1]=(R.drawable.ic_blueberries)+"";
        habitNames[9][1]=(R.drawable.ic_liver)+"";

        Random  random=new Random();

          for(int i=0; i<10; i++)
          {
              NutritionModel hm=new NutritionModel();
              hm.setHabitId(i+"");
              hm.setHabitName(habitNames[i][0]);
              hm.setTempPicture(Integer.parseInt(habitNames[i][1]));
              int joinedPersons=random.nextInt(100);
              hm.setJoinedPersons(joinedPersons+" joined");
              if(i==0) {
                  hm.setWorkingFrom((i+1) + "st weeks");
              }
              else if(i==1)
              {
                  hm.setWorkingFrom((i+1) + "nd weeks");
              }
              else if(i==2)
              {
                  hm.setWorkingFrom((i+1) + "rd weeks");
              }
              else
              {
                  hm.setWorkingFrom((i+1) + "th weeks");
              }
              hmArray.add(hm);
          }


          return  hmArray;
    }
}
