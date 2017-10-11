package edu.gatech.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        HashMap<String, String[]> scoreList = csvFile.read();
        ArrayList<String> uniqueKeys = new ArrayList<String>(scoreList.keySet());
        String[] data = uniqueKeys.toArray(new String[uniqueKeys.size()]);
        //String[] info = scoreList.toArray(new String[scoreList.size()]);
        listView.setAdapter(new ArrayAdapter<String>(StartActivity.this,
                android.R.layout.simple_list_item_1,data));

    }
    public static HashMap<String, String[]> getData() {
        return scoreList;
    }

}
