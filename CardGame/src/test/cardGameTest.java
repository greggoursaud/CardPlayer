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
    public void checkPlayerNumber(){         //Tests to make sure the number of hands created matches the number of players created
        cardGame.generateDecksAndHands(3); 
        Assert.assertEquals(3, cardGame.deckArray.size());
        Assert.assertEquals(3, cardGame.handArray.size());
        Assert.assertEquals(3, cardGame.playersArray.size());
    }   



}   
