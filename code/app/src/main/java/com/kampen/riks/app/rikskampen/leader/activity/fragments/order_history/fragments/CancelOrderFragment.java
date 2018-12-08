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
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.adapters.CancelOrderAdapter;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.adapters.PendingOrderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CancelOrderFragment extends Fragment {


    private RecyclerView mCancelOrderRecyclerView;
    private CancelOrderAdapter mCancelOrderAdapter;


    public static CancelOrderFragment newInstance() {
        CancelOrderFragment fragment = new CancelOrderFragment();
        return fragment;
    }


    public CancelOrderFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_canceled_order, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCancelOrderRecyclerView = (RecyclerView) view.findViewById(R.id.canceledOrderRV);


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);



        mCancelOrderAdapter=new CancelOrderAdapter();


        mCancelOrderRecyclerView.setLayoutManager(mLayoutManager1);
        mCancelOrderRecyclerView.setAdapter(mCancelOrderAdapter);
    }
}
