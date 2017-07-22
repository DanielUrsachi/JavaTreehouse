package com.company;

import java.util.Scanner;

//view
public class Prompter {
    private Game game;

    public Prompter(Game game){
        this.game = game;

    }

    public boolean promptForGuess(){
        Scanner scanner = new Scanner(System.in);
        boolean isHit = false;
        boolean isAcceptable = false;

        do {
            System.out.println("Enter a letter : ");
            String guessInput = scanner.nextLine();


            try {
                isHit = game.applyGuess(guessInput);
                isAcceptable = true;
            } catch (IllegalArgumentException iae) {
                System.out.println("Please try again " + iae.getMessage());
            }
        }while (!isAcceptable);
        return isHit;

    }

    public void displayProgress(){
        System.out.println("You have : " + game.getRemainingTries() + " tries, try to solve : " + game.getCurrentProgress());
    }

    public void displayOutcome(){
        if (game.isWon()){
            System.out.println("Congrats, you won with " + game.getRemainingTries() + " tries");
        }else {
            System.out.println("The word was " + game.getAnswer());
        }
    }




}
