package com.trucktracker.trucktracker;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.Executor;

// This is the only activity in the entire application
// the app works by exchanging fragments
public class MainActivity extends FragmentActivity {

    private ViewPager appViewPager; // controls the navigation between fragments
    private FragAdapter fragmentAdapter; // adapter to manage fragments
    private BottomNavigationView bottomNavBar; // bottom navigation bar
    public static boolean isLoggedIn;

    FragmentManager fragmentManager;


    // reference integers
    private static final int SEARCH = 0, FAVORITES = 1, ACCOUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get a FragmentManager to access and manage fragments
        fragmentManager = getSupportFragmentManager();

        // get ViewPager object to switch between fragment
        fragmentAdapter = new FragAdapter(fragmentManager);
        appViewPager = findViewById(R.id.pager);
        appViewPager.setAdapter(fragmentAdapter);

        // set up bottom navigation
        bottomNavBar = findViewById(R.id.bottom_navigation);
        bottomNavBar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_favorites:
                        appViewPager.setCurrentItem(FAVORITES);
                        break;
                    case R.id.action_login:
                        appViewPager.setCurrentItem(ACCOUNT);
                        break;
                    case R.id.action_search:
                        appViewPager.setCurrentItem(SEARCH);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                return true;
            }
        });


    }

    public static class FragAdapter extends FragmentPagerAdapter {
        // DO NOT MODIFY. Switches between the fragments in the app.

        FragAdapter(FragmentManager fm) { super(fm); }

        @Override
        public int getCount() { return 3; }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            switch (position) {
                case SEARCH:
                    frag = WrapperSearchFragment.newInstance();
                    break;
                case FAVORITES:
                    frag = FavoritesFragment.newInstance();
                    break;
                case ACCOUNT:
                    frag = AccountFragment.newInstance();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            return frag;
        }
    }
}
