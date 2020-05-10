package com.divbyzero.app.githubusersearch.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.divbyzero.app.githubusersearch.api.APIEndPoint;
import com.divbyzero.app.githubusersearch.api.APIService;
import com.divbyzero.app.githubusersearch.response.SearchResponse;
import com.divbyzero.app.githubusersearch.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<User>> mutableLiveData = new MutableLiveData<>();
    //private int totalCount;
    private int TOTAL_PAGES;
    private ArrayList<User> savedData = new ArrayList<User>();

    public void setSearchResult(String who, int page){
        Retrofit retrofit = APIService.getRetrofitService();
        APIEndPoint apiEndpoint = retrofit.create(APIEndPoint.class);
        Call<SearchResponse> call = apiEndpoint.getSearchResult(who, page);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() != null) {
                        TOTAL_PAGES = ((int)Math.ceil((double) response.body().getTotalCount()/(double)30));
                        savedData.addAll(response.body().getItems());
                        //mutableLiveData.setValue((ArrayList<User>) response.body().getItems());
                        mutableLiveData.setValue(savedData);
                        Log.d("DBG", "OK");
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("DBG", "Failed: "+t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<User>> getSearchResult(){
        return mutableLiveData;
    }

    public void clear(){
        savedData.clear();
        mutableLiveData.setValue(new ArrayList<User>());
    }

    public int getTotalPages(){
        return TOTAL_PAGES;
    }

    public int getCurrentSize(){
        return savedData.size();
    }
}
