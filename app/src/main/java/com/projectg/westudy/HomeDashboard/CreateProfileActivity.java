package com.projectg.westudy.HomeDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.projectg.westudy.R;

/**
 * Created by Moiz on 8/20/2016.
 */
public class CreateProfileActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);

        //TODO: Implement Profile Pic, Name, School (university), Program and Year fields
        //Also to consider FB or Hangout API for chat service enablement

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.edit_pf_pic_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "TODO: upload pic", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }
        });

        RelativeLayout temp = (RelativeLayout) findViewById(R.id.create_profile_continue_rl);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreatePreferenceActivity.getIntent(CreateProfileActivity.this));
            }
        });
    }
}


