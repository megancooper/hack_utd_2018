package com.trucktracker.trucktracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * This class uses Firebase's authentication
 * service to login users
 */

public class LoginActivity extends AppCompatActivity {

    public final static String USERNAME = "Username Not Found";
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();

        // If the user is already Logged In
        // Display the main activity
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            String username = currentUser.getDisplayName();
            intent.putExtra(USERNAME, username);

            startActivity(intent);
        }
    }


    /* Called when the user presses the button */
    public void Login (View view) {
        try {

            /*
             * Grab the email address and password entered by
             * the user. Pass this information to Firebase to
             * secure a login if the user already exists.
             */
            EditText User =(EditText) findViewById(R.id.username);
            EditText Pass = (EditText) findViewById(R.id.password);
            String email = User.getText().toString();
            String password = Pass.getText().toString();

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("LOGIN", "signInWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();


                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra(USERNAME, user.getDisplayName());
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("LOGIN", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }

    }

    public void loadStartUpPage(View view) {
        try {
            Intent loadStartup = new Intent(this, NewUserActivity.class);
            startActivity(loadStartup);

        } catch (Exception e) {
            Log.d("LOAD", e.toString());
        }

    }

}
