package com.divbyzero.app.githubusersearch.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.divbyzero.app.githubusersearch.R;
import com.divbyzero.app.githubusersearch.model.User;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {
    private List<User> fullSearchResult;
    private List<User> filteredSearchResult;
    private List<User> theData;
    private Context ctxt;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView github_profile_pic;
        TextView github_login;

       public SearchViewHolder(View itemView) {
            super(itemView);
            github_profile_pic = itemView.findViewById(R.id.github_profile_pic);
            github_login  = itemView.findViewById(R.id.github_login);
        }
    }

    public SearchAdapter(Context ctxt, List<User> list) {
        this.ctxt = ctxt;
        theData = list;
    }

    public void clear() {
        int size = theData.size();
        theData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,
                parent, false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        User user = theData.get(position);
        Glide.with(ctxt)
                .load(user.getAvatarUrl())
                .override(200, 200)
                .into(holder.github_profile_pic);
        holder.github_login.setText(user.getLogin());
    }

    @Override
    public int getItemCount() {
        return theData.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<User> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullSearchResult);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (User item : fullSearchResult) {
                    if (item.getLogin().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredSearchResult.clear();
            filteredSearchResult.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void updateData(List<User> newList){
        this.theData.clear();
        this.theData.addAll(newList);
    }
}
