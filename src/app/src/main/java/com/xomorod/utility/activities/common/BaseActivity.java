package com.xomorod.utility.activities.common;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.xomorod.utility.R;
import com.xomorod.utility.business.MessageEvent;
import com.xomorod.utility.logic.C;
import com.xomorod.utility.logic.Project;

import de.greenrobot.event.EventBus;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         updateTheme();
    }
     public void updateTheme() {

        String color= Project.getTheme(getApplication());
        int themeID=getResources().getIdentifier("Theme."+color,"style", getPackageName());
        if(themeID==0)
            themeID= R.style.Theme_blue;
        setTheme(themeID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            int colorId=  getResources().getIdentifier("primaryColorDark_"+color,"color",getPackageName());
            getWindow().setStatusBarColor(getResources().getColor(colorId));
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEvent(MessageEvent event){
        if(event.CheckEvent(C.Action.RefreshActivity))
        {
            refreshTheme();
        }
     }

    private void refreshTheme()
    {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        refreshTheme();
    }
}
