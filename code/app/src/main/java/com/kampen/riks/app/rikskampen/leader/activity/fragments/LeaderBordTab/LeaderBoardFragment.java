package com.kampen.riks.app.rikskampen.leader.activity.fragments.LeaderBordTab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.user.model.DB_User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeaderBoardFragment
 * to handle interaction events.
 */


public class LeaderBoardFragment extends Fragment {

    private RecyclerView mLeaderRecyclerView;
    private LeaderAdapterSteps mLeaderAdapter;
    private LeaderAdapterCalories mLeaderAdapterCal;
    private LeaderAdapterStar mLeaderAdapterStar;


    private TabLayout         mTablayout;


    private ImageView  mProfileImage,mCalStepImage;

    private TextView   mCalStepTV,mPositionTV;

    private TextView tabLeft,tabRight,tabMiddle;

    private View     steps_icon_container;

    private View      leftContainer1;

    private  ImageView profileImage;

    public  static int selectedTab=0;

    public LeaderBoardFragment() {

    }


    public static LeaderBoardFragment newInstance() {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_leader, container, false);
    }



    public  void manageTabLayout()
    {


        mTablayout.addTab(mTablayout.newTab().setText("Steps"));
        mTablayout.addTab(mTablayout.newTab().setText("Calories"));

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(mTablayout.getSelectedTabPosition() == 0){
                    if(mLeaderAdapter==null)
                    {

                        mLeaderAdapter=new LeaderAdapterSteps(getActivity());

                    }

                    mLeaderRecyclerView.setAdapter(mLeaderAdapter);

                }else if(mTablayout.getSelectedTabPosition() == 1) {

                    if(mLeaderAdapterCal==null)
                    {

                        mLeaderAdapterCal=new LeaderAdapterCalories(getActivity());

                    }

                    mLeaderRecyclerView.setAdapter(mLeaderAdapterCal);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);



        mProfileImage = rootView.findViewById(R.id.profileImage);
        steps_icon_container=rootView.findViewById(R.id.steps_icon_container);
        leftContainer1=rootView.findViewById(R.id.leftContainer1);
        mCalStepImage= rootView.findViewById(R.id.steps_icon);
        mCalStepTV   = rootView.findViewById(R.id.progress);
        mPositionTV   = rootView.findViewById(R.id.position);

        profileImage=rootView.findViewById(R.id.profileImage);

        mCalStepImage.bringToFront();

        //leftContainer1.bringToFront();

        tabLeft=(TextView) rootView.findViewById(R.id.tabLeft);
        tabMiddle=rootView.findViewById(R.id.tabMiddle);
        tabRight=(TextView) rootView.findViewById(R.id.tabRight);

        mLeaderRecyclerView = (RecyclerView) rootView.findViewById(R.id.leaderRV);
        mTablayout          = (TabLayout) rootView.findViewById(R.id.step_cal_tab);




        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);


        //manageTabLayout();


        mLeaderAdapter=new LeaderAdapterSteps(getActivity());

        mCalStepImage.setImageResource(R.mipmap.ic_feeet);
        steps_icon_container.bringToFront();
        mCalStepTV.setText("Sam - 365,765");


        mLeaderRecyclerView.setLayoutManager(mLayoutManager1);
        mLeaderRecyclerView.setAdapter(mLeaderAdapter);





        manageTab();




    }


    @Override
    public void onResume() {
        super.onResume();

        setUser();
    }

    private  void setUser()
    {
        DB_User mUser=MyApplication.tempUser;

        if(mUser!=null)
        {
            if(mUser!=null )
            {

                byte []  profileData=mUser.getProfilePicData();

                if(profileData!=null)
                {
                    Bitmap bmp = BitmapFactory.decodeByteArray(profileData, 0, profileData.length);
                    profileImage.setImageBitmap(bmp);
                }




            }

        }
    }



    private  void manageTab()
    {

        if(selectedTab==0)
        {
            tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
            tabLeft.setTextColor(Color.WHITE);
            tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
            tabRight.setTextColor(Color.parseColor("#c8a167"));
            tabMiddle.setBackgroundResource(R.drawable.tab_middle_unselected);
            tabMiddle.setTextColor(Color.parseColor("#c8a167"));
        }
        else if(selectedTab==1)
        {
            tabMiddle.setBackgroundResource(R.drawable.tab_middle_selected);
            tabMiddle.setTextColor(Color.WHITE);
            tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
            tabRight.setTextColor(Color.parseColor("#c8a167"));
            tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
            tabLeft.setTextColor(Color.parseColor("#c8a167"));
        }
        else
        {
            tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
            tabLeft.setTextColor(Color.parseColor("#c8a167"));
            tabRight.setBackgroundResource(R.drawable.tab_right_selected);
            tabRight.setTextColor(Color.WHITE);
            tabMiddle.setBackgroundResource(R.drawable.tab_middle_unselected);
            tabMiddle.setTextColor(Color.parseColor("#c8a167"));
        }



        tabLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
                tabLeft.setTextColor(Color.WHITE);
                tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
                tabRight.setTextColor(Color.parseColor("#c8a167"));
                tabMiddle.setBackgroundResource(R.drawable.tab_middle_unselected);
                tabMiddle.setTextColor(Color.parseColor("#c8a167"));


                if(mLeaderAdapter==null)
                {

                    mLeaderAdapter=new LeaderAdapterSteps(getActivity());

                }


                selectedTab=0;

                mCalStepImage.setImageResource(R.mipmap.ic_feeet);
                mCalStepTV.setText("Sam - 365,765");

                mLeaderRecyclerView.setAdapter(mLeaderAdapter);

            }
        });


        tabMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabMiddle.setBackgroundResource(R.drawable.tab_middle_selected);
                tabMiddle.setTextColor(Color.WHITE);
                tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
                tabRight.setTextColor(Color.parseColor("#c8a167"));
                tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
                tabLeft.setTextColor(Color.parseColor("#c8a167"));

                if(mLeaderAdapterStar==null)
                {

                    mLeaderAdapterStar=new LeaderAdapterStar(getActivity());

                }

                selectedTab=1;

                mCalStepImage.setImageResource(R.drawable.ic_star_new);
                mCalStepTV.setText("Sam - 345");

                mLeaderRecyclerView.setAdapter(mLeaderAdapterStar);

            }
        });

        tabRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
                tabLeft.setTextColor(Color.parseColor("#c8a167"));
                tabRight.setBackgroundResource(R.drawable.tab_right_selected);
                tabRight.setTextColor(Color.WHITE);
                tabMiddle.setBackgroundResource(R.drawable.tab_middle_unselected);
                tabMiddle.setTextColor(Color.parseColor("#c8a167"));

                if(mLeaderAdapterCal==null)
                {

                    mLeaderAdapterCal=new LeaderAdapterCalories(getActivity());

                }

                selectedTab=1;

                mCalStepImage.setImageResource(R.drawable.calories_t);
                mCalStepTV.setText("Sam - 3800,765");

                mLeaderRecyclerView.setAdapter(mLeaderAdapterCal);



            }
        });




    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }





}
