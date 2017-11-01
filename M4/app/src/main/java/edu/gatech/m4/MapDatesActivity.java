package edu.gatech.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MapDatesActivity extends AppCompatActivity {

    private Button loadMap;
    private EditText startDate;
    private EditText endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_dates);

        startDate = (EditText)findViewById(R.id.startDate);
        endDate = (EditText)findViewById(R.id.endDate);


        loadMap = (Button) findViewById(R.id.loadMap);
        //switch to activity with map
        loadMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] dateStrings = { startDate.getText().toString(), endDate.getText().toString() };
                Intent intent = new Intent(MapDatesActivity.this, MapsActivity.class);
                intent.putExtra("String", dateStrings);
                startActivity(intent);

            }
        });
    }


}
