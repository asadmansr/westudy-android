package com.projectg.westudy.History;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projectg.westudy.R;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, HistoryActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
