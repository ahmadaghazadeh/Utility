package aghazadeh.ahmad.utility.binding;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by 890683 on 1/19/2016.
 */
public abstract class SimpleTextWatcher implements TextWatcher {

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        onTextChanged(s.toString());
    }

    public abstract void onTextChanged(String newValue);
}
