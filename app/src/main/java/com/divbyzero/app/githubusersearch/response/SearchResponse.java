package com.divbyzero.app.githubusersearch.response;

import com.divbyzero.app.githubusersearch.model.User;

import java.util.List;

public class SearchResponse {
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
