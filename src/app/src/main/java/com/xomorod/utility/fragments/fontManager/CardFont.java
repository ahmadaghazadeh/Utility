package com.xomorod.utility.fragments.fontManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xomorod.utility.R;
import com.xomorod.utility.business.CardViewType;
import com.xomorod.utility.business.MessageEvent;
import com.xomorod.utility.data.PreferenceManager;
import com.xomorod.utility.databinding.CardFontBinding;
import com.xomorod.utility.logic.C;
import com.xomorod.utility.logic.Project;

import de.greenrobot.event.EventBus;
import jp.satorufujiwara.binder.recycler.RecyclerBinder;


public class CardFont extends RecyclerBinder<CardViewType> {
    private FontData fontData;
    public CardFont(Activity activity, FontData fontData) {
        super(activity, CardViewType.Theme);
        this.fontData = fontData;
    }
   @Override
    public int layoutResId() {
        return R.layout.card_font;
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
        holder.binding.setData(fontData);
        holder.binding.cardHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=holder.binding.cardHolder.getContext();
                Project.setFont(context, fontData.getFontName());
                Intent intent=new Intent(C.Action.RefreshActivity);
                EventBus.getDefault().post(new MessageEvent(intent));
                PreferenceManager pm=Project.getPref(view.getContext());
                pm.set(C.Fields.FontSelectedPos,position);
             }
        });

    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        CardFontBinding binding;
        public BindingHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }
}
