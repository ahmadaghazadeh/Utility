package com.xomorod.utility.splash;


import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.xomorod.utility.R;


/**
 * Created by 890683 on 1/19/2016.
 */
public class SplashModelView extends BaseObservable implements ISplashView {
    private  String appName;
    private Drawable appLogo;
    @Nullable
    private int color = R.color.md_grey_300 ;


    public SplashModelView( String appName ,Drawable appLogo )
    {
        this.appName=appName;
        this.appLogo=appLogo;
    }

    @Override
    public void setAppName(String appName) {
        this.appName=appName;
    }

    @Override
    public void setAppLogo(Drawable appLogo) {
        this.appLogo=appLogo;
    }

    @Override
    public void setColor(int color) {
        this.color=color;
    }

    public String getAppName() {
        return appName;
    }

    public Drawable getAppLogo() {
        return appLogo;
    }

    public int getColor() {
        return color;
    }
}
