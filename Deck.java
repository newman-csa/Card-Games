import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> cards;
    private int size;

    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                cards.add(new Card(ranks[i], suits[j], values[i]));
            }
        }
        size = cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int size() {
        return size;
    }

    public Card deal() {
        if (isEmpty())
            return null;
        size--;
        return cards.get(size);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void shuffle() {
        Random randInt = new Random();
        for (int i = size - 1; i > 0; i--) {
            int currInt = randInt.nextInt(i + 1);
            Collections.swap(cards, i, currInt);
        }
    }

    public String toString() {
        String out = "";
        for (Card card : cards) {
            out += card + "\n";
        }
        return out;
    }

}
