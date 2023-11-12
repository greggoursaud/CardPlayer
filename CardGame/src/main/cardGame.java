package CardGame.src.main;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class cardGame {
    //public static Integer playerNo;
    public static String textFile;
    public static ArrayList<Decks> deckArray = new ArrayList<Decks>();
    public static ArrayList<Hands> handArray = new ArrayList<Hands>();
    public static ArrayList<Players> playersArray = new ArrayList<Players>();

    Players player = new Players();
    
   //test created to make sure assertions are working correctly
   public String testTest(){
    return "Hello";
   }

    //generates the decks and Hands   
    public static void generateDecksAndHands(Integer playerNo) {
        for(int i = 0; i < playerNo; i++){
            Decks deck = new Decks();
            Hands hand = new Hands();
            Players player = new Players();
            deck.deckName = "Deck" + i; // names the deck object for each player like deck1, deck2 etc.
            hand.handName = "Hand"+ i; // names the hand object for each player like hand1, hand2 etc.
            player.playerName = "Player" + i;
            deckArray.add(deck);
            handArray.add(hand);
            playersArray.add(player);
        }
    }   

    public static void main(String[] args){    
    // Do user input testing in here for now
    }
    

    // public static void createDecksFromTextFile(String filename) {
    //     try {
    //         BufferedReader reader = new BufferedReader(new FileReader(filename));
    //         String line;
    
    //         ArrayList<String> cardValues = new ArrayList<>();
    
    //         while ((line = reader.readLine()) != null) {
    //             cardValues.add(line);
    //         }
    //         reader.close();
    
    //         for (int i = 0; i < playerNo; i++) {
    //             for (int j = i; j < cardValues.size(); j += playerNo) { //round-robin
    //                 Cards card = new Cards(Integer.parseInt(cardValues.get(j))); //creating a new Card object for each card
    //                 deckArray.get(i).deckCardArray.add(card);
    //             }
    
    //             //verification
    //             System.out.print("Deck " + i + " has cards: ");
    //             for (Cards card : deckArray.get(i).deckCardArray) {
    //                 System.out.print(card.cardNumber + " ");
    //             }
    //             System.out.println();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    
    
    
}
 