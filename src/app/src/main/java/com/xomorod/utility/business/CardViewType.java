package com.xomorod.utility.business;

/**
 * Created by 890683 on 1/24/2016.
 */
import jp.satorufujiwara.binder.ViewType;

public enum CardViewType implements ViewType {

    Theme,
    Empty;
    @Override
    public int viewType() {
        return ordinal();
    }

}