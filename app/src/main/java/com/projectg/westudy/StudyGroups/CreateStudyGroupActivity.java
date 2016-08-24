package com.projectg.westudy.StudyGroups;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.projectg.westudy.R;

/***AddEventsActivity.java
 * -----------------------
 * This activity will prompt the user with required information to start a new study group event
 *
 */

public class CreateStudyGroupActivity extends AppCompatActivity implements View.OnClickListener{

    private MainLoginViewHolder mMainViewHolder;
    int REQUEST_CODE = 123;
    int RESULT_CODE = 321;
    Double[] returned_location = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        //Create Spinner for Study Session Types
        Spinner spinner = (Spinner) findViewById(R.id.study_types_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.session_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        controlInitialization();

    }

    private void controlInitialization(){
        mMainViewHolder = new MainLoginViewHolder(findViewById(R.id.create_event_rl),this);
    }

    private class MainLoginViewHolder{
        private RelativeLayout mCreateEvent;

        private MainLoginViewHolder(View view, View.OnClickListener listener){
            mCreateEvent = (RelativeLayout) view.findViewById(R.id.create_event_rl);
            mCreateEvent.setOnClickListener(listener);
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.create_event_rl:
                //TODO: Have user turn on navigation to send their own location as opposed to "result"
                String[] result = {"43.261072", "-79.922574"};
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", result);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
                break;

            //add more cases here in future

        }
    }


    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, CreateStudyGroupActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
