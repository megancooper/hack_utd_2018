package com.trucktracker.trucktracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

// This fragment is created when a search has been requested.
// the query or picker is re-directed here and will pass the bundle arguments
// to the onCreateView() method, which
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

    public static Fragment newInstance(Truck t) {
        Bundle bundle = new Bundle();
        bundle.putString("name", t.getName());
        bundle.putString("phone", t.getPhone());
        bundle.putString("address", t.getAddress());
        bundle.putString("schedule", t.getSchedule());
        bundle.putString("owner", t.getOwner());
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
        final String query = bundle.getString("query");

        // if there was a query, then pull from the database
        if (query!=null) {
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
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Truck t = ds.getValue(Truck.class);
                        if (t != null && t.getName().contains(query))
                            values.add(t);
                    }
                    recyclerView.setAdapter(new RecyclerViewAdapter(values, myRef));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    System.out.println("Failed to read value." + error.toException());
                }
            });
        } else {

            // TODO: need to take in Picker information and port it to a TruckFragment
//            Truck t = new Truck();
//            t.setName();
//            Fragment truckFrag = TruckFragment.newInstance(t);
//
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.search_container, truckFrag)
//                    .addToBackStack(null)
//                    .commit();

        }

        return v;
    }


}
