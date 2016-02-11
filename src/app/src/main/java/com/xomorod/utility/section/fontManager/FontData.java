package com.xomorod.utility.section.fontManager;

/**
 * Created by 890683 on 1/24/2016.
 */
public class FontData {

    private String FontName;

    private String Sample ;

    private String fontTitle ;
    private int color ;


    public FontData(String FontName,String fontTitle, String Sample,int color) {
        this.FontName = FontName;
        this.Sample = Sample;
        this.fontTitle = fontTitle;
        this.color=color;
    }


    public String getFontName() {
        return FontName;
    }

    public void setFontName(String fontName) {
        FontName = fontName;
    }

    public String getSample() {
        return Sample;
    }

    public void setSample(String sample) {
        Sample = sample;
    }

    public String getFontTitle() {
        return fontTitle;
    }

    public void setFontTitle(String fontTitle) {
        this.fontTitle = fontTitle;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
