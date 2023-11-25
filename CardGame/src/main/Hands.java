package CardGame.src.main;

import java.util.ArrayList;

public class Hands {
    public Integer handName;
    public ArrayList<Cards> handCardArray = new ArrayList<Cards>();

    public void passToDeck(){
        Cards cardToDiscard = new Cards();

        //create logic to only pass cards that don't have the player number in them 
        for(Cards card : this.handCardArray){
            if (card.cardNumber != this.handName){ //ensures card does not equal the player number
                cardToDiscard = card;
            }
        }



       


        this.handCardArray.remove(cardToDiscard); //removes the card 

        //make sure to pass to the deck with a number one higher - THERE is some error in this logic - must fix
        for(Decks deck : cardGame.deckArray){ //searches thru hands to find the one with a matching name to the current deck - e.g hand1 passes to deck1 and hand2 passes to deck2
            if(this.handName == cardGame.playersArray.size()){
                cardGame.deckArray.get(0).deckCardArray.add(cardToDiscard);
            }
            else if(deck.deckName == this.handName + 1){
                //Create logic to pass over card
                deck.deckCardArray.add(cardToDiscard);               
            }
        }


        //make sure not to pass to a deck that doesn't exist(above playercount)





        
    }


}
