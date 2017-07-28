package com.teamtreehouse;

import com.teamtreehouse.module.Song;
import com.teamtreehouse.module.SongBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KaraokeMachine { //pentru admin
    private SongBook mSongBook;
    private BufferedReader mReader;
    private Map<String, String> mMenu;

    public KaraokeMachine(SongBook songBook){
        mSongBook = songBook;
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mMenu = new HashMap<>();
        mMenu.put("add", "Add a new song to a song boook");
        mMenu.put("choose", "Choose a song to sing");
        mMenu.put("quit", "Give up. Exit the program");
    }

    private String promptAction() throws IOException {
        System.out.println("There are " + mSongBook.getSongCount() + " songs");
        for (Map.Entry<String, String> option : mMenu.entrySet()){
            System.out.println(option.getKey() + " - " + option.getValue());
        }
        System.out.println("What do you want to do: ");
        String choice = mReader.readLine();
        return choice.trim().toLowerCase();
    }

    public void run(){
        String choice = "";
        do{
            try {
                choice = promptAction();
                switch (choice){
                    case "add":
                        Song song = promptNewSong();
                        mSongBook.addSong(song);
                        System.out.println(song + " is added");
                        break;
                    case "choose":
                        String artist = promptArtist(); // printarea / alegerea artistilor
                        Song artistSong = promptSongForArtist(artist); // printarea / alegerea cintecului de acest artist
                        System.out.println("Yout chose: " + artistSong); // afisarea alegerii
                        break;
                    case "quit":
                        System.out.println("Thanks for playing!");
                        break;
                    default:
                        System.out.println("Unknown choice " + choice + " try again");
                }
            } catch (IOException e) {
                System.out.println("Problem with input");
                e.printStackTrace();
            }
        }while (!choice.equals("quit"));
    }

    private Song promptNewSong() throws IOException { // add
        System.out.println("Enter the artist's name: ");
        String artist = mReader.readLine();
        System.out.println("Enter the title: ");
        String title = mReader.readLine();
        System.out.println("Enter the video URL: ");
        String videoUrl = mReader.readLine();
        return new Song(artist, title, videoUrl);
    }

    private int promptForIndex(List<String> options) throws IOException { // afiseaza orice lista cu numerotatie si asteapta alegerea unuia din puncte
        int counter = 1;
        for (String option : options){
            System.out.println(counter + " )" + option);
            counter++;
        }
        String optionAsString = mReader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());
        System.out.println("Your choice :  ");
        return choice - 1 ;
    }
    private String promptArtist() throws IOException { //pentru afisarea artistilor si alegerea unuia
        System.out.println("Available artists : ");
        List <String> artists = new ArrayList<>(mSongBook.getArtists()); // preluarea listei de artisti din map creeata in karaoke
        int index = promptForIndex(artists); //afisarea artistilor cu index si cererea alegerea uneia din variante
        return artists.get(index); // returnarea variantei alese
    }
    private Song promptSongForArtist(String artist) throws IOException {  // preluarea cintecelor unui artist
        List<Song> songs = mSongBook.getSongsForArtist(artist); //preluarea listei de songuri
        List<String> songTitles = new ArrayList<>();
        for (Song song : songs){ //preluarea doar a denumirii in lista
            songTitles.add(song.getmTitle());
        }
        int index = promptForIndex(songTitles); // afisarea si alegerea
        return songs.get(index);
    }
}
