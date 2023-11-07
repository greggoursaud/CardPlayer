package CardGame;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class cardGame {
    public static Integer playerNo;
    public static String textFile;

    
    
    public static void queryUser(){
        Scanner input = new Scanner(System.in);
        System.out.print("How many players will be playing this game");
        playerNo = input.nextInt();
        System.out.print("Please give the name of the text file to be used");
        textFile = input.nextLine();
        input.close();
    
   }

    //generates the decks 
    //Biggest problem with this is creating a different number of decks for every player - very hard :(, not dealing with this today
    public static void generateDecks() {
        for (int i = 1; i == playerNo; i++){
            String name = "PlayerDeck" + i;
            public ArrayList<Cards> name = new ArrayList<Cards>();
        }

    }   

    public static void generateHands() {

    }

}
