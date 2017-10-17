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
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.HashMap;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.;

import com.google.firebase.auth.FirebaseUser;


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


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


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
        //Create hashmap of user data
        user_data = new HashMap<String, String>();

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();

                if (emailInput.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter an email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwordInput.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter a password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwordInput.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                mAuth.createUserWithEmailAndPassword(emailInput, passwordInput)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Registration.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Registration.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Registration.this, StartActivity.class));
                                    finish();
                                }
                            }
                        });





                //checks to see if a valid email address has been used to register
//                if (name.getText().toString().isEmpty()) { //throws error if name not entered
//                    error.setText("'Name' field cannot be empty");
//                } else if (!email.getText().toString().contains("@")) { //throws error if an invalid email address type is entered
//                    error.setText("Not a valid email address");
//                } else if (user_data.containsKey(email.getText().toString())) { //make sure user not already in hashmap
//                    //error.setText("You're already registered fool!");
//                } else if (password.getText().toString().isEmpty()) { //makes sure password is entered
//                    error.setText("'Password' field cannot be empty");
//                } else {
//                    //add user to hashmap
////                    user_data.put(email.getText().toString(), password.getText().toString());
////                    Intent intent = new Intent(Registration.this, StartActivity.class );
////                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
////                    startActivity(intent);
//
//
//                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //returns to welcome screen
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


    /**
     * Retrieves the a map of all the user login credentials.
     *
     *
     *
     * @return a map of all the user login credentials.
     */
    public static HashMap<String, String> getUser_data() {
        return user_data;
    }

}
