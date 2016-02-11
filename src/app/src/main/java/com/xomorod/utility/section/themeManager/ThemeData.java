package com.xomorod.utility.section.themeManager;

/**
 * Created by 890683 on 1/24/2016.
 */
public class ThemeData {

    private String themeTitle;

    private String color ;

    private int selectedColor ;

    public ThemeData(String themeTitle, String color,int selectedColor ) {
        this.themeTitle = themeTitle;
        this.color = color;
        this.selectedColor = selectedColor;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public String getColor() {
        return color.toLowerCase().replaceAll(" ", "_");
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrimary() {
        return "primaryColor_"+getColor();
    }

    public String getPrimaryDark() {
        return "primaryColorDark_"+getColor();
    }


    public String getPrimaryAccent() {
        return "primaryAccent_"+getColor();
    }

    public String getBackgroundColor() {
        return "primaryColorDark_"+getColor();
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }
}
