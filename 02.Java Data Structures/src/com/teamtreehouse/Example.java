package com.teamtreehouse;

import java.util.Date;

public class Example {
    public static void main(String[] args){
        Treet treet = new Treet(
                "authorNameHere",
                "some intresting description.",
                new Date(1421849732000L)
        );
        System.out.println("this is a new treet: " + treet.toString());
    }
}
