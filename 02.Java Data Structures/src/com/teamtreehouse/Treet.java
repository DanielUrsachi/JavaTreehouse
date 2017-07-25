package com.teamtreehouse;

import java.io.Serializable;
import java.util.Date;

public class Treet implements Comparable, Serializable{

    private static final long serialVersionUID = 5411601062670758017L; //pentru salvarea versiunii clasei, evitarea erorilor  de ser/des la schimbarea structurei clasei

    private String mAuthor;
    private String mDescription;
    private Date mCreationDate;


    @Override
    public String toString() {
        return "Treet \"" + mDescription + "\" - @" + mAuthor + " on " + mCreationDate;
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

    public String[] getWords(){
        return mDescription.toLowerCase().split("[^\\w#@']+");
    }

    @Override
    public int compareTo(Object obj) {
        Treet other = (Treet) obj; //casting la Treet
        if(equals(other)){ //check daca obiectele sunt egale, nu mai are sens de le comparat dupa vre-un parametru
            return 0;
        }
        int dateCMP = mCreationDate.compareTo(other.mCreationDate); //compar dupa cretionDate
        if (dateCMP == 0){
            return mDescription.compareTo(other.mDescription); //compar dupa descriere, pentru cazul in care creationDate este egal
        }
        return dateCMP;
    }
}
