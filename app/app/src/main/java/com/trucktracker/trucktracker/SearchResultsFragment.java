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
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchResultsFragment extends Fragment {

    private RecyclerView recyclerView;
    private Fragment myRef;

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

        this.myRef = this;
        View v = inflater.inflate(R.layout.search_results_fragment, container, false);

        Bundle bundle = getArguments();
        // TODO: String query = bundle.getString("query");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("trucks");

        recyclerView = v.findViewById(R.id.list_of_queried_trucks);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                ArrayList<Truck> values = new ArrayList<>();
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    values.add(ds.getValue(Truck.class));
                }
                recyclerView.setAdapter(new RecyclerViewAdapter(values, myRef));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value." + error.toException());
            }
        });

        return v;
    }


}
