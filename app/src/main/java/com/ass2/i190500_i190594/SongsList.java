package com.ass2.i190500_i190594;

import android.graphics.Bitmap;

public class SongsList {

    String Title, artist;

    Bitmap artistPic, crossPic;

    public SongsList(String t, String ar,Bitmap artistPic, Bitmap crossPic){

        this.Title=t;
        this.artist=ar;
        this.artistPic=artistPic;
        this.crossPic=crossPic;
    }

    public SongsList(){

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Bitmap getArtistPic() {
        return artistPic;
    }

    public void setArtistPic(Bitmap artistPic) {
        this.artistPic = artistPic;
    }

    public Bitmap getCrossPic() {
        return crossPic;
    }

    public void setCrossPic(Bitmap crossPic) {
        this.crossPic = crossPic;
    }
}
