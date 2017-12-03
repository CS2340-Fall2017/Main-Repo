package edu.gatech.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;


public class GraphDatesActivity extends AppCompatActivity {

    private DatePicker startDateVal;
    private DatePicker endDateVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_dates);

        startDateVal = findViewById(R.id.startDate_Picker_Graph);
        endDateVal = findViewById(R.id.endDate_Picker_Graph);

        Button loadGraph= findViewById(R.id.loadGraph);
        //switch to activity with map
        loadGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sDay = Integer.toString(startDateVal.getDayOfMonth());
                sDay = (sDay.length() < 2) ? "0" + sDay : sDay;
                String sMonth = Integer.toString(startDateVal.getMonth() + 1); //gets month starting from zero
                sMonth = (sMonth.length() < 2) ? "0" + sMonth : sMonth;
                String sYear  = Integer.toString(startDateVal.getYear());
                String startDate = sYear + "-" + sMonth + "-" + sDay;
                Log.i("START DATE:", startDate);
                String eDay = Integer.toString(endDateVal.getDayOfMonth());
                eDay = (eDay.length() < 2) ? "0" + eDay : eDay;
                String eMonth = Integer.toString(endDateVal.getMonth() + 1); //gets month starting from zero
                eMonth = (eMonth.length() < 2) ? "0" + eMonth : eMonth;
                String eYear  = Integer.toString(endDateVal.getYear());
                String endDate = eYear + "-" + eMonth + "-" + eDay;

                String[] dateStrings = { startDate, endDate };
                Intent intent = new Intent(GraphDatesActivity.this, GraphActivity.class);
                intent.putExtra("String", dateStrings);
                startActivity(intent);

            }
        });
    }


}