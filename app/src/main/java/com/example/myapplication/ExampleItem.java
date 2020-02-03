package com.example.myapplication;

public class ExampleItem {

    public String imgUrl ;
    private String mCreator;
    private String song;

    public ExampleItem(String imgUrl, String mCreator, String song){

        this.imgUrl = imgUrl;
        this.mCreator = mCreator;
        this.song = song;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public void setmCreator(String mCreator) {
        this.mCreator = mCreator;
    }

    public String getmLikes() {
        return this.song;
    }

    public void setmLikes(String song) {
        this.song = song;
    }
}
