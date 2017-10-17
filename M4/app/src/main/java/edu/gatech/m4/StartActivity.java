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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.support.annotation.NonNull;

import android.widget.ArrayAdapter;

public class StartActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authListener;
    private Button LogOut;
    private ListView listView;
    HashMap<String, String[]> scoreList;
    String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        listView = (ListView) findViewById(R.id.listView);

        mAuth = FirebaseAuth.getInstance();
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(StartActivity.this, WelcomeActivity.class));
                    finish();
                }
            }
        };


        if (savedInstanceState == null) {
            InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings);
            CSVFile csvFile = new CSVFile(inputStream);

            scoreList = csvFile.read();
            ArrayList<String> uniqueKeys = new ArrayList<String>(scoreList.keySet());
            data = uniqueKeys.toArray(new String[uniqueKeys.size()]);
        }
        LogOut = (Button)findViewById(R.id.logout_button);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
//        InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings);
//        CSVFile csvFile = new CSVFile(inputStream);
//
//        final HashMap<String, String[]> scoreList = csvFile.read();
//        ArrayList<String> uniqueKeys = new ArrayList<String>(scoreList.keySet());
//        String[] data = uniqueKeys.toArray(new String[uniqueKeys.size()]);
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
    public HashMap<String, String[]> getData() {
        return scoreList;
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            mAuth.removeAuthStateListener(authListener);
        }
    }

}
