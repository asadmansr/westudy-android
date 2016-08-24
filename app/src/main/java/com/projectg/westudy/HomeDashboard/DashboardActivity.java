package com.projectg.westudy.HomeDashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback, View.OnClickListener {
    private DashboardViewHolder mDashboardViewHolder;
    private GoogleMap mMap;
    private int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        controlInitialization();

        /**Important*/
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        //toggle.syncState();
    }

    private void controlInitialization(){
        mDashboardViewHolder = new DashboardViewHolder(findViewById(R.id.drawer_layout),findViewById(R.id.relative_container),this,this,this);
    }

    private class DashboardViewHolder{
        private FloatingActionButton mFloatingBtn;
        DrawerLayout mDrawer;
        NavigationView mNavigationView;
        private ImageButton mToggle;
        private EditText mSearchEt;

        private DashboardViewHolder(View view,View subview,NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener,OnMapReadyCallback onMapReadyCallback,View.OnClickListener listener){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(onMapReadyCallback);

            mFloatingBtn = (FloatingActionButton) subview.findViewById(R.id.create_group_fab);
            mToggle = (ImageButton) subview.findViewById(R.id.toggle);
            mSearchEt = (EditText) subview.findViewById(R.id.edit);

            mDrawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
            mNavigationView = (NavigationView) view.findViewById(R.id.nav_view);
            mNavigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);

            mFloatingBtn.setOnClickListener(listener);
            mToggle.setOnClickListener(listener);
            mSearchEt.setOnClickListener(listener);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_group_fab:
                Intent create_sg = new Intent(getApplicationContext(), CreateStudyGroupActivity.class);

                //need to use start activity for result here so we can retrieve data from the create study group activity
                startActivityForResult(create_sg, REQUEST_CODE);
                break;

            case  R.id.toggle:
                mDashboardViewHolder.mDrawer.openDrawer(GravityCompat.START);
                break;

            case R.id.edit:
                mDashboardViewHolder.mSearchEt.setCursorVisible(true);
                break;

        }
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

    //  The following method will perform all Map handling.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Set default map centered at McMaster University's location
        LatLng mcmaster = new LatLng(43.261316, -79.919267);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mcmaster, 17));
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
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (id == R.id.nav_profile){
            startActivity(EditProfileActivity.getIntent(this));

        } else if (id == R.id.nav_study_group){
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

        /**Important*/
        //android:checkableBehavior="single" took it out because it would be pre-selected
        return true;
    }
}