import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
/**
 * A Deck of {@link Card} objects, used to implement tabletop card games in Java.
 * @author jaspersn
 * @version 1.1.1
 */
public class Deck {
    /**
     * The current {@link Deck} of {@link Card}s represented as a {@link Deque}.
     */
    private Deque<Card> deck = new LinkedList<>();
    /**
     * Array of all valid character values on a {@link Card}.
     */
    private final Value[] VALUES = {Value.ACE, Value.TWO, Value.THREE, Value.FOUR, Value.FIVE, Value.SIX, Value.SEVEN, Value.EIGHT, Value.NINE, Value.TEN, Value.JACK, Value.QUEEN, Value.KING};
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
     * @param deck the {@link Deque} of {@link Card} objects.
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
    /**
     * Splits the current {@link Deck} into {@code n} decks of equal size contained within an {@code array}.
     * The {@link Deck} at index {@code 0} being the first portion of the current {@link Deck}.
     * @param n the number of portions to divide the deck into evenly.
     * @return an array containing each portion of the current {@link Deck}.
     * @throws DeckException if the current {@link Deck} cannot be divided into {@code n} portions evenly.
     */
    public Deck[] split(int n) {
        Deck[] halves = new Deck[n];
        int halfSize = deck.size()/n;
        if (halfSize%2 == 1) throw new DeckException("Deck could not be divided into " + n + " portions evenly.");
        for (int i = 0; i < n; i++) {
            halves[i] = new Deck(new LinkedList<>());
            for (int j = 0; j < halfSize; j++) {
                halves[i].add(deck.remove());
            }
        }
        return halves;
    }
    /**
     * A shorthand to the {@link Deck#split(int)} method. Splits the current {@link Deck} into 2 decks of equal size
     * contained within an {@code array}. The {@link Deck} at index {@code 0} being the first half of the
     * current {@link Deck}.
     * @return an array containing each half of the current {@link Deck}.
     * @throws DeckException if the current {@link Deck} cannot be divided in half evenly.
     */
    public Deck[] split() {
        return split(2);
    }
    /**
     * Adds a {@link Card} to the bottom of the current {@link Deck}.
     * @param card The {@link Card} to be added
     */
    public void add(Card card) {
        deck.add(card);
    }
    /**
     * Adds a {@link Deck} to the bottom of the current {@link Deck}.
     * @param deck The {@link Card} to be added.
     */
    public void add(Deck deck) {
        for(Card c : deck.deck) {
            add(c);
        }
    }
    /**
     * Removes and returns the {@link Card} on the top of the current {@link Deck}.
     * @return the {@link Card} on the top of the current {@link Deck}
     * @throws DeckException if attempting to remove a {@link Card} from an empty {@link Deck}.
     */
    public Card removeTop() {
        if (size() == 0) throw new DeckException("Attempted to remove card from an empty deck.");
        return deck.removeLast();
    }
    /**
     * Removes and returns the {@link Card} on the bottom of the current {@link Deck}.
     * @return the {@link Card} on the bottom of the current {@link Deck}
     * @throws DeckException if attempting to remove a {@link Card} from an empty {@link Deck}.
     */
    public Card removeBottom() {
        if (size() == 0) throw new DeckException("Attempted to remove card from an empty deck.");
        return deck.removeFirst();
    }
    /**
     * Returns the current number of {@link Card}s in the current {@link Deck}.
     * @return the current number of {@link Card}s in the current {@link Deck}.
     */
    public int size() {
        return deck.size();
    }
    @Override
    public String toString() {
        String s = "Deck:\n";
        for(Card c : deck) {
            s += c.toString();
        }
        return s;
    }

    /**
     * Returns whether the current {@link Deck} has any cards in it.
     * @return whether the current {@link Deck} has any cards in it.
     */
    public boolean isEmpty() {
        return size() == 0;
    }
}

/**
 * A {@link RuntimeException} that can be thrown during the normal operation of the Java Virtual Machine. Only intended for operations
 * done on a {@link Deck} object.
 * @author jaspersn
 * @version 1.0
 */
class DeckException extends RuntimeException {
    /**
     * Constructs a new {@link DeckException} with the specified detail {@code message}.
     * @param message the detail message.
     */
    protected DeckException(String message) {
        super(message);
    }
}

/**
 * An enumeration for valid {@link Card} values to be stored in the {@link Deck} object.
 * @author jaspersn
 * @version 1.0
 */
enum Value {
    /**
     * Ace
     */
    ACE('A', 13),
    /**
     * Two
     */
    TWO('2', 1),
    /**
     * Three
     */
    THREE('3', 2),
    /**
     * Four
     */
    FOUR('4', 3),
    /**
     * Five
     */
    FIVE('5', 4),
    /**
     * Six
     */
    SIX('6', 5),
    /**
     * Seven
     */
    SEVEN('7', 6),
    /**
     * Eight
     */
    EIGHT('8', 7),
    /**
     * Nine
     */
    NINE('9', 8),
    /**
     * Ten
     */
    TEN('X', 9),
    /**
     * Jack
     */
    JACK('J', 10),
    /**
     * Queen
     */
    QUEEN('Q', 11),
    /**
     * King
     */
    KING('K', 12);
    /**
     * The {@code int value} of the card value.
     */
    private final int value;
    /**
     * The {@code char symbol} of the card value.
     */
    private final char symbol;

    /**
     * Constructs a new card value given a value and symbol
     * @param symbol the symbol of the card value.
     * @param value the value of the card value
     */
    Value(final char symbol, final int value) {
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Returns the {@link Value#symbol} of the card value.
     * @return the {@link Value#symbol} of the card value.
     */
    public char getSymbol() {
        return symbol;
    }
    /**
     * Returns the {@link Value#value} of the card value.
     * @return the {@link Value#value} of the card value.
     */
    public int getValue() {
        return value;
    }
}

