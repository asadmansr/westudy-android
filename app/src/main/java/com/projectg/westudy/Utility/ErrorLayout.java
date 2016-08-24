package com.projectg.westudy.Utility;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectg.westudy.R;


public class ErrorLayout {
    private TextView mErrorStatus;
    private RelativeLayout mErrorLayout;
    private Context mContext;
    private View mView;

    public ErrorLayout(View view, Context context){
        mView = view;
        mContext = context;
        mErrorStatus = (TextView) mView.findViewById(R.id.error_status);
        mErrorLayout = (RelativeLayout) mView.findViewById(R.id.error_container);

        mErrorLayout.setVisibility(View.GONE);
    }

    public void showError(String error_status){
        mErrorLayout.setVisibility(View.VISIBLE);
        mErrorStatus.setText(error_status);

        Animation slide = AnimationUtils.loadAnimation(mContext.getApplicationContext(), R.anim.slide_top);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animation slide = AnimationUtils.loadAnimation(mContext.getApplicationContext(), R.anim.slide_bottom);
                        mErrorLayout.startAnimation(slide);
                        mErrorLayout.setVisibility(View.GONE);
                    }
                }, 2000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mErrorLayout.startAnimation(slide);
    }
}
