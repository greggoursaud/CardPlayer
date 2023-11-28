package CardGame.src.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class cardGame {
    //public static Integer playerNo;
    public static String textFile;
    private ArrayList<Decks> deckArray = new ArrayList<>();
    private ArrayList<Hands> handArray = new ArrayList<>();
    private ArrayList<Players> playersArray = new ArrayList<>();
    public static AtomicInteger winningPlayer = new AtomicInteger(0);

    public ArrayList<Players> getPlayersArray() {
        return playersArray;
    }

    public ArrayList<Decks> getDeckArray() {
        return deckArray;
    }

    public ArrayList<Hands> getHandArray() {
        return handArray;
    }
    
   //test created to make sure assertions are working correctly
   public String testTest(){
    return "Hello";
   }
 
    /**
     * Generates decks and hands for the specified number of players.
     * Creates a new player object for each player and constructs a new deck and hand object for each player.
     * 
     * @param playerNo The number of players in the game.
     */
    public void generateDecksAndHands(int playerNo) {
        for (int i = 0; i < playerNo; i++) {
            Decks deck = new Decks();
            Hands hand = new Hands();
            deck.setDeckName(i);
            hand.setHandName(i);
            Players player = new Players(this, hand, deck);
            player.setPlayerName("Player" + i);
            deckArray.add(deck);
            handArray.add(hand);
            playersArray.add(player);
        }
    }





    public static void main(String[] args){    
        // Call the static method shuffleFileContent using the class name
        cardGame.shuffleFileContent("CardGame/src/packs/4.txt"); 
        
        // Create a cardGame object
        cardGame game = new cardGame();

        // Call the instance method createHandsAndDecksFromTextFile
        game.createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);
        game.startGame();
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
    

    public void createHandsAndDecksFromTextFile(String filename, int playerNo) {
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
    
        generateDecksAndHands(playerNo); //generate decks and hands based on the player count
        int y = 1;
    
        for (int x = 0; x <= cardValues.size() -1; x++){
            if(y == playerNo + 1){
               y = 1;  
            }
            Cards cardz = new Cards(Integer.parseInt(cardValues.get(x)));
            if(handArray.get(playerNo - 1).getHandCardArray().size() != 4)
            {
                handArray.get(y - 1).getHandCardArray().add(cardz);
            }
            else{
                deckArray.get(y - 1).deckCardArray.add(cardz);
            }
    
            for (Cards card : handArray.get(y - 1).getHandCardArray()) {
                System.out.print(card.getCardNumber() + " ");
            }
            System.out.println();
           y++;
        }
    
        for (int i = 0; i < playerNo; i++) {
            for (int j = i; j < cardValues.size(); j += playerNo) { //round-robin
                Cards card = new Cards(Integer.parseInt(cardValues.get(j)));
                if(i <= 0){
                    //handArray.get(i).handCardArray.add(card);
                }
                else{
                    //deckArray.get(i).deckCardArray.add(card);
                }
            }
        }
    }

    public void startGame() {
        for (Players player : playersArray) {
            Thread thread = new Thread(player);
            thread.start();
        }
    }

    public void setWinner(Players winner) {
        winningPlayer.set(winner.getPlayerNo());
    }
    
    
  
    

    
    
}