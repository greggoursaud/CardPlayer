package CardGame.src.main;

import java.util.ArrayList;

public class Hands {
    public int handValue;
    public String handName;
    public ArrayList<Cards> handCardArray = new ArrayList<>();
    private final Object passLock = new Object();
    
    public Hands() {
        // No initialization needed here
    }

    public Hands(Integer handValue) {
        this.handValue = handValue;
        this.handName = "Hand" + Integer.toString(handValue);       
    }

    public void setHandName(Integer handValue) {
        this.handName = "Hand" + Integer.toString(handValue);
    }

    public void passToDeck(){
        synchronized (passLock){
        Cards cardToDiscard = new Cards();

        //create logic to only pass cards that don't have the player number in them 
        for(Cards card : this.handCardArray){
            if (card.cardNumber != this.handValue){ //ensures card does not equal the player number
                cardToDiscard = card;
            }
        }
        this.handCardArray.remove(cardToDiscard); //removes the card 
        //make sure to pass to the deck with a number one higher - THERE is some error in this logic - must fix
        // havent tested this yet but?
        // The issue might be with the way you're checking if the current hand is the last one. You're comparing this.handValue with cardGame.playersArray.size(), 
        //but handValue starts from 1 while array indices start from 0. So, if you have 4 players, the last player's handValue will be 4, but playersArray.size() 
        //will also be 4, so the condition this.handValue == cardGame.playersArray.size() will be true for the last player, but it should be false.
        for(Decks deck : cardGame.deckArray){ //searches through hands to find the one with a matching name to the current deck - e.g hand1 passes to deck1 and hand2 passes to deck2
            if(this.handValue == cardGame.handArray.size() - 1){                                          
                cardGame.deckArray.get(0).deckCardArray.add(cardToDiscard);
                break;
            }
            else if(deck.deckValue == this.handValue + 1){
                //Create logic to pass over card
                deck.deckCardArray.add(cardToDiscard);               
            }
        }
        }

        //make sure not to pass to a deck that doesn't exist(above playercount) 
    }

    public boolean checkHandHasCard(){ //checks if the hand this object contains has any available cards to take 
       if( this.handCardArray.size() == 0){
        System.out.println("No cards to take");
        return false;
       }
        return true;
    }

    public Boolean checkWinCondition(){ // checks to see if the current hand object has achieved the win condition of 4 card objects with the same value
        boolean flag = true;
        Integer tempValue = this.handCardArray.get(0).cardNumber;
        for(Cards card : this.handCardArray){
            if(card.cardNumber != tempValue){
                flag = false;
            }
        }
        return flag;
    }
}
