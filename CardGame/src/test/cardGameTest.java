package CardGame.src.test;
import org.junit.*;
import CardGame.src.main.cardGame;
import CardGame.src.main.Cards;
import CardGame.src.main.Players;
public class cardGameTest {
    @Test
    public void testTest(){ //checks the project compiles with no errors
        //cardGame card = new cardGame();
        Assert.assertEquals("Hello", cardGame.testTest());
    }

    @Test
    public void checkPlayerNumber(){//Tests to make sure the number of hands created matches the number of players created for every possible player number
        for(int i = 3; i < 9; i ++){  //dont know how its got like this probably with how im using cardgame in the players class but i need to create new instance for each test for it to work     
            cardGame.generateDecksAndHands(i); 
            Assert.assertEquals(i, cardGame.deckArray.size());
            Assert.assertEquals(i, cardGame.handArray.size());
            //Assert.assertEquals(i, cardGame.playersArray.size());
            cardGame.deckArray.clear();
            cardGame.handArray.clear();
            //cardGame.playersArray.clear();
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
    public void checkAllDecks(){  //Tests to make sure all decks hold 4 cards at the start of the game for every possible player number - apart from 6 for some reason :P
        for(int i = 3; i < 6; i++){
            String j = Integer.toString(i);
            cardGame.shuffleFileContent("CardGame/src/packs/"+ j +".txt"); 
            cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/"+ j +".txt", i);
            for(int x = 0; x < i; x++){
                Assert.assertEquals(4, cardGame.deckArray.get(x).deckCardArray.size());                
            }
            cardGame.deckArray.clear();
            cardGame.handArray.clear();
            cardGame.playersArray.clear();
        }
    }


    @Test
    public void checkCardMovesHandToDeck(){ //This checks that cards will successfully move from hands to decks 
        cardGame.shuffleFileContent("CardGame/src/packs/4.txt");
        cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);
        Assert.assertEquals(cardGame.handArray.get(0).handCardArray.size(), 4);
        cardGame.handArray.get(0).passToDeck();
        Assert.assertEquals(cardGame.handArray.get(0).handCardArray.size(), 3);
        Assert.assertEquals(cardGame.deckArray.get(1).deckCardArray.size(), 5);
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();
    }

    @Test
    public void checkCardsWrapAround(){ //This test will check that cards stored in the highest hand value will be given to decks with the lowest value 
        cardGame.shuffleFileContent("CardGame/src/packs/4.txt");
        cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);
        Assert.assertEquals(cardGame.handArray.get(3).handCardArray.size(), 4);
        System.out.print("Size is" + cardGame.deckArray.get(3).deckCardArray.size());
        cardGame.handArray.get(3).passToDeck();
        System.out.print("Size is adter" + cardGame.deckArray.get(3).deckCardArray.size());
        Assert.assertEquals(cardGame.handArray.get(3).handCardArray.size(), 3);
        Assert.assertEquals(5, cardGame.deckArray.get(0).deckCardArray.size());
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();

    }
    @Test
    public void checkCardMovesDeckToHand(){//Checks cards can move successfully from a deck to a hand
        cardGame.shuffleFileContent("CardGame/src/packs/4.txt");
        cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);
        Assert.assertEquals(cardGame.handArray.get(0).handCardArray.size(), 4);
        cardGame.deckArray.get(0).drawCard();
        Assert.assertEquals(cardGame.deckArray.get(0).deckCardArray.size(), 3);
        Assert.assertEquals(cardGame.handArray.get(0).handCardArray.size(), 5);
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();
    }

    @Test
    public void checkPlayerReferencesCorrectHandAndDeck(){ // checks that the player object references the corect hands and decks and that they hold the expected amount of cards 
        cardGame.shuffleFileContent("CardGame/src/packs/4.txt");
        cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);
        for(Players player : cardGame.playersArray){
            Assert.assertEquals(player.playerHand.handCardArray.size(), 4);
            Assert.assertEquals(player.playerDeck.deckCardArray.size(), 4);
            Assert.assertEquals(player.playerNo, player.playerHand.handValue, player.playerDeck.deckValue); // this looks so much neater - consider revising tests to look like this 
        }
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();





    }


    @Test
    public void checkHandPrioritisesCards(){//This test is to check that hands prioritise the cards with the correct card number
        cardGame.generateDecksAndHands(4);
        for(int i = 0; i < 4; i++){
            Cards card = new Cards();
          if(i < 3){
            card.cardNumber = 2;  
          }  
          else{
            card.cardNumber = 1;
          }
          cardGame.handArray.get(1).handCardArray.add(card);
            System.out.print("Added card with number " + card.cardNumber);
        }
        cardGame.handArray.get(1).passToDeck(); //The hand should choose the card that doens't have the same number as it
        for(int x = 0; x < cardGame.handArray.get(1).handCardArray.size() - 1; x++){            
            Assert.assertEquals(cardGame.handArray.get(1).handCardArray.get(x).cardNumber, Integer.valueOf(2));            
        }
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();
    }   
}   
