package CardGame.src.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class cardGame {
    public static String textFile;
    public static ArrayList<Players> playersArray = new ArrayList<Players>();
    public static ArrayList<Decks> deckArray = new ArrayList<Decks>();
    public static ArrayList<Hands> handArray = new ArrayList<Hands>();
    public static AtomicInteger winningPlayer = new AtomicInteger(0);
    public static volatile Boolean gameWon = false;
    public static ArrayList<Thread> threadList = new ArrayList<Thread>();
    public static volatile Integer winningPlayerNo;

    /**
     * This method is a test method that returns a greeting message.
     * 
     * @return A string representing a greeting message.
     */
   public static String testTest(){
    return "Hello";
   }
 
    /**
     * Generates decks and hands for the specified number of players.
     * Creates a new player object for each player and constructs a new deck and hand object for each player.
     * 
     * @param playerNo The number of players in the game.
     */
    public static void generateDecksAndHands(int playerNo) {
        for (int i = 0; i < playerNo; i++) {
            Decks deck = new Decks(i, new gameUpdates());
            Hands hand = new Hands(i, new gameUpdates());
            Players player = new Players(hand, deck);
            player.playerName = "Player" + i;    
            deckArray.add(deck);
            handArray.add(hand);
            playersArray.add(player);
        }
    }

    public static void main(String[] args){    
        // Call the static method shuffleFileContent using the class name
        shuffleFileContent("CardGame/src/packs/4.txt"); 
        
        // Call the instance method createHandsAndDecksFromTextFile
        createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);
        startGame();

    }


    /**
     * Shuffles the content of a file.
     * Reads the content of the specified file, shuffles the lines, and writes the shuffled content back to the file.
     *
     * @param filename the name of the file to be shuffled
     */
    public static void shuffleFileContent(String filename) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        ArrayList<String> lines = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        // Shuffle the lines
        Collections.shuffle(lines);

        // Write shuffled content back to the file
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
    

    /**
     * This method reads card values from a specified text file and initializes the game environment.
     * It creates the necessary hands and decks for a card game based on the number of players.
     *
     * @param filename The path of the text file. This file should contain the card values, 
     *                 each value representing a card. The method will read these values to 
     *                 create the initial set of cards for the game.
     *
     * @param playerNo The number of players participating in the game. This parameter will 
     *                 determine the number of hands that will be created. Each player will 
     *                 be assigned one hand.
     */
    public static void createHandsAndDecksFromTextFile(String filename, int playerNo) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> cardValues = new ArrayList<>();
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line;
    
                while ((line = reader.readLine()) != null) {
                    cardValues.add(line);
                }
                reader.close();
    
                if (cardValues.isEmpty()) {
                    System.out.println("No card values in the file");
                } else if (cardValues.size() % playerNo != 0) {
                    System.out.println("Invalid pack. The number of card values must be a multiple of the number of players.");
                } else {
                    break; // If the pack is valid, break the loop
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            System.out.println("Please enter a valid pack file:");
            filename = scanner.nextLine();
    
            // Clear the old card values
            cardValues.clear();
        }    
        generateDecksAndHands(playerNo); // Generate decks and hands based on the player count
        int y = 1;
    
        for (int x = 0; x <= cardValues.size() -1; x++){
            if(y == playerNo + 1){
               y = 1;  
            }
            Cards cardz = new Cards(Integer.parseInt(cardValues.get(x)));
            if(handArray.get(playerNo - 1).handCardArray.size() != 4)
            {
                handArray.get(y - 1).handCardArray.add(cardz);
            }
            else{
                deckArray.get(y - 1).deckCardArray.add(cardz);
            }
           y++;
        }
    }

    /**
     * Starts the card game.
     * 
     * This method initializes the gameUpdates object, prepares the directory, and starts a separate thread for each player in the playersArray.
     * 
     * @see gameUpdates
     * @see Players
     */
    public static void startGame() {
        gameUpdates updater = new gameUpdates();
        updater.prepareDirectory();
        for (Players player : playersArray) {           
            Thread thread = new Thread(player);            
            thread.start();
            threadList.add(thread);
        }
    }    

}