package OldMaid;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Seiji Dominic Bautista
 */
public class GameController {

    //This class will handle the initialization of the OldMaid instance
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to the game!");
        System.out.println("How many players are there?");
        int playerNum = -1;

        do {
            String temp = s.nextLine();
            if (temp.matches("\\d+")) {
                playerNum = Integer.parseInt(temp);
                if (playerNum > 25) {
                    System.out.println("Game cannot have player count above 25.");
                } else if (playerNum < 3) {
                    System.out.println("Go get some friends to play with.");
                }
            } else {
                System.out.println("Please enter a valid number.");
            }
        } while (playerNum > 25 || playerNum < 3);


        //Player initialization
        ArrayList<Player> initPlayers = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            System.out.println("What is this player's name?");
            String name = s.nextLine();
            initPlayers.add(new Player(name));
        }

        OldMaid om = new OldMaid(initPlayers);
        om.start();
    }
}
