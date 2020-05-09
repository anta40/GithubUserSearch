package com.divbyzero.app.githubusersearch.model;

import java.util.List;

public class SearchResponse {
    private long totalCount;
    private boolean incompleteResults;
    private List<User> items;

    public SearchResponse(long totalCount, boolean incompleteResults, List<User> items){
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    public long getTotalCount(){
        return totalCount;
    }

    public boolean getIncompleteResults(){
        return incompleteResults;
    }

    public List<User> getItems(){
        return items;
    }
}
