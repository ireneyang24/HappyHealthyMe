package com.example.happyhealthyme;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class viewGraphsActivity extends AppCompatActivity
{
    LineChart biometricsGraph;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, viewGraphsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_graphs);

        biometricsGraph = findViewById(R.id.biometrics_graph);
        List<Entry> entries = new ArrayList<Entry>();

        DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {HappyHealthyMeDbSchema.BiometricsTable.Cols.DATE,
                            HappyHealthyMeDbSchema.BiometricsTable.Cols.WEIGHT};

        Cursor cursor = db.query(HappyHealthyMeDbSchema.BiometricsTable.NAME, columns, null, null, null, null, null, null);

        while(cursor.moveToNext()){
            System.out.println("DATE FOR CURRENT CURSOR ENTRY: " + cursor.getString(0));

            String[] values = cursor.getString(0).split("/");

            String year = String.format("%04d", Integer.parseInt(values[2]));
            String month = String.format("%02d", Integer.parseInt(values[0]));
            String day = String.format("%02d", Integer.parseInt(values[1]));

            int orderedDate = Integer.valueOf(String.valueOf(year) + String.valueOf(month) + String.valueOf(day));

            System.out.println("REORDERED DATE: " + orderedDate);

            entries.add(new Entry(orderedDate,cursor.getInt(1)));
        }

        cursor.close();

        LineDataSet weightSet = new LineDataSet(entries,"Weight");
        weightSet.setColor(R.color.lineGraphWeight);
        weightSet.setValueTextColor(R.color.lineGraphWeight);

        LineData lineData = new LineData(weightSet);

        biometricsGraph.setData(lineData);
        biometricsGraph.getXAxis().setValueFormatter(new dateFormatter());
        biometricsGraph.invalidate();

    }

    private class dateFormatter implements IAxisValueFormatter{

        @Override
        public String getFormattedValue(float value, AxisBase axis)
        {
            SimpleDateFormat fromUser = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
            String toReturn = Float.toString(value);
            try
            {
                toReturn = myFormat.format(fromUser.parse(Float.toString(value)));
            } catch(ParseException e)
            {
                e.printStackTrace();
            }
            return toReturn;
        }
    }
}
