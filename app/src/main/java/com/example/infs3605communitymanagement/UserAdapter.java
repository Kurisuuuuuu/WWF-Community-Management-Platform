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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {
    public static UserAdapter setData;
    private List<User> mUser;
    private RecyclerviewClickListener mListener;
    private List<User> mUserFiltered;

    public UserAdapter(List<User> user, RecyclerviewClickListener listener){
        mUser = user;
        mListener = listener;
        mUserFiltered = user;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    mUserFiltered=mUser;
                } else {
                    ArrayList<User> filteredList = new ArrayList<>();
                    for(User user : mUser) {
                        if(user.getFullName().toLowerCase().contains(charString.toLowerCase())&& !filteredList.contains(user)) {
                            filteredList.add(user);
                        }
                    }
                    mUserFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mUserFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mUserFiltered = (ArrayList<User>) results.values;
                Log.d("FilterResults", String.valueOf(mUserFiltered));
                notifyDataSetChanged();
            }
        };
    }

    public interface RecyclerviewClickListener{
        void onClick(View view, String id);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_row, parent, false);
        return new UserViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = mUserFiltered.get(position);

        holder.mTitle.setText(user.getUsername());
        holder.mSummary.setText(user.getBio());
        holder.mTheme.setText(user.getImpactTheme());
        holder.mSupportNeeded.setText(user.getSuperPower());
        //Glide.with(holder.mImage.getContext())
        //        .load(user.)
        //        .into(holder.mImage);

        holder.itemView.setTag(user.getUserID());
        String replaceText = user.getUserID().toLowerCase().trim();
        String replacedSpaceText = replaceText.replace(' ', '-');
        String replacedDotText = replacedSpaceText.replace('.', '-');
    }
    public void setData(ArrayList<User> user){
        mUserFiltered.clear();
        mUserFiltered.addAll(user);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mUserFiltered.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTitle, mSummary, mTheme,mSupportNeeded;
        private RecyclerviewClickListener mListener;
        public ImageView mImage;

        public UserViewHolder(@NonNull View itemView, RecyclerviewClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            mTitle = itemView.findViewById(R.id.tvProjectTitle);
            mSummary = itemView.findViewById(R.id.tvProjectSumary);
            mTheme = itemView.findViewById(R.id.tvProjectTheme);
            mSupportNeeded = itemView.findViewById(R.id.tvProjectSupportNeeded);
            mImage = itemView.findViewById(R.id.ivProject);

        }
        @Override
        public void onClick(View v) {
            mListener.onClick(v, (String) v.getTag());
            Log.d("Test",(String) v.getTag());
        }
    }
/*
    // Sort method
    public void sort(final int sortMethod) {
        if(mProjectFiltered.size() > 0) {
            Collections.sort(mProject, new Comparator<Project>() {
                @Override
                public int compare(Project o1, Project o2) {
                    //Name (A-Z)
                    if (sortMethod == 1) {
                        Log.d("1", "");
                        return o1.getProjectTitle().compareTo(o2.getProjectTitle());
                    } else if (sortMethod == 2) {
                        Log.d("2", "");
                        return o2.getProjectTitle().compareTo(o1.getProjectTitle());
                    } else {
                    }
                    return o1.getProjectTitle().compareTo(o2.getProjectTitle());}
            });
        }
        notifyDataSetChanged();
    }
*/

}
