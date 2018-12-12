package com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.CancelOrderFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.CompletedOrderFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.fragments.PendingOrderFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 */
public class OrderHistoryFragment extends Fragment {


    private ViewPager mViewPager;
    private OrderPagerAdapter orderPagerAdapter;


    private final String[] PAGE_TITLES = new String[]{

            "Pending",
            "Completed",
            "Canceled"

    };

    public Fragment[] getFragments() {


        Fragment[] PAGES = new Fragment[]{

                PendingOrderFragment.newInstance(),
                CompletedOrderFragment.newInstance(),
                CancelOrderFragment.newInstance(),

        };

        return PAGES;
    }


    public OrderHistoryFragment() {

    }

    public static OrderHistoryFragment newInstance() {
        OrderHistoryFragment fragment = new OrderHistoryFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_order_history, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        orderPagerAdapter=new OrderPagerAdapter(getChildFragmentManager(),getFragments());
        mViewPager.setAdapter(orderPagerAdapter);

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

    }


    public class OrderPagerAdapter extends FragmentPagerAdapter {

        Fragment[] PAGES;

        public OrderPagerAdapter(FragmentManager fragmentManager, Fragment[] PAGES) {

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
