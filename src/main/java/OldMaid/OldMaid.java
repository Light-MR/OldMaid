package OldMaid;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class is a cardGame. Model of Old Maid.
 * @author Seiji Dominic Bautista
 */
public class OldMaid extends CardGame {
    public OldMaid(List<Player> players) {
        super(players);
        deck.discard(Card.Suit.HEARTS, Card.Value.QUEEN);
        CardDistributor.distribute(deck, players);
    }

    @Override
    void start() {
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
            System.out.println("\n<" + current.getName() + "'s turn.>\n");

            /*
             * pick card from previous player.
             * If currentPlayer is the first player, skip this if block
             */
            if (previous != null) {
                previousHand = previous.getHand();
                System.out.printf("Pick one from %s!\n", previous);
                for (int i = 1; i <= previousHand.size(); i++) {
                    System.out.printf("    %d    ", i);
                }
                System.out.println();

                int num = -1;
                String userInput;
                do {
                    userInput = s.nextLine();
                    if (userInput.matches("\\d+")) {
                        int tem = Integer.parseInt(userInput);

                        if (tem > previousHand.size()) {
                            System.out.println("Please enter a valid index number");
                        } else {
                            num = tem;
                        }
                    } else {
                        System.out.println("Please enter a valid number");
                    }
                } while(num == -1);

                Card picked = previousHand.disposeCard(--num);
                System.out.printf("You picked %s from %s!\n", picked, previous.getName());
                current.addToHand(picked);
                currentHand = current.getHand();
            }

            String input;
            Card first;
            Card second;
            String[] index;
            do {
                if (currentHand.isEmpty()){
                    System.out.printf("%s is safe!\n", current.getName());
                    break;
                }
                System.out.println("""
                    
                    - Type in 2 numbers separated by a whitespace.
                    - Type "exit" to exit.
                    - Type "end" to end turn.
                    - Press enter (don't type anything in) to shuffle.
                    """);

                System.out.println("The cards currently in your hand.");
                for (int i = 0; i < currentHand.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, currentHand.get(i));
                }

                /*
                 * Validate user input.
                 * Possible inputs:
                 * 	1. <First card index> <Second card index>;
                 * 	2. end - End turn.
                 * 	3. sort - Sort cards in hand.
                 *   4. shuffle - shuffle cards in hand.
                 * */
                input = s.nextLine();
                if (input.equalsIgnoreCase("end"))
                    break;
                else if (input.equalsIgnoreCase("sort")) {
                    currentHand.sortCards();
                } else if (input.equalsIgnoreCase("")) {
                    currentHand.shuffleCards();
                } else if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Thank you for playing.");
                    System.exit(0);
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
                    System.out.println("Please Enter a valid number separated by a whitespace.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Enter the index of the card from the previous player!");
                }

            } while(!input.equalsIgnoreCase("end"));

            /*
             * From here, check if:
             * 	1. ...there is a player that has an empty hand. -> remove player from game.
             *  2. ...the number of of players is greater than 1. If so, end game.
             * */
            if (current.getHand().isEmpty()) {
                players.destroyCurrent();
            }
            previous = players.getCurrentData();
            players.next();
            current = players.getCurrentData();

            if (players.size() == 1) {
                System.out.println("WE'VE GOT A LOSER!!!");
                break;
            }

        } while (players.size() > 1);

    }
}
