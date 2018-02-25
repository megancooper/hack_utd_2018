package com.trucktracker.trucktracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

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
        EditText searchBar = v.findViewById(R.id.search_food_trucks_edit_text);

        // Initialize search submit button
        Button submitSearchButton = v.findViewById(R.id.submitSearchButton);
        submitSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* TODO: get searchBar info, and lookup entry in database. if doesn't exist,
                 * then pull from Google Places
                 */
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

        // TODO: setup recycler view
        //recyclerView = v.findViewById(R.id.nearby_trucks_recycler_view);
        //recyclerView.setHasFixedSize(true);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(linearLayoutManager);
        // create the adapter to hold the truck data
        //cardAdapter = new CardAdapter(v);
        //recyclerView.setAdapter(cardAdapter);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            final Place place = PlacePicker.getPlace(getActivity(), data);
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();
            final CharSequence phoneNumber = place.getPhoneNumber();
            final CharSequence rating = Float.toString(place.getRating());
            String attributions = (String) place.getAttributions();
            if (attributions == null)
                attributions = "";


            Log.d("PickerInfo",name.toString());
            Log.d("PickerInfo",address.toString());
            Log.d("PickerInfo",phoneNumber.toString());
            Log.d("PickerInfo",rating.toString());

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // in outState, put the information to save: strings, ints, etc.
        super.onSaveInstanceState(outState);
    }


    // TODO... implement "nearby" results
    private final class CardAdapter extends RecyclerView.Adapter<FoodTruckCardHolder> {

        private CardAdapter(View itemView) {

        }

        @Override
        public void onBindViewHolder(FoodTruckCardHolder holder, int position) {

        }

        @Override
        public FoodTruckCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_truck_card_view, recyclerView, false);
            return new FoodTruckCardHolder(v);
        }

        @Override
        public int getItemCount() {
            return 3; // TODO ?
        }

    }

    // TODO... implement "nearby" results
    private class FoodTruckCardHolder extends RecyclerView.ViewHolder {
        private FoodTruckCardHolder(View itemView) {
            super(itemView);
        }
    }

}