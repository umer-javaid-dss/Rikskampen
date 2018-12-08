package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.leaderboard;

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
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.habits.adapters.HabitsAdapter;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.habits.adapters.HealthFitnessAdapter;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.habits.adapters.StressReliefAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeaderBoardFragment
 * to handle interaction events.
 */
public class LeaderBoardFragment extends Fragment {

    private RecyclerView mLeaderRecyclerView;
    private LeaderAdapter mLeaderAdapter;

    public LeaderBoardFragment() {

    }

    public static LeaderBoardFragment newInstance() {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_leader, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mLeaderRecyclerView = (RecyclerView) rootView.findViewById(R.id.leaderRV);


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);



        mLeaderAdapter=new LeaderAdapter();


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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
