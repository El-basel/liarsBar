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
        this.cards.clear();
        this.cards.addAll(Arrays.asList(cards));
    }
    void showCards() {
        boolean newLine = false;
        Colors.print(this.name +"'s Cards: [\n", Colors.ORANGE);
        for (int i = 0; i < cards.size(); i++) {
            Colors.println((i + 1) + "." + Deck.cardsRepresentation.get(cards.get(i)), Colors.ORANGE);
//            if(!newLine) {
//                System.out.print("\u001B[5A");
//                System.out.print("\u001B[12C");
//            }
//            newLine = !newLine;
        }
        Colors.println("]", Colors.ORANGE);
    }

    String[] playCards() {
        int numberOfCards = 0;
        int[] cardsIndex = new int[3];
        // TODO input validation
        Scanner scanner = new Scanner(System.in);
        showCards();
        do {
            Colors.print("Enter the number of cards you will play (Max. 3): ", Colors.LIGHT_BLUE);
            numberOfCards = scanner.nextInt();
            scanner.nextLine();
        } while (numberOfCards > 3);
        String[] playedCards = new String[numberOfCards];
        Colors.print("Enter the index of the cards you want to play separated by spaces (card1 card2 card3): ", Colors.LIGHT_BLUE);
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

    @Override
    public String toString() {
        return name + " :" + cards + " " + alive;
    }
}
