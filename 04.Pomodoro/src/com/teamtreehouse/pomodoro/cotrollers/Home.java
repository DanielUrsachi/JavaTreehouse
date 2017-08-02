package com.teamtreehouse.pomodoro.cotrollers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sun.net.idn.StringPrep;

public class Home { //controller
    @FXML private VBox container;
    @FXML private Label title;
    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;
    private Timeline mTimeline;

    public Home(){
        mTimerText = new SimpleStringProperty();
        setTimerText(0); //bined textul cu field-ul
    }

    public String getTimerText() {
        return mTimerText.get();
    }

    public StringProperty timerTextProperty() {
        return mTimerText;
    }

    public void setTimerText(String timerText) {
        mTimerText.set(timerText);
    }
    public void setTimerText(int remainingSeconds){ // alternativa la afisarea timer pentru tic-tac
        int minutes = remainingSeconds / 60; // convertirea in minute
        int seconds = remainingSeconds % 60; // sec
        setTimerText(String.format("%02d:%02d", minutes, seconds));
    }

    private void prepareAttempt(AttemptKind kind){ // metoda de baza
        clearAttemptStyles(); // curatirea de classuri
        mCurrentAttempt   = new Attempt("", kind); //initierea Attempt
        addAttemptStyle(kind); // adaugarea class
        title.setText(kind.getDisplayName()); // se afiseaza tip-ul de kind
        setTimerText(mCurrentAttempt.getRemainingSeconds());
        //TODO: csd This is creating multiple timelines we need to fix this!
        mTimeline = new Timeline();
        mTimeline.setCycleCount(kind.getTotalSeconds()); // ciclu pentru fiecare frame
        mTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> { // lambda pentru fiecare sec
            mCurrentAttempt.tick();
            setTimerText(mCurrentAttempt.getRemainingSeconds());
        }
        ));
    }
    //pentru timer
    public void playTimer(){
        mTimeline.play();
    }
    public void pauseTimer(){
        mTimeline.pause();
    }
    //

    private void addAttemptStyle(AttemptKind kind){ // adauga class in dependenta de tip-ul cind
        container.getStyleClass().add(kind.toString().toLowerCase());
    }
    private void clearAttemptStyles(){ // parcurge si sterge toate classurile de king
        for (AttemptKind kind : AttemptKind.values()){
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void DEBUG(ActionEvent actionEvent) { // test
        System.out.println("hhi mom");
    }

    public void handleRestart(ActionEvent actionEvent) { // restart
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
    }
}

