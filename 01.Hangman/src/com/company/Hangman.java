package com.company;
//clasa de baza
public class Hangman{

    public static void main(String[] args) {
        //args[] = "alo";
        if(args.length == 0){
            System.out.println("<answer> is required");
            System.exit(1);
        }
        Game game = new Game("aloo");
        Prompter prompter = new Prompter(game);

        while (game.getRemainingTries() > 0 && !game.isWon()){
            prompter.displayProgress();
            prompter.promptForGuess();
        }
        prompter.displayOutcome();



    }
}
