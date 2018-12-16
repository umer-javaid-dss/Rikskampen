package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.model.DailyPick;
import com.kampen.riks.app.rikskampen.user.model.DB_DailyFitnessPic;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class DailyPicAdapter extends RecyclerView.Adapter<DailyPicAdapter.ViewHolder> {

    private  ArrayList<DailyPick>  list;

    private ActivityFragment tempFragment;

    private   Realm  mDataBase;

    public  DailyPicAdapter(ActivityFragment fragment, Realm dataBase)
    {
        tempFragment=fragment;

        mDataBase=dataBase;



        list=getDummyData();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_daily_pick, viewGroup, false);

        return new DailyPicAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        if(i==0)
        {
            if(list.get(i).getPicData()==null) {
                viewHolder.dailyPick.setImageResource(R.drawable.ic_camera);
            }
            else
            {
                byte []  picData=list.get(i).getPicData();
                Bitmap bmp = BitmapFactory.decodeByteArray(picData, 0, picData.length);
                viewHolder.dailyPick.setImageBitmap(bmp);
            }

            viewHolder.timeTV.setText("Today");

            manageImageClick(viewHolder.dailyPick,list.get(i));


        }
        else {

            byte []  picData=list.get(i).getPicData();

            if(picData!=null)
            {
                Bitmap bmp = BitmapFactory.decodeByteArray(picData, 0, picData.length);
                viewHolder.dailyPick.setImageBitmap(bmp);
            }
            else
            {
                viewHolder.dailyPick.setImageResource(R.drawable.ic_holder);
            }


            viewHolder.timeTV.setText((i+1)+" week");
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public   void addYourDailyPick(String imagePath)
    {
        if(list!=null)
        {
            Bitmap  bitmap=BitmapFactory.decodeFile(imagePath);
          DailyPick dailyPick= list.get(0);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
            final byte[] byteArray = stream.toByteArray();

            dailyPick.setPicData(byteArray);


           /* DB_DailyFitnessPic db_dailyFitnessPic=



          notifyDataSetChanged();*/
        }

    }

    private  void manageImageClick(final ImageView imageView,final DailyPick dailyPick)
    {
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                tempFragment.onAddPicture();


                return false;
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView timeTV;
        public ImageView dailyPick;

        public ViewHolder(View v) {
            super(v);

            timeTV=v.findViewById(R.id.timeTV);
            dailyPick=v.findViewById(R.id.dailyPick);
        }


    }

    private  ArrayList<DailyPick>  getDummyData()
    {

        ArrayList<DailyPick>  dailyPicks=new ArrayList<>();

        try {

            final RealmResults<DB_DailyFitnessPic> user = mDataBase.where(DB_DailyFitnessPic.class)
                    .equalTo("user_email", SaveSharedPreference.getUserID(tempFragment.getContext()))

                    .findAll();


            if (user.size() > 0) {

                for (int i = 0; i < user.size(); i++) {
                    DailyPick dailyPick = new DailyPick();

                    dailyPicks.add(dailyPick);

                }

            }

            if (dailyPicks.size() == 0)
            {
                dailyPicks.add(new DailyPick());
                dailyPicks.add(new DailyPick());
                dailyPicks.add(new DailyPick());
                dailyPicks.add(new DailyPick());
                dailyPicks.add(new DailyPick());
                dailyPicks.add(new DailyPick());
                dailyPicks.add(new DailyPick());
                dailyPicks.add(new DailyPick());
                dailyPicks.add(new DailyPick());
                dailyPicks.add(new DailyPick());
            }



        }catch (Exception ex)
        {
            ex.toString();
        }



              return  dailyPicks;


    }


}
