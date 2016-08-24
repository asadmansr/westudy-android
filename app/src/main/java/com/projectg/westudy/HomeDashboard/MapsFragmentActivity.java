package com.projectg.westudy.HomeDashboard;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapsFragmentActivity extends FragmentActivity implements OnMapReadyCallback {

    // Test update from Android Studio.


//    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
    }

    //When Map is ready, initiate basic Map items here.
    @Override
    public void onMapReady(GoogleMap googleMap) {



    }
}