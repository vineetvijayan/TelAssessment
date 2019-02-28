package com.example.telassessment.network;


import android.arch.lifecycle.MutableLiveData;

import com.example.telassessment.model.DataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    public static MutableLiveData<List<DataModel>> getList() {
        final MutableLiveData<List<DataModel>> data = new MutableLiveData<>();
        RetrofitAPIInterface retrofitApiInterface = RetrofitAPIClient.getRetrofitClient().create(RetrofitAPIInterface.class);

        Call<ArrayList<DataModel>> call = retrofitApiInterface.retrieveList();
        call.enqueue(new Callback<ArrayList<DataModel>>() {

            @Override
            public void onResponse(Call<ArrayList<DataModel>> call, Response<ArrayList<DataModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<DataModel>> call, Throwable t) {
            }
        });

        return data;
    }
}
