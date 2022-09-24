/**
 * You must comment all fields and methods in this file.
 * 
 * @author William Kreahling
 */
public class Card {
    private Face name;
    private Suit suit;

    public Card(Face name, Suit suit) {
        this.name = name;
        this.suit = suit;
    }

    public Face getName() {
        return name;
    }

    public Suit getSuit() {
        return suit;
    }

    public int rank() {
        return name.getRank();
    }

    public int value() {
        return name.getValue();
    }

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
