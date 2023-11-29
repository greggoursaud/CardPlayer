package CardGame.src.main;

public class Cards {
    public Integer cardNumber;
    public String currentDeck;
    public String currentHand;
    public Integer heirarchy;

    //default constructor class
    public Cards() {
    }

    //creates a cards object 
    public Cards(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }
   
}
