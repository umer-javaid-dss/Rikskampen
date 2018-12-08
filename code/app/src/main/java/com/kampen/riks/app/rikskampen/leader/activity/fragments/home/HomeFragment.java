package com.kampen.riks.app.rikskampen.leader.activity.fragments.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.habits.HabitsFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.habits.adapters.HabitsAdapter;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.leaderboard.LeaderBoardFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {


    private ViewPager mViewPager;
    private HomePagerAdapter homePagerAdapter;



    private final String[] PAGE_TITLES = new String[]{
            "Leader Board",
            "Habits & Programs",

    };


    public Fragment[] getFragments() {


      Fragment[] PAGES = new Fragment[]{

            LeaderBoardFragment.newInstance(),
            HabitsFragment.newInstance(),

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


        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        homePagerAdapter=new HomePagerAdapter(getChildFragmentManager(),getFragments());
        mViewPager.setAdapter(homePagerAdapter);
        //mViewPager.invalidate();
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);


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

}
