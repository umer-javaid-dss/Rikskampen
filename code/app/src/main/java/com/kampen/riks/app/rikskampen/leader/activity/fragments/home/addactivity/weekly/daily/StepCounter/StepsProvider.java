package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StepsProvider implements SensorEventListener {


    private  Context mContext;

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private Sensor stepDetectorSensor;


    private  StepsResult  stepsResult;

    private  int countSteps=0;

    public  StepsProvider(Context context)
    {
        mContext=context;


        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        sensorManager.registerListener(StepsProvider.this, stepCounterSensor,  0);
        sensorManager.registerListener(StepsProvider.this, stepDetectorSensor, 0);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

          countSteps = (int) event.values[0];

        }

        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {

           countSteps = (int) event.values[0];
        }


        if(stepsResult!=null)
        {
            stepsResult.onSteps(countSteps);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void setStepListener(StepsResult  listener)
    {
        stepsResult=listener;
    }

    public  interface  StepsResult
    {
        public  void  onSteps(int steps);
    }
}
