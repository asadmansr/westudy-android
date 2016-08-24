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

import com.projectg.westudy.HomeDashboard.CreateProfileActivity;
import com.projectg.westudy.R;
import com.projectg.westudy.Utility.ErrorLayout;


public class NewAccountRegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private NewAccountRegisterViewHolder mNewAccountRegisterViewHolder;
    private String email_address_str = "";
    private String password_str = "";
    private String confirm_passwork_str = "";
    private ColorStateList referenceColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_acc_register);
        controlInitialization();
        init();
    }

    private void controlInitialization(){
        mNewAccountRegisterViewHolder = new NewAccountRegisterViewHolder(findViewById(R.id.relative_container),this);
    }

    private class NewAccountRegisterViewHolder{
        private TextView mAlreadyMemberTv;
        private TextView mEmailAddressTv, mPasswordTv, mConfirmPasswordTv;
        private EditText mEmailAddressEt, mPasswordEt, mConfirmPasswordEt;
        private RelativeLayout mSignInLayout;
        private ErrorLayout mErrorLayout;

        private NewAccountRegisterViewHolder(View view, View.OnClickListener listener){
            mErrorLayout = new ErrorLayout(view, NewAccountRegisterActivity.this);
            mAlreadyMemberTv = (TextView) view.findViewById(R.id.already_member_tv);
            mSignInLayout = (RelativeLayout) view.findViewById(R.id.sign_in_rl);

            mEmailAddressTv = (TextView) view.findViewById(R.id.email_tv);
            mPasswordTv = (TextView) view.findViewById(R.id.regstr_pword_tv);
            mConfirmPasswordTv = (TextView) view.findViewById(R.id.regstr_con_pword_tv);
            mEmailAddressEt = (EditText) view.findViewById(R.id.regstr_email_et);
            mPasswordEt = (EditText) view.findViewById(R.id.regstr_pword_et);
            mConfirmPasswordEt = (EditText) view.findViewById(R.id.regstr_con_pword_et);

            referenceColor = mEmailAddressTv.getTextColors();

            mSignInLayout.setOnClickListener(listener);
            mAlreadyMemberTv.setOnClickListener(listener);
        }
    }

    private void init(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void clearErrorMessages(TextView email_textview, TextView pass_textview, TextView confirm_pass_textview){
        email_textview.setTextColor(referenceColor);
        pass_textview.setTextColor(referenceColor);
        confirm_pass_textview.setTextColor(referenceColor);
    }

    private void getValues(){
        email_address_str = mNewAccountRegisterViewHolder.mEmailAddressEt.getText().toString();
        password_str = mNewAccountRegisterViewHolder.mPasswordEt.getText().toString();
        confirm_passwork_str = mNewAccountRegisterViewHolder.mConfirmPasswordEt.getText().toString();
    }

    private boolean validateSignupFields(){
        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email_address_str).matches())){
            mNewAccountRegisterViewHolder.mEmailAddressTv.setTextColor(getResources().getColor(R.color.error_layout));
            mNewAccountRegisterViewHolder.mErrorLayout.showError("Invalid email format");
            return false;
        } else if (!(email_address_str.contains("mcmaster"))){
            mNewAccountRegisterViewHolder.mEmailAddressTv.setTextColor(getResources().getColor(R.color.error_layout));
            mNewAccountRegisterViewHolder.mErrorLayout.showError("Seems like we are not available in your area yet. Stay tuned!");
            return false;
        } else if (password_str.length() < 6) {
            mNewAccountRegisterViewHolder.mPasswordTv.setTextColor(getResources().getColor(R.color.error_layout));
            mNewAccountRegisterViewHolder.mErrorLayout.showError("Password must be at least six characters");
            return false;
        } else if (confirm_passwork_str.length() < 6){
            mNewAccountRegisterViewHolder.mConfirmPasswordTv.setTextColor(getResources().getColor(R.color.error_layout));
            mNewAccountRegisterViewHolder.mErrorLayout.showError("Password must be at least six characters");
            return false;
        } else if (!(password_str.equals(confirm_passwork_str))){
            mNewAccountRegisterViewHolder.mPasswordTv.setTextColor(getResources().getColor(R.color.error_layout));
            mNewAccountRegisterViewHolder.mConfirmPasswordTv.setTextColor(getResources().getColor(R.color.error_layout));
            mNewAccountRegisterViewHolder.mErrorLayout.showError("Password do not match");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.sign_in_rl:
                clearErrorMessages(mNewAccountRegisterViewHolder.mEmailAddressTv,mNewAccountRegisterViewHolder.mPasswordTv,mNewAccountRegisterViewHolder.mConfirmPasswordTv);
                getValues();
                if (validateSignupFields()) {
                    startActivity(CreateProfileActivity.getIntent(NewAccountRegisterActivity.this));
                    finish();
                }
                break;

            case R.id.already_member_tv:
                startActivity(LoginActivity.getIntent(NewAccountRegisterActivity.this));
                finish();
                break;
        }
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, NewAccountRegisterActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
