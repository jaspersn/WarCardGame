/**
 * A card representation to be used in tabletop games, similar to those found in a Standard USPCC Bicycle Deck of cards.
 * @author jaspersn
 * @version 1.0
 */
public class Card {
    /**
     * The value of the {@link Card}.
     */
    private final char value;
    /**
     * The suit/symbol of the {@link Card}.
     */
    private final char suit;
    /**
     * Constructs a new {@link Card} object, given a character {@code value} and {@code suit}.
     * @param value the value to be associate with the card
     * @param suit the symbol to be associated with the card
     */
    public Card(char value, char suit) {
        this.value = value;
        this.suit = suit;
    }
    /**
     * Returns the {@link Card#value} of the card.
     * @return the {@link Card#value} of the card.
     */
    public char getValue() {
        return value;
    }
    /**
     * Returns the {@link Card#suit} of the card.
     * @return the {@link Card#suit} of the card.
     */
    public char getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        String s = "+-----+\n";
        s += "|" + value + "    |\n";
        s += "|" + suit + "    |\n";
        //s += "|     |\n";
        s += "|    " + suit + "|\n";
        s += "|    " + value + "|\n";
        s += "+-----+\n";
        return s;
    }
}
