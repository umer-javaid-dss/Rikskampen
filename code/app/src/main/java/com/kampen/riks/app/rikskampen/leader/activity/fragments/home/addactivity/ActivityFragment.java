package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.adapter.DailyPicAdapter;

import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService;
import com.kampen.riks.app.rikskampen.user.module.DB_User_Module;
import com.kampen.riks.app.rikskampen.utils.Constants;

import java.util.ArrayList;

import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;
import io.realm.Realm;
import io.realm.RealmConfiguration;


import static android.support.v4.content.PermissionChecker.checkSelfPermission;


public class ActivityFragment extends Fragment {


    private Realm mRealm;

    private ImagePicker imagePicker;

    private RecyclerView  dailyPickRecyclerView;

    private DailyPicAdapter dailyPicAdapter;


    private LineChart chart;

    public final static String    TAG_="Activity Fragment";







    public ActivityFragment() {


    }



    public static ActivityFragment newInstance() {
        ActivityFragment fragment = new ActivityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_activity, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        dailyPickRecyclerView = (RecyclerView) view.findViewById(R.id.dailyPicRV);

        setUpDB();

        dailyPicAdapter=new DailyPicAdapter(this,mRealm);

        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

        dailyPickRecyclerView.setLayoutManager(mLayoutManager1);

        dailyPickRecyclerView.setAdapter(dailyPicAdapter);

        imagePicker = new ImagePicker();

        setChartData(view);


        if(!Constants.isMyServiceRunning(StepCountingService.class,getContext()))
        {
            getActivity().startService(new Intent(getContext(), StepCountingService.class));
            // register our BroadcastReceiver by passing in an IntentFilter. * identifying the message that is broadcasted by using static string "BROADCAST_ACTION".
            getActivity().registerReceiver(broadcastReceiver, new IntentFilter(StepCountingService.BROADCAST_ACTION));

        }

    }




    private  void setChartData(View view)
    {
        chart = view.findViewById(R.id.chart1);

        chart.setViewPortOffsets(0, 0, 0, 0);
        //chart.setBackgroundColor(Color.rgb(20, 20, 20));

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);

        XAxis x = chart.getXAxis();
        x.setEnabled(false);

        YAxis y = chart.getAxisLeft();
        //y.setTypeface(tfLight);
        y.setLabelCount(0, false);
        y.setTextColor(Color.TRANSPARENT);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.GREEN);

        chart.getAxisRight().setEnabled(false);



        chart.getLegend().setEnabled(false);

        chart.animateXY(2000, 2000);

        // don't forget to refresh the drawing

        setData(10,10);
        chart.invalidate();

    }



    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            float val = (float) (Math.random() * (range + 1)) + 20;
            values.add(new Entry(i, val));
        }

        for (int i = 5; i < 10; i++) {

            values.add(new Entry(i,0));
        }
        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.GREEN);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.GREEN);
            set1.setFillColor(Color.GREEN);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a data object with the data sets
            LineData data = new LineData(set1);
            //data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            chart.setData(data);
        }
    }



    // --------------------------------------------------------------------------- \\
    // ___ create Broadcast Receiver ___ \\
    // create a BroadcastReceiver - to receive the message that is going to be broadcast from the Service
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // call updateUI passing in our intent which is holding the data to display.
            updateViews(intent);
        }
    };
    // ___________________________________________________________________________ \\



    // --------------------------------------------------------------------------- \\
    // ___ retrieve data from intent & set data to textviews __ \\
    private void updateViews(Intent intent) {
        // retrieve data out of the intent.
        String countedStep = intent.getStringExtra("Counted_Step");
        String DetectedStep = intent.getStringExtra("Detected_Step");
        Log.d(TAG_, String.valueOf(countedStep));
        Log.d(TAG_, String.valueOf(DetectedStep));

        Toast.makeText(getActivity(), countedStep, Toast.LENGTH_SHORT).show();
    }
    // ___________________________________________________________________________ \\



    private void  setUpDB()
    {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(getActivity().getPackageName() + ".realm")
                .schemaVersion(2)
                .modules(new DB_User_Module())
                .build();

        mRealm = Realm.getInstance(config);


    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }




//image picker


    public void onAddPicture( ) {

        isReadStoragePermissionGranted();



    }


    public  void  startPickingImage()
    {
        try {
            imagePicker.withActivity(getActivity()) //calling from activity
                    //.withFragment(W) //calling from fragment
                    .chooseFromGallery(true) //default is true
                    .chooseFromCamera(true) //default is true
                    .withCompression(true) //default is true
                    .start();
        }catch (Exception ex)
        {
            ex.toString();
        }
    }




    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                isWriteStoragePermissionGranted();

                return true;
            } else {


                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation


            startPickingImage();

            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(getActivity(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                startPickingImage();

                return true;
            } else {


                //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation

            startPickingImage();

            return true;
        }
    }




    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:

                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){


                }else{


                }
                break;

            case 3:


                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){

                    try {
                        imagePicker.withActivity(getActivity()) //calling from activity

                                .chooseFromGallery(true) //default is true

                                .withCompression(true) //default is true
                                .start();
                    }catch (Exception ex)
                    {
                        ex.toString();
                    }

                }else{

                }
                break;
        }
    }





    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (requestCode == ImagePicker.SELECT_IMAGE && resultCode == Activity.RESULT_OK) {

            imagePicker.addOnCompressListener(new ImageCompressionListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompressed(String filePath) {



                    dailyPicAdapter.addYourDailyPick(filePath);

                }
            });
        }

        String filePath = imagePicker.getImageFilePath(data);
        if (filePath != null) {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }








}
