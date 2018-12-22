package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.nutrition.detail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.nutrition.detail.model.TakeItem;

import java.util.ArrayList;


public class DetailNutritionAdapter extends RecyclerView.Adapter<DetailNutritionAdapter.ViewHolder>{



    ArrayList<TakeItem> mDailyVideoArrayList;

    private Context mContext;


    public DetailNutritionAdapter(Context context)
    {
        mDailyVideoArrayList =generateDummyData();
        mContext=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_detail_nutrition, viewGroup, false);

        return new DetailNutritionAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        try {
            viewHolder.thumbNail.setImageResource(mDailyVideoArrayList.get(i).getTempPath());
            viewHolder.headingTV.setText(mDailyVideoArrayList.get(i).getTitle());
            viewHolder.qtyTV.setText(mDailyVideoArrayList.get(i).getQty() + " serving");
        }catch (Exception ex)
        {

        }

    }

    @Override
    public int getItemCount() {


        return mDailyVideoArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView headingTV,qtyTV;

        public ImageView thumbNail;

        public View view;

        public ViewHolder(View v) {
            super(v);
            headingTV=v.findViewById(R.id.title);
            qtyTV=v.findViewById(R.id.qty);
            thumbNail=v.findViewById(R.id.itemThumbNail);
            view=v;

        }


    }


    private   ArrayList<TakeItem> generateDummyData()
    {
        ArrayList<TakeItem> hmArray=new ArrayList<>();


        String [][] habitNames=new String[4][2];

        habitNames[0][0]="Simple Spanish Scramble";
        habitNames[1][0]="Tropical Fruit Smoothie";
        habitNames[2][0]="Simple Yolk Scramble";
        habitNames[3][0]="Tropical Juice Smoothie";


        habitNames[0][1]=(R.drawable.ic_patatoes)+"";
        habitNames[1][1]=(R.drawable.ic_blueberries)+"";
        habitNames[2][1]=(R.drawable.ic_eggs_yolk)+"";
        habitNames[3][1]=(R.drawable.ic_liver)+"";



        for(int i=0; i<habitNames.length; i++)
        {
            TakeItem hm=new TakeItem();
            hm.setId(i);

            hm.setTitle(habitNames[i][0]);
            hm.setTempPath(Integer.parseInt(habitNames[i][1]));

            hm.setQty(1);
            hm.setProtein(40);
            hm.setCalories(50);
            hm.setFats(45);
            hmArray.add(hm);
        }


        return  hmArray;
    }
}
