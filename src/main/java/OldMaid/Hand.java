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
public class Hand {

	LinkedList<Card> hand = new LinkedList<>();

	public Hand() {
	}

	public void addToHand(Card card) {
		hand.add(card);
	}

	public Card disposeCard(int index) {
		index--;
		Card toBeDisposed = hand.get(index);
		hand.remove(index);
		return toBeDisposed;
	}
}
