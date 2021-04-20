/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldMaid;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Seiji Dominic Bautista
 */
class Player {
      private String name;
      private final Hand hand;

      public Player(String name, Hand hand) {
            setName(name);
            this. hand = hand;
      }

      public Player(String name) {
            this.name = name;
            hand = new Hand();
      }

      public void addToHand(Card card) {
            hand.addToHand(card);
      }

      public Card disposeCard(int index) {
            return hand.disposeCard(index);
      }

      public Hand getHand() {
            return hand;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            String first = name.substring(0, 1);
            String last = name.substring(1, name.length() + 1);
            if (Character.isAlphabetic(name.charAt(0))) {
                  first = first.toUpperCase();
            }

            this.name = first.concat(last);
      }

//      public String toString() {
//            return String.format("%s has %d cards in hand.", name, hand.size());
//      }

      public String toString() {
            return name;
      }

}
