package com.trucktracker.trucktracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class NewUserActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_newuser);
        auth = FirebaseAuth.getInstance();
    }

    public void SignUp(View view) {
        /*
         * Grab the email address and password entered by
         * the user. Pass this information to Firebase to
         * secure a login if the user already exists.
         */
        EditText User =(EditText) findViewById(R.id.newUsername);
        EditText Email = (EditText) findViewById(R.id.newEmail);
        EditText Pass = (EditText) findViewById(R.id.newPassword);
        final String username = User.getText().toString();
        String email = Email.getText().toString();
        String password = Pass.getText().toString();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SIGNUP", "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            boolean useDefaultName = false;

                            // Setup User Display Name
                            // as their requested username
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username).build();

                            user.updateProfile(profileUpdates);

                            // Load the main screen with
                            // the new user's information
                            Intent intent = new Intent(NewUserActivity.this, MainActivity.class);
                            intent.putExtra(LoginActivity.USERNAME, username);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SIGNUP", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(NewUserActivity.this, "Failed to create a new account.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }

//    public void loadLoginPage(View view) {
//        Intent loadLogin = new Intent(NewUserActivity.this, LoginActivity.class);
//        startActivity(loadLogin);
//    }
}
