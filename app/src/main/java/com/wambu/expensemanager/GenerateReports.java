package com.wambu.expensemanager;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class GenerateReports extends Activity {


    TextView textView=findViewById(R.id.t_amount);
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    DisplayTransaction displayTransaction;

    float[] rainfall = {98.8f, 123.8f, 13.8f, 66.8f, 23.4f, 46.4f, 23.8f, 113.8f, 46.8f, 23.8f, 113.8f, 46.8f};
    String[] monthNames={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};

//String[] amounts= columnValues();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_reports);


        databaseHelper=new DatabaseHelper(this);
        sqLiteDatabase=databaseHelper.getWritableDatabase();


        setupPieChart();
    }

    private void setupPieChart() {
        List<PieEntry> pieEntries=new ArrayList<>();

        //populate list of pie entries
        for(int i=0;i<rainfall.length;i++){
            pieEntries.add(new PieEntry(rainfall[i],monthNames[i]));
            }

        PieDataSet dataSet =new PieDataSet(pieEntries,"Rainfall for Nairobi");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataSet);


        //Get the chart

        PieChart chart=findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate(); //cause it to redraw
    }









}
