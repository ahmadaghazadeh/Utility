package com.xomorod.utility.activities.common;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xomorod.utility.R;
import com.xomorod.utility.adapters.FragmentMoreAppAdapter;
import com.xomorod.utility.logic.Project;
import com.xomorod.utility.os.network.IProductsAPI;
import com.xomorod.utility.os.network.ProductAPIGenerator;
import com.xomorod.utility.section.market.CategoriesData;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreApplicationActivity extends BaseNoActionBarActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_application);

        initToolbar();
        initInstances();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception ex)
        {
            ex.getMessage();
        }

    }

    private void initInstances() {

      final   MaterialDialog materialDialog=Project.getMaterialDialogProgress(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        IProductsAPI client = ProductAPIGenerator.createService(IProductsAPI.class);
        try {
            Call<CategoriesData> call =client.getCategory();
            call.enqueue(new Callback<CategoriesData>() {
                @Override
                public void onResponse(Call<CategoriesData> call, Response<CategoriesData> response) {
                   viewPager.setAdapter(new FragmentMoreAppAdapter(getSupportFragmentManager(),
                            MoreApplicationActivity.this,response.body()));
                    // Give the TabLayout the ViewPager
                    TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_categories);
                    tabLayout.setupWithViewPager(viewPager);
                    materialDialog.cancel();

                }

                @Override
                public void onFailure(Call<CategoriesData> call, Throwable t) {
                    Project.Toast(MoreApplicationActivity.this, t.getMessage());
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
             return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
