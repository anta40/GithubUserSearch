package com.divbyzero.app.githubusersearch.api;

import com.divbyzero.app.githubusersearch.response.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIEndPoint {

    @GET(APIConfig.END_POINT_SEARCH_USERS)
    Call<SearchResponse> getSearchResult(@Query("q") String who, @Query("page") int pageNumber);

}
