package com.teamtreehouse.pomodoro.cotrollers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sun.net.idn.StringPrep;
import javafx.scene.media.AudioClip;


public class Home { //controller
    private final AudioClip mApplause;
    @FXML private VBox container;
    @FXML private Label title;
    @FXML private TextArea message;
    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;
    private Timeline mTimeline;


    public Home() {
        mTimerText = new SimpleStringProperty();
        setTimerText(0); //bined textul cu field-ul
        mApplause = new javafx.scene.media.AudioClip(getClass().getResource("/sounds/tone.wav").toExternalForm());
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
        reset();
        mCurrentAttempt   = new Attempt("", kind); //initierea Attempt
        addAttemptStyle(kind); // adaugarea class
        title.setText(kind.getDisplayName()); // se afiseaza tip-ul de kind
        setTimerText(mCurrentAttempt.getRemainingSeconds());
        mTimeline = new Timeline();
        mTimeline.setCycleCount(kind.getTotalSeconds()); // ciclu pentru fiecare frame
        mTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> { // lambda pentru fiecare sec
            mCurrentAttempt.tick();
            setTimerText(mCurrentAttempt.getRemainingSeconds());
        }));
        mTimeline.setOnFinished(event -> { // la finish, schimba starea lui attemptKind
            saveCurrentAttempt();
            mApplause.play();
            prepareAttempt(mCurrentAttempt.getKind() == AttemptKind.FOCUS ? AttemptKind.BREAK : AttemptKind.FOCUS);
        });
    }

    private void saveCurrentAttempt() {
        mCurrentAttempt.setMessage(message.getText());
        mCurrentAttempt.save();
    }

    private void reset() {
        clearAttemptStyles(); // curatirea de classuri
        if (mTimeline != null && mTimeline.getStatus() == Animation.Status.RUNNING){
            mTimeline.stop();
        }
    }

    //pentru timer
    public void playTimer(){
        container.getStyleClass().add("playing"); // adaugarea claselor la play
        mTimeline.play();
    }
    public void pauseTimer(){
        container.getStyleClass().remove("playing"); // stergerea claselor play
        mTimeline.pause();
    }
    //

    private void addAttemptStyle(AttemptKind kind){ // adauga class in dependenta de tip-ul cind
        container.getStyleClass().add(kind.toString().toLowerCase());
    }
    private void clearAttemptStyles(){ // parcurge si sterge toate classurile de king
        container.getStyleClass().remove("playing");
        for (AttemptKind kind : AttemptKind.values()){
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void handleRestart(ActionEvent actionEvent) { // buton restart
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
    }

    public void handlePlay(ActionEvent actionEvent) { // buton play
        if (mCurrentAttempt == null){
            handleRestart(actionEvent);
        }else {
            playTimer();
        }
    }

    public void handlePause(ActionEvent actionEvent) { // buton pause
        pauseTimer();
    }
}

