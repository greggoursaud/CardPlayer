package CardGame;
public class Cards {
    Integer cardNumber;
    String currentDeck;

    //default constructor class
    public Cards(){
        return;
    }
    //creates a cards object - im not 100 sure we want to use objects for cards (could just use an array instead and scrap the currentDeck property) but for now this
    public Cards(Integer cardNumber){
        this.cardNumber = cardNumber;
    }

}
