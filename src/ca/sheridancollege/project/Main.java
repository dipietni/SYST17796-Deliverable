package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Nicholas Di Pietrantonio, 2019
 */
public class Main {

    public static void main(String[] args) {

        java.util.Scanner input = new java.util.Scanner(System.in);

        GroupOfCards groupOfCards = new GroupOfCards();
        groupOfCards.shuffle();

        System.out.println("How many players would like to play?(2-4)");
        int playerTotal = input.nextInt();

        while (playerTotal > 4 || playerTotal < 2) {
            System.out.println("Enter a valid player amount.(2-4)");
            playerTotal = input.nextInt();
        }

        ArrayList<BlackJackPlayer> players = new ArrayList(playerTotal);
        String[] playerNames = new String[playerTotal];

        System.out.println("Enter player names");
        for (int i = 0; i < playerTotal; i++) {
            ArrayList<BlackJackCard> hand = new ArrayList(0);
            System.out.println("Enter name for player " + (i + 1));
            playerNames[i] = input.next();
            players.add(new BlackJackPlayer(playerNames[i], hand));
        }

        BlackJackGame play = new BlackJackGame(players, groupOfCards);
        play.play();
    }

}
