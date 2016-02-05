package com.xomorod.utility.activities.splash;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.xomorod.utility.R;
import com.xomorod.utility.databinding.ActivitySplashBinding;


/**
 * Created by 890683 on 1/19/2016.
 */

public  abstract  class SplashPresenterActivity extends Activity implements  ISplashHandler {


    ActivitySplashBinding binding;
    public SplashModelView splashModelView;
    public Intent mainActivity;
    public int timeStartMainActivity =3500;
    private boolean isRunStop=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        binding =
                DataBindingUtil.setContentView(this, R.layout.activity_splash);

        setData(splashModelView,mainActivity);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isRunStop)
                {
                    startActivity(mainActivity);
                }
            }
        }, timeStartMainActivity);

    }

    @Override
    protected void onStop() {
        isRunStop=true;
        super.onStop();
    }

    @Override
    public void onClickSplash(View view) {
        onClickSplash();
    }
    public abstract void onClickSplash();

    public abstract void setData(SplashModelView splashModelView,Intent mainActivity);

}
