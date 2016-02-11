package com.xomorod.utility.section.market;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xomorod.utility.R;
import com.xomorod.utility.business.CardViewType;
import com.xomorod.utility.databinding.CardAppBinding;

import jp.satorufujiwara.binder.recycler.RecyclerBinder;


public class CardApp extends RecyclerBinder<CardViewType> {
    private AppData appData;
    public CardApp(Activity activity, AppData appData) {
        super(activity, CardViewType.App);
        this.appData = appData;
    }
   @Override
    public int layoutResId() {
        return R.layout.card_app;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View parent) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutResId(), (ViewGroup) parent, false);
        BindingHolder holder = new BindingHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        final BindingHolder holder = (BindingHolder) viewHolder;
         holder.binding.setData(appData);
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        CardAppBinding binding;
        public BindingHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }
}
