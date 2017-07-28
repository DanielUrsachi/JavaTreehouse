package com.teamtreehouse.module;

import java.util.*;

public class SongBook {
    private List<Song> mSongs;

    public SongBook(){
        mSongs = new ArrayList<>();
    }
    public void addSong(Song song){
        mSongs.add(song);
    }
    public int getSongCount(){
        return mSongs.size();
    }

    private Map<String, List<Song>> byArtist(){
        Map<String, List<Song>> byArtist = new HashMap<>(); // initializarea mapului final cu artist-cintec
        for (Song song : mSongs){ // pentru fiecare cintec
            List<Song> artistSongs = byArtist.get(song.getmArtist()); // in lista se adauga Song-ul, daca este astfel de key - autor in map
            if (artistSongs == null){ // in cazul in care pentru prima data se intilneste acesta, se initializeaza valorile
                artistSongs = new ArrayList<>();
                byArtist.put(song.getmArtist(), artistSongs);
            }
            artistSongs.add(song); // se adauga song-ul
        }
        return byArtist;
    }

    public Set<String> getArtists(){ // transforma in set, artistii = key-ul mapei byArtist
        return byArtist().keySet();
    }
    public List<Song> getSongsForArtist(String artistName){
        return byArtist().get(artistName);
    }


}
