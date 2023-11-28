package CardGame.src.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Players implements Runnable {
    String playerName;
    public int playerNo;
    public cardGame cardGame;
    private Hands hand;
    private Decks deck;

    public Players( Hands hand, Decks deck) {   
        this.hand = hand;
        this.deck = deck;
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


    // public void drawCard() {
    //     Cards card = deck.drawCard();
    //     if (card != null) {
    //         hand.addCard(card);
    //     }
    // }

    public void discardCard(Cards card) {
        hand.removeCard(card);
        deck.addCard(card);
    }

    @Override
    public void run() {
        try {
            while (cardGame.winningPlayer.get() == 0) {
                synchronized (cardGame) {
                    if (hand.hasMatchingCards()) {
                        System.out.println(playerName+ " has matching cards.");
                        cardGame.setWinner(this);
                        cardGame.notifyAll();
                        break;
                    }

                    if (deck.isDeckEmpty()) {
                        System.out.println(playerName + "'s deck is empty.");
                        try {
                            cardGame.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return; // End the thread if it's interrupted
                        }
                    } else {
                        //drawCard();
                        System.out.println(playerName + " drew a card.");
                        Cards card = hand.getLatestCard();
                        if (card.cardNumber != playerNo) {
                            discardCard(card);
                            System.out.println(playerName + " discarded a card.");
                            cardGame.notifyAll();
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Handle the exception
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }



}
