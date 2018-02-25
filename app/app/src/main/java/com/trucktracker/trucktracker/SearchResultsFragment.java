package com.trucktracker.trucktracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchResultsFragment extends Fragment {

    public static Fragment newInstance(String query) {
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        Fragment frag = new SearchResultsFragment();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String query = bundle.getString("query");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance()
                .getReference();

        Query trucksQuery = mDatabase.child("trucks")
                .orderByChild("name");

        FirebaseRecyclerOptions<Truck> options =
                new FirebaseRecyclerOptions.Builder<Truck>()
                        .setQuery(trucksQuery, Truck.class)
                        .build();

        TruckAdapter truckAdapter = new TruckAdapter(options);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.search_results_fragment, container, false);

        // setup recycler view in this fragment
        RecyclerView recyclerView = v.findViewById(R.id.list_of_queried_trucks);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(truckAdapter);

        return v;
    }

    public class TruckAdapter extends FirebaseRecyclerAdapter<Truck, TruckHolder> {

        public TruckAdapter(FirebaseRecyclerOptions<Truck> options) {
            super(options);
        }


        @Override
        public TruckHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new instance of the ViewHolder, in this case we are using a custom
            // layout called R.layout.message for each item
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.food_truck_card_view, parent, false);

            // TODO: images
            TextView truckName = view.findViewById(R.id.truck_name);
            TextView truckType = view.findViewById(R.id.truck_type);

            return new TruckHolder(view, truckName, truckType);
        }

        @Override
        protected void onBindViewHolder(TruckHolder holder, int position, Truck model){
            holder.truckName.setText(model.getName());
            // TODO: holder.truckType.setText(model.getType());
            holder.truckType.setText("Enter food type here");
        }

    }

    private static class TruckHolder extends RecyclerView.ViewHolder {
        private TextView truckName, truckType;
        TruckHolder(View itemView, TextView truckName, TextView truckType) {
            super(itemView);
            this.truckName = truckName;
            this.truckType = truckType;
        }
    }

}
