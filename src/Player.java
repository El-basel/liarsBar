import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player {
    String name;
    ArrayList<String> cards = new ArrayList<>(5);
    Gun gun = new Gun();
    boolean alive = true;
    Player() {
        this.name = "";
    }
    Player(String name, String[] cards) {
        this.name = name;
        this.cards.addAll(Arrays.asList(cards));
    }
    void setCards(String... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }
    void showCards() {
        System.out.print(this.name +"'s Cards: [");
        for (int i = 0; i < cards.size(); i++) {
            System.out.print((i + 1) + "." + cards.get(i));
            if(i != (cards.size() - 1)) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    String[] playCards() {
        int numberOfCards = 0;
        int[] cardsIndex = new int[3];
        // TODO input validation
        Scanner scanner = new Scanner(System.in);
        showCards();
        do {
            System.out.print("Enter the number of cards you will play (Max. 3): ");
            numberOfCards = scanner.nextInt();
            scanner.nextLine();
        } while (numberOfCards > 3);
        String[] playedCards = new String[numberOfCards];
        System.out.print("Enter the index of the cards you want to play separated by spaces (card1 card2 card3): ");
        for (int i = 0; i < numberOfCards; i++) {
            cardsIndex[i] = scanner.nextInt();
            playedCards[i] = this.cards.get((cardsIndex[i] - 1));
        }
        for (int i = 0; i < numberOfCards; i++) {
            if(i != 0 && cardsIndex[i] > cardsIndex[i-1]) {
                --cardsIndex[1];
                --cardsIndex[2];
            }
            this.cards.remove((cardsIndex[i] - 1));
        }
        return playedCards;
    }
}
