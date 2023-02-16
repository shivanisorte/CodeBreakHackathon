package com.example.leyfit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{


    private GoogleMap mMap;
    private GoogleApiClient client;
    private Button findspare;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationmMarker;

    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;
    double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findspare = (Button) findViewById(R.id.findSpare);
        findspare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Do something in response to button click
//                Intent myIntent = new Intent(MapsActivity.this, ServiceCenters.class);
//                MapsActivity.this.startActivity(myIntent);

                final DialogPlus dialogPlus=DialogPlus.newDialog(MapsActivity.this)
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();

                final TextInputEditText item=myview.findViewById(R.id.dialogBoxtTextField);
                final TextInputEditText quantity=myview.findViewById(R.id.dialogBoxtTextField2);
                final Button search=myview.findViewById(R.id.dialogSubmit);
                dialogPlus.show();

                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String itemData= item.getText().toString().trim();
                        String quantityData= quantity.getText().toString().trim();
                        Intent i = new Intent(MapsActivity.this, SpareParts.class);
                        i.putExtra("itemData",itemData);
                        i.putExtra("quantityData",quantityData);
                        startActivity(i);
                    }
                });

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            bulidGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }


    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;
        if(currentLocationmMarker != null)
        {
            currentLocationmMarker.remove();

        }
        Log.d("lat = ",""+latitude);
        LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationmMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if(client != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        }
    }

    public void onClick(View v)
    {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        switch(v.getId())
        {
            case R.id.puc:
                mMap.clear();
                String puc = "PUC Center";
//                String url = getUrl(latitude, longitude, puc);
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);

                LatLng item1 = new LatLng(18.500875529629358, 73.96312410893465);
                LatLng item2 = new LatLng(18.54173086831727, 73.9341133380309);
                LatLng item3 = new LatLng(18.482316497435246, 73.955742670184);
                LatLng item4 = new LatLng(18.49127066747351, 73.93325503119942);
                LatLng item5 = new LatLng(18.462452850978547, 73.96672899762683);

                // creating array list for adding all our locations.
                locationArrayList = new ArrayList<>();
                locationArrayList.add(item1);
                locationArrayList.add(item2);
                locationArrayList.add(item3);
                locationArrayList.add(item4);
                locationArrayList.add(item5);


                for (int i = 0; i < locationArrayList.size(); i++) {

                    // below line is use to add marker to each location of our array list.
                    mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("PUC Center"));


                    // below line is use to zoom our camera on map.
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

                    // below line is use to move our camera to the specific location.
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                }








                Toast.makeText(MapsActivity.this, "Showing Nearby PUC Centers", Toast.LENGTH_SHORT).show();
                break;


            case R.id.servicing:
                mMap.clear();
                String servicecen = "Ashok LeyLand Service Centers";
//                url = getUrl(latitude, longitude, servicecen);
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;

//                getNearbyPlacesData.execute(dataTransfer);



                 item1 = new LatLng(18.506078285217786, 73.92741850463595);
                 item2 = new LatLng(18.501520251204305, 73.95625761417341);
                 item3 = new LatLng(18.497124889203036, 73.97565534856469);
                 item4 = new LatLng(18.49020154899323, 74.03547047758136);
                 item5 = new LatLng(18.49622503753943, 73.99238347557944);

                // creating array list for adding all our locations.
                locationArrayList = new ArrayList<>();
                locationArrayList.add(item1);
                locationArrayList.add(item2);
                locationArrayList.add(item3);
                locationArrayList.add(item4);
                locationArrayList.add(item5);


                for (int i = 0; i < locationArrayList.size(); i++) {

                    // below line is use to add marker to each location of our array list.
                    mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Service Center"));


                    // below line is use to zoom our camera on map.
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

                    // below line is use to move our camera to the specific location.
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                }



                Toast.makeText(MapsActivity.this, "Showing Nearby Ashok LeyLand Service Centers", Toast.LENGTH_SHORT).show();
                break;
            case R.id.petrol:
                mMap.clear();
                String petrol = "Petrol Pump";
//                url = getUrl(latitude, longitude, resturant);
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);



                LatLng reliancePetrolPump = new LatLng(18.489, 74.022);
                LatLng hpPetrolPump_sanjayServiceStation = new LatLng(18.491, 74.013);
                LatLng bharatPetroleumPrajakta = new LatLng(18.489, 74.023);
                LatLng hPCorporationLtd = new LatLng(18.490, 74.014);
                LatLng bPRajendraFuelCentre = new LatLng(18.489, 74.035);

                // creating array list for adding all our locations.
                locationArrayList = new ArrayList<>();
                locationArrayList.add(reliancePetrolPump);
                locationArrayList.add(hpPetrolPump_sanjayServiceStation);
                locationArrayList.add(bharatPetroleumPrajakta);
                locationArrayList.add(hPCorporationLtd);
                locationArrayList.add(bPRajendraFuelCentre);


                for (int i = 0; i < locationArrayList.size(); i++) {

                    // below line is use to add marker to each location of our array list.
                    mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Petrol Pump"));


                    // below line is use to zoom our camera on map.
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

                    // below line is use to move our camera to the specific location.
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                }


                Toast.makeText(MapsActivity.this, "Showing Petrol Pumps", Toast.LENGTH_SHORT).show();
                break;



        }
    }


    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+getString(R.string.google_maps_key));

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }


    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}