package com.projectg.westudy.Options;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projectg.westudy.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
