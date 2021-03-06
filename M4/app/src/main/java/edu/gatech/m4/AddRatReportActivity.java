package edu.gatech.m4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class AddRatReportActivity extends AppCompatActivity {

    //vars for inputs
    private EditText uniqueID;
    private EditText createdDate;
    private EditText locationType;
    private EditText incidentZip;
    private EditText incidentAddress;
    private EditText city;
    private EditText borough;
    private EditText latitude;
    private EditText longitude;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__rat__report_);

        dbHelper = new DBHelper(this);

        uniqueID = findViewById(R.id.add_report_uniqueID);
        createdDate = findViewById(R.id.add_report_date_created);
        locationType = findViewById(R.id.add_report_location_type);
        incidentZip = findViewById(R.id.add_report_incident_zip);
        incidentAddress = findViewById(R.id.add_report_incident_address);
        city = findViewById(R.id.add_report_city);
        borough = findViewById(R.id.add_report_borough);
        latitude = findViewById(R.id.add_report_latitude);
        longitude = findViewById(R.id.add_report_longitude);

        uniqueID.setHint("Unique ID");
        createdDate.setHint("Date Created");
        locationType.setHint("Location Type");
        incidentZip.setHint("Incident Zip");
        incidentAddress.setHint("Incident Address");
        city.setHint("City");
        borough.setHint("Borough");
        latitude.setHint("Latitude");
        longitude.setHint("Longitude");

        //for cancel button, return to previous screen
        Button cancelButton = findViewById(R.id.add_report_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddRatReportActivity.this, StartActivity.class));
            }
        });


        //action for accept button
        Button acceptButton = findViewById(R.id.add_report_accept_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(dbHelper.insertReport(uniqueID.getText().toString(),createdDate.getText().toString(),locationType.getText().toString(), longitude.getText().toString(),latitude.getText().toString(), incidentZip.getText().toString(),incidentAddress.getText().toString(),
                        city.getText().toString(),borough.getText().toString())) { // 49 and 50
                        Toast.makeText(getApplicationContext(), "Report Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Could not Insert report", Toast.LENGTH_SHORT).show();
                    }



                //aggregate all of the fields into a string array
//                String[] data = new String[]{uniqueID.getText().toString(),createdDate.getText().toString(),locationType.getText().toString(),incidentZip.getText().toString(),incidentAddress.getText().toString(),
//                city.getText().toString(),borough.getText().toString(),latitude.getText().toString(), longitude.getText().toString()};
//
//                Model.getInstance().addReport(data[0], data);
                startActivity(new Intent(AddRatReportActivity.this, StartActivity.class));
//                finish();




            }
        });


    }
}
