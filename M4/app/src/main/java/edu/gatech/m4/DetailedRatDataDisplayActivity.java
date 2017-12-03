package edu.gatech.m4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class    DetailedRatDataDisplayActivity extends AppCompatActivity {
    Button shareButton;
    String specificData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_rat_data_display);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        DBHelper dbHelper = new DBHelper(this);




        //Integer data = (Integer) getIntent().getSerializableExtra("Int");
        String data = (String) getIntent().getSerializableExtra("String");
        Cursor cursor = dbHelper.getReport(data);
        cursor.moveToFirst();
        String key = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_NAME));
        String location = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_LOCATIONTYPE));
        String date = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_DATE));
        String zip = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_ZIPCODE));
        String address = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_ADDRESS));
        String city = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_CITY));
        String borough = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_BOROUGH));
        String latitude = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_LATITUDE));
        String longitude = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_LONGITUDE));

        //get id of textView
        TextView uniqueIDDisplay = findViewById(R.id.detailed_textView);
        //display textData
        specificData = "";
            specificData += "Unique Key: " + key + "\n";
            specificData += "Created Date: " + date + "\n";
            specificData += "Location Type: " +  location + "\n";
            specificData += "Incident ZIP: " + zip + "\n";
            specificData += "Incident Address: " + address + "\n";
            specificData += "City: " + city + "\n";
            specificData += "Borough: " + borough + "\n";
            specificData += "Latitude: " + latitude + "\n";
            specificData += "Longitude: " + longitude;


        uniqueIDDisplay.setText(specificData);

        shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String body = "RAT REPORT!";
                String subject = specificData;
                intent.putExtra(Intent.EXTRA_SUBJECT, body);
                intent.putExtra(Intent.EXTRA_TEXT, subject);
                startActivity(Intent.createChooser(intent, "Share using"));


            }

        });

        // back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(DetailedRatDataDisplayActivity.this, StartActivity.class);
//                startActivity(intent);
                finish();
            }
        });

    }
}
