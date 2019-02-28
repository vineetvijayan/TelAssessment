package com.example.telassessment.network;


import com.example.telassessment.model.DataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPIInterface {

    @GET("facts.json")
    Call<DataModel> retrieveList();
}
