package edu.gatech.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;



public class MapDatesActivity extends AppCompatActivity {

    private Button loadGraph;
    private DatePicker startDateVal;
    private DatePicker endDateVal;
    private String startDate;
    private String endDate;
    private EditText numInstances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_dates);

        startDateVal = (DatePicker)findViewById(R.id.startDate_Picker);
        endDateVal = (DatePicker)findViewById(R.id.endDate_Picker_Graph);
        numInstances = (EditText)findViewById(R.id.numInstancesDateRange);


        loadGraph = (Button) findViewById(R.id.loadGraph);
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

                // get how many instances to display on the map




                String[] dateStrings = { startDate, endDate, numInstances.getText().toString() };
                Intent intent = new Intent(MapDatesActivity.this, MapsActivity.class);
                intent.putExtra("String", dateStrings);
                startActivity(intent);

            }
        });
    }


}