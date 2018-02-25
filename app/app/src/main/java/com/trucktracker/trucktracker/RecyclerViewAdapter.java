package com.trucktracker.trucktracker;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JEFRI SINGH(ஜெப்ரி சிங்) on 11/4/2016.
 * Organization "The Tuna Group" - Kerala
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TruckHolder> {
    private ArrayList<Truck> values;
    private Fragment parentFragment;

    public RecyclerViewAdapter(ArrayList<Truck> values, Fragment parentFragment) {
        this.values = values;
        this.parentFragment = parentFragment;
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
    public void onBindViewHolder(TruckHolder holder, int position) {
        final int i = position;
        holder.truckName.setText(values.get(position).getName());
        holder.truckType.setText("Food type"); // TODO: change this
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment truckFrag = TruckFragment.newInstance(values.get(i));

                parentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.search_container, truckFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class TruckHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView truckName, truckType;
        TruckHolder(View itemView, TextView truckName, TextView truckType) {
            super(itemView);
            this.itemView = itemView;
            this.truckName = truckName;
            this.truckType = truckType;
        }
    }
}