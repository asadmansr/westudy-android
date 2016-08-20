package com.projectg.westudy.LoginSignup;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectg.westudy.HomeDashboard.HomeActivity;
import com.projectg.westudy.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginViewHolder mLoginViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        controlInitialization();
    }

    private void controlInitialization(){
        mLoginViewHolder = new LoginViewHolder(findViewById(R.id.relative_container),this);
    }

    private class LoginViewHolder{
        private EditText mEmailAddressEt, mPasswordEt;
        private TextView mForgotPasswordTv, mCreateAccountTv;
        private RelativeLayout mSignInRl;

        private LoginViewHolder(View view, View.OnClickListener listener){
            mEmailAddressEt = (EditText) view.findViewById(R.id.name_et);
            mPasswordEt = (EditText) view.findViewById(R.id.pass_et);
            mForgotPasswordTv = (TextView) view.findViewById(R.id.forgot_password_tv);
            mCreateAccountTv = (TextView) view.findViewById(R.id.already_member_tv);
            mSignInRl = (RelativeLayout) view.findViewById(R.id.sign_in_rl);


            mForgotPasswordTv.setOnClickListener(listener);
            mCreateAccountTv.setOnClickListener(listener);
            mSignInRl.setOnClickListener(listener);
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.forgot_password_tv:
                Snackbar.make(view, "ToDo: forgot password", Snackbar.LENGTH_SHORT).show();
                break;

            case R.id.already_member_tv:
                Intent intent = new Intent(this, NewAccountRegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.sign_in_rl:
                Intent home_intent = new Intent(this, HomeActivity.class);
                startActivity(home_intent);
                break;
        }
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
