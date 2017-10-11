package edu.gatech.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DisplayRatDataActivity extends AppCompatActivity {

    private ListView keyInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        keyInfo = (ListView) findViewById(R.id.keyInfo);
        //String[] info = scoreList.toArray(new String[scoreList.size()]);
//        keyInfo.setAdapter(new ArrayAdapter<String>(DisplayRatDataActivity.this,
//                android.R.layout.simple_list_item_1,StartActivity.getData().get(key)));

    }
}
