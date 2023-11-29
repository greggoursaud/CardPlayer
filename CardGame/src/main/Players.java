package CardGame.src.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Players implements Runnable {
    String playerName;
    public int playerNo; 
    public Hands playerHand;
    public Decks playerDeck;
    public gameUpdates updater;
    public Players winner;

    public Players( Hands hand, Decks deck) {   
        this.playerHand = hand;
        this.playerDeck = deck;
        this.updater = new gameUpdates();

    }

    //Will generate players  //not sure if this is needed now?
    public static void generatePlayers(Integer playerNo){       
    }

    /**
     * Prompts the user to enter the number of players for the game.
     * 
     * @return The number of players entered by the user.
     */
    public void queryUser() {
        Scanner input = new Scanner(System.in);
        while (playerNo == 0) {
            try {
                System.out.print("How many players will be playing this game: ");
                playerNo = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.next(); // discard the invalid input
            }
        }
        input.close();
    }

    /**
     * Main execution point for each player in the game.
     * 
     * The method runs in a loop until a player wins. 
     * Each iteration checks if the player's hand and deck have cards. 
     * If they do, the player discards and draws a card respectively, and prints their hand.
     * If not, the player waits for 100 milliseconds.
     * 
     * If a player's hand meets the win condition, the player is declared the winner, 
     * the gameWon flag is set to true, and the current thread is interrupted.
     * 
     * If an InterruptedException is thrown, it prints the stack trace.
     * 
     * At the end, it notifies any threads waiting on this player's monitor.
     */
    @Override
    public void run() {
        synchronized(this){ //Synchronise on each instance of Players
        try {
            String fileInput = "Player " + (this.playerHand.handValue + 1) + " initial hand: " + this.playerHand.handCardArray.toString();
            updater.writePlayerAction("player" + this.playerHand.handValue, fileInput);
            while (!cardGame.gameWon) {
                if(!cardGame.gameWon && this.playerHand.checkHandHasCard()){               
                    this.playerDeck.drawCard();
                    System.out.println("Player number " + (this.playerHand.handValue + 1) +" has drawn a card, their hand looks like" );
                    for(Cards card : this.playerHand.handCardArray){
                        System.out.println(card.cardNumber);
                    }
                }else{
                    try{
                            this.wait(100);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                if(!cardGame.gameWon && this.playerDeck.checkDeckHasCard()){
                    this.playerHand.passToDeck();
                    System.out.println("Player number " + this.playerHand.handValue +" has discarded a card, their hand looks like" );
                    for(Cards card : this.playerHand.handCardArray){
                        System.out.println(card.cardNumber);
                    }      
                fileInput = "Player " + (this.playerHand.handValue + 1) + " current hand is: " + this.playerHand.handCardArray.toString();   
                updater.writePlayerAction("player" + this.playerHand.handValue, fileInput);        
                }else{
                        try{
                            this.wait(100);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }


                if(this.playerHand.handCardArray.size() >= 4 && this.playerHand.checkWinCondition() == true){
                    System.out.print("Player "+ this.playerHand.handValue +"has won the Game ");
                    setWinner(this);
                    // fileInput = "Player " + this.playerHand.handValue + " has won the game";
                    // updater.writePlayerAction("player" + this.playerHand.handValue, fileInput);
                    cardGame.gameWon = true;
                    Thread.currentThread().interrupt();    
                }   
            }
       }finally{
        this.notify();
       }
    }
    end();
    }

    public void end(){
        String fileInput;
        
        if (cardGame.winningPlayerNo == this.playerHand.handValue) {
            fileInput = "Player " + (this.playerHand.handValue + 1) + " has won the game";
            updater.writePlayerAction("player" + this.playerHand.handValue, fileInput);
        } else {
            fileInput = "Player " + (cardGame.winningPlayerNo + 1) + " has informed Player " + (this.playerHand.handValue + 1) + " that Player " + (cardGame.winningPlayerNo + 1) + " has won";
            updater.writePlayerAction("player" + this.playerHand.handValue, fileInput);
        }
        String fileInputExit = "Player " + (this.playerHand.handValue + 1) + " exits";
        updater.writePlayerAction("player" + this.playerHand.handValue, fileInputExit);
        String fileInputFinalHand = "Player " + (this.playerHand.handValue + 1) + " final hand: " + this.playerHand.handCardArray.toString();
        updater.writePlayerAction("player" + this.playerHand.handValue, fileInputFinalHand);

        fileInput = "Deck " + this.playerDeck.deckValue + " contents: " + this.playerDeck.deckCardArray.toString(); 
        updater.writePlayerAction("deck" + this.playerDeck.deckValue, fileInput);

    }

    public void setWinner(Players winner) {
        this.winner = winner;
    }

    public Players getWinner() {
        return this.winner;
    }



}

