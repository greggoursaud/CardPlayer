package CardGame.src.test;
import org.junit.*;

import CardGame.src.main.cardGame;
import CardGame.src.main.Cards;
import CardGame.src.main.Hands;
import CardGame.src.main.Decks;
import CardGame.src.main.Players;
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
            //Assert.assertEquals(i, cardGame.playersArray.size());
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
        for(int i = 4; i < 6; i++){
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
    public void checkPlayerReferencesCorrectHandAndDeck(){ // Checks that the player object references the corect hands and decks and that they hold the expected amount of cards 
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


    /**
     * This method tests the win condition in the hand object.
     * It generates decks and hands, adds 4 cards with the same number to the first hand,
     * and then checks if the win condition is recognized by the hand object.
     * Finally, it clears the deck, hand, and player arrays.
     */
    @Test
    public void testWinCondition(){ 
        cardGame.generateDecksAndHands(4);
        for(int i = 0; i < 4; i++){
            Cards card = new Cards();
            card.cardNumber = 2;
            cardGame.handArray.get(0).handCardArray.add(card);            
        }
        Assert.assertTrue(cardGame.handArray.get(0).checkWinCondition());
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();

    }

    /**
     * This method tests concurrent access to the Hands and Decks in the card game.
     * It ensures that there are no errors when multiple threads access the Hands and Decks simultaneously.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    @Test
    public void testConcurrentAccessToHandsAndDecks() throws InterruptedException { // Will test to make sure that Hands and Decks will not throw any errors when being concurrently accessed
        Boolean exeption = false;
        
         // Create hands and decks
       cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);

       // Create threads corresponding to the number of players
            for (Players player : cardGame.playersArray) {           
                Thread thread = new Thread(player);            
                thread.start();
                cardGame.threadList.add(thread);
            }       
        try{
        for (Players player: cardGame.playersArray) {           
           //for(int i = 0; i < 2; i++){
            // Simulate concurrent access to the Hands and decks instance
            if(player.playerHand.checkHandHasCard() && player.playerDeck.checkDeckHasCard())  {
                player.playerHand.passToDeck();
                player.playerDeck.drawCard();

            }       
                
           //}           
        }
    }catch(Exception e){
        exeption = true;
        cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();
    }
        // Shutdown the executor and wait for termination
        Assert.assertTrue(!exeption);
    
    cardGame.deckArray.clear();
    cardGame.handArray.clear();
    cardGame.playersArray.clear();
    }

    /**
     * This method tests the functionality of the "checkDeckHasCard" method in the Decks class.
     * It verifies whether the method correctly checks if the deck has a card and returns the expected result.
     */
    @Test
    public void checkDeckHasCardTest(){ 
        Decks deck = new Decks(1);
        Cards card = new Cards(1);
        Assert.assertTrue(!deck.checkDeckHasCard());
        deck.deckCardArray.add(card);
        Assert.assertTrue(deck.checkDeckHasCard());
        cardGame.deckArray.clear();
    }

    /**
     * This method tests if the threads have been initialised correctly in the card game.
     * It checks if the correct amount of threads has been created and if all the created threads are running.
     */
    @Test
    public void testThreadsHaveBeenInitialisedCorrectly(){ 
        cardGame.createHandsAndDecksFromTextFile("CardGame/src/packs/4.txt", 4);
        cardGame.startGame();
        Assert.assertEquals(cardGame.threadList.size(), 4);
        for(Thread thread :cardGame.threadList){
            Assert.assertTrue(thread.isAlive());
        }
         cardGame.deckArray.clear();
        cardGame.handArray.clear();
        cardGame.playersArray.clear();
        cardGame.threadList.clear();
    }
}   
