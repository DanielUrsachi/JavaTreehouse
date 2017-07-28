package com.teamtreehouse.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Song {
    //schimbat nivelul de acces pentru folosirea in SongBook
    protected String mArtist;
    protected String mTitle;
    protected String mVideoUrl;

    public Song(String artist, String title, String videoUrl){
        mArtist = artist;
        mTitle = title;
        mVideoUrl = videoUrl;
    }

    public String getmArtist() {
        return mArtist;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmVideoUrl() {
        return mVideoUrl;
    }

    @Override
    public String toString() {
        return String.format("Song: " + mTitle + " by " + mArtist);
    }


}
