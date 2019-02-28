package com.example.telassessment.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPIClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl";

    public static Retrofit getRetrofitClient() {

        OkHttpClient client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
