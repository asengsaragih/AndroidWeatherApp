package com.suncode.weatherapp.network;

import com.suncode.weatherapp.base.Constant;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class ApiClient {
    public static Retrofit build() {
        //untuk oauth ver 1.0 methode pakai llibrary yang di dapat dari github
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constant.CONSUMER_KEY, Constant.CONSUMER_SECRET);
        consumer.setTokenWithSecret("", "");

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_WEATHER)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
