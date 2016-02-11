package com.xomorod.utility.section.market;

/**
 * Created by 890683 on 1/24/2016.
 */
public class CategoryData {


    private String catName;

    private String url;


    public CategoryData(String CatName, String Url) {
        this.catName = CatName;
        this.url = Url;
     }


    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
