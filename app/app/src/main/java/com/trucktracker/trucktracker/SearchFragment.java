package com.trucktracker.trucktracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

        Button pickerButton = v.findViewById(R.id.pickerButton);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    Intent intent = intentBuilder.build(getActivity());
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e){
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // in outState, put the information to save: strings, ints, etc.
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            final Place place = PlacePicker.getPlace(getActivity(), data);
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();
            final CharSequence phoneNumber = place.getPhoneNumber();
            final int priceLevel = place.getPriceLevel();
            CharSequence priceTag = "";
            switch (priceLevel) {
                case 0:
                    priceTag = "$";
                    break;
                case 1:
                    priceTag = "$$";
                    break;
                case 2:
                    priceTag = "$$$";
                    break;
                case 3:
                    priceTag = "$$$$";
                    break;
                case 4:
                    priceTag = "$$$$$";
                    break;
                default:
                    priceTag = "";
                    break;
            }
            final CharSequence rating = Float.toString(place.getRating());
            final Uri uri = place.getWebsiteUri();
            final String uriString = uri.toString();
            String attributions = (String) place.getAttributions();
            if (attributions == null) {
                attributions = "";
            }

//            mName.setText(name);
//            mAddress.setText(address);
//            mPhoneNumber.setText(phoneNumber);
//            mPriceLevel.setText(priceTag);
//            mRating.setText(rating);
//            mURI.setText(uriString);
//            mAttributions.setText(Html.fromHtml(attributions));

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

}