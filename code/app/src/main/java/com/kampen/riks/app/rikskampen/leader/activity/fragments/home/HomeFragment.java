package com.kampen.riks.app.rikskampen.leader.activity.fragments.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.TrainingFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {


    private ViewPager mViewPager;
    private HomePagerAdapter homePagerAdapter;


    private   TabLayout mTablayout;


    private   TextView  tabLeft,tabRight;



    Fragment[] PAGES;



    private final String[] PAGE_TITLES = new String[]{
            "Activity",
            "Health & Programs",


    };


    public  static int selectedTab=0;


    public Fragment[] getFragments() {


     PAGES = new Fragment[]{

             ActivityFragment.newInstance(),
            TrainingFragment.newInstance(),


    };

        return PAGES;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tabLeft=(TextView) view.findViewById(R.id.tabLeft);
        tabRight=(TextView) view.findViewById(R.id.tabRight);


        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTablayout=(TabLayout) view.findViewById(R.id.tab_layout);
        homePagerAdapter=new HomePagerAdapter(getChildFragmentManager(),getFragments());
        mViewPager.setAdapter(homePagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);




        manageTab();



    }




    private  void manageTab()
    {


       /* mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                if(selectedTab==0)
                {

                    tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
                    tabLeft.setTextColor(Color.WHITE);
                    tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
                    tabRight.setTextColor(Color.parseColor("#c8a167"));

                }

                else
                {

                    tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
                    tabLeft.setTextColor(Color.parseColor("#c8a167"));
                    tabRight.setBackgroundResource(R.drawable.tab_right_selected);
                    tabRight.setTextColor(Color.WHITE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });*/

        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        if(selectedTab==0)
        {

            tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
            tabLeft.setTextColor(Color.WHITE);
            tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
            tabRight.setTextColor(Color.parseColor("#c8a167"));

        }

        else
        {

            tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
            tabLeft.setTextColor(Color.parseColor("#c8a167"));
            tabRight.setBackgroundResource(R.drawable.tab_right_selected);
            tabRight.setTextColor(Color.WHITE);
        }



        tabLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
                tabLeft.setTextColor(Color.WHITE);
                tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
                tabRight.setTextColor(Color.parseColor("#c8a167"));


                mViewPager.setCurrentItem(0);

                selectedTab=0;

            }
        });


        tabRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
                tabLeft.setTextColor(Color.parseColor("#c8a167"));
                tabRight.setBackgroundResource(R.drawable.tab_right_selected);
                tabRight.setTextColor(Color.WHITE);

                mViewPager.setCurrentItem(1);
                selectedTab=1;
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

        mViewPager=null;

    }



    public class HomePagerAdapter extends FragmentPagerAdapter {

        Fragment[] PAGES;

        public HomePagerAdapter(FragmentManager fragmentManager,Fragment[] PAGES) {

            super(fragmentManager);

            this.PAGES=PAGES;
        }

        @Override
        public Fragment getItem(int position) {

            return PAGES[position];
        }

        @Override
        public int getCount() {

            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(PAGES[0]!=null)
        {
            PAGES[0].onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }



    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (PAGES[0] != null) {
                PAGES[0].onActivityResult(requestCode, resultCode, data);
            }
        }catch (Exception ex)
        {
            ex.toString();
        }

    }

}
