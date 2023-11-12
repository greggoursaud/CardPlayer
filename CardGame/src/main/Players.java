package CardGame.src.main;

import java.util.Scanner;

public class Players {
    String playerName;


    //Will generate players
    public static void generatePlayers(Integer playerNo){       
    }

    public Integer queryUser(){
        Scanner input = new Scanner(System.in);
        System.out.print("How many players will be playing this game");
        Integer playerNo;
        playerNo = input.nextInt();       
        input.close();
    return playerNo;
   }



}
