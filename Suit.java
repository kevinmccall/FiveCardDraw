/**
 * Enum that represents the suit of a card.
 */

public enum Suit {
    SPADES("Spades"),
    HEARTS("Hearts"),
    CLUBS("Clubs"),
    DIAMONDS("Diamonds");

    /** Name of the suit */
    private String name;

    /**
     * Constructor for a new Suit
     * 
     * @param name Name of the suit
     */
    private Suit(String name) {
        this.name = name;
    }

    /**
     * Gets the suit name
     * 
     * @return String with the suit name
     */
    public String getName() {
        return this.toString();
    }

    /** Gets a string representation of the Suit */
    public String toString() {
        return name;
    }
}
