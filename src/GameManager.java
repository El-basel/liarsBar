import java.util.Scanner;

public class GameManager {
    Player[] players = new Player[4];
    Deck deck = new Deck();
    int rounds = 0;
    void initializeGame() {
        Scanner scanner = new Scanner(System.in);
        String name;
        for (int i = 0; i < players.length; i++) {
            System.out.print("Enter player " + (i+1) + "'s name: ");
            // TODO check if name is empty and ask again for the name
            name = scanner.nextLine();
            players[i] = new Player(name, deck.randomCardGenerator());
        }
    }
    void initializeRound() {
        rounds = 0;
        deck = new Deck();
        for (Player player : players) {
            player.setCards(deck.randomCardGenerator());
        }
        printState();
    }

    void start() {
        int turn = 0;
        while (!winner()) {
            turn = rounds % players.length;
            while (!players[turn].alive) {
                ++rounds;
                turn = rounds % players.length;
            }
            System.out.println(deck.tableType + "'s Table");
            if(players[turn].alive) {
                getTurn(turn);
            }
        }
        for (Player player : players) {
            if(player.alive) {
                System.out.println(player.name + " won");
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
        System.out.println(players[turn].name + "'s turn");
        Scanner scanner = new Scanner(System.in);
        System.out.print("PRESS ANY KEY TO CONTINUE");
        scanner.nextLine();
        int choice;
        if(rounds != 0) {
            System.out.println("1. Play Cards");
            System.out.println("2. Call Liar");
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
            default -> System.out.println("Invalid Choice");
        }
        ++rounds;
    }

    void callLiar(int turn) {
        String[] cardsOnTable = deck.getLastPlayedCards();
        for(String card: cardsOnTable) {
            if(!card.equals(deck.tableType) && !card.equals("Joker")) {
                System.out.println(players[turn-1].name + " is A LIAR");
                if(players[turn-1].gun.getShot()) {
                    System.out.println(players[turn-1].name + " died");
                    players[turn-1].alive = false;
                } else {
                    System.out.println(players[turn-1].name + " lived");
                }
                return;
            }
        }
        System.out.println(players[turn-1].name + " is NOT A LIAR");
        if(players[turn].gun.getShot()) {
            System.out.println(players[turn].name + " died");
            players[turn].alive = false;
        } else {
            System.out.println(players[turn].name + " lived");
        }
    }

    void printState() {
        for(Player player: players) {
            System.out.println(player);
        }
    }
}
