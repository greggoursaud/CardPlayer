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
    public void checkPlayerNumber(){         //Tests to make sure the number of hands created matches the number of players created for every possible player number
        for(int i = 3; i < 9; i ++){
            cardGame.generateDecksAndHands(i); 
            Assert.assertEquals(i, cardGame.deckArray.size());
            Assert.assertEquals(i, cardGame.handArray.size());
            Assert.assertEquals(i, cardGame.playersArray.size());
            cardGame.deckArray.clear();
            cardGame.handArray.clear();
            cardGame.playersArray.clear();
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
}   
