/**
 * A class representing a single card. It is public because it needs to be
 * accessed from decks of cards.
 * 
 * @author William Kreahling
 */
public class Card {
    /**
     * A face enum representing the face name, the value, and the rank of the card
     */
    private Face name;
    /** A suit enum representing the suit of an object */
    private Suit suit;

    /**
     * Constructor for creating a new card
     * 
     * @param name The Face enum for a card
     * @param suit The Suit enum for the suit of a card
     */
    public Card(Face name, Suit suit) {
        this.name = name;
        this.suit = suit;
    }

    /**
     * Getter for Face of card
     * 
     * @return An enum representing the face of the card
     */
    public Face getName() {
        return name;
    }

    /**
     * Getter for the suit of a card
     * 
     * @return An enum representing the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Getter method for getting the rank value of a card
     * 
     * @return integer value representing the rank of a card
     */
    public int rank() {
        return name.getRank();
    }

    /**
     * Getter method for getting the value of a card
     * 
     * @return integer value representing the value of a card
     */
    public int value() {
        return name.getValue();
    }

    /** returns a String representation of a card */
    public String toString() {
        return String.format("%5s of %s", name.toString(), suit.toString());
    }

    /**
     * Compare the ranks of two cards.
     * 
     * @return a value less than 0 if the rank of 'this' card is less than the one
     *         passed in, 0 if
     *         the ranks of the cards are equal and a value greater than 0 if the
     *         rank of 'this' card is
     *         greater than the rank of the one passed in.
     *
     *         Note: This method works ONLY if another Card object is passed in as a
     *         parameter.
     */
    public int compareTo(Object other) {
        return this.name.getRank() - ((Card) other).name.getRank();
    }

    /**
     * Main method for testing and debugging cards
     * 
     * @param args unused
     */
    public static void main(String args[]) {

        Card one = new Card(Face.QUEEN, Suit.HEARTS);
        Card two = new Card(Face.JACK, Suit.DIAMONDS);

        if (one.compareTo(two) < 0) {
            System.out.println(one + "\n\tis less than than\n" + two);
        } else if (one.compareTo(two) > 0) {
            System.out.println(one + "\n\tis greater than\n" + two);
        } else {
            System.out.println(one + "\nand\n" + two + "\nARE EQUAL");
            ;
        }
    }
}
