package com.kampen.riks.app.rikskampen.leader.activity.fragments.LeaderBordTab;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.LeaderBordTab.models.TopContestant;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeaderAdapterSteps extends RecyclerView.Adapter<LeaderAdapterSteps.ViewHolder>  {


    ArrayList<TopContestant> list;

    Context  mContext;

    private  int  mWidth=320;

    private  int  mHeight=720;


    private  int  mMaxSteps=120;




    public LeaderAdapterSteps(Context  context)
    {
        this.mContext=context;
        generateDummyData();


        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mHeight = (int) (displayMetrics.heightPixels / displayMetrics.density);
        mWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);

        mMaxSteps=707324;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_leader_board, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.positionTV.setText("#"+(i+1));

        viewHolder.nameTV.setText(list.get(i).getName()+"-"+list.get(i).getSteps());

        RelativeLayout.LayoutParams parems= (RelativeLayout.LayoutParams) viewHolder.leftContainer.getLayoutParams();

        parems.width= (int) convertDpToPixel(calculateItemWidth(Integer.parseInt(list.get(i).getSteps().replace(",","")),calculateItemMaxWidth(),mMaxSteps),mContext);//(int)convertDpToPixel(list.get(i).getPosition(),mContext);

        viewHolder.leftContainer.setLayoutParams(parems);


        viewHolder.profileImage1.setImageResource(list.get(i).tempResId);

        viewHolder.stepsIconContainer.bringToFront();


        onItemClick(viewHolder.selfView,i);

    }



    private int  calculateItemWidth(int totalSteps,int maxAvailableWidth,int maxSteps)
    {

        double temp1=totalSteps;
        double temp2=maxSteps;

        double percentile=(temp1/temp2);

        int itemWidth= (int) (percentile*maxAvailableWidth);

        return itemWidth;
    }


    private int  calculateItemMaxWidth(  )
    {

        int maxAvailableWidth= (int) (mWidth*.70);

        return maxAvailableWidth;
    }



    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView  positionTV;
        public TextView  nameTV;
        public LinearLayout leftContainer;
        public CircleImageView profileImage1;
        public View    stepsIconContainer;

        public View    selfView;


        public ViewHolder(View v) {
            super(v);
            selfView=v;
            positionTV=v.findViewById(R.id.position1);
            nameTV=v.findViewById(R.id.progress);
            leftContainer=v.findViewById(R.id.leftContainer1);
            profileImage1=v.findViewById(R.id.profileImage1);
            stepsIconContainer=v.findViewById(R.id.steps_icon_container);
        }


    }


    private  void onItemClick(View view,final int pos)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TopContestant temp=list.get(pos);


                Toast.makeText(mContext, "Clicked "+temp.getName(), Toast.LENGTH_SHORT).show();




            }
        });
    }

    private void generateDummyData()
    {
        list = new ArrayList<>();

        TopContestant topContestant1=new TopContestant();
        topContestant1.setName("Sami");
        topContestant1.setSteps("707,324");
        topContestant1.setPosition(250);
        topContestant1.tempResId=R.drawable.profile4;
        list.add(topContestant1);

        TopContestant topContestant2=new TopContestant();
        topContestant2.setName("Yum");
        topContestant2.setSteps("607,124");
        topContestant2.setPosition(240);
        topContestant2.tempResId=R.drawable.profile2;
        list.add(topContestant2);

        TopContestant topContestant3=new TopContestant();
        topContestant3.setName("Loki");
        topContestant3.setSteps("606,000");
        topContestant3.setPosition(230);
        topContestant3.tempResId=R.drawable.profile3;
        list.add(topContestant3);

        TopContestant topContestant4=new TopContestant();
        topContestant4.setName("Jamil");
        topContestant4.setSteps("576,315");
        topContestant4.setPosition(210);
        topContestant4.tempResId=R.drawable.profile4;
        list.add(topContestant4);

        TopContestant topContestant5=new TopContestant();
        topContestant5.setName("Sara");
        topContestant5.setSteps("536,222");
        topContestant5.setPosition(190);
        topContestant5.tempResId=R.drawable.profile5;
        list.add(topContestant5);

        TopContestant topContestant6=new TopContestant();
        topContestant6.setName("Sami");
        topContestant6.setSteps("520,765");
        topContestant6.setPosition(180);
        topContestant6.tempResId=R.drawable.profile2;
        list.add(topContestant6);

        TopContestant topContestant7=new TopContestant();
        topContestant7.setName("Jones");
        topContestant7.setSteps("504,760");
        topContestant7.setPosition(170);
        topContestant7.tempResId=R.drawable.profile3;
        list.add(topContestant7);

        TopContestant topContestant8=new TopContestant();
        topContestant8.setName("Smith");
        topContestant8.setSteps("454,435");
        topContestant8.setPosition(170);
        topContestant8.tempResId=R.drawable.profile4;
        list.add(topContestant8);

        TopContestant topContestant9=new TopContestant();
        topContestant9.setName("Kool");
        topContestant9.setSteps("424,234");
        topContestant9.setPosition(160);
        topContestant9.tempResId=R.drawable.profile5;
        list.add(topContestant9);


    }


    private void  showDetailedPopup(final TopContestant topContestant)
    {
        Button showPopupBtn, closePopupBtn;
        PopupWindow popupWindow;

        LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.pop_up_contestent_details,null);

        //closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);

        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        popupWindow.showAtLocation(((Activity)mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        //close the popup window on button click
        /*closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });*/



    }
}
