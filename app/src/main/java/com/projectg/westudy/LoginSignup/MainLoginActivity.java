package com.projectg.westudy.LoginSignup;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.projectg.westudy.R;

public class MainLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private MainLoginViewHolder mMainLoginViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        controlInitialization();
    }

    private void controlInitialization(){
        mMainLoginViewHolder = new MainLoginViewHolder(findViewById(R.id.relative_container),this);
    }

    private class MainLoginViewHolder{
        private LinearLayout mSignInLayout;
        private RelativeLayout mJoinNowLayout;

        private MainLoginViewHolder(View view, View.OnClickListener listener){
            mSignInLayout = (LinearLayout) view.findViewById(R.id.sign_in_ll);
            mJoinNowLayout = (RelativeLayout) view.findViewById(R.id.join_now_rl);

            mSignInLayout.setOnClickListener(listener);
            mJoinNowLayout.setOnClickListener(listener);
        }
    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.sign_in_ll:
                startActivity(LoginActivity.getIntent(MainLoginActivity.this));
                break;

            case R.id.join_now_rl:
                Snackbar.make(view, "ToDo: join now", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
