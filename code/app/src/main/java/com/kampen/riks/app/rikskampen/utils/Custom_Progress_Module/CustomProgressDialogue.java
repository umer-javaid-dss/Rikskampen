package com.kampen.riks.app.rikskampen.utils.Custom_Progress_Module;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.R;
import com.wang.avi.AVLoadingIndicatorView;

public class CustomProgressDialogue   extends ProgressDialog {

    AVLoadingIndicatorView mAVLoadingIndicatorView;

    TextView progressMessage;

    String message="";

    public CustomProgressDialogue(Context context, String message) {
        super(context,ProgressDialog.THEME_HOLO_DARK);


        this.message=message;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.custom_progress_layout);

        mAVLoadingIndicatorView= (AVLoadingIndicatorView) findViewById(R.id.triangleProgressView);
        progressMessage= (TextView) findViewById(R.id.progressMessage);
        mAVLoadingIndicatorView.show();

        progressMessage.setText(message);

    }


    public void StopView()
    {
        try {

            this.hide();
            if (mAVLoadingIndicatorView != null) {
                mAVLoadingIndicatorView.hide();
            }
        }catch (Exception ex){}
    }

}
