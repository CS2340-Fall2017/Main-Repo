package edu.gatech.m4;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GraphTotalActivity extends AppCompatActivity {
    private final Map<String, Integer> graphHelper = new TreeMap<>();
    private final List<BarEntry> entries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


        DBHelper dbHelper = new DBHelper(this);

        String[] dates = (String[]) getIntent().getSerializableExtra("String");
        Cursor cursor = dbHelper.getDateRange(dates[0], dates[1]);
        cursor.moveToFirst();

        BarChart barChart = findViewById(R.id.chart);

        //loop through each of the dates, adding all of the ones with a similar month
        //to the same bucket in the histogram
        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.moveToNext()) {
                String month = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_DATE));
                month = month.substring(0,7);
                if (!graphHelper.containsKey(month)) {
                    graphHelper.put(month, 1);
                } else {
                    int count = graphHelper.get(month);
                    count++;
                    graphHelper.put(month, count);
                }
            }
        }

        Set<String> keys = graphHelper.keySet();
        float count = 0;
        float total = 0;
        for (String key : keys) {
            float floatKey = (float) graphHelper.get(key);
            total += floatKey;
            entries.add(new BarEntry(count, total));
            count++;
        }



        BarDataSet set = new BarDataSet(entries, "RatSightings");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        data.setValueTextColor('W');
        barChart.setData(data);
        barChart.setBackgroundColor(2);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh

        Button backButton = findViewById(R.id.graphBackButton);
        //switch to activity with map
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GraphTotalActivity.this, GraphDatesActivity.class));
            }
        });
    }
}
