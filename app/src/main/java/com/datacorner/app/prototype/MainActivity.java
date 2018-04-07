package com.datacorner.app.prototype;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;

public class MainActivity  extends AppCompatActivity {

    private Context context;
    ArrayList<BarEntry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    BarDataSet dataset;
    BarChart barChart;
    BarData data = new BarData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirstExample firstExample = new FirstExample();
        ResultSet rs = firstExample.executeQuery();

        barChart = (BarChart) findViewById(R.id.chart);



        chartData(rs);





    }

    //retrieve and put data in chart
    public void chartData(ResultSet rs){

        String companyName;
        String visualType;
        String companySector;
        int postLikes;
        int postComments;

        Map<String, Integer> likeDictionary = new HashMap<>();
        Map<String, Integer> commentDictionary = new HashMap<>();

        String sectorVisualType;

        try {
            while(rs.next()){
                visualType = rs.getString("VisualType");
                companySector = rs.getString("CompanySector");
                postLikes = rs.getInt("PostLikes");
                postComments = rs.getInt("PostComments");

                sectorVisualType = companySector + "-" + visualType;
                //Retrieve by column name
                if(likeDictionary.get(sectorVisualType) == null){
                    likeDictionary.put(sectorVisualType, postLikes);
                }
                else{
                    int a = likeDictionary.get(sectorVisualType);
                    likeDictionary.replace(sectorVisualType, a + postLikes);
                }

                if(commentDictionary.get(sectorVisualType) == null){
                    commentDictionary.put(sectorVisualType, rs.getInt("PostComments"));
                }
                else{
                    int a = commentDictionary.get(sectorVisualType);
                    commentDictionary.replace(sectorVisualType, a + postComments);
                }

//                PostLikes  = rs.getInt("PostLikes");
//                Brand = rs.getString("CompanyName");
//                Comments = rs.getInt("PostComments");
            }
            int i = 0;
            int j = 1;
            for (Map.Entry<String, Integer> entry : likeDictionary.entrySet())
            {

                entries.add(new BarEntry(i, entry.getValue()));
                labels.add(entry.getKey()+" Likes");
                i = i+2;
            }
            for (Map.Entry<String, Integer> entry : commentDictionary.entrySet())
            {
                entries.add(new BarEntry(j, entry.getValue()));
                labels.add(entry.getKey()+" Comments");
                j = j+2;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        createChart();


    }
    public void createChart(){
        ArrayList<String> lobels = new ArrayList<>(labels);
        BarDataSet dataSet = new BarDataSet(entries, "Tenses");
        dataSet.setDrawValues(true);

        // Create a data object from the dataSet
        BarData data = new BarData(dataSet);
        // Format data as percentage
//        data.setValueFormatter(new PercentFormatter());

        // Make the chart use the acquired data
        barChart.setData(data);

        // Create the labels for the bars
//        final ArrayList<String> xVals = new ArrayList<>();
//        xVals.add("Present");
//        xVals.add("Pres. Continuous");
//        xVals.add("Simple Past");
//        xVals.add("Past Perfect");


        // Display labels for bars
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(lobels));

        // Set the maximum value that can be taken by the bars
        //barChart.getAxisLeft().setAxisMaximum(100);

        // Bars are sliding in from left to right
        barChart.animateXY(1000, 1000);
        // Display scores inside the bars
        barChart.setDrawValueAboveBar(false);

        // Hide grid lines
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        // Hide graph description
        barChart.getDescription().setEnabled(false);
        // Hide graph legend
        barChart.getLegend().setEnabled(false);

        // Design
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);

        barChart.invalidate();

    }
}