package com.projectg.westudy.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectg.westudy.R;

/**
 * Created by Moiz on 8/19/2016.
 */
public class NewAccountRegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private MainLoginViewHolder mMainLoginViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_acc_register);
        controlInitialization();
    }

    private void controlInitialization(){
        mMainLoginViewHolder = new MainLoginViewHolder(findViewById(R.id.relative_container),this);
    }

    private class MainLoginViewHolder{
        private TextView mAlreadyMemberTv;
        private RelativeLayout mSignInLayout;

        private MainLoginViewHolder(View view, View.OnClickListener listener){
            mAlreadyMemberTv = (TextView) view.findViewById(R.id.already_member_tv);
            mSignInLayout = (RelativeLayout) view.findViewById(R.id.sign_in_rl);

            mSignInLayout.setOnClickListener(listener);
            mAlreadyMemberTv.setOnClickListener(listener);
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.sign_in_rl:
                Snackbar.make(view, "TODO: Home Page", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.already_member_tv:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainLoginActivity.class);
        startActivity(intent);;
    }
}
