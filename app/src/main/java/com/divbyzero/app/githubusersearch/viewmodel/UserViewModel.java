package com.divbyzero.app.githubusersearch.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.divbyzero.app.githubusersearch.api.APIEndPoint;
import com.divbyzero.app.githubusersearch.api.APIService;
import com.divbyzero.app.githubusersearch.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<User>> mutableLiveData = new MutableLiveData<>();

    public void setSearchResult(String param){
        Retrofit retrofit = APIService.getRetrofitService();
        APIEndPoint apiEndpoint = retrofit.create(APIEndPoint.class);
        Call<List<User>> call = apiEndpoint.getSearchResult(param);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mutableLiveData.setValue((ArrayList<User>) response.body());
                Log.d("DBG", "OK");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("DBG", "Failed");
            }
        });
    }

    public LiveData<ArrayList<User>> getSearchResult(){
        return mutableLiveData;
    }
}
