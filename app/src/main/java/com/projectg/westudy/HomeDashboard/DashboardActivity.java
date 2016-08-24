package com.projectg.westudy.HomeDashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.projectg.westudy.Analytics.AnalyticsActivity;
import com.projectg.westudy.History.HistoryActivity;
import com.projectg.westudy.Options.AboutActivity;
import com.projectg.westudy.Options.HelpActivity;
import com.projectg.westudy.Options.SettingsActivity;
import com.projectg.westudy.Profile.EditProfileActivity;
import com.projectg.westudy.R;
import com.projectg.westudy.StudyGroups.CreateStudyGroupActivity;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback {

    private GoogleMap mMap;
    int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Initialize Google Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Create nav drawer
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ImageButton imageButton = (ImageButton) findViewById(R.id.toggle);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        final EditText edit = (EditText) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setCursorVisible(true);
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    //  The following method will perform all Map handling.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Set default map centered at McMaster University's location
        LatLng mcmaster = new LatLng(43.261316, -79.919267);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mcmaster, 17));

        //On FAB clicked, initiate CreateStudyGroupActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.create_group_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create_sg = new Intent(getApplicationContext(), CreateStudyGroupActivity.class);


                //need to use start activity for result here so we can retrieve data from the create study group activity
                startActivityForResult(create_sg, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                String[] result = data.getStringArrayExtra("result");
                Double Lat = Double.parseDouble(result[0]);
                Double Lng = Double.parseDouble(result[1]);

                LatLng study_group_location = new LatLng(Lat, Lng);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(study_group_location, 18));

                //TODO: Review what data needs to be received from CreateStudyGroupActivity
                mMap.addMarker(new MarkerOptions().position(study_group_location).title("Your Study Group"));

            }
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (id == R.id.nav_profile){
            startActivity(EditProfileActivity.getIntent(this));

        } else if (id == R.id.nav_events){
            startActivity(CreateStudyGroupActivity.getIntent(this));

        } else if (id == R.id.nav_history){
            startActivity(HistoryActivity.getIntent(this));

        } else if (id == R.id.nav_analytics){
            startActivity(AnalyticsActivity.getIntent(this));

        } else if (id == R.id.nav_settings){
            startActivity(SettingsActivity.getIntent(this));

        } else if (id == R.id.nav_about){
            startActivity(AboutActivity.getIntent(this));

        } else if (id == R.id.nav_help){
            startActivity(HelpActivity.getIntent(this));

        }

        //        android:checkableBehavior="single" took it out because it would be pre-selected


        return true;
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, DashboardActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
