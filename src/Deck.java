import java.util.ArrayList;
import java.util.Random;

/**
 * A standard deck of French-suited playing cards
 */
public class Deck {
    /**
     * An {@link ArrayList<Card>} of {@link Card cards}
     */
    private final ArrayList<Card> deck;

    /**
     * Initializes a standard deck of
     * French-suited playing cards
     */
    public Deck() {
        deck = new ArrayList<>();
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 13; j++)
                deck.add(new Card(i, j));
        }
    }

    /**
     * Shuffles the {@link Card cards} in
     * the {@link Deck#deck} using
     * the Fisher-Yates shuffle
     */
    public void shuffle() {
        long seed = System.currentTimeMillis();
        Random rng = new Random((int)seed);
        int n = deck.size();
        while(n > 1)
        {
            n--;
            int k = rng.nextInt(n + 1);
            Card current = deck.get(k);
            deck.set(k, deck.get(n));
            deck.set(n, current);
        }
    } //Using the Fisher-Yates Shuffle

    /**
     * @return the first {@link Card card}
     * of the {@link Deck#deck}
     */
    public Card give() {
        return deck.remove(0);
    }

    /**
     * Adds the {@link @param taken} {@link Card card}
     * to the {@link Deck#deck}
     * @param taken
     */
    public void take(Card taken) {
        deck.add(taken);
    }
}
