package com.trucktracker.trucktracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.Executor;

/**
 * Created by megan on 2/24/18.
 */

public class SignUpFragment extends Fragment {

    private View signupView;
    private FirebaseAuth auth;

    public static Fragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        signupView = inflater.inflate(R.layout.signup_fragment, container, false);

        Button signupBtn = (Button) signupView.findViewById(R.id.signupButton);
        signupBtn.setOnClickListener(new View.OnClickListener()
        {
            /*
             * When the user clicks the Login button,
             * we use the instantiated Firebase authentication
             * object to check and see if the user's entered
             * credentials match something in the database
             */
            @Override
            public void onClick(View v)
            {
                /*
                 * Grab the email address and password entered by
                 * the user. Pass this information to Firebase to
                 * secure a login if the user already exists.
                 */
                // Instantiate Firebase Authentication Instance
                final FirebaseAuth auth = FirebaseAuth.getInstance();


                EditText Email =(EditText) getView().findViewById(R.id.newEmail);
                EditText Pass = (EditText) getView().findViewById(R.id.newPassword);
                EditText User = (EditText) getView().findViewById(R.id.newUsername);
                String email = Email.getText().toString();
                String password = Pass.getText().toString();
                final String username = User.getText().toString();

                if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in both your email and password.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
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

                                        Toast.makeText(getActivity(), "Succeeded In Created New Account!",
                                                Toast.LENGTH_SHORT).show();

                                        // TODO Load Truck Screen
                                        FragmentTransaction trans = getFragmentManager()
                                                .beginTransaction();
                                        trans.replace(R.id.account_layout, new OwnerFragment());
                                        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                        trans.addToBackStack(null);
                                        trans.commit();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("SIGNUP", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getActivity(), "Failed to create a new account.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }
            }
        });

        /*
         * Add functionality for switching the
         * fragment screen
         */
        TextView switchScreen = (TextView) signupView.findViewById(R.id.signuplink);

        switchScreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.account_layout, new LoginFragment());
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);
                trans.commit();

            }
        });


        return signupView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // in outState, put the information to save: strings, ints, etc.
        super.onSaveInstanceState(outState);
    }
}
