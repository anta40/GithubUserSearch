package com.divbyzero.app.githubusersearch.api;

import com.divbyzero.app.githubusersearch.model.SearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIEndPoint {

    @GET(APIConfig.END_POINT_SEARCH_USERS)
    Call<SearchResponse> getSearchResult(@Query("q") String param);

}
