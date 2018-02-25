package com.trucktracker.trucktracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class TruckActivity extends AppCompatActivity {

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
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("trucks");
    private TextView mName;
    private TextView mAddress;
    private TextView mSchedule;
    private TextView mPhone;
    private TextView mOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck);
        mName = findViewById(R.id.et_name);
        mAddress = findViewById(R.id.et_address);
        mSchedule = findViewById(R.id.et_schedule);
        mPhone = findViewById(R.id.et_phone);
        mOwner = findViewById(R.id.et_owner);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getText().toString();
                String address = mAddress.getText().toString();
                String schedule = mSchedule.getText().toString();
                String phone = mPhone.getText().toString();
                String owner = mOwner.getText().toString();

                String userId = mDatabase.push().getKey();
                Truck truck = new Truck(name,address,schedule,phone,owner);
                mDatabase.child(userId).setValue(truck);

            }
        });

    }

}
