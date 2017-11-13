package edu.gatech.m4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class DisplayRatDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ListView keyInfo = findViewById(R.id.listView);


        //String[] info = scoreList.toArray(new String[scoreList.size()]);
//        keyInfo.setAdapter(new ArrayAdapter<String>(DisplayRatDataActivity.this,
//                android.R.layout.simple_list_item_1,StartActivity.getData().get(key)));

    }
}
