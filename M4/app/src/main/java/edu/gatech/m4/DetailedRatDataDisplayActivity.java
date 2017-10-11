package edu.gatech.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


import java.io.Serializable;
import java.util.HashMap;

public class DetailedRatDataDisplayActivity extends AppCompatActivity {

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_rat_data_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        String[] data = (String[]) getIntent().getSerializableExtra("String Array");
        //get id of textView
        TextView uniqueIDDisplay = (TextView) findViewById(R.id.detailed_textView);
        //display textData
        String specificData = "";
        specificData += "Unique Key: " + data[0] + "\n";
        specificData += "Created Date: " + data[1] + "\n";
        specificData += "Location Type: " +  data[7] + "\n";
        specificData += "Incident ZIP: " + data[8] + "\n";
        specificData += "Incident Address: " + data[9] + "\n";
        specificData += "City: " + data[16] + "\n";
        specificData += "Borough: " + data[23] + "\n";
        specificData += "Latitude: " + data[49] + "\n";
        specificData += "Longitude: " + data[50];


        uniqueIDDisplay.setText(specificData);


        // back button
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailedRatDataDisplayActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });    }
}
