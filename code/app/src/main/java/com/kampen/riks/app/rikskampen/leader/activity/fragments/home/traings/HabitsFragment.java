package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.adapters.NutritionAdapter;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.adapters.HealthFitnessAdapter;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.adapters.TrainingAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HabitsFragment
 * to handle interaction events.
 */
public class HabitsFragment extends Fragment {


    private  RecyclerView mHabitsRecyclerView;
    private NutritionAdapter mHabitsAdapter;
    private  RecyclerView mHealthRecyclerView;
    private HealthFitnessAdapter  mHealthAdapter;
    private  RecyclerView mStressRecyclerView;
    private TrainingAdapter mStressAdapter;

    public HabitsFragment() {

    }

    public static HabitsFragment newInstance() {
        HabitsFragment fragment = new HabitsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_habit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mHabitsRecyclerView = (RecyclerView) rootView.findViewById(R.id.habitsRV);
        mHealthRecyclerView = (RecyclerView) rootView.findViewById(R.id.health_and_fitnessRV);
        mStressRecyclerView = (RecyclerView) rootView.findViewById(R.id.stress_and_reliefRV);

        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity());
        mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity());
        mLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);

        mHabitsAdapter=new NutritionAdapter();
        mHealthAdapter=new HealthFitnessAdapter();
        mStressAdapter=new TrainingAdapter();

        mHabitsRecyclerView.setLayoutManager(mLayoutManager1);
        mHealthRecyclerView.setLayoutManager(mLayoutManager2);
        mStressRecyclerView.setLayoutManager(mLayoutManager3);

        mHabitsRecyclerView.setAdapter(mHabitsAdapter);
        mHealthRecyclerView.setAdapter(mHealthAdapter);
        mStressRecyclerView.setAdapter(mStressAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

        mHabitsRecyclerView=null;
        mHabitsAdapter=null;
        mHealthRecyclerView=null;
        mHabitsAdapter=null;
        mStressRecyclerView=null;
        mHabitsAdapter=null;

    }



}
