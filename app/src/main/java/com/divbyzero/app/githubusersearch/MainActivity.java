package com.divbyzero.app.githubusersearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.divbyzero.app.githubusersearch.adapter.SearchAdapter;
import com.divbyzero.app.githubusersearch.model.User;
import com.divbyzero.app.githubusersearch.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SearchAdapter searchAdapter;
    RecyclerView recyclerView;
    Context context;
    int currentPage, TOTAL_PAGES;
    UserViewModel viewModel;
    String who;
    List<User> tmpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        setUpRecyclerView();
        currentPage = 1;

        tmpList = new ArrayList<User>();

        viewModel = viewModel = new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);

    }

    private void doSearchUser(String who, int pageNum) {
        searchAdapter.clear();
        viewModel.setSearchResult(who, pageNum);

        viewModel.getSearchResult().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> theList) {
                TOTAL_PAGES = viewModel.getTotalPages();
                searchAdapter.updateData(theList);
                tmpList = theList;
            }
        });

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
               // searchAdapter.notifyItemInserted(tmpList.size() - 1);
                searchAdapter.notifyDataSetChanged();
            }
        });

       // searchAdapter.notifyDataSetChanged();
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        List<User> userList = new ArrayList<User>();
        searchAdapter = new SearchAdapter(context, userList) ;

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(!recyclerView.canScrollVertically(1) && dy != 0) {
                    if (currentPage < TOTAL_PAGES) {
                        currentPage++;
                        doSearchUser(who, currentPage);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                who = query;
                currentPage = 1;
                searchAdapter.clear();
                searchAdapter.notifyDataSetChanged();
                doSearchUser(query, currentPage);
                //searchAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }
}
