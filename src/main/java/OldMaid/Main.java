package OldMaid;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Create a breakpoint on "System.out.println("HEllo")" and debug to see the output of the LinkedNodes<Player> on the
 * debugger console. Thanks
 */
public class Main {

    //This class will handle the initialization of the OldMaid instance
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        System.out.println("How many players are there?");
        int playerNum = s.nextInt();
        s.nextLine();

        ArrayList<Player> initPlayers = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            System.out.println("What is this player's name?");
            String name = s.nextLine();
            initPlayers.add(new Player(name));
        }

        CardDistributor.distribute(initPlayers);    //Distribute cards to players
        LinkedNodes<Player> players = new LinkedNodes<>(initPlayers, true); //Create cyclic list of players
        System.out.println("HEllo");
        /*
        what do you guys think if we make a class like "OldMadiInitializer" or something.
         */
    }
}
