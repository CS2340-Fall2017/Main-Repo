package edu.gatech.m4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class AddRatReportActivity extends AppCompatActivity {
    private Button cancelButton;
    private Button acceptButton;

    //vars for inputs
    private EditText uniqueID;
    private EditText createdDate;
    private EditText locationType;
    private EditText incidentZip;
    private EditText incidentAddress;
    private EditText city;
    private EditText borough;
    private EditText lattitude;
    private EditText longitude;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__rat__report_);

        dbHelper = new DBHelper(this);

        uniqueID = (EditText)findViewById(R.id.add_report_uniqueID);
        createdDate = (EditText)findViewById(R.id.add_report_date_created);
        locationType = (EditText)findViewById(R.id.add_report_location_type);
        incidentZip = (EditText)findViewById(R.id.add_report_incident_zip);
        incidentAddress = (EditText)findViewById(R.id.add_report_incident_address);
        city = (EditText)findViewById(R.id.add_report_city);
        borough = (EditText)findViewById(R.id.add_report_borough);
        lattitude = (EditText)findViewById(R.id.add_report_lattitude);
        longitude = (EditText)findViewById(R.id.add_report_longitude);

        uniqueID.setHint("Unique ID");
        createdDate.setHint("Date Created");
        locationType.setHint("Location Type");
        incidentZip.setHint("Incident Zip");
        incidentAddress.setHint("Incident Address");
        city.setHint("City");
        borough.setHint("Borough");
        lattitude.setHint("Lattitude");
        longitude.setHint("Longitude");

        //for cancel button, return to previous screen
        cancelButton = (Button) findViewById(R.id.add_report_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddRatReportActivity.this, StartActivity.class));
            }
        });


        //action for accept button
        acceptButton = (Button) findViewById(R.id.add_report_accept_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(dbHelper.insertReport(uniqueID.getText().toString(),createdDate.getText().toString(),locationType.getText().toString(), longitude.getText().toString(),lattitude.getText().toString(), incidentZip.getText().toString(),incidentAddress.getText().toString(),
                        city.getText().toString(),borough.getText().toString())) { // 49 and 50
                        Toast.makeText(getApplicationContext(), "Report Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Could not Insert report", Toast.LENGTH_SHORT).show();
                    }



                //aggregate all of the fields into a string array
//                String[] data = new String[]{uniqueID.getText().toString(),createdDate.getText().toString(),locationType.getText().toString(),incidentZip.getText().toString(),incidentAddress.getText().toString(),
//                city.getText().toString(),borough.getText().toString(),lattitude.getText().toString(), longitude.getText().toString()};
//
//                Model.getInstance().addReport(data[0], data);
                startActivity(new Intent(AddRatReportActivity.this, StartActivity.class));
//                finish();




            }
        });


    }
}
