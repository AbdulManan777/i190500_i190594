package com.ass2.i190500_i190594;

import android.graphics.Bitmap;

public class SongsListCheckBox {

    private boolean isSelected;
    String Title, artist;

    Bitmap artistPic;

    public SongsListCheckBox(){

    }

    public SongsListCheckBox(boolean isSelected, String title, String artist, Bitmap artistPic) {
        this.isSelected = isSelected;
        Title = title;
        this.artist = artist;
        this.artistPic = artistPic;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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
}
