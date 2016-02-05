package com.xomorod.utility.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xomorod.utility.R;
import com.xomorod.utility.data.PreferenceManager;
import com.xomorod.utility.logic.Project;


/**
 * Created by 890683 on 1/19/2016.
 */
public class BindingCustom {

    @BindingAdapter("addTextChangedListener")
    public static void addTextChangedListener(EditText view, SimpleTextWatcher simpleTextWatcher) {
        view.addTextChangedListener(simpleTextWatcher);
    }

    @BindingAdapter("android:background")
    public static void setBackgroundColor(View view, @NonNull int color) {

         view.setBackgroundColor(view.getResources().getColor(color));
    }

    @BindingAdapter("backgroundColor")
    public static void setBackgrandColor(View view, @Nullable String color) {
        int colorId= view.getResources().getIdentifier(color,"color",view.getContext().getPackageName());
        view.setBackgroundColor(view.getResources().getColor(colorId));
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("resId")
    public static void setImageDrawable(ImageView view, int resId) {
        view.setImageResource(resId);
    }

    @BindingAdapter("text_font")
    public static void setFont(TextView view, @Nullable String fontName) {
        String fontPath = "fonts/" + fontName ;
        view.setTypeface(Project.getTypeFace(view.getContext(), fontPath));
    }

    @BindingAdapter("is_app_default_Font")
    public static void setAppdefaultFont(TextView view, @Nullable Boolean isActive) {
        if(isActive)
        {
            Context context=view.getContext();
            PreferenceManager pm=Project.getPref(context);
            String font=pm.get(context.getString(R.string.prefs_font_key),"");
            if(!font.equals(""))
            {
                setFont(view,font);
            }
        }
     }


    @BindingAdapter({"imageUrl", "error", "paletteResId"})
    public static void loadImage(final ImageView view, String url, @Nullable Drawable error, @Nullable final int paletteResId) {
        com.squareup.picasso.Callback callback = new Callback() {
            @Override
            public void onSuccess() {
                Bitmap photo = Project.drawableToBitmap(view.getDrawable());
                Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        int mutedLight = palette.getMutedColor(view.getContext().getResources().getColor(android.R.color.white));
                        View paletteLayout = (view.getRootView()).findViewById(paletteResId);
                        if (paletteLayout != null) {
                            paletteLayout.setBackgroundColor(mutedLight);
                        }
                    }
                });
            }

            @Override
            public void onError() {
            }
        };
        Picasso.with(view.getContext()).load(url).error(error).into(view, callback);

    }
}