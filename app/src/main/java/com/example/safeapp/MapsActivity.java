package com.example.safeapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.renderscript.RenderScript;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private float mZoomLevel = 15;
    private GoogleMap mMap;
    private FusedLocationProviderClient mClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;

    public static HashMap<Integer,Incident> populateIncidents(){
        Incident incident1 =  new Incident(28.538336, -81.379234,"The incident happened in","11/8/2022");
        Incident incident2 = new Incident (28.596655, -81.355733,"The incident happened in","12/30/2022");
        Incident incident3 = new Incident (28.595347, -81.332631,"The incident happened in","1/8/2023");
        Incident incident4 = new Incident (28.582870, -81.344416,"The incident happened in","2/23/2023");
        Incident incident5 = new Incident (28.590342, -81.339906,"The incident happened in","3/14/2023");
        Incident incident6 = new Incident (28.599382, -81.347437,"The incident happened in","4/10/2023");
        Incident incident7 = new Incident (28.601212, -81.360285,"The incident happened in","4/25/2023");


        HashMap<Integer,Incident> incidentHashMap = new HashMap<>();

        incidentHashMap.put(1,incident1);
        incidentHashMap.put(2,incident2);
        incidentHashMap.put(3,incident3);
        incidentHashMap.put(4,incident4);
        incidentHashMap.put(5,incident5);
        incidentHashMap.put(6,incident6);
        incidentHashMap.put(7,incident7);

        return incidentHashMap;
    }
    HashMap<Integer,Incident> map = populateIncidents();
//
//    private MarkerOptions insertMarkers(HashMap<Integer,Incident> map) {
//        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        for (HashMap.Entry<Integer,Incident> entry : map.entrySet()) {
//            //Integer key = entry.getKey();
//            Incident value = entry.getValue();
//            final LatLng position = new LatLng(value.getLatitude(),value.getLongtitude());
//            final MarkerOptions options = new MarkerOptions().position(position);
//            //mMap.addMarker(options);
//            builder.include(position);
//        }
//        return options;

    HashMap markerMap = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        if (hasLocationPermission()) {
            trackLocation();
        }
    }

    private void trackLocation() {

        // Create location request
        mLocationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(3000)
                .build();

        // Create location callback
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                for (Location location : locationResult.getLocations()) {
                    updateMap(location);
                }
            }
        };

        mClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void updateMap(Location location) {

        // Get current location
        LatLng myLatLng = new LatLng(location.getLatitude(),
                location.getLongitude());

        LatLng myLatLng1 = new LatLng(28.538336, -81.379234);
        LatLng myLatLng2 = new LatLng(28.596655, -81.355733);
        LatLng myLatLng3 = new LatLng(28.595347, -81.332631);
        LatLng myLatLng4 = new LatLng(28.582870, -81.344416);
        LatLng myLatLng5 = new LatLng(28.590342, -81.339906);
        LatLng myLatLng6 = new LatLng(28.599382, -81.347437);
        LatLng myLatLng7 = new LatLng(28.601212, -81.360285);

        // Place a marker at the current location
        MarkerOptions myMarker = new MarkerOptions()
                .title("Here you are!")
                .position(myLatLng);

        // Remove previous marker
        mMap.clear();

        // Add incident markers
//        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        for (HashMap.Entry<Integer,Incident> entry : map.entrySet()) {
//            //Integer key = entry.getKey();
//            Incident value = entry.getValue();
//            final LatLng position = new LatLng(value.getLatitude(), value.getLongtitude());
//            final MarkerOptions options = new MarkerOptions().position(position);
//            mMap.addMarker(options);
//            builder.include(position);
//        }

        // Add new marker
        mMap.addMarker(myMarker);
        mMap.addMarker(myMarker.title("Incident 1").position(myLatLng1));
        mMap.addMarker(myMarker.title("Incident 2").position(myLatLng2));
        mMap.addMarker(myMarker.title("Incident 3").position(myLatLng3));
        mMap.addMarker(myMarker.title("Incident 4").position(myLatLng4));
        mMap.addMarker(myMarker.title("Incident 5").position(myLatLng5));
        mMap.addMarker(myMarker.title("Incident 6").position(myLatLng6));
        mMap.addMarker(myMarker.title("Incident 7").position(myLatLng7));

        markerMap.put("Incident 1", mMap.addMarker(myMarker.title("Incident 1").position(myLatLng1)));
        markerMap.put("Incident 2", mMap.addMarker(myMarker.title("Incident 2").position(myLatLng2)));
        markerMap.put("Incident 3", mMap.addMarker(myMarker.title("Incident 3").position(myLatLng3)));
        markerMap.put("Incident 4", mMap.addMarker(myMarker.title("Incident 4").position(myLatLng4)));
        markerMap.put("Incident 5", mMap.addMarker(myMarker.title("Incident 5").position(myLatLng5)));
        markerMap.put("Incident 6", mMap.addMarker(myMarker.title("Incident 6").position(myLatLng6)));
        markerMap.put("Incident 7", mMap.addMarker(myMarker.title("Incident 7").position(myLatLng7)));

        // Move and zoom to current location at the street level
        // Zoom to previously saved level
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myLatLng, mZoomLevel);
        mMap.animateCamera(update);
    }

//    public void onIncidentCLick(View view){
//        HashMap<String,MarkerOptions>markerHashMap = markerMap;
//        Marker marker = (Marker)markerMap.get(getTitle());
//        if(marker.equals("Incident 1")){
//            Toast.makeText(this,"Incident happened on 11/8/2022", Toast.LENGTH_LONG).show();
//        }
//        else{
//            System.out.println(0);
//        }
//    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Save zoom level
        mMap.setOnCameraMoveListener(() -> {
            CameraPosition cameraPosition = mMap.getCameraPosition();
            mZoomLevel = cameraPosition.zoom;
        });

        // Handle marker click
        mMap.setOnMarkerClickListener(marker -> {
//            Toast.makeText(MapsActivity.this, "Lat: " + marker.getPosition().latitude +
//                            System.getProperty("line.separator") + "Long: " + marker.getPosition().longitude,
//                    Toast.LENGTH_LONG).show();
            String date = " ";
            int number = 0;
            if(marker.getPosition().latitude == map.get(1).getLatitude()){
                date = map.get(1).getDate();
                number = 1;
            } else if(marker.getPosition().latitude == map.get(2).getLatitude()){
                date = map.get(2).getDate();
                number = 2;
            }else if(marker.getPosition().latitude == map.get(3).getLatitude()){
                date = map.get(3).getDate();
                number = 3;
            }else if(marker.getPosition().latitude == map.get(4).getLatitude()){
                date = map.get(4).getDate();
                number = 4;
            }else if(marker.getPosition().latitude == map.get(5).getLatitude()){
                date = map.get(5).getDate();
                number = 5;
            }else if(marker.getPosition().latitude == map.get(6).getLatitude()){
                date = map.get(6).getDate();
                number = 6;
            }else {
                date = map.get(7).getDate();
                number = 7;
            }

            Toast.makeText(MapsActivity.this, "Incident " + number + " happened on " + date + " at " + marker.getPosition().latitude +
                    System.getProperty("line.separator") + marker.getPosition().longitude, Toast.LENGTH_LONG).show();

            return false;
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mClient != null) {
            mClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onResume() {
        super.onResume();

        if (hasLocationPermission()) {
            mClient.requestLocationUpdates(mLocationRequest, mLocationCallback,
                    Looper.getMainLooper());
        }
    }

    private boolean hasLocationPermission() {

        // Request fine location permission if not already granted
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            mRequestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            return false;
        }

        return true;
    }

    private final ActivityResultLauncher<String> mRequestPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    trackLocation();
                }
            });


}