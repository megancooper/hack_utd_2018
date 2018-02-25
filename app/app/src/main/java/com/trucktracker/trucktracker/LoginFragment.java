package com.trucktracker.trucktracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    private View loginView;

    public static Fragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginView = inflater.inflate(R.layout.login_fragment, container, false);

        Button button = (Button) loginView.findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener()
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



                EditText Email =(EditText) getView().findViewById(R.id.email);
                EditText Pass = (EditText) getView().findViewById(R.id.password);
                String email = Email.getText().toString();
                String password = Pass.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in both your email and password.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("LOGIN", "signInWithEmail:success");
                                        FirebaseUser user = auth.getCurrentUser();

                                        Toast.makeText(getActivity(), "Login Successful!",
                                                Toast.LENGTH_SHORT).show();

                                        FragmentTransaction trans = getFragmentManager()
                                                .beginTransaction();
                                        trans.replace(R.id.account_layout, new OwnerFragment());
                                        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                        trans.addToBackStack(null);
                                        trans.commit();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("LOGIN", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getActivity(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });


        /*
         * Creates onclick function for the hyperlink
         * that switches between the Login and Signup
         * screen
         */
        TextView switchFrag = (TextView) loginView.findViewById(R.id.loginlink);

        switchFrag.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
				/*
				 * IMPORTANT: We use the "account frame" defined in
				 * "account_fragment.xml" as the reference to replace fragment
				 */
                trans.replace(R.id.account_layout, new SignUpFragment());

				/*
				 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);
                trans.commit();
            }
        });


        return loginView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // in outState, put the information to save: strings, ints, etc.
        super.onSaveInstanceState(outState);
    }

}