package CardGame.src.main;

public class Cards {
    public Integer cardNumber;
    public String currentDeck;
    public String currentHand;
    public Integer heirarchy;

    
    /**
     * Represents a deck of cards.
     */
    public Cards() {
        
    }


    /**
     * Constructs a new Cards object with the specified card number.
     *
     * @param cardNumber the number of the card
     */
    public Cards(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void passCard(Integer passNo) {
        // your code here
    } 

    /**
     * Returns a string representation of the card.
     *
     * @return the string representation of the card
     */
    @Override
    public String toString() {
        return String.valueOf(cardNumber);
    }
    
}
