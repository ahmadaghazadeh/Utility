package com.xomorod.utility.section.market;

/**
 * Created by 890683 on 1/24/2016.
 */
public class AppData {

    private String appName;

    private String imgUrl ;

    private String price ;


    public AppData(String appName, String imgUrl,String price ) {
        this.appName = appName;
        this.imgUrl = imgUrl;
        this.price = price;
     }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
