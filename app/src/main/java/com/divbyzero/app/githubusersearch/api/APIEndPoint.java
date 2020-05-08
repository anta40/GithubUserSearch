package com.divbyzero.app.githubusersearch.api;

import com.divbyzero.app.githubusersearch.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIEndPoint {

    @GET(APIConfig.END_POINT_SEARCH_USERS)
    Call<List<User>> getSearchUserResult(@Path("q") String param);

}
