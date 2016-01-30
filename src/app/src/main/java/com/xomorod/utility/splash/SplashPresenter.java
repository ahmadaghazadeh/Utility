package com.xomorod.utility.splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xomorod.utility.R;
import com.xomorod.utility.databinding.FragmentSplashBinding;


/**
 * Created by 890683 on 1/19/2016.
 */

public  abstract  class SplashPresenter extends Fragment implements  ISplashHandler {


    FragmentSplashBinding binding;
    public  SplashModelView splashModelView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_splash,
                container,
                false);
        splashModelView = new SplashModelView();

        return binding.getRoot();
    }


    @Override
    public void onClickSplash(View view) {
        onClickSplash();
    }

    public abstract void onClickSplash();
}
