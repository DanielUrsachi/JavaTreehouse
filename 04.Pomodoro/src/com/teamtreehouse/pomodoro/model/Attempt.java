package com.teamtreehouse.pomodoro.model;

public class Attempt { // main functional class
    private String mMessage;
    private int mRemainingSeconds;
    private AttemptKind mKind;

    public Attempt(String message, AttemptKind kind) {
        mMessage = message;
        mKind = kind;
        mRemainingSeconds = kind.getTotalSeconds();
    }

    public String getMessage() {
        return mMessage;
    }

    public int getRemainingSeconds() {
        return mRemainingSeconds;
    }

    public AttemptKind getKind() {
        return mKind;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public void tick() { // metoda accesata la fiecare frame (fiecare sec)
        mRemainingSeconds--;
    }

    @Override
    public String toString() { // afisarea
        return "Attempt{" +
                "Message='" + mMessage + '\'' +
                ", RemainingSeconds=" + mRemainingSeconds +
                ", Kind=" + mKind +
                '}';
    }

    public void save() { // printarea mesajului
        System.out.println("Saving : " + this);
    }
}
