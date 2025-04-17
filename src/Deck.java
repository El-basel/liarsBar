import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Deck {
    ArrayList<String> cards = new ArrayList<>(20);
    static Map<String, String> cardsRepresentation = new HashMap<>();
    String[] lastPlayedCards = {"","",""};
    String tableType;
    Random random = new Random();
    int numberOfCards;
    Deck() {
        cardsRepresentation.put("Ace", """
                    ┌─────────┐
                      │A   C    │
                      │    E    │
                      │        A│
                      └─────────┘""");
        cardsRepresentation.put("King", """
                    ┌─────────┐
                      │K   I    │
                      │    N    │
                      │    G   K│
                      └─────────┘""");
        cardsRepresentation.put("Queen", """
                    ┌─────────┐
                      │Q   U    │
                      │  E   E  │
                      │    N   Q│
                      └─────────┘""");
        cardsRepresentation.put("Joker", """
                ┌─────────┐
                  │J O   K  │
                  │  E   E  │
                  │    R   J│
                  └─────────┘""");
        for (int i = 0; i < 6; i++) {
            cards.add("Ace");
            cards.add("King");
            cards.add("Queen");
        }

        cards.add("Joker");
        cards.add("Joker");
        do {
            tableType = cards.get(random.nextInt(0, cards.size()));
        } while (tableType.equalsIgnoreCase("Joker"));
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
    void setLastPlayedCards(String[] cardsOnTable) {
        numberOfCards = cardsOnTable.length;
        System.arraycopy(cardsOnTable, 0, lastPlayedCards, 0, cardsOnTable.length);
    }
}
