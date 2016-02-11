package com.xomorod.utility.section.market;

import java.util.List;

/**
 * Created by Ahmad on 2/11/2016.
 */
public class CategoriesData {

    private int totalCount;
    private List<CategoryData> cats;

    public CategoriesData(int totalCount, List<CategoryData> cats) {
        this.totalCount = totalCount;
        this.cats = cats;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<CategoryData> getCats() {
        return cats;
    }

    public void setCats(List<CategoryData> cats) {
        this.cats = cats;
    }
}
