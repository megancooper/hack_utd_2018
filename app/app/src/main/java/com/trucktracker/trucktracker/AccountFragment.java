package com.trucktracker.trucktracker;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

//import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;

/**
 * Created by megan on 2/25/18.
 */

public class AccountFragment extends Fragment {

    private static final String TAG = "AccountFragment";

    public static Fragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.account_fragment, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        // Instantiate Firebase Authentication Instance
        final FirebaseAuth auth = FirebaseAuth.getInstance();
		if (auth.getCurrentUser() == null) {
            transaction.replace(R.id.account_layout, new LoginFragment());
        }
        else {
            transaction.replace(R.id.account_layout, new OwnerFragment());
        }

        transaction.commit();

        return view;
    }
}
