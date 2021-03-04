/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldMaid;

import java.util.ArrayList;
import java.util.Random;

/**
 * Holds all available cards.
 * @author Seiji Dominic Bautista
 */
public class Deck {
     private final boolean[][] cards;
     private int cardsInDeck;
     Random random = new Random();

     /**
      * Instantiate boolean[][] cards and set all flags to true.
      * Instantiate cardsInDeck (All available cards)
      */
     public Deck() {
          cardsInDeck = 3;
          cards = new boolean[4][14];
          for (int s = 0; s < 4; s++) {
               for (int f = 0; f < 14; f++) {
                    cards[s][f] = true;
               }
          }
     }

     /**
      * This method iterates through the cards array and returns true if
      * card is available and returns false if not.
      *
      * This method is the only method that can change the boolean flags and
      * decrement the value of cardsInDeck. I can say that this is the
      * heart of this class.
      *
      * THROWS ASSERTION ERROR ONCE ALL CARDS IN THE DECK ARE GONE SO PLEASE
      * CATCH IT. Thanks :)
      *
      * @param suit Number representation of suit
      * @param value Card value
      * @return card availability.
      */
     public boolean checkAvailability(int suit, int value) throws AssertionError {
          boolean suitAvailable = cards[suit][0];
          boolean cardAvailable = false;

          //check if there are still cards available in the deck.
          if (isEmpty()) {
               throw new AssertionError("There are no cards " +
                       "available in the deck.");
          }

          //check if this suit of cards are available.
          if (!suitAvailable) {
               return false;
          }

          //check card availability.
          if (cards[suit][value]) {
               cards[suit][value] = false;
               cardsInDeck--;
               cardAvailable = true;
          }

          /*Iterate through the cards and see if there are still
          cards available in the suit. If none, change the first flag in the arr
          which indicates the availability of the suit.
           */
          for (int i = 1; i < 14; i++) {
               suitAvailable = cards[suit][1] || cards[suit][i];
          }

          //set the suit flag to false.
          if (!suitAvailable) {
               cards[suit][0] = false;
          }

          return cardAvailable;
     }

     /**
      * Pick a specific Card.
      * @param suit card suit
      * @param number card value
      * @return the Card if available. Return null otherwise.
      */
     public Card pickCard(int suit, int number) {
          try {
               if (checkAvailability(suit, number)) {
                    cards[suit][number] = false;
                    return new Card(suit, number);
               }
          } catch (AssertionError e) {
               System.out.println("All cards in the deck are gone.");
               return null;
          }

          System.out.println("This card is not available.");
          return null;
     }

     /**
      * Pick a random Card
      * @return a random unique Card.
      */
     public Card pickCard() {
          int suit = random.nextInt(4);
          int val = random.nextInt(13) + 1;

          try {
               while(!checkAvailability(suit, val)){
                    suit = random.nextInt(4);
                    val = random.nextInt(13) + 1;
               }
          } catch(AssertionError e) {
               System.out.println("All cards in the deck are gone.");
               return null;
          }

          return new Card(suit, val);
     }

     /**
      * Just return the count of the cards in the deck.
      * I trust my checkAvailability() method.
      * @return cardsInDeck
      */
     public int countCards() {
          return cardsInDeck;
     }

     /**
      *
      * @return boolean
      */
     public boolean isEmpty() {
          return !(cards[0][0] && cards[1][0] && cards[2][0] && cards[3][0]);
     }

     public boolean contains(Card card) {
          return cards[card.getSuit().getNumber()][card.getValue()];
     }

     /**
      * Accepts an arraylist of players and distribute the cards to those
      * players evenly. Left cards are distributed to random players.
      * @param players ArrayList of players
      */
     public void distribute(ArrayList<Player> players) {
          // TODO: 2021-03-03 Implement card distribution once Player class is finished.
     }
}