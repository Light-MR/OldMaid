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
     private static Deck deck = null;
     private final boolean[][] cards;
     private int cardsInDeck;
     private static Random random;


     /**
      * Instantiate boolean[][] cards and set all flags to true.
      * Instantiate cardsInDeck (All available cards)
      */
     private Deck() {
          random = new Random();
          cards = new boolean[4][14];
          for (int s = 0; s < 4; s++) {
               for (int f = 0; f < 14; f++) {
                    cards[s][f] = true;
               }
          }
          countCards();
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
               suitAvailable = cards[suit][i];
               if (suitAvailable) break;
          }

          //set the suit flag to false.
          if (!suitAvailable) {
               cards[suit][0] = false;
          }

          return cardAvailable;
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
      * Gets the card suit and value using the Card.Suit and Card.Value enums.
      * @param suits Card.Suit
      * @param value Card.Value
      * @return Available card
      */
     public Card pickCard(Card.Suit suits, Card.Value value) {
          int suit = suits.getNumber();
          int number = value.getNumber();

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
      * Just return the count of the cards in the deck.
      * I trust my checkAvailability() method.
      * @return cardsInDeck
      */
     public int countCards() {
          int count = 0;

          for (boolean[] suit : this.cards) {
               for (int i = 1; i < 14; i++) {
                    if (suit[i]) count++;
               }
          }

          cardsInDeck = count;
          return count;
     }

     /**
      * Checks if there are still cards in the deck.
      * @return boolean
      */
     public boolean isEmpty() {
          return !(cards[0][0] || cards[1][0] || cards[2][0] || cards[3][0]);
     }

     public boolean contains(Card card) {
          int suit = card.getSuit().getNumber();
          int value = card.getValue();
          return cards[suit][value];
     }

     public static Deck getDeck() {
          if (deck == null) {
               deck = new Deck();
          }
          return deck;
     }

     public int getCardsInDeck() {
          return cardsInDeck;
     }
}