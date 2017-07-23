package com.teamtreehouse;

import java.util.Date;

public class Example {
    public static void main(String[] args){
        Treet treet = new Treet(
                "craigsdennis",
                "Want to be famous? Simply tweet about Java and use the hashtag #treet. I'll use your tweet in new @treehouse course about data structures.",
                new Date(1421849732000L)
        );
        System.out.println("this is a new treet " + treet);
    }
}
