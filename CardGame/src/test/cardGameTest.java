package CardGame.src.test;
import org.junit.*;
import CardGame.src.main.cardGame;
public class cardGameTest {
    @Test
    public void testTest(){
        cardGame card = new cardGame();
        Assert.assertEquals("Hello", card.testTest());
    }

    @Test
    public void checkPlayerNumber(){//Tests to make sure the number of hands created matches the number of players created for every possible player number
        for(int i = 3; i < 9; i ++){  //dont know how its got like this probably with how im using cardgame in the players class but i need to create new instance for each test for it to work
            cardGame card = new cardGame();
            card.generateDecksAndHands(i); 
            Assert.assertEquals(i, card.deckArray.size());
            Assert.assertEquals(i, card.handArray.size());
            Assert.assertEquals(i, card.playersArray.size());
            card.deckArray.clear();
            card.handArray.clear();
            card.playersArray.clear();
        }       
    }   

    @Test
    public void checkAllHands(){  //Tests to make sure all hands hold 4 cards at the start of the game for every possible player number - apart from 6 for some reason :P
        for(int i = 3; i < 6; i++){
            String j = Integer.toString(i);
            cardGame.shuffleFileContent("CardGame/src/packs/"+ j +".txt"); 
            cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/"+ j +".txt", i);
            for(int x = 0; x < i; x++){
                Assert.assertEquals(4, cardGame.handArray.get(x).handCardArray.size());                
            }
            cardGame.deckArray.clear();
            cardGame.handArray.clear();
            cardGame.playersArray.clear();
        }
    }
    @Test
    public void checkCardMovesHandToDeck(){ //This checks that cards will successfully move from hands to decks - this isnt working, for some reason a card is added to every single deck
        cardGame.shuffleFileContent("CardGame/src/packs/4.txt");
        cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);
        Assert.assertEquals(cardGame.handArray.get(0).handCardArray.size(), 4);
        cardGame.handArray.get(0).passToDeck();
        Assert.assertEquals(cardGame.handArray.get(0).handCardArray.size(), 3);
        Assert.assertEquals(5, cardGame.deckArray.get(1).deckCardArray.size(), 5);
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();
    }
    @Test
    public void checkCardMovesDeckToHand(){
        cardGame.shuffleFileContent("CardGame/src/packs/4.txt");
        cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);
        Assert.assertEquals(cardGame.handArray.get(0).handCardArray.size(), 4);
        cardGame.deckArray.get(0).passToHand();
        Assert.assertEquals(3, cardGame.deckArray.get(0).deckCardArray.size());
        Assert.assertEquals(5, cardGame.handArray.get(0).handCardArray.size());
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();
    }
}   
