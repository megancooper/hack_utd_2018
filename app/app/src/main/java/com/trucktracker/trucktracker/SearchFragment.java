package com.trucktracker.trucktracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class SearchFragment extends Fragment {

    private static final int PLACE_PICKER_REQUEST = 1;

    public static Fragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.search_fragment, container, false);

        // Get search editText
        final EditText searchBar = v.findViewById(R.id.search_food_trucks_edit_text);

        // Initialize search submit button
        Button submitSearchButton = v.findViewById(R.id.submitSearchButton);
        submitSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment resultsFrag = SearchResultsFragment.newInstance(searchBar.getText().toString());

                getFragmentManager().beginTransaction()
                                    .replace(R.id.search_container, resultsFrag)
                                    .addToBackStack(null)
                                    .commit();
            }
        });

        // Initialize button
        Button mapButton = v.findViewById(R.id.search_in_maps_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // launch Jared's code
                try{
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    startActivityForResult(intentBuilder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e){
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

    // this is called after the PlacePicker has finished
    // it obtains the information and ports it to a SearchResultsFragment TODO: incomplete
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            final Place place = PlacePicker.getPlace(getActivity(), data);
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();
            final CharSequence phoneNumber = place.getPhoneNumber();
            String attributions = (String) place.getAttributions();
            if (attributions == null)
                attributions = "";


            Log.d("PickerInfo",name.toString());
            Log.d("PickerInfo",address.toString());
            Log.d("PickerInfo",phoneNumber.toString());

            Truck t = new Truck();
            t.setName(name.toString());
            t.setAddress(address.toString());
            t.setPhone(phoneNumber.toString());

            Fragment resultsFrag = SearchResultsFragment.newInstance(t);

            getFragmentManager().beginTransaction()
                    .replace(R.id.search_container, resultsFrag)
                    .addToBackStack(null)
                    .commit();

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // in outState, put the information to save: strings, ints, etc.
        super.onSaveInstanceState(outState);
    }

}