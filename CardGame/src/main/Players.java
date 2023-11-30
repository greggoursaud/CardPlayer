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

    /**
     * Constructs a Players object with the specified hand and deck.
     * 
     * @param hand the hand of cards for the player
     * @param deck the deck of cards for the player
     */
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
     * This method encapsulates the run() method of the Players class.
     * It executes the core game logic for each player in the card game.
     * The method is synchronized to prevent race conditions and ensure thread safety.
     * It leverages the updater object to log player actions to a file.
     * The method operates in a loop until a player fulfills the game's win condition.
     * In each iteration, the player may perform one of two actions: draw a card from the deck or discard a card.
     * If the player's hand satisfies the win condition, the game terminates and the player is declared the winner.
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

    /**
     * This method is called when the game ends. It updates the game log with the final results and actions of the player.
     * If the player's hand value matches the winning player's hand value, it writes that the player has won the game.
     * Otherwise, it writes that the winning player has informed the current player about their victory.
     * It also writes that the current player exits the game and logs their final hand and the contents of their deck.
     */
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

}

