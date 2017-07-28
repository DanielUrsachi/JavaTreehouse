package com.teamtreehouse.module;

import java.io.*;
import java.util.*;

public class SongBook { //implementarea multipla a obiectelor Song
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

    private Map<String, List<Song>> byArtist(){ //map cu list pentru fiecare artist
        Map<String, List<Song>> byArtist = new TreeMap<>(); // initializarea mapului final cu artist-cintec ( folosim treeMap pentru sortare alfabetica automata )
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
    public List<Song> getSongsForArtist(String artistName){ // scoate valorile dupa key
        //return byArtist().get(artistName); renuntare la asa format, pentru necesitatea sortarii
        List<Song> songs = byArtist().get(artistName);
        songs.sort(new Comparator<Song>() { // interfata noua pentru pentru ordonare alfabetica (comparare)
            @Override
            public int compare(Song song1, Song song2) {
                if (song1.equals(song2)){
                    return 0;
                }
                return song1.mTitle.compareTo(song2.mTitle); // formula de comparare pentru sortarea alfabetica dupa titlu
            }
        });
        return songs;
    }

    public void exportTo(String filename){
        try (
            FileOutputStream fos = new FileOutputStream(filename); // creem file-ul
            PrintWriter writer = new PrintWriter(fos); // metoda de scriere
        ){
            for (Song song : mSongs){
                writer.printf(song.getmArtist() + "|" + song.getmTitle() + "|" + song.getmVideoUrl() + "%n");
            }
        } catch (IOException e) {
            System.out.println("Problem with saving " + filename);
            e.printStackTrace();
        }
    }

    public void importFrom (String filename){
        try(
            FileInputStream fis = new FileInputStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        ){
            String line;
            while ((line = reader.readLine()) != null) { // atita timp cind este content pentru citire
                String[] args = line.split("\\|"); // definit delimitator intre field-uri, si efectuarea parsarii lui
                addSong(new Song(args[0], args[1], args[2]));

            }
        } catch (IOException e) {
            System.out.println("Problems loading " + filename);
            e.printStackTrace();
        }
    }

}
