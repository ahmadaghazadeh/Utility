package com.xomorod.utility.business;

import jp.satorufujiwara.binder.Section;

/**
 * Created by 890683 on 1/10/2016.
 */
public enum TitleSection implements Section {
    Default,
    Line;
    @Override
    public int position() {
        return ordinal();
    }
}
