import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<String> cards = new ArrayList<>(20);
    String[] lastPlayedCards = {"","",""};
    String tableType;
    Random random = new Random();
    int numberOfCards;
    Deck() {
        for (int i = 0; i < 6; i++) {
            cards.add("Ace");
            cards.add("King");
            cards.add("Queen");
        }
        cards.add("Joker");
        cards.add("Joker");
        tableType = cards.get(random.nextInt(0, cards.size()));
    }

    String[] getLastPlayedCards() {
        String[] cardsOnTable = new String[numberOfCards];
        System.arraycopy(lastPlayedCards,0,cardsOnTable,0,numberOfCards);
        return cardsOnTable;
    }

    String[] randomCardGenerator() {
        int randomIndex = 0;
        String[] playerCards = new String[5];
        for (int i = 0; i < playerCards.length; i++) {
            randomIndex = random.nextInt(0, cards.size());
            playerCards[i] = cards.get(randomIndex);
            cards.remove(randomIndex);
        }
        return playerCards;
    }
    void setTableType() {
        tableType = cards.get(random.nextInt(0, cards.size()));
    }
    void setLastPlayedCards(String[] cardsOnTable) {
        numberOfCards = cardsOnTable.length;
        System.arraycopy(cardsOnTable, 0, lastPlayedCards, 0, cardsOnTable.length);
    }
}
