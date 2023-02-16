package com.example.leyfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class SpareParts extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_parts);

        String[] locations=new String[]{"ASL Commercial Service Centre","Ashok Leyland Vehicles","ASL Service Pune","Ashok Leyland light vehicles"};

        TextView title=findViewById(R.id.spare_title);
        RecyclerView spares=findViewById(R.id.spares);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String item = extras.getString("itemData");
            String quantity=extras.getString("quantityData");
            //The key argument here must match that used in the other activity
            String titleText=item+" near you..";
            title.setText(titleText);
            SparesListAdapter adapter=new SparesListAdapter(locations);
            spares.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
            spares.setAdapter(adapter);
        }
    }
}