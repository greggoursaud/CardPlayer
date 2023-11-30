package CardGame.src.main;

import java.util.ArrayList;

public class Hands {
    public int handValue;
    public String handName;
    public ArrayList<Cards> handCardArray = new ArrayList<>();
    private final Object passLock = new Object();
    public gameUpdates updater;
    
    /**
     * Represents a collection of hands in a card game.
     */
    public Hands() {
        // No initialization needed here
    }

    /**
     * Represents a hand in a card game.
     * Each hand has a hand value and an associated game updater.
     */
    public Hands(Integer handValue, gameUpdates updater) {
        this.handValue = handValue;
        this.handName = "Hand" + Integer.toString(handValue);   
        this.updater = updater;    
    }

    /**
     * Sets the name of the hand based on its value.
     * 
     * @param handValue the value of the hand
     */
    public void setHandName(Integer handValue) {
        this.handValue = handValue;
        this.handName = "Hand" + Integer.toString(handValue);
    }

    /**
     * Passes a card from the player's hand to the corresponding deck.
     * The card is chosen based on the condition that it does not equal the player number.
     * The discarded card is removed from the player's hand and added to the deck.
     * The player's action is recorded in a file.
     * If the player's hand value is equal to the size of the hand array minus one, the card is added to the first deck in the deck array.
     * Otherwise, the card is added to the deck with a matching value to the player's hand value plus one.
     */
    public void passToDeck(){
        synchronized (passLock){
        Cards cardToDiscard = new Cards();

        for(Cards card : this.handCardArray){
            if (card.cardNumber != this.handValue + 1){ // Ensures card does not equal the player number
                cardToDiscard = card;
            }
        }
        this.handCardArray.remove(cardToDiscard); // Removes the card 
        int cardNumber = cardToDiscard.cardNumber;       
        String fileInput = "Player " + (this.handValue + 1) + " discards a " + cardNumber + " to deck " + this.handValue;
        updater.writePlayerAction("player" + this.handValue, fileInput);

        for(Decks deck : cardGame.deckArray){ // Searches through hands to find the one with a matching name to the current deck - e.g hand1 passes to deck1 and hand2 passes to deck2
            if(this.handValue == cardGame.handArray.size() - 1){                                          
                cardGame.deckArray.get(0).deckCardArray.add(cardToDiscard);
                break;
            }
            else if(deck.deckValue == this.handValue + 1){
                deck.deckCardArray.add(cardToDiscard);               
            }
        }
        }

        //make sure not to pass to a deck that doesn't exist(above playercount) have we done this?
    }

    /**
     * Checks if the hand has any cards.
     * 
     * @return true if the hand has cards, false otherwise.
     */
    public boolean checkHandHasCard(){ 
       if( this.handCardArray.size() == 0){        
        return false;
       }
        return true;
    }

    /**
     * This method checks if the current hand satisfies the win condition of the game.
     * It uses a flag to indicate whether the win condition is met. The flag is set to true 
     * if the hand meets the winning criteria, and false otherwise.
     * 
     * @return A boolean flag - returns true if the hand meets the win condition, false if it does not.
     */
    public Boolean checkWinCondition(){ 
        boolean flag = true;
        Integer tempValue = this.handCardArray.get(0).cardNumber;
        for(Cards card : this.handCardArray){
            if(card.cardNumber != tempValue){
                flag = false;
            }
        }

        if(flag) {
            cardGame.winningPlayerNo = this.handValue;
        }
        return flag;
    }
}
