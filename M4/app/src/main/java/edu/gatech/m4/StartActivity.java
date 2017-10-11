package edu.gatech.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.app.Activity;
import android.os.Parcelable;
import android.os.Bundle;
import android.widget.ListView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.widget.ArrayAdapter;

public class StartActivity extends AppCompatActivity {

    private Button LogOut;
    private ListView listView;
    static HashMap<String, String[]> scoreList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        listView = (ListView) findViewById(R.id.listView);


        LogOut = (Button)findViewById(R.id.logout_button);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, WelcomeActivity.class );
                startActivity(intent);
            }
        });
        InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings);
        CSVFile csvFile = new CSVFile(inputStream);

        final HashMap<String, String[]> scoreList = csvFile.read();
        ArrayList<String> uniqueKeys = new ArrayList<String>(scoreList.keySet());
        String[] data = uniqueKeys.toArray(new String[uniqueKeys.size()]);
        //String[] info = scoreList.toArray(new String[scoreList.size()]);
        listView.setAdapter(new ArrayAdapter<String>(StartActivity.this,
                android.R.layout.simple_list_item_1,data));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String uniqueID = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(StartActivity.this, DetailedRatDataDisplayActivity.class);
                //pass the hashmap to detailedRatDataActivity
                intent.putExtra("String Array", scoreList.get(uniqueID));
                startActivity(intent);
            }
        });

    }
    public static HashMap<String, String[]> getData() {
        return scoreList;
    }

}
