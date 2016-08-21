package com.projectg.westudy.HomeDashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.projectg.westudy.R;

/**
 * Created by Moiz on 8/20/2016.
 */
public class CreateProfileActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile_stage2);

        //TODO: Implement Profile Pic, Name, School (university), Program and Year fields
        //Also to consider FB or Hangout API for chat service enablement

        Button temp = (Button) findViewById(R.id.btn);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreatePreferenceActivity.getIntent(CreateProfileActivity.this));
            }
        });
    }
}
