package CardGame.src.main;

import java.util.ArrayList;

public class Decks {
    public Integer deckName;
    public ArrayList<Cards> deckCardArray = new ArrayList<Cards>();
    
    public void passToHand(){ // test this tommorow with dummy objects
        //Need to create a way to decrease the heirarchy of every other card whenever a card is passed
        Cards tempCard = new Cards();
        for (Cards card : this.deckCardArray) { //finds the card at the bottom of the hierarchy
            if (card.heirarchy == 1){
                tempCard = card;
                //System.out.print("Found the card");
               
            }
        }
        this.deckCardArray.remove(tempCard); //removes the card 
        tempCard.heirarchy = 4;

       for(Hands hand : cardGame.handArray){ //searches thru hands to find the one with a matching name to the current deck - e.g hand1 passes to deck1 and hand2 passes to deck2
            if(hand.handName == this.deckName){
                //Create logic to pass over card
                hand.handCardArray.add(tempCard);
                
            }
        }
        




    }
    





}
