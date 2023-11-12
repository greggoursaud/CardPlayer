package CardGame.src.main;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class cardGame {
    public static Integer playerNo;
    public static String textFile;
    public static ArrayList<Decks> deckArray = new ArrayList<Decks>();
    public static ArrayList<Hands> handArray = new ArrayList<Hands>();
    
    public static void queryUser(){
        Scanner input = new Scanner(System.in);
        System.out.print("How many players will be playing this game");
        playerNo = input.nextInt();
        System.out.print("Please give the name of the text file to be used");
        textFile = input.nextLine();
        input.close();
    
   }
   //test created to make sure assertions are working correctly
   public String testTest(){
    return "Hello";
   }

    //generates the decks and Hands
    public static void generateDecksAndHands() {
        for(int i = 0; i < playerNo; i++){
            Decks deck = new Decks();
            Hands hand = new Hands();
            deck.deckName = "Deck" + i; // names the deck object for each player like deck1, deck2 etc.
            hand.handName = "Hand"+ i; // names the hand object for each player like hand1, hand2 etc.
            deckArray.add(deck);
            handArray.add(hand);
        }
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
 