/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldMaid;

import OldMaid.LinkedNodes.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Seiji Dominic Bautista
 */
public class OldMaid {
	 private static LinkedNodes<Player> players;
	 private static Card oldMaid;
	 private static Deck deck;

     public OldMaid(List<Player> playerList) {
     	deck = Deck.getDeck();
     	oldMaid = deck.pickCard(Card.Suit.HEARTS, Card.Value.QUEEN);
     	CardDistributor.distribute(deck, playerList);
     	players = new LinkedNodes<>(playerList, true);
	 }

	 public static void start() {

     	//Automatically dispose all of possible pairs. (Happens only at the start of the game)
		for (int i = 0; i < players.size(); i ++) {
			players.getCurrentData().getHand().disposePairs();
			players.next();
		}
		System.out.println("All the pairs in players' hand are discarded.");

     	Scanner s = new Scanner(System.in);
     	Player previous = null;
     	Player current = players.getCurrentData();
     	Hand previousHand = null;
     	Hand currentHand = current.getHand();

     	//Turn starts here
     	do {
			System.out.println("\n" + current.getName() + "'s turn.\n");

			//pick card from previous player.
			//If currentPlayer is the first player, skip this if block
     		if (previous != null) {
     			previousHand = previous.getHand();
     			System.out.println(previous + " Pick one!");
     			for (int i = 1; i <= previousHand.size(); i++) {
     				System.out.printf("    %d    ", i);
				}
				System.out.println();

				int userInput;
     			do {
     				userInput = s.nextInt();
     				s.nextLine();
				} while(userInput > previousHand.size());

     			Card picked = previousHand.disposeCard(--userInput);
				System.out.printf("You picked %s from %s!\n", picked, previous.getName());
     			current.addToHand(picked);
     			currentHand = current.getHand();
			}

     		//
			String input;
			Card first;
			Card second;
			String[] index;
			do {
				if (currentHand.isEmpty()){
					System.out.printf("%s is safe!", current.getName());
					break;
				} else if (currentHand.size() == 1 && players.size() == 1) {

				}

				System.out.println("The cards currently in your hand.");
				for (int i = 0; i < currentHand.size(); i++) {
					System.out.printf("%d. %s\n", i + 1, currentHand.get(i));
				}
				System.out.println("Enter the number of the pair of cards separated by a whitespace. " +
						"Type in \"end\" to end turn");


				input = s.nextLine();
				if (input.equalsIgnoreCase("end"))
					break;
				else if (input.equalsIgnoreCase("sort")) {
					currentHand.sortCards();
				} else if (input.equalsIgnoreCase("shuffle")) {
					currentHand.shuffleCards();
				}

				try {
					index = input.split(" ");
					first = currentHand.get(Integer.parseInt(index[0]) - 1);
					second = currentHand.get(Integer.parseInt(index[1]) - 1);


				if (first.compareTo(second) == 0) {
					System.out.printf("Removing %s and %s from your hand.\n", first, second);
					currentHand.remove(first);
					currentHand.remove(second);
				} else {
					System.out.printf("%s and %s does not have the same value.\n", first, second);
				}
				} catch (NumberFormatException e) {
					System.out.println("Please Enter a valid number separated by a whitespace or enter \"end\" to " +
							"end turn");
				}
			} while(!input.equalsIgnoreCase("end"));

     		//Throw pair of cards away

     		if (current.getHand().isEmpty()) {
				players.destroyCurrent();

			} else {
     			previous = current;
     			players.next();
			}
			current = players.getCurrentData();

			if (players.size() == 1) {
				System.out.println("WE'VE GOT A LOSER!!!");
				break;
			}

		} while (players.size() > 1);

	 }

	 public Player getCurrent() {
		return players.getCurrentData();
	 }
}
