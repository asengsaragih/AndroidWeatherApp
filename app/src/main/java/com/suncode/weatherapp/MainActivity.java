package com.suncode.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.suncode.weatherapp.base.Constant;
import com.suncode.weatherapp.fragment.LocationFragment;
import com.suncode.weatherapp.fragment.SettingFragment;
import com.suncode.weatherapp.fragment.WeatherFragment;
import com.suncode.weatherapp.model.Weather;
import com.suncode.weatherapp.network.ApiClient;
import com.suncode.weatherapp.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService mApiService;
    private BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = ApiClient.build().create(ApiService.class);
        mBottomNavigation = findViewById(R.id.bottomNavigationView);

        //ketika pertama kali aplikasi dijalankan maka akan ngejalanin yang weather fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameContainer, new WeatherFragment())
                .commit();

        transparentActionBar();
        bottomNavigationConfigure();
    }

    private void transparentActionBar() {
        //hide action bar
        getSupportActionBar().hide();

        //change color text in status/notification bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void bottomNavigationConfigure() {
        //ketika button diklik
        BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_weather:
//                        changeFragment(new WeatherFragment());
                        break;
                    case R.id.action_location:
//                        changeFragment(new LocationFragment());
                        shortToast("Coming Soon");
                        break;
                    case R.id.action_setting:
//                        changeFragment(new SettingFragment());
                        shortToast("Coming Soon");
                        break;
                }
                return false;
//                return true;
            }
        };

        //ketika button diklik ulang
        BottomNavigationView.OnNavigationItemReselectedListener itemReselectedListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_weather:
                        break;
                    case R.id.action_location:
                        break;
                    case R.id.action_setting:
                        break;
                }
            }
        };

        mBottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener);
        mBottomNavigation.setOnNavigationItemReselectedListener(itemReselectedListener);

    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.commit();
    }

    private void shortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}