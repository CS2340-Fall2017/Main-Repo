package edu.gatech.m4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    //Edit Texts
    private EditText name;
    private EditText email;
    private EditText password;
    //Buttons
    private Button cancel;
    private Button register;
    public HashMap<String,String> user_data;
    private EditText error;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_registration);
        //Map UI to variables
        name = (EditText)findViewById(R.id.name_registration);
        email = (EditText)findViewById(R.id.email_registration);
        password = (EditText)findViewById(R.id.password_registration);
        register = (Button) findViewById(R.id.registration_button);
        cancel = (Button) findViewById(R.id.cancel_registration_button);
        error = (EditText) findViewById(R.id.error_message);
        //Create hashmap
        user_data = new HashMap<String, String>();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make sure user not already in hashmap
                if (user_data.containsKey(email.getText())) {
                    error.setText("You're already registered fool!");
                } else {
                    //add user to hashmap
                    user_data.put(email.getText().toString(), password.getText().toString());
                }
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //setup the registration form.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public HashMap<String, String> getUser_data() {
        return user_data;
    }

}
