package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.leaderboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.kampen.riks.app.rikskampen.R;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.ViewHolder>  {




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_leader_board, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

            viewHolder.prog.setProgress(70.0f);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public IconRoundCornerProgressBar prog;

        public ViewHolder(View v) {
            super(v);

            prog=v.findViewById(R.id.progress3);

        }


    }
}
