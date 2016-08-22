package com.projectg.westudy.Analytics;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projectg.westudy.R;

public class AnalyticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, AnalyticsActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
