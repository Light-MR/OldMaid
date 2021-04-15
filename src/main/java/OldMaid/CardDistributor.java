package OldMaid;

import java.util.*;

public class CardDistributor {
    private static final Deck deck = Deck.getDeck();
    private static final Random rand = new Random();

    public static void distribute(ArrayList<Player> players) {
        int handSize = deck.getCardsInDeck() / players.size();
        players.forEach(player -> {
            for (int i = 0; i < handSize; i++) {
                player.addToHand(deck.pickCard());
            }
        });

        while (!deck.isEmpty()) {
            players.get(rand.nextInt(players.size())).addToHand(deck.pickCard());
        }
    }

}
