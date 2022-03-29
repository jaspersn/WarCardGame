import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
/**
 * A Deck of {@link Card} objects, used to implement tabletop card games in Java.
 * @author jaspersn
 * @version 1.0
 */
public class Deck {
    /**
     * The current {@link Deck} of {@link Card}s represented as a {@link Deque}.
     */
    private Deque<Card> deck = new LinkedList<>();
    /**
     * Array of all valid character values on a {@link Card}.
     */
    private final char[] VALUES = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K'};
    /**
     * Array of all valid suits on a {@link Card}.
     */
    private final char[] SUITS = {'♠', '♦', '♣', '♥'};
    /**
     * Constructs a new {@link Deck} of 52 {@link Card}s, sorted as per the Standard USPCC Bicycle Deck.
     */
    public Deck() {
        for (int i = 0; i < SUITS.length/2; i++) {
            for (int j = 0; j < VALUES.length; j++) {
                deck.add(new Card(VALUES[j], SUITS[i]));
            }
        }
        for (int i = SUITS.length/2; i < SUITS.length; i++) {
            for (int j = VALUES.length - 1; j >= 0; j--) {
                deck.add(new Card(VALUES[j], SUITS[i]));
            }
        }
    }
    /**
     * Constructs a new {@link Deck} of {@link Card}s, given a {@link Deque} of {@link Card} objects.
     * @param deck
     */
    public Deck(Deque<Card> deck) {
        this.deck = deck;
    }
    /**
     * Shuffles the current deck of cards to a new random order.
     */
    public void shuffle() {
        Collections.shuffle((List<Card>) deck);
    }

    @Override
    public String toString() {
        String s = "Deck:\n";
        for(Card c : deck) {
            s += c.toString();
        }
        return s;
    }
}
