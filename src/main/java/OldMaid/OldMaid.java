/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldMaid;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Seiji Dominic Bautista
 */
public class OldMaid {
	 LinkedNodes<Player> players;
	 Card oldMaid;
	 Deck deck;

     public OldMaid() {
     	deck = Deck.getDeck();
     	oldMaid = deck.pickCard(Card.Suit.HEARTS, Card.Value.QUEEN);
	 }
}
