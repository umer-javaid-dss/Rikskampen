package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder>  {




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_stress_relief, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.stressNameTV.setText("training "+(i+1));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView stressNameTV;

        public ViewHolder(View v) {
            super(v);

            stressNameTV=v.findViewById(R.id.stressNameTV);
        }


    }
}
