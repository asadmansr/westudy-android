package com.projectg.westudy.Profile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projectg.westudy.R;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, EditProfileActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
