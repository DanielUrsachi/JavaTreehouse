package com.teamtreehouse;

import java.util.Arrays;
import java.util.Date;

public class Example {
    public static void main(String[] args){
        Treet treet = new Treet(
                "authorNameHere",
                "some intresting description.",
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
        System.out.println("Deserialization");
        Treets.save(treets);
        for (Treet exampleTreet : treets){
            System.out.println(exampleTreet);
        }
    }
}
