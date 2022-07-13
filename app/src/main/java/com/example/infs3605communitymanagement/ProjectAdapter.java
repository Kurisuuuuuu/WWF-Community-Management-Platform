package com.example.infs3605communitymanagement;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> implements Filterable {
    public static ProjectAdapter setData;
    private List<Project> mProject;
    private RecyclerviewClickListener mListener;
    private List<Project> mProjectFiltered;

    public ProjectAdapter(List<Project> projects, RecyclerviewClickListener listener){
        mProject = projects;
        mListener = listener;
        mProjectFiltered = projects;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    mProjectFiltered=mProject;
                } else {
                    ArrayList<Project> filteredList = new ArrayList<>();
                    for(Project project : mProject) {
                        if(project.getProjectTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(project);
                        }
                    }
                    mProjectFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mProjectFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mProjectFiltered = (ArrayList<Project>) results.values;
                Log.d("FilterResults", String.valueOf(mProjectFiltered));
                notifyDataSetChanged();
            }
        };
    }

    public interface RecyclerviewClickListener{
        void onClick(View view, String id);
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_list_row, parent, false);
        return new ProjectViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        Project project = mProjectFiltered.get(position);

        holder.mTitle.setText(project.getProjectTitle());
        holder.mSummary.setText(project.getProjectSummary());
        holder.mTheme.setText(project.getTheme());
        holder.mSupportNeeded.setText(project.getSupportNeeded());
        holder.itemView.setTag(project.getProjectID());
        String replaceText = project.getProjectTitle().toLowerCase().trim();
        String replacedSpaceText = replaceText.replace(' ', '-');
        String replacedDotText = replacedSpaceText.replace('.', '-');
    }
    public void setData(List<Project> projects){
        mProjectFiltered.clear();
        mProjectFiltered.addAll(projects);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mProjectFiltered.size();
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTitle, mSummary, mTheme,mSupportNeeded;
        private RecyclerviewClickListener mListener;
        public ImageView mImage;

        public ProjectViewHolder(@NonNull View itemView, RecyclerviewClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            //mTitle = itemView.findViewById(R.id.tvCoinNameMain);
            //mSummary = itemView.findViewById(R.id.tvPriceMain);
            //mTheme = itemView.findViewById(R.id.tvChangeMain);
            //mSupportNeeded = itemView.findViewById(R.id.imageViewCoin);
        }
        @Override
        public void onClick(View v) {
            mListener.onClick(v, (String) v.getTag());
            Log.d("Test",(String) v.getTag());
        }
    }

    // Sort method
    /*public void sort(final int sortMethod) {
        if(mCoinFiltered.size() > 0) {
            Collections.sort(mCoinFiltered, new Comparator<Coin>() {
                @Override
                public int compare(Coin o1, Coin o2) {
                    if(sortMethod == 1) {
                        return o1.getName().compareTo(o2.getName());
                    } else if(sortMethod == 2)
                        return Double.valueOf(o1.getPriceUsd()).compareTo(Double.valueOf(o2.getPriceUsd()));
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        notifyDataSetChanged();
    }

*/
}
