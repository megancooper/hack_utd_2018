package com.trucktracker.trucktracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    public static Fragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.search_fragment, container, false);

        // Initialize button
        Button mapButton = v.findViewById(R.id.search_in_maps_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // launch Jared's code

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