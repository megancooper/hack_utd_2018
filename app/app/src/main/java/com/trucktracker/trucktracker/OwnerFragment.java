
package com.trucktracker.trucktracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class OwnerFragment extends Fragment {
    public static Fragment newInstance() {
        return new SignUpFragment();
    }

    private View ownerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ownerView = inflater.inflate(R.layout.owner_fragment, container, false);

        Button addTruck = (Button) ownerView.findViewById(R.id.addtruckButton);
        addTruck.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.account_layout, new AddTruckFragment());
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        Button editTruck = (Button) ownerView.findViewById(R.id.edittruckButton);
        editTruck.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

            }
        });

        Button sendUpdate = (Button) ownerView.findViewById(R.id.sendupdateButton);
        sendUpdate.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

            }
        });

        TextView signout = (TextView) ownerView.findViewById(R.id.signoutlink);
        signout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                // Log them out
                // Instantiate Firebase Authentication Instance
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.getInstance().signOut();

                // Show Login Screen
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.account_layout, new LoginFragment());
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        return ownerView;
    }
}
