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
import java.util.List;


public class MatchmakingAdapter extends RecyclerView.Adapter<MatchmakingAdapter.MatchmakingListViewHolder> implements Filterable {
    public static MatchmakingAdapter setData;
    private List<Matchmaking> mMatchmaking;
    private RecyclerviewClickListener mListener;
    private List<Matchmaking> mMatchmakingFiltered;

    public MatchmakingAdapter(List<Matchmaking> matchmaking, RecyclerviewClickListener listener){
        mMatchmaking = matchmaking;
        mListener = listener;
        mMatchmakingFiltered = matchmaking;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    mMatchmakingFiltered=mMatchmaking;
                } else {
                    ArrayList<Matchmaking> filteredList = new ArrayList<>();
                    for(Matchmaking matchmaking : mMatchmaking) {
                        if(matchmaking.getProjectID().toLowerCase().contains(charString.toLowerCase())&& !filteredList.contains(matchmaking)) {
                            filteredList.add(matchmaking);
                        }
                        if(matchmaking.getUserID().toLowerCase().contains(charString.toLowerCase())&& !filteredList.contains(matchmaking)) {
                            filteredList.add(matchmaking);
                        }
                        if(matchmaking.getStatus().toLowerCase().contains(charString.toLowerCase())&& !filteredList.contains(matchmaking)) {
                            filteredList.add(matchmaking);
                        }
                    }
                    mMatchmakingFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mMatchmakingFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mMatchmakingFiltered = (ArrayList<Matchmaking>) results.values;
                Log.d("FilterResults", String.valueOf(mMatchmakingFiltered));
                notifyDataSetChanged();
            }
        };
    }

    public interface RecyclerviewClickListener{
        void onClick(View view, String id);
    }

    @NonNull
    @Override
    public MatchmakingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_row, parent, false);
        return new MatchmakingListViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(MatchmakingListViewHolder holder, int position) {
        Matchmaking matchmaking = mMatchmakingFiltered.get(position);

        holder.mTitle.setText(matchmaking.getMatchmakeID());
        holder.mUserID.setText("UserID: "+matchmaking.getUserID());
        holder.mProjectID.setText("ProjectID: "+matchmaking.getProjectID());
        //holder.mSupportNeeded.setText(user.getSuperPower());
        //Glide.with(holder.mImage.getContext())
        //        .load(user.)
        //        .into(holder.mImage);

        holder.itemView.setTag(matchmaking.getMatchmakeID());
        //String replaceText = user.getUserID().toLowerCase().trim();
        //String replacedSpaceText = replaceText.replace(' ', '-');
        //String replacedDotText = replacedSpaceText.replace('.', '-');
    }
    public void setData(ArrayList<Matchmaking> matchmaking){
        mMatchmakingFiltered.clear();
        mMatchmakingFiltered.addAll(matchmaking);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mMatchmakingFiltered.size();
    }

    public static class MatchmakingListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTitle, mUserID, mProjectID;
        private RecyclerviewClickListener mListener;
        //public ImageView mImage;

        public MatchmakingListViewHolder(@NonNull View itemView, RecyclerviewClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            mTitle = itemView.findViewById(R.id.tvProjectTitle);
            mUserID = itemView.findViewById(R.id.tvProjectSumary);
            mProjectID = itemView.findViewById(R.id.tvProjectTheme);
            //mSupportNeeded = itemView.findViewById(R.id.tvProjectSupportNeeded);
            //mImage = itemView.findViewById(R.id.ivProject);

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
