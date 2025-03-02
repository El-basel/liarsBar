import java.util.Scanner;

public class GameManager {
    Player[] players = new Player[4];
    Deck deck = new Deck();
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

    void start() {
        int turn = 0;
        while (!winner()) {
            System.out.println(deck.tableType + "'s Table");
            getTurn(turn);
            System.out.println(deck.lastPlayedCards[0] + " " + deck.lastPlayedCards[1] + " " + deck.lastPlayedCards[2]);
            ++turn;
            turn %= players.length;
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
        if(turn != 0) {
            System.out.println("1. Play Cards");
            System.out.println("2. Call Liar");
        }
        // TODO input validation
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> deck.setLastPlayedCards(players[turn].playCards());
            case 2 -> {
                callLiar(turn);
                deck.setTableType();
                turn = 0;
            }
            default -> System.out.println("Invalid Choice");
        }
    }

    void callLiar(int turn) {
        String[] cardsOnTable = deck.getLastPlayedCards();
        for(String card: cardsOnTable) {
            if(!card.equals(deck.tableType)) {
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
}
