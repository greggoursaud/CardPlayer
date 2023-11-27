package CardGame.src.main;

import java.util.ArrayList;

public class Hands {
    private int handValue;
    public String handName;
    private ArrayList<Cards> handCardArray = new ArrayList<>();
    private cardGame cardGame; // Add a reference to the CardGame object

    public Hands(cardGame cardGame) { // Modify the constructor to accept a CardGame object
        this.cardGame = cardGame;
    }

    public Hands() {
        // No initialization needed here
    }

    public int getHandValue() {
        return handValue;
    }

    public ArrayList<Cards> getHandCardArray() {
        return handCardArray;
    }

    public Hands(Integer handValue) {
        this.handValue = handValue;
        this.handName = "Hand" + Integer.toString(handValue);
    }

    public void setHandName(Integer handValue) {
        this.handName = "Hand" + Integer.toString(handValue);
    }

    // public void passToDeck(){
    //     Cards cardToDiscard = new Cards();

    //     //create logic to only pass cards that don't have the player number in them 
    //     for(Cards card : this.handCardArray){
    //         if (card.getCardNumber() != this.handValue){ //ensures card does not equal the player number
    //             cardToDiscard = card;
    //         }
    //     }



       


    //     this.handCardArray.remove(cardToDiscard); //removes the card 

    //     //make sure to pass to the deck with a number one higher - THERE is some error in this logic - must fix
    //     // havent tested this yet but?
    //     // The issue might be with the way you're checking if the current hand is the last one. You're comparing this.handValue with cardGame.playersArray.size(), 
    //     //but handValue starts from 1 while array indices start from 0. So, if you have 4 players, the last player's handValue will be 4, but playersArray.size() 
    //     //will also be 4, so the condition this.handValue == cardGame.playersArray.size() will be true for the last player, but it should be false.
    //     for(Decks deck : cardGame.deckArray){ //searches through hands to find the one with a matching name to the current deck - e.g hand1 passes to deck1 and hand2 passes to deck2
    //         if(this.handValue == cardGame.playersArray.size() - 1){                                
    //             cardGame.deckArray.get(0).deckCardArray.add(cardToDiscard);
    //         }
    //         else if(deck.deckValue == this.handValue + 1){
    //             //Create logic to pass over card
    //             deck.deckCardArray.add(cardToDiscard);               
    //         }
    //     }


    //     //make sure not to pass to a deck that doesn't exist(above playercount)
   
    // }

    public synchronized void discardCard() {
        Cards cardToDiscard = null;

        // Select a card to discard.
        for (Cards card : handCardArray) {
            if (card.getCardNumber() != handValue) { //ensures card does not equal the player number
                cardToDiscard = card;
                break;
            }
        }

        if (cardToDiscard != null) {
            // Remove the card from the hand.
            handCardArray.remove(cardToDiscard);

            // Add the card to the appropriate deck.
            if (handValue == cardGame.getPlayersArray().size() - 1) {
                cardGame.getDeckArray().get(0).addCard(cardToDiscard);
            } else {
                for (Decks deck : cardGame.getDeckArray()) {
                    if (deck.getDeckValue() == handValue + 1) {
                        deck.addCard(cardToDiscard);
                        break;
                    }
                }
            }
        }
    }

    public void addCard(Cards card) {
        handCardArray.add(card);
    }

    public void removeCard(Cards card) {
        handCardArray.remove(card);
    }

    public Cards getLatestCard() {
        if (!handCardArray.isEmpty()) {
            return handCardArray.get(handCardArray.size() - 1);
        }
        return null;
    }

    /**
     * Checks if the hand is a winning hand.
     * A winning hand is defined as a hand with exactly 4 cards, all having the same card number.
     *
     * @return true if the hand is a winning hand, false otherwise.
     */
    public boolean hasMatchingCards() {
        if (handCardArray.size() != 4) {
            return false;
        }
        int firstCardNumber = handCardArray.get(0).getCardNumber();
        for (Cards card : handCardArray) {
            if (card.getCardNumber() != firstCardNumber) {
                return false;
            }
        }
        return true;
    }


}
