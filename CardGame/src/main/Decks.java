package CardGame.src.main;

import java.util.ArrayList;

public class Decks {
    public int deckValue;
    public String deckName;
    public ArrayList<Cards> deckCardArray = new ArrayList<Cards>();
    private final Object drawLock = new Object();
    public gameUpdates updater;

    /**
     * This class represents a collection of decks in a card game.
     * It provides methods for managing and manipulating the decks.
     */
    public Decks() {
        // No initialization needed here
    }

    public Decks(Integer deckValue) {
        this.deckValue = deckValue;
        this.deckName = "Deck" + Integer.toString(deckValue);
    }

    /**
     * Constructs a Decks object with the specified deck value and game updater.
     *
     * @param deckValue the value of the deck
     * @param updater the game updater object
     */
    public Decks(Integer deckValue, gameUpdates updater) {
        this.deckValue = deckValue;
        this.deckName = "Deck" + Integer.toString(deckValue);
        this.updater = updater;
    }

    /**
     * Sets the name of the deck based on the given deck value.
     * The deck name is set as "Deck" followed by the deck value converted to a string.
     *
     * @param deckValue the value of the deck
     */
    public void setDeckName(Integer deckValue) {
        this.deckName = "Deck" + Integer.toString(deckValue);
    }

        /**
         * Checks if the deck is empty.
         * 
         * @return true if the deck is empty, false otherwise.
         */
        public boolean isDeckEmpty() {
        return deckCardArray.isEmpty();
    }
    
    /**
     * This method is used to draw a card from the deck.
     * It removes the card from the deck and adds it to the corresponding hand.
     * It also updates the player's action in a file.
     */
    public void drawCard(){ 
        synchronized (drawLock){
        Cards tempCard = new Cards();
        for (Cards card : this.deckCardArray) { 
                tempCard = card;
        }
        this.deckCardArray.remove(tempCard); // Removes the card from the deck
        String fileInput = "Player " + (this.deckValue + 1) + " draws a " + tempCard.cardNumber + " from deck " + this.deckValue;
        updater.writePlayerAction("player" + this.deckValue, fileInput);

       for(Hands hand : cardGame.handArray){ // Searches through hands to find the one with a matching name to the current deck - e.g hand1 passes to deck1 and hand2 passes to deck2
            if(hand.handValue == this.deckValue){
                hand.handCardArray.add(tempCard);
                
            }
        }
    }
    }
    
    /**
     * Checks if the deck has any cards.
     * 
     * @return true if the deck has cards, false otherwise.
     */
    public boolean checkDeckHasCard(){  
        if( this.deckCardArray.size() == 0){        
         return false;
        }
         return true;
    }



}
