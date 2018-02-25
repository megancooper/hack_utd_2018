package com.trucktracker.trucktracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


        // setContentView(R.layout.activity_display_message);
        // Get the message from the intent
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginActivity.USERNAME);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("Welcome " + username);

        // Set the text view as the activity layout
        setContentView(textView);
    }

}
