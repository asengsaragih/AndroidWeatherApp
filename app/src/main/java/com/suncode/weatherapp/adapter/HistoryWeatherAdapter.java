package com.suncode.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.suncode.weatherapp.R;
import com.suncode.weatherapp.model.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryWeatherAdapter extends RecyclerView.Adapter<HistoryWeatherAdapter.HistoryWeatherHolder> {

    private Context mContext;
    private List<Weather.Forecasts> mData;

    public HistoryWeatherAdapter(Context mContext, List<Weather.Forecasts> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public HistoryWeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_history_weather, parent, false);
        return new HistoryWeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryWeatherHolder holder, int position) {
        Weather.Forecasts forecasts = mData.get(position);

        //untuk ngatur hari dan tanggal
        String dateTime = forecasts.getDay() + " " + new SimpleDateFormat("dd").format(new Date((long) (forecasts.getDate() * 1000L)));
        holder.mDateTextview.setText(dateTime.toUpperCase());

        //untuk ngatur low dan high temp
        String low = String.valueOf(forecasts.getLow()) + "\u00B0";
        String high = String.valueOf(forecasts.getHigh()) + "\u00B0";

        holder.mTemperatureTextview.setText(low + "/" + high);

        //untuk ngatur icon
        Glide.with(mContext)
                .load("https://raw.githubusercontent.com/asengsaragih/public-image/master/yahoo-weather/" + forecasts.getCode() + ".png")
                .into(holder.mIconImageview);
    }

    @Override
    public int getItemCount() {
        return mData.size() - 5;
    }

    class HistoryWeatherHolder extends RecyclerView.ViewHolder {
        final TextView mDateTextview;
        final ImageView mIconImageview;
        final TextView mTemperatureTextview;

        public HistoryWeatherHolder(@NonNull View itemView) {
            super(itemView);
            mDateTextview = itemView.findViewById(R.id.textView_list_history_date);
            mIconImageview = itemView.findViewById(R.id.imageView_list_history_icon);
            mTemperatureTextview = itemView.findViewById(R.id.textView_list_history_temp);
        }
    }
}
