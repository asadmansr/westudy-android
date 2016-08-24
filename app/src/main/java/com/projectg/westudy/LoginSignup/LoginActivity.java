package com.projectg.westudy.LoginSignup;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectg.westudy.HomeDashboard.DashboardActivity;
import com.projectg.westudy.R;
import com.projectg.westudy.Utility.ErrorLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginViewHolder mLoginViewHolder;
    private String email_address_str = "";
    private String password_str = "";
    private ColorStateList referenceColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        controlInitialization();
        init();
    }

    private void controlInitialization(){
        mLoginViewHolder = new LoginViewHolder(findViewById(R.id.relative_container),this);
    }

    private class LoginViewHolder{
        private EditText mEmailAddressEt, mPasswordEt;
        private TextView mEmailAddressTv, mPasswordTv;
        private TextView mForgotPasswordTv, mCreateAccountTv;
        private RelativeLayout mSignInRl, mRelativeContainer;
        private ErrorLayout mErrorLayout;

        private LoginViewHolder(View view, View.OnClickListener listener){
            mErrorLayout = new ErrorLayout(view, LoginActivity.this);
            mEmailAddressEt = (EditText) view.findViewById(R.id.email_et);
            mPasswordEt = (EditText) view.findViewById(R.id.pass_et);
            mForgotPasswordTv = (TextView) view.findViewById(R.id.forgot_password_tv);
            mCreateAccountTv = (TextView) view.findViewById(R.id.already_member_tv);
            mSignInRl = (RelativeLayout) view.findViewById(R.id.sign_in_rl);
            mRelativeContainer = (RelativeLayout) view.findViewById(R.id.relative_container);

            mEmailAddressTv = (TextView) view.findViewById(R.id.email_tv);
            mPasswordTv = (TextView) view.findViewById(R.id.regstr_pword_tv);
            referenceColor = mEmailAddressTv.getTextColors();

            mRelativeContainer.setOnClickListener(listener);
            mForgotPasswordTv.setOnClickListener(listener);
            mCreateAccountTv.setOnClickListener(listener);
            mSignInRl.setOnClickListener(listener);
        }
    }

    private void init(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.forgot_password_tv:
                //Snackbar.make(view, "ToDo: forgot password", Snackbar.LENGTH_SHORT).show();
                startActivity(DashboardActivity.getIntent(LoginActivity.this));
                finish();
                break;

            case R.id.already_member_tv:
                startActivity(NewAccountRegisterActivity.getIntent(LoginActivity.this));
                finish();
                break;

            case R.id.sign_in_rl:
                clearErrorMessages(mLoginViewHolder.mEmailAddressTv,mLoginViewHolder.mPasswordTv);
                getValues();
                if (validateLoginFields()) {
                    startActivity(DashboardActivity.getIntent(LoginActivity.this));
                    finish();
                }
                break;
        }
    }

    private void clearErrorMessages(TextView email_textview, TextView pass_textview){
        email_textview.setTextColor(referenceColor);
        pass_textview.setTextColor(referenceColor);
    }

    private void getValues(){
        email_address_str = mLoginViewHolder.mEmailAddressEt.getText().toString();
        password_str = mLoginViewHolder.mPasswordEt.getText().toString();
    }

    private boolean validateLoginFields(){
        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email_address_str).matches())){
            mLoginViewHolder.mEmailAddressTv.setTextColor(getResources().getColor(R.color.error_layout));
            mLoginViewHolder.mErrorLayout.showError("Invalid email format");
            return false;
            //} else if (!(email_address_str.contains("mcmaster"))){
            //    mLoginViewHolder.mEmailAddressTv.setTextColor(getResources().getColor(R.color.error_layout));
            //    mLoginViewHolder.mErrorLayout.showError("Seems like we are not available in your area yet. Stay tuned!");
            //    return false;
        } else if (password_str.length() < 6){
            mLoginViewHolder.mPasswordTv.setTextColor(getResources().getColor(R.color.error_layout));
            mLoginViewHolder.mErrorLayout.showError("Password must be at least six characters");
            return false;
        } else if (!((email_address_str.contains("admin@mcmaster.ca")) & (password_str.contains("admintest")))){
            mLoginViewHolder.mEmailAddressTv.setTextColor(getResources().getColor(R.color.error_layout));
            mLoginViewHolder.mPasswordTv.setTextColor(getResources().getColor(R.color.error_layout));
            mLoginViewHolder.mErrorLayout.showError("Your credentials do not match our records");
            return false;
        }
        return true;
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
