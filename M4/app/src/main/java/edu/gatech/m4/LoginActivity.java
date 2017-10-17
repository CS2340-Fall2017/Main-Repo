package edu.gatech.m4;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    //private EditText
    private View mProgressView;
    private View mLoginFormView;
    private Button Login;
    private EditText errorView;
    private Button Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


        //Firebase Users
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, StartActivity.class));
            finish();
        }
        mPasswordView = (EditText) findViewById(R.id.password);
        errorView = (EditText) findViewById(R.id.errorText);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        Login = (Button)findViewById(R.id.email_sign_in_button);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(mEmailView.getText().toString(), mPasswordView.getText().toString());
            }
        });

        Cancel = (Button)findViewById(R.id.cancel_sign_in_button);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class );
                startActivity(intent); //go back to welcome page
            }
        });
    }




    /**
     * Validates a entered user credentials and does appropriate actions based on credentials entered.
     *
     *
     * @param userName an inputed user name from the Login activity
     * @param password an inputd password from the Login Activity
     */
    private void validate(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(getApplicationContext(), "Enter an email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter a password!", Toast.LENGTH_SHORT).show();
            return;
        }
        //authenticate user
        mAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            // there was an error

                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }


//        if (Registration.getUser_data().containsKey(userName)) { //
//            if (password.equals(Registration.getUser_data().get(userName))) {
//                Intent intent = new Intent(LoginActivity.this, StartActivity.class );
//                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);
//            } else {
//                errorView.setText("Incorrect Username or Password"); //incorrect password entered
//            }
//        } else {
//            errorView.setText("Incorrect Username or Password");//invalid username was entered
//        }


}


