package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.leaderboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kampen.riks.app.rikskampen.R;

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
    private TabLayout         mTablayout;

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

        mLeaderRecyclerView = (RecyclerView) rootView.findViewById(R.id.leaderRV);
        mTablayout          = (TabLayout) rootView.findViewById(R.id.step_cal_tab);

        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);


        manageTabLayout();


        mLeaderAdapter=new LeaderAdapterSteps(getActivity());


        mLeaderRecyclerView.setLayoutManager(mLayoutManager1);
        mLeaderRecyclerView.setAdapter(mLeaderAdapter);

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
