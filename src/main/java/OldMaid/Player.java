/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldMaid;

import java.util.LinkedList;

/**
 *
 * @author
 */
class Player {
      String name;
      Hand hand;

      public Player(String name, Hand hand) {
            this.name = name;
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
}
