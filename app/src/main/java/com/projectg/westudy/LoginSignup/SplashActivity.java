package com.projectg.westudy.LoginSignup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.projectg.westudy.R;

/**
 * Created by Moiz on 8/19/2016.
 */
public class SplashActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);

        // New Handler to start the Home-Activity and close this Splash-Screen after some seconds

        /* TODO: Add conditions for:
               - If new user
               - If returning user
               - If no user/pass history in app
        */

        //For now, it automatically assumes its a new user and goes to the MainLoginActivity
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this,MainLoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
