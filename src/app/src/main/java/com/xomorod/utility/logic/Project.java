package aghazadeh.ahmad.utility.logic;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Hashtable;

/**
 * Created by 890683 on 1/7/2016.
 */
public class Project {
    private static Hashtable<String, Typeface> typeFaceList;
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
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
            AssetManager mgr = context.getAssets();
            Typeface tf = Typeface.createFromAsset(mgr, fontPath);
            typeFaceList.put(fontPath, tf);
        }

        return typeFaceList.get(fontPath);
    }
}
