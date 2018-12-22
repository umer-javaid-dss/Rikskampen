package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.workout.detail;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.kampen.riks.app.rikskampen.R;
import com.wang.avi.AVLoadingIndicatorView;

public class VideoPlayActivity extends AppCompatActivity {


   private boolean running=false;

    private MediaPlayer mediaPlayer;

    private TextView    timeTextView;

    boolean   isVolumeTrue=true;


    private AVLoadingIndicatorView triangleProgressView;

    private ImageView mReplayVideo;


    private static final float BEEP_VOLUME = 0.80f;


    private static final float NO_VOLUME = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        timeTextView=findViewById(R.id.timeTV);


        triangleProgressView=findViewById(R.id.triangleProgressView);

        mReplayVideo=findViewById(R.id.replayVideo);


        triangleProgressView.show();

        final VideoView videoView;
        videoView = (VideoView)findViewById(R.id.exerciseVideo);
        videoView.setVideoPath("http://riks.pt2019.ae/riksAssets/videos/RK1small.mp4");
        videoView.start();


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {

                mediaPlayer=mp;


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        triangleProgressView.hide();
                    }
                });


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



        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                mReplayVideo.setVisibility(View.VISIBLE);


            }
        });

        mReplayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                triangleProgressView.show();
                mReplayVideo.setVisibility(View.GONE);
                videoView.setVideoPath("http://riks.pt2019.ae/riksAssets/videos/RK1small.mp4");
                videoView.start();

            }
        });



    }

    public void onVolumeClick(View view) {


        if(isVolumeTrue)
        {
                 mediaPlayer.setVolume(NO_VOLUME,NO_VOLUME);

            isVolumeTrue=false;
        }
        else
        {
            mediaPlayer.setVolume(BEEP_VOLUME,BEEP_VOLUME);
            isVolumeTrue=true;
        }


    }

    public void onBackClick(View view) {

        finish();

    }
}
