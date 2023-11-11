package CardGame.src.main;

import java.io.*;
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
            deck.deckName = "Deck" + i;
            hand.handName = "Hand"+ i;
            deckArray.add(deck);
            handArray.add(hand);
        }
    }   
}
 