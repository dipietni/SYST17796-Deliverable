package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Nicholas Di Pietrantonio, 2019
 */
public class BlackJackGame extends Game {

    private GroupOfCards groupOfCards;
    ArrayList<BlackJackPlayer> players;

    public BlackJackGame(ArrayList<BlackJackPlayer> players, GroupOfCards groupOfCards) {
        super("BlackJack", players);
        this.players = players;
        this.groupOfCards = groupOfCards;
    }

    @Override
    public void play() {
        int i = 0;
        java.util.Scanner input = new java.util.Scanner(System.in);

        for (int j = 0; j < players.size(); j++) {
            for (int k = 0; k < 2; k++) {
                players.get(j).addHand(groupOfCards.drawCard(i));
                i++;
            }
            players.get(j).checkPoints();

            //statement if player gets two aces and sets both to 11
            if (players.get(j).checkLoss()) {
                players.remove(j);
                j++;
            }
        }
        System.out.println("The cards have been dealt");
        while (i < groupOfCards.getSize()) {
            for (int j = 0; j < players.size(); j++) {
                players.get(j).play();

                switch (input.nextInt()) {
                    case 1:
                        players.get(j).addHand(groupOfCards.drawCard(i));
                        if (players.get(j).checkLoss()) {
                            players.remove(j);
                        }
                        i++;
                        break;
                    case 2:
                        break;
                    case 3:
                        players.get(j).checkHand();
                        players.get(j).checkPoints();
                        j--;//reset back to players turn
                        break;
                    case 4:
                        declareWinner();
                        return;
                    default:
                        System.out.println("Please enter a valid input");
                        j--;//resets back to players turn
                        break;
                }
                if (players.size() < 2) {
                    declareWinner();
                    return;
                }
            }
        }
    }

    @Override
    public void declareWinner() {
        if (players.size() == 1) {
            System.out.println(players.get(0).getPlayerID() + " wins!");
            return;
        }
        ArrayList<Integer> sorting = new ArrayList(0);
        int biggest = 0;
        int winner = 0;
        for (int i = 0; i < players.size(); i++) {
            sorting.add(i, players.get(i).calculatePoints(players.get(i).getHand()));
            if (sorting.get(i) > biggest) {
                biggest = sorting.get(i);
                winner = i;
            }
        }
        Collections.sort(sorting);

        if (sorting.get(players.size() - 1) == sorting.get(players.size() - 2)) {
            System.out.println("Tie game");
        } else {
            System.out.println(players.get(winner).getPlayerID() + " wins!");
        }
    }

}
