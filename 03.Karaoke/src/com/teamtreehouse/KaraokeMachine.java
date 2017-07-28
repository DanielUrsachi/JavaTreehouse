package com.teamtreehouse;

import com.teamtreehouse.module.Song;
import com.teamtreehouse.module.SongBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KaraokeMachine { //pentru afisare
    private SongBook mSongBook;
    private BufferedReader mReader;
    private Map<String, String> mMenu;
    private Queue<Song> mSongQueue;

    public KaraokeMachine(SongBook songBook){
        mSongBook = songBook;
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mMenu = new HashMap<>();
        mSongQueue = new ArrayDeque<Song>();
        mMenu.put("add", "Add a new song to a song boook");
        mMenu.put("choose", "Choose a song to sing");
        mMenu.put("play", "Play next song in the queue");
        mMenu.put("quit", "Give up. Exit the program");
    }

    private String promptAction() throws IOException {
        System.out.println("\nThere are " + mSongBook.getSongCount() + " songs available, and " + mSongQueue.size() + " in the Queue");
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
                    case "choose": // alege un element si il adauga in queue
                        String artist = promptArtist(); // printarea / alegerea artistilor
                        Song artistSong = promptSongForArtist(artist); // printarea / alegerea cintecului de acest artist
                        mSongQueue.add(artistSong); // adauga in queu cite un element
                        System.out.println("Yout choose: " + artistSong); // afisarea alegerii
                        break;
                    case "play":
                        playNext();
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
        System.out.println("Your choice :  ");
        String optionAsString = mReader.readLine();
        int choice = Integer.parseInt(optionAsString.trim());
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
        System.out.println("Available songs for " + artist);
        for (Song song : songs){ //preluarea doar a denumirii in lista
            songTitles.add(song.getmTitle());
        }
        int index = promptForIndex(songTitles); // afisarea si alegerea
        return songs.get(index);
    }

    public void playNext(){ // scoate cite un element din queue si il afiseaza
        Song song = mSongQueue.poll(); // extragem elementul din head
        if (song == null){
            System.out.println("Sorry there are no songs in Queue, please add one");
        }
        else {
            System.out.println("Opent " + song.getmVideoUrl() + " to hear " + song.getmTitle() + " by " + song.getmArtist());
        }
    }
}
