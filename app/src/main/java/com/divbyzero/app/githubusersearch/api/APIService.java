package com.divbyzero.app.githubusersearch.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    public static Retrofit getRetrofitService() {
        return new Retrofit.Builder()
                .baseUrl(APIConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
