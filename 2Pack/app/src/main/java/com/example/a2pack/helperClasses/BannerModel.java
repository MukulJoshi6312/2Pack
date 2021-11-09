package com.example.a2pack.helperClasses;

public class BannerModel {

    private String bannerTag;
    private int bannerImage;

    public BannerModel(String bannerTag, int bannerImage) {
        this.bannerTag = bannerTag;
        this.bannerImage = bannerImage;
    }

    public String getBannerTag() {
        return bannerTag;
    }

    public void setBannerTag(String bannerTag) {
        this.bannerTag = bannerTag;
    }

    public int getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(int bannerImage) {
        this.bannerImage = bannerImage;
    }
}
