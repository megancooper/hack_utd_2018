package com.trucktracker.trucktracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTruckFragment extends Fragment {
    public static Fragment newInstance() {
        return new SignUpFragment();
    }

    public static class Truck {
        public String name;
        public String address;
        public String schedule;
        public String phone;
        public String owner;

        public Truck(){
        }

        public Truck(String name, String address, String schedule, String phone, String owner){
            this.name = name;
            this.address = address;
            this.schedule = schedule;
            this.phone = phone;
            this.owner = owner;
        }
    }

    private View addTruckView;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("trucks");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        addTruckView = inflater.inflate(R.layout.addtruck_fragment, container, false);


        Button addButton = addTruckView.findViewById(R.id.addTruckButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText mName = addTruckView.findViewById(R.id.et_name);
                EditText mAddress = addTruckView.findViewById(R.id.et_address);
                EditText mSchedule = addTruckView.findViewById(R.id.et_schedule);
                EditText mPhone = addTruckView.findViewById(R.id.et_phone);

                // Pull new truck information
                String name = mName.getText().toString();
                String address = mAddress.getText().toString();
                String schedule = mSchedule.getText().toString();
                String phone = mPhone.getText().toString();

                // Pull owner's unique uID
                final FirebaseAuth auth = FirebaseAuth.getInstance();
                String owner = auth.getCurrentUser().getUid();

                String userId = mDatabase.push().getKey();
                Truck truck = new Truck(name,address,schedule,phone,owner);
                mDatabase.child("truck").setValue(truck);




                // Do a toast
                Toast.makeText(getActivity(), "Your New Truck has been added.",
                        Toast.LENGTH_SHORT).show();

                // Return to owner screen
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.account_layout, new OwnerFragment());
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);
                trans.commit();

            }
        });

        TextView ownerScreen = (TextView) addTruckView.findViewById(R.id.ownerlink);
        ownerScreen.setOnClickListener(new View.OnClickListener()
        {
            /*
                Switch fragment back to owner screen
             */
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.account_layout, new OwnerFragment());
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        return addTruckView;
    }
}
