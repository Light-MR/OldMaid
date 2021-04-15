package OldMaid;

import java.util.*;

/**
 * A class whose sole purpose is to distribute cards. (Oh god)
 * @author Dominic
 */
public class CardDistributor {
    private static final Deck deck = Deck.getDeck(); //Still not sure about the Deck implementing Singleton pattern
    private static final Random rand = new Random();

    /**
     * Hand Cards = (Playing cards) / (Player Number);
     * (While there are left cards, randomly distribute);
     * @param players
     */
    public static void distribute(List<Player> players) {
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
