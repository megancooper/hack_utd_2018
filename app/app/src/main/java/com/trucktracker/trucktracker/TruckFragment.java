package com.trucktracker.trucktracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TruckFragment extends Fragment {

    private TextView truckName, truckAddress, truckSchedule;

    public static Fragment newInstance(Truck truck) {
        Fragment fragment = new TruckFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",truck.getName()==null ? "" : truck.getName());
        bundle.putString("address",truck.getAddress()==null ? "" : truck.getAddress());
        bundle.putString("schedule",truck.getSchedule()==null ? "" : truck.getSchedule());
        bundle.putString("phone",truck.getPhone()==null ? "" : truck.getPhone());
        bundle.putString("owner",truck.getOwner()==null ? "" : truck.getOwner());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.food_truck_page_fragment, container, false);

        // TODO: get image(s)
        truckName = v.findViewById(R.id.truck_name);
        truckAddress = v.findViewById(R.id.truck_location);
        truckSchedule = v.findViewById(R.id.truck_hours);

        Bundle bundle = this.getArguments();
        Truck truck = new Truck(bundle.getString("name"),
                                bundle.getString("address"),
                                bundle.getString("schedule"),
                                bundle.getString("phone"),
                                bundle.getString("owner"));

        truckName.setText(truck.getName());
        truckAddress.setText(truck.getAddress());
        truckSchedule.setText(truck.getSchedule());

        return v;
    }



}
