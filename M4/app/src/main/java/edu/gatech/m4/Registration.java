package edu.gatech.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    //Edit Texts
    private EditText name;
    private EditText email;
    private EditText password;
    //Buttons
    private Button Cancel;
    private Button Register;
    //Radio Buttons
    private RadioButton Admin;
    private RadioButton User;
    //Hash Map to store user registrations
    public static HashMap<String,String> user_data;
    private EditText error;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //Map UI to variables
        name = (EditText)findViewById(R.id.name_registration);
        email = (EditText)findViewById(R.id.email_registration);
        password = (EditText)findViewById(R.id.password_registration);
        Register = (Button) findViewById(R.id.registration_button);
        Cancel = (Button) findViewById(R.id.cancel_registration_button);
        Admin = (RadioButton) findViewById(R.id.radio_admin);
        User = (RadioButton) findViewById(R.id.radio_user);
        error = (EditText) findViewById(R.id.error_message);
        //Create hashmap
        user_data = new HashMap<String, String>();
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checks to see if a valid email address has been used to register
                if (name.getText().toString().isEmpty()) {
                    error.setText("'Name' field cannot be empty");
                } else if (!email.getText().toString().contains("@")) {
                    error.setText("Not a valid email address");
                } else if (user_data.containsKey(email.getText().toString())) { //make sure user not already in hashmap
                    error.setText("You're already registered fool!");
                } else if (password.getText().toString().isEmpty()) {
                    error.setText("'Password' field cannot be empty");
                } else {
                    //add user to hashmap
                    user_data.put(email.getText().toString(), password.getText().toString());
                    Intent intent = new Intent(Registration.this, StartActivity.class );
                    startActivity(intent);
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, WelcomeActivity.class );
                startActivity(intent);
            }
        });
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_registration);
        //setup the registration form.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_admin:
                if (checked)
                    // Admin
                    break;
            case R.id.radio_user:
                if (checked)
                    // User
                    break;
        }
    }

    public static HashMap<String, String> getUser_data() {
        return user_data;
    }

}
