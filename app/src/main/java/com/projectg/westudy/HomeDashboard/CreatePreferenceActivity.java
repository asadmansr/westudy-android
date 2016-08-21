package com.projectg.westudy.HomeDashboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectg.westudy.R;

import java.util.ArrayList;

public class CreatePreferenceActivity extends AppCompatActivity {

    final String[] subjects_array = new String[] {"Math", "Physics","Chem", "Bio", "Geog", "Ling","Eng", "French", "Soc", "Art","History", "Antro" };
    final String[] type_array = new String[] {"Review Session", "Assignment","Lab", "Quizzes"};
    ArrayList<String> subject_list = new ArrayList<>();
    ArrayList<String> type_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_perference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select Preference");
        setSupportActionBar(toolbar);

        GridView subject_gridview = (GridView) findViewById(R.id.subject_grid);
        GridView type_gridview = (GridView) findViewById(R.id.type_grid);
        final TextView subject_selected = (TextView) findViewById(R.id.subject_selected_tv);
        final TextView type_selected = (TextView) findViewById(R.id.type_selected_tv);


        subject_gridview.setAdapter(new GridViewAdapter(this, subjects_array));
        type_gridview.setAdapter(new GridViewAdapter(this, type_array));


        subject_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView subject_grid_title = (TextView) v.findViewById(R.id.circle_item_tv);
                RelativeLayout subject_grid_circle = (RelativeLayout) v.findViewById(R.id.circle_item_rl);

                if (subject_list.contains(subjects_array[position])){
                    subject_list.remove(subjects_array[position]);
                    subject_grid_title.setTextColor(getResources().getColor(R.color.colorAccent));
                    subject_grid_circle.setBackgroundDrawable(getDrawable(R.drawable.unselected_grid_circle));
                } else {
                    subject_list.add(subjects_array[position]);
                    subject_grid_title.setTextColor(getResources().getColor(R.color.colorWhite));
                    subject_grid_circle.setBackgroundDrawable(getDrawable(R.drawable.selected_grid_circle));
                }

                if (subject_list.size()>0){
                    subject_selected.setVisibility(View.VISIBLE);
                    subject_selected.setText(String.valueOf(subject_list.size())+" selected");
                } else {
                    subject_selected.setVisibility(View.GONE);
                }
            }
        });

        type_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView type_grid_title = (TextView) v.findViewById(R.id.circle_item_tv);
                RelativeLayout type_grid_circle = (RelativeLayout) v.findViewById(R.id.circle_item_rl);

                if (type_list.contains(type_array[position])){
                    type_list.remove(type_array[position]);
                    type_grid_title.setTextColor(getResources().getColor(R.color.colorAccent));
                    type_grid_circle.setBackgroundDrawable(getDrawable(R.drawable.unselected_grid_circle));

                } else {
                    type_list.add(type_array[position]);
                    type_grid_title.setTextColor(getResources().getColor(R.color.colorWhite));
                    type_grid_circle.setBackgroundDrawable(getDrawable(R.drawable.selected_grid_circle));
                }

                if (type_list.size()>0){
                    type_selected.setVisibility(View.VISIBLE);
                    type_selected.setText(String.valueOf(type_list.size())+" selected");
                } else {
                    type_selected.setVisibility(View.GONE);
                }
            }
        });
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, CreatePreferenceActivity.class);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_done_preference, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_onboarding_done) {
            startActivity(DashboardActivity.getIntent(this));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
