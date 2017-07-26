package com.teamtreehouse;

import java.util.Arrays;
import java.util.Date;

public class Example {
    public static void main(String[] args){
        Treet treet = new Treet(
                "authorNameHere",
                "some intresting description with @symbol and a couple of #hash and #tags.",
                new Date(1421849732000L)
        );

        Treet secondTreet = new Treet(
                "journeytocode",
                "@treehouse makes learning Java sooooo fun! #treet",
                new Date(1421878767000L)
        );


        System.out.println("this is a new treet: " + treet.toString());
        System.out.println("the words are: ");
        for (String word: treet.getWords()){
            System.out.println(word);
        }

        //Sort
        System.out.println("Sort");

        Treet[] treets = {treet, secondTreet};
        Arrays.sort(treets);
        for (Treet exampleTreet : treets){
            System.out.println(exampleTreet);
        }


        //Save
        Treets.save(treets);
        //load
        System.out.println("Deserialization");
        Treet[] reloadedTreets = Treets.load();
        for (Treet exampleTreet : reloadedTreets){
            System.out.println(exampleTreet);
        }

        //Collection
//        System.out.println("Collection using prints: ");
//        Treet[] treets1 = Treet.load();

    }
}
