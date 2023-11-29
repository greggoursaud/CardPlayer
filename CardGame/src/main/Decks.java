package CardGame.src.main;

import java.util.ArrayList;

public class Decks {
    public int deckValue;
    public String deckName;
    public ArrayList<Cards> deckCardArray = new ArrayList<Cards>();
    private final Object drawLock = new Object();
    public gameUpdates updater;

    public Decks() {
        // No initialization needed here
    }

    public Decks(Integer deckValue, gameUpdates updater) {
        this.deckValue = deckValue;
        //System.out.print("The value of the created deck is" + deckValue);
        this.deckName = "Deck" + Integer.toString(deckValue);
        this.updater = updater;
    }

    public void setDeckName(Integer deckValue) {
        this.deckName = "Deck" + Integer.toString(deckValue);
    }

        public boolean isDeckEmpty() {
        return deckCardArray.isEmpty();
    }
    
    public void drawCard(){ 
        synchronized (drawLock){
        //Need to create a way to decrease the heirarchy of every other card whenever a card is passed
        Cards tempCard = new Cards();
        for (Cards card : this.deckCardArray) { //finds the card at the bottom of the hierarchy
            // if (card.Heirarchy == 1){
                tempCard = card;
                //System.out.print("Found the card");
               
            //}
        }
        this.deckCardArray.remove(tempCard); //removes the card 
        //tempCard.setHeirarchy(4);
        String fileInput = "Player " + (this.deckValue + 1) + " draws a " + tempCard.cardNumber + " from deck " + this.deckValue;
        updater.writePlayerAction("player" + this.deckValue, fileInput);

       for(Hands hand : cardGame.handArray){ //searches through hands to find the one with a matching name to the current deck - e.g hand1 passes to deck1 and hand2 passes to deck2
            if(hand.handValue == this.deckValue){
                //Create logic to pass over card
                hand.handCardArray.add(tempCard);
                
            }
        }
    }
    }
    
    public boolean checkDeckHasCard(){ //checks if the deck this object contains has any available cards to take 
        if( this.deckCardArray.size() == 0){
         System.out.println("No cards to take");
         return false;
        }
         return true;
    }



}
