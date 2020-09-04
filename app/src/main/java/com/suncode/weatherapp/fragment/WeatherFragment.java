package com.suncode.weatherapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suncode.weatherapp.R;
import com.suncode.weatherapp.adapter.HistoryWeatherAdapter;
import com.suncode.weatherapp.model.Weather;
import com.suncode.weatherapp.network.ApiClient;
import com.suncode.weatherapp.network.ApiService;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherFragment extends Fragment {

    private TextView mTimeTextview;
    private TextView mDateTextview;

    private View mBoxWeather;

    private TextView mLocationTextview;
    private TextView mTemperatureTextview;
    private TextView mConditionTextview;
    private TextView mPressureTextview;
    private TextView mHumidityTextview;
    private ImageView mConditionImageView;
    private ImageButton mMoreImageButton;

    private RecyclerView mHistoryRecycleview;

    private ApiService mApiService;

    private HistoryWeatherAdapter mAdapter;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTimeTextview = getView().findViewById(R.id.textView_weather_time);
        mDateTextview = getView().findViewById(R.id.textView_weather_date);

        mApiService = ApiClient.build().create(ApiService.class);

        mHistoryRecycleview = getView().findViewById(R.id.recyclerView_weather);

        //configure box custom
        mBoxWeather = getView().findViewById(R.id.box_weather);

        mLocationTextview = mBoxWeather.findViewById(R.id.textView_box_location);
        mTemperatureTextview = mBoxWeather.findViewById(R.id.textView_box_temperature);
        mConditionTextview = mBoxWeather.findViewById(R.id.textView_box_condition);
        mPressureTextview = mBoxWeather.findViewById(R.id.textView_box_pressure);
        mHumidityTextview = mBoxWeather.findViewById(R.id.textView_box_humidity);
        mConditionImageView = mBoxWeather.findViewById(R.id.imageView_box_condition);
        mMoreImageButton = mBoxWeather.findViewById(R.id.imageButton_box_more);

        //box custom data
        getWeatherData();

        //current time and date
        getCurrentDateTime();
    }

    private void getWeatherData() {
        Call<Weather> weatherCall = mApiService.getWeather(
                "bojongsoang",
                "json",
                "c"
        );

        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather data = response.body();
                Weather.Location location = data.getLocation();

                Weather.CurrentObservation currentObservation = data.getCurrentObservation();

                mLocationTextview.setText(location.getCity());
                mTemperatureTextview.setText(currentObservation.getCondition().getTemperature() + " \u2103"); // c \u2103 & f \u2109
                mConditionTextview.setText(currentObservation.getCondition().getText());
                mPressureTextview.setText(getString(R.string.pressure) + " " + currentObservation.getAtmosphere().getPressure());
                mHumidityTextview.setText(getString(R.string.humidity) + " " + currentObservation.getAtmosphere().getHumidity() + "%");

                //untuk recycle view
                List<Weather.Forecasts> forecasts = data.getForecasts();
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                mAdapter = new HistoryWeatherAdapter(getContext(), forecasts);

                mHistoryRecycleview.setLayoutManager(layoutManager);
                mHistoryRecycleview.setAdapter(mAdapter);

                Glide.with(getContext())
                        .load("https://raw.githubusercontent.com/asengsaragih/public-image/master/yahoo-weather/" + currentObservation.getCondition().getCode() + ".png")
                        .into(mConditionImageView);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfDate = new SimpleDateFormat("EEEE | MMM dd");

        mTimeTextview.setText(sdfTime.format(date));
        mDateTextview.setText(sdfDate.format(date));
    }
}