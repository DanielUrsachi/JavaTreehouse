package com.teamtreehouse;

import java.beans.IntrospectionException;
import java.util.*;

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
        System.out.println("\nSort");

        Treet[] treets = {treet, secondTreet};
        Arrays.sort(treets);
        for (Treet exampleTreet : treets){
            System.out.println(exampleTreet);
        }


        //Save
        Treets.save(treets);
        //load
        System.out.println("\nDeserialization");
        Treet[] reloadedTreets = Treets.load();
        for (Treet exampleTreet : reloadedTreets){
            System.out.println(exampleTreet);
        }

        //Collection
        System.out.println("\nCollection using prints: ");
        Treet[] treets1 = Treets.load();
        System.out.println("there are " + treets.length + " treets");
        Treet originalTreet = treets[0];
        System.out.println("Hashtags: ");
        for (String hashtag : originalTreet.getHashTags()){
            System.out.println(hashtag);
        }
        //alphabetical ordering
        System.out.println("\nAplhabetical sort ");
        Set<String> allHashTags = new HashSet<String>();
        Set<String> allMentions = new HashSet<String>();
        for (Treet treetEx : treets1){
            allHashTags.addAll(treetEx.getHashTags());
            allMentions.addAll(treetEx.getMentions());
        }
        System.out.println("\nHashtags: " + allHashTags);
        System.out.println("Mentions: " + allMentions);
        //maps
        Map<String, Integer> hashTagCounts = new HashMap<>();
        for (Treet treetEx: treets1){ //pentru fiecare treet din treets1[]
            for (String hashTag : treetEx.getHashTags()){ //pentru fiecare string din acest treet
                Integer count = hashTagCounts.get(hashTag); //din map compara cu fiecare cuvint, daca nu e repetitie
                if (count == null) {
                    count = 0;
                }
                count++;
                hashTagCounts.put(hashTag,count); //adauga element
            }
        }
        System.out.println("\nHashtag counts: " + hashTagCounts);

        ///
        Map<String, List<Treet>> treetsByAuthor = new HashMap<String, List<Treet>>(); //map general pentru autor si lista de treets
        for (Treet treetEx : treets1){ //pentru fiecare treet
            List<Treet> authoredTreets = treetsByAuthor.get(treetEx.getmAuthor()); //lista cu fiecare treet al unui autor = element din map general cu acelasi nume cu rol de key
            if (authoredTreets == null){ //daca acest nume nu a mai fost intilnit
                authoredTreets = new ArrayList<Treet>(); //pentru acest autor se creeaza un noua lista de treets
                treetsByAuthor.put(treetEx.getmAuthor(), authoredTreets); //se introduc datele in map general, !!!! la schimbarea datelor, se fac schimbari in map
            }
            authoredTreets.add(treetEx); //se adauga lista de treet, treet-ul de la loop-ul acesta + !!!! automat, valorile din authoredTreets, pleaca in map
            System.out.println("a");
        }

        System.out.println("\nTreets by author: " + treetsByAuthor);
        System.out.println("Treets by authorNameHere: " + treetsByAuthor.get("authorNameHere"));



    }
}
