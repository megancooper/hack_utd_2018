package com.trucktracker.trucktracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;



public class MainActivity extends AppCompatActivity {
    private static final int PLACE_PICKER_REQUEST = 1;
    private TextView mName;
    private TextView mAddress;
    private TextView mPhoneNumber;
    private TextView mPriceLevel;
    private TextView mRating;
    private TextView mURI;
    private TextView mAttributions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = (TextView) findViewById(R.id.tv_name);
        mAddress = (TextView) findViewById(R.id.tv_address);
        mPhoneNumber = (TextView) findViewById(R.id.tv_phoneNumber);
        mPriceLevel = (TextView) findViewById(R.id.tv_priceLevel);
        mRating = (TextView) findViewById(R.id.tv_rating);
        mURI = (TextView) findViewById(R.id.tv_uri);
        mAttributions = (TextView) findViewById(R.id.tv_attributions);
        Button pickerButton = (Button) findViewById(R.id.pickerButton);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    Intent intent = intentBuilder.build(MainActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e){
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK){
            final Place place = PlacePicker.getPlace(this, data);
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();
            final CharSequence phoneNumber = place.getPhoneNumber();
            final int priceLevel = place.getPriceLevel();
            CharSequence priceTag = "";
            switch (priceLevel){
                case 0: priceTag = "$";
                        break;
                case 1: priceTag = "$$";
                        break;
                case 2: priceTag = "$$$";
                        break;
                case 3: priceTag = "$$$$";
                        break;
                case 4: priceTag = "$$$$$";
                        break;
                default: priceTag = "";
                        break;
            }
            final CharSequence rating = Float.toString(place.getRating());
            final Uri uri = place.getWebsiteUri();
            final String uriString = uri.toString();
            String attributions = (String) place.getAttributions();
            if(attributions == null){
                attributions="";
            }

            mName.setText(name);
            mAddress.setText(address);
            mPhoneNumber.setText(phoneNumber);
            mPriceLevel.setText(priceTag);
            mRating.setText(rating);
            mURI.setText(uriString);
            mAttributions.setText(Html.fromHtml(attributions));

        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
