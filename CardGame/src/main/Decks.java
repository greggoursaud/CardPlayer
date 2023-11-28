package CardGame.src.main;

import java.util.ArrayList;

public class Decks {
    public int deckValue;
    public String deckName;
    public ArrayList<Cards> deckCardArray = new ArrayList<Cards>();

    public Decks() {
        // No initialization needed here
    }

    /**
     * Constructs a new Decks object with the specified deck value.
     *
     * @param deckValue the value of the deck
     */
    public Decks(Integer deckValue) {
        this.deckValue = deckValue;
        System.out.print("The value of the created deck is" + deckValue);
        this.deckName = "Deck" + Integer.toString(deckValue);
    }

    public void setDeckName(Integer deckValue) {
        this.deckName = "Deck" + Integer.toString(deckValue);
    }

        public boolean isDeckEmpty() {
        return deckCardArray.isEmpty();
    }
    
    public void drawCard(){ // test this tommorow with dummy objects
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

       for(Hands hand : cardGame.handArray){ //searches through hands to find the one with a matching name to the current deck - e.g hand1 passes to deck1 and hand2 passes to deck2
            if(hand.handValue == this.deckValue){
                //Create logic to pass over card
                hand.handCardArray.add(tempCard);
                
            }
        }
    }

    // public synchronized Cards drawCard() {
    //     if (!deckCardArray.isEmpty()) {
    //         return deckCardArray.remove(deckCardArray.size() - 1);
    //     } else {
    //         return null;
    //     }
    // }

    public synchronized void addCard(Cards card) {
        deckCardArray.add(card);
    }
    





}
