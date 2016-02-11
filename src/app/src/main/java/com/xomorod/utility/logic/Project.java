package com.xomorod.utility.logic;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.xomorod.utility.R;
import com.xomorod.utility.data.PreferenceManager;

import java.util.Hashtable;
import java.util.Locale;

/**
 * Created by 890683 on 1/7/2016.
 */
public class Project {
    private static Hashtable<String, Typeface> typeFaceList;

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    public static final Typeface getTypeFace(Context context, String fontPath) {
        if (typeFaceList == null)
            typeFaceList = new Hashtable<String, Typeface>();

        if (!typeFaceList.containsKey(fontPath)) {
            try {
                AssetManager mgr = context.getAssets();
                Typeface tf = Typeface.createFromAsset(mgr, fontPath);
                typeFaceList.put(fontPath, tf);
            } catch (Exception ex) {
                ex.getMessage();
            }

        }

        return typeFaceList.get(fontPath);
    }


    public static void setLanguage(Context context) {
        PreferenceManager pm = Project.getPref(context);
        String lng= pm.get(context.getString(R.string.prefs_language_key),"fa");
        setLanguage(context,lng);

    }
    public static void setLanguage(Context context,String lng)
    {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lng.toLowerCase());
        res.updateConfiguration(conf, dm);
    }


    public static void setFont(Context context, String font) {
        PreferenceManager pm = Project.getPref(context);
        pm.set(C.Fields.FontKey, font);
    }

    public static void setTheme(Context context, String theme) {
        PreferenceManager pm = Project.getPref(context);
        pm.set(C.Fields.ThemeKey, theme);
    }

    public static String getTheme(Context context) {
        PreferenceManager pm = Project.getPref(context);
        return pm.get(C.Fields.ThemeKey, "");
    }

    public static PreferenceManager getPref(Context context) {
        return new PreferenceManager(context);
    }
    public static void Toast(Context context,String str)
    {
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
    public static  MaterialDialog getMaterialDialogProgress(Context context)
    {
        GravityEnum gravity=GravityEnum.values()[ context.getResources().getInteger(R.integer.)];
        return new MaterialDialog.Builder(context)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .titleGravity(gravity)
                .contentGravity(gravity)
                .btnStackedGravity(gravity)
                .itemsGravity(gravity)
                .buttonsGravity(gravity)
                .progress(true, 0)
                .show();
    }
}
