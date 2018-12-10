package com.kampen.riks.app.rikskampen.leader.activity.fragments.me;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kampen.riks.app.rikskampen.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyWork extends Fragment {


    LineChart chart;

    public MyWork() {
        // Required empty public constructor
    }

    public static MyWork newInstance() {
        MyWork fragment = new MyWork();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_work, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setChartData(view);
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
}
