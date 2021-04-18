/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldMaid;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author
 */
public class Hand extends LinkedList<Card> {


	public void addToHand(Card card) {
		add(card);
	}

	public Card disposeCard(int index) {
		Card toBeDisposed = get(index);
		remove(index);
		return toBeDisposed;
	}

	@Override
	public void sort(Comparator<? super Card> c) {
		super.sort(c);
	}

	public void sortCards() {
		this.sort(Card::compareTo);
	}

	public void shuffleCards() {
		Collections.shuffle(this);
	}

	public void disposePairs() {
		sortCards();

		int perma = 0;
		int tem;

		while (perma + 1 <  this.size()) {
			tem = perma + 1;
			Card c1 = get(perma);
			Card c2 = get(tem);

			if (c1.compareTo(c2) == 0) {
				remove(tem);
				remove(perma);
			} else {
				perma++;
			}
		}

		System.out.println(this);

	}
}
