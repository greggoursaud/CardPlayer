package CardGame.src.test;
import org.junit.*;
import CardGame.src.main.cardGame;
import CardGame.src.main.Cards;
public class cardGameTest {
    
    /**
     * This method is a unit test that checks if the project compiles with no errors.
     * It calls the `testTest()` method from the `cardGame` class and asserts that the returned value is "Hello".
     */
    @Test
    public void testTest(){ 
        Assert.assertEquals("Hello", cardGame.testTest());
    }

    /**
     * This method tests to make sure the number of hands created matches the number of players created for every possible player number.
     * It generates decks and hands for each player number, and then asserts that the size of the deckArray, handArray, and playersArray is equal to the player number.
     * After each test, it clears the deckArray, handArray, and playersArray.
     */
    @Test
    public void checkPlayerNumber(){
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

    /**
     * This method tests to make sure all hands hold 4 cards at the start of the game for every possible player number.
     * It shuffles the content of a text file, creates hands and decks based on the file, and then checks
     * that each hand contains 4 cards.
     */
    @Test
    public void checkAllHands(){  
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

    /**
     * This method tests to make sure all decks hold 4 cards at the start of the game for every possible player number.
     * It shuffles the content of the text file, creates hands and decks from the text file,
     * and then checks if each deck has 4 cards.
     */
    @Test
    public void checkAllDecks(){  
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


    /**
     * This method tests the functionality of moving cards from hands to decks.
     * It shuffles the content of a text file, creates hands and decks from the shuffled file,
     * and then checks if the cards are successfully moved from the hand to the deck.
     * It asserts the expected sizes of the hand and deck arrays before and after the move.
     * Finally, it clears the deck, hand, and player arrays.
     */
    @Test
    public void checkCardMovesHandToDeck(){ 
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

    /**
     * This test method checks if the cards stored in the highest hand value are given to decks with the lowest value.
     * It performs the following steps:
     * 1. Shuffles the file content from "CardGame/src/packs/4.txt".
     * 2. Creates hands and decks from the text file "CardGame/src/packs/4.txt" with 4 players.
     * 3. Asserts that the hand at index 3 has 4 cards.
     * 4. Prints the size of the deck at index 3.
     * 5. Passes the cards from the hand at index 3 to the corresponding deck.
     * 6. Prints the size of the deck at index 3 after passing the cards.
     * 7. Asserts that the hand at index 3 has 3 cards.
     * 8. Asserts that the deck at index 0 has 5 cards.
     * 9. Clears the deckArray, handArray, and playersArray.
     */
    @Test
    public void checkCardsWrapAround(){ 
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
    
    /**
     * This method tests the functionality of moving cards from the deck to the hand in the card game.
     * It performs the following steps:
     * 1. Shuffles the content of a text file containing card information.
     * 2. Creates hands and decks based on the shuffled content.
     * 3. Asserts that the initial hand size is 4.
     * 4. Draws a card from the deck.
     * 5. Asserts that the deck size is reduced by 1 and the hand size is increased by 1.
     * 6. Clears the deck, hand, and player arrays.
     */
    @Test
    public void checkCardMovesDeckToHand(){
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

    /**
     * This test is to check that hands prioritize the cards with the correct card number.
     * It generates decks and hands, adds cards to the hand with different card numbers,
     * and then verifies that the hand chooses the card that doesn't have the same number as it.
     */
    @Test
    public void checkHandPrioritisesCards(){
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
        cardGame.handArray.get(1).passToDeck(); 
        for(int x = 0; x < cardGame.handArray.get(1).handCardArray.size() - 1; x++){            
            Assert.assertEquals(cardGame.handArray.get(1).handCardArray.get(x).cardNumber, Integer.valueOf(2));            
        }
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();
    }   
}   
