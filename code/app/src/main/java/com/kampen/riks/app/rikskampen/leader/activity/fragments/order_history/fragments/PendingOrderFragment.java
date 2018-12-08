package com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments;


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
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.adapters.PendingOrderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PendingOrderFragment extends Fragment {


    private RecyclerView mPendingOrderRecyclerView;
    private PendingOrderAdapter mPendingOrderAdapter;


    public static PendingOrderFragment newInstance() {
        PendingOrderFragment fragment = new PendingOrderFragment();
        return fragment;
    }


    public PendingOrderFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pending_order, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPendingOrderRecyclerView = (RecyclerView) view.findViewById(R.id.pendingOrderRV);


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);



        mPendingOrderAdapter=new PendingOrderAdapter();


        mPendingOrderRecyclerView.setLayoutManager(mLayoutManager1);
        mPendingOrderRecyclerView.setAdapter(mPendingOrderAdapter);
    }
}
