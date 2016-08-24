package com.projectg.westudy.HomeDashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.projectg.westudy.R;
import com.projectg.westudy.Utility.ErrorLayout;

//TODO: Implement Profile Pic, Name, School (university), Program and Year fields
//TODO: Implement FB or Hangout API for chat service

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private CreateProfileViewHolder mCreateProfileViewHolder;
    private String full_name_str = "";
    private String university_str = "";
    private String program_department_str = "";
    private String program_year_str = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Create Profile");
        setSupportActionBar(toolbar);
        controlInitialization();

    }

    private void controlInitialization(){
        mCreateProfileViewHolder = new CreateProfileViewHolder(findViewById(R.id.relative_container),this);
    }

    private class CreateProfileViewHolder{
        private FloatingActionButton mFloatingBtn;
        private EditText mFullNameEt, mUniversityEt, mProgramDepartmentEt, mProgramYearEt;
        private RelativeLayout mAPIBtn;
        private ErrorLayout mErrorLayout;

        private CreateProfileViewHolder(View view, View.OnClickListener listener){
            mErrorLayout = new ErrorLayout(view, CreateProfileActivity.this);
            mFloatingBtn = (FloatingActionButton) view.findViewById(R.id.edit_pf_pic_fab);
            mAPIBtn = (RelativeLayout) view.findViewById(R.id.create_profile_continue_rl);

            mFullNameEt = (EditText) view.findViewById(R.id.create_pf_name_tv);
            mUniversityEt = (EditText) view.findViewById(R.id.create_pf_uni_et);
            mProgramDepartmentEt = (EditText) view.findViewById(R.id.create_pf_prgrm_et);
            mProgramYearEt = (EditText) view.findViewById(R.id.create_pf_prgrm_tv);

            mFloatingBtn.setOnClickListener(listener);
            mAPIBtn.setOnClickListener(listener);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_pf_pic_fab:
                Toast toast_fab = Toast.makeText(getApplicationContext(), "TODO: Upload Pic", Toast.LENGTH_SHORT);
                toast_fab.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast_fab.show();
                break;

            case R.id.create_profile_continue_rl:
                Toast toast_api = Toast.makeText(getApplicationContext(), "TODO: API Integration", Toast.LENGTH_SHORT);
                toast_api.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast_api.show();
                break;
        }
    }

    private void getValues(){
        full_name_str = mCreateProfileViewHolder.mFullNameEt.getText().toString();
        university_str = mCreateProfileViewHolder.mUniversityEt.getText().toString();
        program_department_str = mCreateProfileViewHolder.mProgramDepartmentEt.getText().toString();
        program_year_str = mCreateProfileViewHolder.mProgramYearEt.getText().toString();
    }

    private boolean validateProfileFields(){
        if (full_name_str.length() == 0){
            mCreateProfileViewHolder.mErrorLayout.showError("Full name is required");
            return false;
        } else if (university_str.length() == 0){
            mCreateProfileViewHolder.mErrorLayout.showError("University is required");
            return false;
        } else if (program_department_str.length() == 0){
            mCreateProfileViewHolder.mErrorLayout.showError("Program department is required");
            return false;
        } else if (program_year_str.length() == 0){
            mCreateProfileViewHolder.mErrorLayout.showError("Program year is required");
            return false;
        }

        return true;
    }


    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, CreateProfileActivity.class);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_next_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_next) {
            getValues();
            if (validateProfileFields()) {
                startActivity(CreatePreferenceActivity.getIntent(CreateProfileActivity.this));
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}