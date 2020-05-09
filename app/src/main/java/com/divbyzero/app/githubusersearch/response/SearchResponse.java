package com.divbyzero.app.githubusersearch.response;

import com.divbyzero.app.githubusersearch.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("total_count")
    @Expose
    private int totalCount;

    private boolean incompleteResults;
    private List<User> items;

    public SearchResponse(int totalCount, boolean incompleteResults, List<User> items){
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    public int getTotalCount(){
        return totalCount;
    }

    public boolean getIncompleteResults(){
        return incompleteResults;
    }

    public List<User> getItems(){
        return items;
    }
}
