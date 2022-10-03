/**
 * Class representing the face, rank, and value of a card. This class is public
 * because it needs to be accessed from other classes.
 * 
 * @author Kevin McCall
 * @version 10/3/2022
 */
public enum Face {
    /** Ace is low */
    ACE("Ace", 1, 1),
    TWO("Two", 2, 2),
    THREE("Three", 3, 3),
    FOUR("Four", 4, 4),
    FIVE("Five", 5, 5),
    SIX("Six", 6, 6),
    SEVEN("Seven", 7, 7),
    EIGHT("Eight", 8, 8),
    NINE("Nine", 9, 9),
    TEN("Ten", 10, 10),
    JACK("Jack", 10, 11),
    QUEEN("Queen", 10, 12),
    KING("King", 10, 13);

    /** String representing name of a face card */
    private String name;
    /** Integer representing the value of a card */
    private int value;
    /** Integer representing rank of a card */
    private int rank;

    /**
     * Getter method for the name of the card face
     * 
     * @return String of card face name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the worth of the card (Useful in go fish).
     * 
     * @return the value of a card one through 10
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the rank of a card. Ace is only low (rank of one).
     * 
     * @return Integer representing the rank of a card one through thirteen
     */
    public int getRank() {
        return rank;
    }

    /**
     * Constructor for Face
     * 
     * @param name  name for the face card
     * @param value value of the card one through ten
     * @param rank  rank of the card one through thirteen
     */
    Face(String name, int value, int rank) {
        this.name = name;
        this.value = value;
        this.rank = rank;
    }
}
