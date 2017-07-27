package com.teamtreehouse;

import com.teamtreehouse.module.SongBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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
                        //
                        break;
                    case "quit":
                        //
                        break;
                    default:
                        System.out.println("Unknown choice " + choice + " try again");
                }
            } catch (IOException e) {
                System.out.println("Problem with input");
                e.printStackTrace();
            }
        }while (choice.equals("quit"));
    }
}
