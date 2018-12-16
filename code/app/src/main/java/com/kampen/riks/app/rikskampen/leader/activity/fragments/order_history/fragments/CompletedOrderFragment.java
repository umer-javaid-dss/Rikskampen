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
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.adapters.CompletedOrderAdapter;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.adapters.PendingOrderAdapter;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.model.Order;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedOrderFragment extends Fragment {


    private RecyclerView mCompletedOrderRecyclerView;
    private CompletedOrderAdapter mCompletedOrderAdapter;





    public static CompletedOrderFragment newInstance() {
        CompletedOrderFragment fragment = new CompletedOrderFragment();
        return fragment;
    }


    public CompletedOrderFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pending_order, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCompletedOrderRecyclerView = (RecyclerView) view.findViewById(R.id.pendingOrderRV);


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);



        mCompletedOrderAdapter=new CompletedOrderAdapter();


        mCompletedOrderRecyclerView.setLayoutManager(mLayoutManager1);
        mCompletedOrderRecyclerView.setAdapter(mCompletedOrderAdapter);
    }
}
