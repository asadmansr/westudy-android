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
import com.projectg.westudy.Utility.ErrorLayout;

import java.util.ArrayList;

public class CreatePreferenceActivity extends AppCompatActivity {

    private CreatePreferenceViewHolder mCreatePreferenceViewHolder;
    private final String[] subjects_array = new String[] {"Math", "Physics","Chem", "Bio", "Geog", "Ling","Eng", "French", "Soc", "Art","History", "Antro" };
    private final String[] type_array = new String[] {"Review Session", "Tutorial","Lab", "Quizzes"};
    private ArrayList<String> subject_list = new ArrayList<>();
    private ArrayList<String> type_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_preference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select Preference");
        setSupportActionBar(toolbar);
        controlInitialization();
    }

    private void controlInitialization(){
        mCreatePreferenceViewHolder = new CreatePreferenceViewHolder(findViewById(R.id.relative_container),this);
    }

    private class CreatePreferenceViewHolder{
        private GridView subjectGridView, typeGridView;
        private TextView subjectSelectedTv, typeSelectedTv;
        private ErrorLayout mErrorLayout;

        private CreatePreferenceViewHolder(View view, Context context){
            mErrorLayout = new ErrorLayout(view, context);
            subjectGridView = (GridView) view.findViewById(R.id.subject_grid);
            typeGridView = (GridView) view.findViewById(R.id.type_grid);
            subjectSelectedTv = (TextView) view.findViewById(R.id.subject_selected_tv);
            typeSelectedTv = (TextView) view.findViewById(R.id.type_selected_tv);

            subjectGridView.setAdapter(new GridViewAdapter(context, subjects_array));
            typeGridView.setAdapter(new GridViewAdapter(context, type_array));


            subjectGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        subjectSelectedTv.setVisibility(View.VISIBLE);
                        subjectSelectedTv.setText(String.valueOf(subject_list.size())+" selected");
                    } else {
                        subjectSelectedTv.setVisibility(View.GONE);
                    }
                }
            });

            typeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        typeSelectedTv.setVisibility(View.VISIBLE);
                        typeSelectedTv.setText(String.valueOf(type_list.size())+" selected");
                    } else {
                        typeSelectedTv.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private boolean validatePreferenceFields(){
        if (subject_list.size() == 0){
            mCreatePreferenceViewHolder.mErrorLayout.showError("Select at least one subject preference");
            return false;
        } else if (type_list.size() == 0){
            mCreatePreferenceViewHolder.mErrorLayout.showError("Select at least one type preference");
            return false;
        }

        return true;
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, CreatePreferenceActivity.class);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done_preference, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_done) {
            if (validatePreferenceFields()) {
                startActivity(DashboardActivity.getIntent(this));
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
