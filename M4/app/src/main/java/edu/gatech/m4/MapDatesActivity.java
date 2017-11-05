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

    private Button loadMap;
    private DatePicker startDateVal;
    private DatePicker endDateVal;
    private String startDate;
    private String endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_dates);

        startDateVal = (DatePicker)findViewById(R.id.startDate_Picker);
        endDateVal = (DatePicker)findViewById(R.id.endDate_Picker);

        loadMap = (Button) findViewById(R.id.loadMap);
        //switch to activity with map
        loadMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sDay = Integer.toString(startDateVal.getDayOfMonth());
                sDay = (sDay.length() < 2) ? "0" + sDay : sDay;
                String sMonth = Integer.toString(startDateVal.getMonth());
                String sYear  = Integer.toString(startDateVal.getYear()).substring(2,4);
                String startDate = sMonth + "/" + sDay + "/" + sYear;

                String eDay = Integer.toString(endDateVal.getDayOfMonth());
                eDay = (eDay.length() < 2) ? "0" + eDay : eDay;
                String eMonth = Integer.toString(endDateVal.getMonth());
                String eYear  = Integer.toString(endDateVal.getYear()).substring(2,4);
                String endDate = eMonth + "/" + eDay + "/" + eYear;

                String[] dateStrings = { startDate, endDate };
                Intent intent = new Intent(MapDatesActivity.this, MapsActivity.class);
                intent.putExtra("String", dateStrings);
                startActivity(intent);

            }
        });
    }


}