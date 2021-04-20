package OldMaid;

import java.util.List;

/**
 *
 * @author Seiji Dominic Bautista
 */
abstract class CardGame {
    protected LinkedNodes<Player> players;
    protected static Deck deck;

    protected CardGame(List<Player> players) {
        this.players = new LinkedNodes<>(players, true);
        deck = new Deck();
    }

    abstract void start();
}
