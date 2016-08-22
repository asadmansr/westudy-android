package com.projectg.westudy.Events;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projectg.westudy.R;

/***AddEventsActivity.java
 * -----------------------
 * This activity will prompt the user with required information to start a new study group event
 *
 */

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, AddEventActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
