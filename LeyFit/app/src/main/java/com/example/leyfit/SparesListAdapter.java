package com.example.leyfit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
        holder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, "Request Sent To The Inventory. You shall be receiving a call from the Service Center now.", Toast.LENGTH_LONG).show();
                //SEND MESSAGE TO INVENTORY

                PackageManager pm=context.getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "YOUR TEXT HERE";

                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    //Check if package exists or not. If not then code
                    //in catch block will be called
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    context.startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Log.d("DEBUG","namenotfound");
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.length;
    }

    public class SpareListViewHolder extends RecyclerView.ViewHolder {
        TextView location;
        ImageButton contact;
        public SpareListViewHolder(@NonNull View itemView) {
            super(itemView);
            location=itemView.findViewById(R.id.location);
            contact=itemView.findViewById(R.id.imageButton);
        }
    }
}
