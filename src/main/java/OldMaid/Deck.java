/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldMaid;

import java.util.ArrayList;
import java.util.Random;

/**
 * Holds all available cards. UTILITY CLASS.
 * @author Seiji Dominic Bautista
 */
public class Deck {
     //I will use the first col of flags for suit availability.
     private static final boolean[][] cards = new boolean[4][14];
     public static int count = 52;
     static Random random = new Random();

     static {
          for (int s = 0; s < 4; s++) {
               for (int f = 0; f < 14; f++) {
                    cards[s][f] = true;
               }
          }
     }

     public boolean contains(Card card) {
          return cards[card.getSuit().getNumber()][card.getValue()];
     }

     /**
      * Pick a specific Card
      * @param suit
      * @param number
      * @return
      */
     public static Card pickCard(int suit, int number) {
          if (checkAvailability(suit, number)) {
               cards[suit][number] = false;
               return new Card(suit, number);
          }
          return null;
     }

     /**
      * Pick a random Card
      * @return
      */
     public static Card pickCard() {
          int suit, val;
          do {
//               suit = random.nextInt(4);
//               val = random.nextInt(13) + 1;
               suit = (int) (Math.random() * 4);
               val = (int) (Math.random() * 13) + 1;
          } while(!checkAvailability(suit, val));
          return new Card(suit, val);
     }

     public static boolean checkAvailability(int suit, int value) {
          boolean suitAvailable = cards[suit][0];
          boolean cardAvailable = false;

          //check if this suit of cards are available.
          if (!suitAvailable) {
               return false;
          }

          //check card availability.
          if (cards[suit][value]) {
               cards[suit][value] = false;
               count--;
               cardAvailable = true;
          }

          //Check suit availability. Changes cards[][0] if incorrect.
          for (int i = 1; i < 14; i++) {
               suitAvailable = cards[suit][0] || cards[suit][i];
          }

          //set the suit flag to false.
          if (!suitAvailable) {
               cards[suit][0] = false;
          }

          return cardAvailable;
     }

//     public static int countCards() {
//          int count = 0;
//          for (int i = 0; i < 4; i++) {
//               for (boolean b : cards[i]) {
//                    if (b) count++;
//               }
//          }
//          return count;
//     }

     public static boolean isEmpty() {
          return cards[0][0] && cards[1][0] && cards[2][0] && cards[3][0];
     }

     //==================added 2/3===========================



     public static void distribute(ArrayList<Player> players) {
          int cardsPerPerson = count / players.size();

          for (Player player : players) {
               for (int j = 0; j < cardsPerPerson; j++) {
                    player.addToHand(pickCard());
               }
          }
     }
}
