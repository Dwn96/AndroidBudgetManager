package com.wambu.expensemanager;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
//import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class GenerateReports extends Activity {



    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    BarChart barChart;


/*
    float[] rainfall = {98.8f, 123.8f, 13.8f, 66.8f, 23.4f, 46.4f, 23.8f, 113.8f, 46.8f, 23.8f, 113.8f, 46.8f};
    String[] monthNames={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};

//String[] amounts= columnValues();

*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_reports);
        barChart=findViewById(R.id.chart);
        databaseHelper=new DatabaseHelper(this);
        sqLiteDatabase=databaseHelper.getWritableDatabase();




        addData();


    }
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        menu.clear();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }



    private void addData(){



        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for (int i = 0; i < databaseHelper.queryYData().size(); i++)
            yVals.add(new BarEntry(databaseHelper.queryYData().get(i), i));

        ArrayList<String> xVals = new ArrayList<String>();
        for(int i = 0; i < databaseHelper.queryXData().size(); i++)
            xVals.add(databaseHelper.queryXData().get(i));

        BarDataSet dataSet = new BarDataSet(yVals, "expense values");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        BarData data = new BarData(xVals, dataSet);


        LimitLine line = new LimitLine(12f, "average daily expense");
        line.setTextSize(12f);
        line.setLineWidth(4f);
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.addLimitLine(line);

        barChart.setData(data);
        barChart.setDescription("The income chart.");
        barChart.animateY(2000);

    }








}
