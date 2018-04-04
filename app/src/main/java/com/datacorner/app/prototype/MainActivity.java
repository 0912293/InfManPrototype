package com.datacorner.app.prototype;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity {

    private Context context;
    ArrayList<BarEntry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    BarDataSet dataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart barChart = (BarChart) findViewById(R.id.chart);

        chartData();


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);

        BarData data = new BarData(dataset); // initialize BarData
        barChart.setData(data); //set data into chart
        dataset.setColors(Color.CYAN); // set the color
        dataset.setValueTextSize(15f);

        Legend l = barChart.getLegend();
        l.setEnabled(true);
        l.setFormSize(10f);
        l.setTextSize(15.8f);
        l.setWordWrapEnabled(true);

        barChart.setVisibleXRangeMaximum(1.5f);

        barChart.animateY(1000);
//        barChart.setDescription("a");  // set the description rechtsonder
    }

    //retrieve and put data in chart
    public void chartData(){
//        DataFactory dataFactory = new DataFactory();
//        IData<BarEntry> query1 = dataFactory.getQuery("BAR");
//        query1.query(context);
//        entries = query1.getData();
//        labels = query1.getLabel();
        for(int i = 0; i<=10; i++)
        {
            entries.add(new BarEntry(i,i));
            labels.add(Integer.toString(i));
        }

        dataset = new BarDataSet(entries, "dummy");
    }
}