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

import java.util.ArrayList;


public class DailyPicAdapter extends RecyclerView.Adapter<DailyPicAdapter.ViewHolder> {

    private  ArrayList<DailyPick>  list;

    private ActivityFragment tempFragment;

    public  DailyPicAdapter(ActivityFragment fragment)
    {
        tempFragment=fragment;

        getDummyData();
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
            if(list.get(i).getPath()==null) {
                viewHolder.dailyPick.setImageResource(R.drawable.ic_camera);
            }
            else
            {
                Bitmap selectedImage = BitmapFactory.decodeFile(list.get(i).getPath());
                viewHolder.dailyPick.setImageBitmap(selectedImage);
            }

            viewHolder.timeTV.setText("Today");

            manageImageClick(viewHolder.dailyPick,list.get(i));


        }
        else {

            viewHolder.dailyPick.setImageResource(list.get(i).getTempRecourse());

            viewHolder.timeTV.setText(list.get(i).getTime());
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
          DailyPick dailyPick= list.get(0);
          dailyPick.setPath(imagePath);
          notifyDataSetChanged();
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

        DailyPick dailyPick1=new DailyPick();
        dailyPick1.setTempRecourse(R.drawable.profile2);
        dailyPick1.setTime("1 week");

        DailyPick dailyPick2=new DailyPick();
        dailyPick2.setTempRecourse(R.drawable.profile2);
        dailyPick2.setTime("2 week");

        DailyPick dailyPick3=new DailyPick();
        dailyPick3.setTempRecourse(R.drawable.profile2);
        dailyPick3.setTime("3 week");

        DailyPick dailyPick4=new DailyPick();
        dailyPick4.setTempRecourse(R.drawable.profile2);
        dailyPick4.setTime("4 week");

        list=new ArrayList<>();

        list.add(dailyPick1);
        list.add(dailyPick2);
        list.add(dailyPick3);
        list.add(dailyPick4);

        return list;

    }


}
