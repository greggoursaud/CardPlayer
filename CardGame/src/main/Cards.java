package CardGame.src.main;

public class Cards {
    private Integer cardNumber;
    private String currentDeck;
    private String currentHand;
    private Integer heirarchy;

    //default constructor class
    public Cards() {
    }

    //creates a cards object 
    public Cards(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Getter methods
    public Integer getCardNumber() {
        return this.cardNumber;
    }

    public String getCurrentDeck() {
        return this.currentDeck;
    }

    public String getCurrentHand() {
        return this.currentHand;
    }

    public Integer getHeirarchy() {
        return this.heirarchy;
    }

    public void setHeirarchy(Integer heirarchy) {
        this.heirarchy = heirarchy;
    }

    public void passCard(Integer passNo) {
        // your code here
    } 
}
