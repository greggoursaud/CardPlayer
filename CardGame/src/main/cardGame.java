package CardGame.src.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
            //deck.deckName = "Deck" + i; // names the deck object for each player like deck1, deck2 etc.
            //hand.handName = "Hand"+ i; // names the hand object for each player like hand1, hand2 etc.
            deck.deckName = i;
            hand.handName = i;

            player.playerName = "Player" + i;
            deckArray.add(deck);
            handArray.add(hand);
            playersArray.add(player);
        }
    }   

    public static void main(String[] args){    
    // Do user input testing in here for now
    shuffleFileContent("CardGame/src/packs/4.txt"); //dont shuffle pack 6 ;)
    createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);

    /////////////////////////////////////////////////////////////// Testing space ignore everything it's probably retarded lmao
    for(Decks deck : deckArray){
        for(Cards card : deck.deckCardArray){
            System.out.print(card.cardNumber + " ");
        }
        System.out.println();
    }
 System.out.println("Before Move");
    Decks deck = deckArray.get(0);
    deck.passToHand();
                       
    for(Decks deckz : deckArray){
        for(Cards card : deckz.deckCardArray){
            System.out.print(card.cardNumber + " ");
        }
        System.out.println();
    } 
    System.out.println("Deck after move");
    System.out.println();
    for(Hands hand : handArray){
        for(Cards card : hand.handCardArray){
            System.out.print(card.cardNumber + " ");
        }
        System.out.println();
    } 



    /////////////////////////////////////////////////////////////////////
    }

    public static void shuffleFileContent(String filename) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        ArrayList<String> lines = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        //shuffle the lines
        Collections.shuffle(lines);

        //write shuffled content back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (String shuffledLine : lines) {
            writer.write(shuffledLine);
            writer.newLine();
        }
        writer.close();
        
        System.out.println("Input pack has been shuffled\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    

    public static void createHandsAndDecksFromTextFile(String filename, int playerNo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            ArrayList<String> cardValues = new ArrayList<>();
    
            while ((line = reader.readLine()) != null) {
                cardValues.add(line);
            }
            reader.close();
    
            if (cardValues.isEmpty()) {
                System.out.println("No card values in the file");
            } else {
                generateDecksAndHands(playerNo); //generate decks and hands based on the player count
                int y = 1;
                
                for (int x = 0; x <= cardValues.size() -1; x++){
                    if(y == playerNo + 1){
                       y = 1;  
                    }
                    //System.out.print(y);                  
                    Cards cardz = new Cards(Integer.parseInt(cardValues.get(x)));

                    if(handArray.get(playerNo - 1).handCardArray.size() != 4)
                    {
                        cardz.heirarchy = handArray.get(y - 1).handCardArray.size() + 1;
                        handArray.get(y - 1).handCardArray.add(cardz);
                        
                    }
                    else{
                        cardz.heirarchy = deckArray.get(y - 1).deckCardArray.size() + 1;
                        deckArray.get(y - 1).deckCardArray.add(cardz);
                    }


                    /////////////////////////////////////////////////Used to see generation of cards and decks, leave for now/////////////////////////////////////////////////////
                    // if(x < cardValues.size()/2){
                    //     for (Cards card : handArray.get(y - 1).handCardArray) {
                    //     System.out.print(card.cardNumber + " ");
                    //     }
                    //     System.out.println();
                    // }
                    // else{
                    //     for (Cards card : deckArray.get(y - 1).deckCardArray) {
                    //     System.out.print(card.cardNumber + " ");
                    //     }
                    //     System.out.println();

                    // }
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                   y++;

                }







                //Greg's original code - can keep or delete idm

                // for (int i = 0; i < playerNo; i++) {
                //     for (int j = i; j < cardValues.size(); j += playerNo) { //round-robin
                //         Cards card = new Cards(Integer.parseInt(cardValues.get(j)));
                //         if(i <= 0){
                //             //handArray.get(i).handCardArray.add(card);
                //         }
                //         else{
                //             //deckArray.get(i).deckCardArray.add(card);
                //         }
                //     }
                    
                //     //System.out.print("Deck " + (i+1) + " has cards: ");
                //     //for (Cards card : handArray.get(i).handCardArray) {
                //     //    System.out.print(card.cardNumber + " ");
                //     //}
                //     //System.out.println("Now decks \n");
                    
                // }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    
    
  
    

    
    
}