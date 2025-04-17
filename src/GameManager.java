import java.util.Scanner;

public class GameManager {
    Player[] players = new Player[2];
    Deck deck = new Deck();
    int rounds = 0;
    GameManager() {
        Scanner scanner = new Scanner(System.in);
        String name;
        for (int i = 0; i < players.length; i++) {
            Colors.print("Enter player " + (i+1) + "'s name: ", "\u001B[38;5;39m");
            do {
                name = scanner.nextLine();
            } while(name.isBlank());
            players[i] = new Player(name, deck.randomCardGenerator());
        }
    }
    void initializeGame() {

    }
    void initializeRound() {
        rounds = 0;
        deck = new Deck();
        for (Player player : players) {
            player.setCards(deck.randomCardGenerator());
        }
//        printState();
    }

    void start() {
        int turn = 0;
        while (!winner()) {
            turn = rounds % players.length;
            while (!players[turn].alive) {
                ++rounds;
                turn = rounds % players.length;
            }
            Colors.println("-".repeat(deck.tableType.length() + 8), Colors.RED);
            Colors.println(deck.tableType + "'s Table", Colors.CYAN);
            Colors.println("-".repeat(deck.tableType.length() + 8), Colors.RED);
            if(players[turn].alive) {
                getTurn(turn);
            }
        }
        for (Player player : players) {
            if(player.alive) {
                Colors.println("-".repeat(player.name.length() + 4), Colors.YELLOW);
                Colors.println(player.name + " won", Colors.YELLOW);
                Colors.println("-".repeat(player.name.length() + 4), Colors.YELLOW);
            }
        }
    }

    boolean winner() {
        int foundAlive = 0;
        for (Player player: players) {
            if(player.alive) {
                foundAlive++;
            }
        }
        return foundAlive == 1;
    }

    void getTurn(int turn) {
        Colors.println(players[turn].name + "'s turn", Colors.ORANGE);
        Scanner scanner = new Scanner(System.in);
        Colors.print("PRESS ANY KEY TO CONTINUE", Colors.WHITE);
        scanner.nextLine();
        int choice;
        if(rounds != 0) {
            Colors.println("1. Play Cards", Colors.LIGHT_BLUE);
            Colors.println("2. Call Liar", Colors.LIGHT_BLUE);
            // TODO input validation
            choice = scanner.nextInt();
            scanner.nextLine();
        } else {
            choice = 1;
        }
        switch (choice) {
            case 1 -> deck.setLastPlayedCards(players[turn].playCards());
            case 2 -> {
                callLiar(turn);
                initializeRound();
                return;
            }
            default -> Colors.println("Invalid Choice", Colors.RED);
        }
        ++rounds;
    }

    void callLiar(int turn) {
        String[] cardsOnTable = deck.getLastPlayedCards();
        // find the previous alive player that the current player called a liar
        int previousPlayer = turn - 1 >= 0 ? turn-1 : players.length - 1;
        while(!players[previousPlayer].alive) {
            previousPlayer = previousPlayer - 1 >= 0 ? previousPlayer - 1 : players.length - 1;
        }
        // compare the last played card with the deck type or if the card is a joker
        for(String card: cardsOnTable) {
            if(!card.equals(deck.tableType) && !card.equals("Joker")) {
                // the previous player found to be laying and gets a shoot
                Colors.println(players[previousPlayer].name + " is A LIAR", Colors.RED);
                if(players[previousPlayer].gun.getShot()) {
                    Colors.println(players[previousPlayer].name + " died", Colors.RED);
                    players[previousPlayer].alive = false;
                } else {
                    Colors.println(players[previousPlayer].name + " lived", Colors.GREEN);
                }
                System.out.println();
                return;
            }
        }
        // the previous player was not laying and the caller player gets a shoot
        Colors.println(players[previousPlayer].name + " is NOT A LIAR", Colors.GREEN);
        System.out.println();
        if(players[turn].gun.getShot()) {
            Colors.println(players[turn].name + " died", Colors.RED);
            players[turn].alive = false;
        } else {
            Colors.println(players[turn].name + " lived", Colors.GREEN);
        }
        System.out.println();
    }

    void printState() {
        for(Player player: players) {
            System.out.println(player);
        }
    }
}
