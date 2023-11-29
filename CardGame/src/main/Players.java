package CardGame.src.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Players implements Runnable {
    String playerName;
    public int playerNo; 
    public Hands playerHand;
    public Decks playerDeck;

    public Players( Hands hand, Decks deck) {   
        this.playerHand = hand;
        this.playerDeck = deck;
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

    @Override
    public void run() {
        synchronized(this){ //Synchronise on each instance of Players
        try {
            while (!cardGame.gameWon) {
                if(!cardGame.gameWon && this.playerHand.checkHandHasCard()){               
                    this.playerHand.passToDeck();
                    System.out.println("Player number " + this.playerHand.handValue +" has discarded a card, their hand looks like" );
                    for(Cards card : this.playerHand.handCardArray){
                        System.out.println(card.cardNumber);
                    }
                }else{
                    try{
                            this.wait(30);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                if(!cardGame.gameWon && this.playerDeck.checkDeckHasCard()){
                    this.playerDeck.drawCard();
                    System.out.println("Player number " + this.playerHand.handValue +" has drawn a card, their hand looks like" );
                    for(Cards card : this.playerHand.handCardArray){
                        System.out.println(card.cardNumber);
                    }                 
                }else{
                        try{
                            this.wait(30);
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
}
}

