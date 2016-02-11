package com.xomorod.utility.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xomorod.utility.fragments.MoreAppFragment;
import com.xomorod.utility.section.market.CategoriesData;

/**
 * Created by Ahmad on 2/7/2016.
 */
public class FragmentMoreAppAdapter extends FragmentPagerAdapter {


    private CategoriesData categoriesData;
    private Context context;



    public FragmentMoreAppAdapter(FragmentManager fm, final Context context,CategoriesData categoriesData) {
        super(fm);
        this.context = context;
        this.categoriesData=categoriesData;

    }

    @Override
    public int getCount() {
        return categoriesData.getTotalCount();
    }

    @Override
    public Fragment getItem(int position) {
        return MoreAppFragment.newInstance(categoriesData.getCats().get(position).getUrl());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return categoriesData.getCats().get(position).getCatName();
    }
}
