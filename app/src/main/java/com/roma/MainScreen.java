package com.roma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationVew);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                    if (item.getItemId() == R.id.item3) {
                        Intent mapAct = new Intent(MainScreen.this, MapActivity.class);
                        startActivity(mapAct);
                        return true;
                    } else {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.item1:
                                selectedFragment = new HomeFragment();
                                break;
                            case R.id.item2:
                                selectedFragment = new ExploreFragment();
                                break;
                            case R.id.item4:
                                selectedFragment = new SettingsFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_layout
                                        , selectedFragment).commit();

                        return true;
                    }
                }
            };


}