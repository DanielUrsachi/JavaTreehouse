package com.teamtreehouse;

import java.util.Date;

public class Treet {
    private String mAuthor;
    private String mDescription;
    private Date mCreationDate;


    @Override
    public String toString() {
        return "Treet \"" + mDescription + "\" - @" + mAuthor;
    }

    public Treet(String author, String description, Date creationDate){
       mAuthor = author;
       mDescription = description;
       mCreationDate = creationDate;
   }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmDescription() {
        return mDescription;
    }

    public Date getmCreationDate() {
        return mCreationDate;
    }
}
