package com.example.listview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SampleDtaService {
    @GET("/todos")
    Call<List<SampleDta>> getSampleData();
}
