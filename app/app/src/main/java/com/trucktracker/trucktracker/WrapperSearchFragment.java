package com.trucktracker.trucktracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WrapperSearchFragment extends Fragment {

    public static Fragment newInstance() {
        return new WrapperSearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.search_container_fragment, container, false);

        getFragmentManager()
            .beginTransaction()
            .replace(R.id.search_container, SearchFragment.newInstance())
            .commit();

        return v;
    }

}