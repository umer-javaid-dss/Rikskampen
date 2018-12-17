package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.workout_video;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.kampen.riks.app.rikskampen.R;

public class VideoPlayActivity extends AppCompatActivity {


   private boolean running=false;

    private MediaPlayer mediaPlayer;

    private TextView    timeTextView;

    boolean   isVolumeTrue=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        timeTextView=findViewById(R.id.timeTV);

        final VideoView videoView;
        videoView = (VideoView)findViewById(R.id.exerciseVideo);
        videoView.setVideoPath("http://riks.pt2019.ae/riksAssets/videos/RK1small.mp4");
        videoView.start();


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {

                mediaPlayer=mp;

                running = true;
                final int duration = videoView.getDuration();

                new Thread(new Runnable() {
                    public void run() {
                        do{
                            timeTextView.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoView.getCurrentPosition())/1000;
                                    timeTextView.setText(time+"");
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(!running) break;
                        }
                        while(videoView.getCurrentPosition()<duration);
                    }
                }).start();
            }
        });





    }

    public void onVolumeClick(View view) {


        if(isVolumeTrue)
        {
                 mediaPlayer.setVolume(0,0);

            isVolumeTrue=false;
        }
        else
        {
            mediaPlayer.setVolume(10,10);
            isVolumeTrue=true;
        }


    }
}
