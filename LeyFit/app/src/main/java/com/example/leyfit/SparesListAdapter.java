package com.example.leyfit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SparesListAdapter extends RecyclerView.Adapter<SparesListAdapter.SpareListViewHolder> {

String[] locations;
    public SparesListAdapter(String[] locations) {

        this.locations=locations;

    }

    @NonNull
    @Override
    public SpareListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.spareslist_spare_layout,parent,false);
        return new SpareListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareListViewHolder holder, int position) {
//        String location=locations[position];
        holder.location.setText(locations[position]);
    }

    @Override
    public int getItemCount() {
        return locations.length;
    }

    public class SpareListViewHolder extends RecyclerView.ViewHolder {
        TextView location;
        public SpareListViewHolder(@NonNull View itemView) {
            super(itemView);
            location=itemView.findViewById(R.id.location);
        }
    }
}
