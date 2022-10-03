import java.util.Arrays;

/**
 * Class that represents a hand in a game of poker. It is public so people can
 * use this class in other card game related programs.
 *
 * @author Kevin McCall
 * @version 10/3/2022
 */
public class Hand {

    /** Represents cards in a hand */
    private Card[] cards;
    /**
     * An array with 13 slots that has the count of each rank of card in it
     * Ex: [0,0,2,0,0,0,1,0,1,1,0,0,0] -> pair of threes, a seven, a nine, and a ten
     */
    private int[] cardRanks;

    /** Default number of cards in a poker hand */
    public final static int DEFALT_POKER_CARDS = 5;

    /**
     * Constructor for a new hand with numberOfCards card slots
     * 
     * @param numberOfCards number of cards in this hand
     */
    public Hand(int numberOfCards) {
        cards = new Card[numberOfCards];
        cardRanks = new int[13];
    }

    /**
     * Use this after setting cards. Sorts cards for finding attributes of the hand.
     */
    public void updateHandValues() {
        sort();
        for (int i = 0; i < cardRanks.length; i++) {
            cardRanks[i] = 0; // fill cardRanks with 0s
        }
        for (int i = 0; i < cards.length; i++) {
            cardRanks[cards[i].getName().getRank() - 1] += 1; // create an array for each type of card 1 - 13
        }
    }

    /**
     * Creates a hand for a game of poker with 5 cards
     */
    public Hand() {
        this(DEFALT_POKER_CARDS);
    }

    /**
     * Set a card value at a certain index
     * 
     * @param index Index of which card you would like to modify
     * @param card  New card to replace the old card.
     */
    public void setCard(int index, Card card) {
        cards[index] = card;
    }

    /**
     * Helper method to get amount 1, 2, 3... N kind of hands.
     * 
     * @param N How many duplicate cards of the same rank should we search for.
     * @return the highest rank of cards with N duplicates.
     */
    private int getNKind(int N) {
        int pair = -1;
        for (int i = cardRanks.length - 1; i >= 0 && pair == -1; i--) {
            if (cardRanks[i] >= N) {
                pair = i;
            }
        }
        return pair;
    }

    /**
     * Gets a pair of cards with the same rank.
     * 
     * @return Returns an integer of the highest ranked pair of cards.
     */
    public int getPair() {
        return getNKind(2);
    }

    /**
     * Gets a three of a kind of cards with the same rank.
     * 
     * @return Returns an integer of the highest ranked trio of cards.
     */
    public int getThreeKind() {
        return getNKind(3);
    }

    /**
     * Gets a four of a kind of cards with the same rank.
     * 
     * @return Returns an integer of the highest ranked quad of cards.
     */
    public int getFourKind() {
        return getNKind(4);
    }

    /**
     * Returns the rank of the highest card in the hand.
     * 
     * @return integer representing the rank of the highest card.
     */
    public int getHighestCard() {
        int highCard = -1;
        for (int i = cardRanks.length - 1; i >= 0 && highCard == -1; i--) {
            if (cardRanks[i] != 0) {
                highCard = i;
            }
        }
        return highCard;
    }

    /**
     * Returns the most valuable hand type of this hand.
     * 
     * @return WinHand enum representing the strongest hand.
     */
    public WinHand getHandType() {
        WinHand handType;
        if (straightFlush()) {
            handType = WinHand.STRAIGHT_FLUSH;
        } else if (fourOfAKind()) {
            handType = WinHand.FOUR_OF_A_KIND;
        } else if (fullHouse()) {
            handType = WinHand.FULL_HOUSE;
        } else if (flush()) {
            handType = WinHand.FLUSH;
        } else if (straight()) {
            handType = WinHand.STRAIGHT;
        } else if (threeOfAKind()) {
            handType = WinHand.THREE_OF_A_KIND;
        } else if (twoPair()) {
            handType = WinHand.TWO_PAIR;
        } else if (pair()) {
            handType = WinHand.PAIR;
        } else if (getHighestCard() != -1) {
            handType = WinHand.HIGH_CARD;
        } else {
            handType = WinHand.INVALID;
        }
        return handType;
    }

    /**
     * Helper method for finding a straight flush. Assumes that the game is only
     * played with five cards.
     * 
     * @return boolean for if there is a straight flush.
     */
    private boolean straightFlush() {
        return flush() && straight();
    }

    /**
     * Returns true if there is a straight.
     * 
     * @return boolean for if hand contains a straight. Assumes that the game is
     *         only played with five cards.
     */
    private boolean straight() {
        int maxConsecutive = 0;
        int consecutive = 0;
        for (int i = cardRanks.length - 1; i >= 0; i--) {
            if (cardRanks[i] != 0) {
                consecutive += 1;
            } else {
                consecutive = 0;
            }
            if (consecutive > maxConsecutive) {
                maxConsecutive = consecutive;
            }
        }
        return maxConsecutive >= 5; // if there is 5 cards in a row
    }

    /**
     * Helper method that returns true if there is a flush.
     * 
     * @return boolean for if hand contains a flush.
     */
    private boolean flush() {
        boolean hasFlush = true;
        Suit suit = cards[0].getSuit();
        for (int i = 1; i < cards.length; i++) { // skip first iteration because we already set it in a variable
            if (!cards[i].getSuit().equals(suit)) {
                hasFlush = false;
            }
        }
        return hasFlush;
    }

    /**
     * Helper method that returns true if there is a four of a kind.
     * 
     * @return boolean for if hand contains a four of a kind.
     */
    private boolean fourOfAKind() {
        return getNKind(4) != -1; // See if it has 4 of the same card
    }

    /**
     * Helper method that returns true if there is a three of a kind.
     * 
     * @return boolean for if hand contains a three of a kind.
     */
    private boolean threeOfAKind() {
        return getNKind(3) != -1; // See if it has 3 of the same card
    }

    /**
     * Helper method that returns true if there is a two of a kind.
     * 
     * @return boolean for if hand contains a two of a kind.
     */
    private boolean pair() {
        return getNKind(2) != -1; // See if it has 2 of the same card
    }

    /**
     * Helper method that returns true if a hand contains two pair.
     * 
     * @return boolean for if hand contains a two pair.
     */
    private boolean twoPair() {
        int secondSearchIndex = -1;
        boolean hasTwoPair = false;
        for (int i = cardRanks.length - 1; i >= 0 && secondSearchIndex == -1; i--) {
            if (cardRanks[i] >= 2) { // first two of a kind
                secondSearchIndex = i - 1;
            }
        }
        for (int i = secondSearchIndex; i >= 0 && !hasTwoPair && secondSearchIndex != -1; i--) {
            if (cardRanks[i] >= 2) { // second two of a kind
                hasTwoPair = true;
            }
        }
        return hasTwoPair;
    }

    /**
     * Helper method that returns true if a hand contains a full house. Assumes that
     * the hand only has five cards.
     * 
     * @return boolean for if hand contains a full house.
     */
    private boolean fullHouse() {
        int blacklistedIndex = -1;
        boolean hasFullHouse = false;
        for (int i = cardRanks.length - 1; i >= 0 && blacklistedIndex == -1; i--) {
            if (cardRanks[i] >= 3) { // three of a kind
                blacklistedIndex = i;
            }
        }
        for (int i = cardRanks.length - 1; i >= 0 && blacklistedIndex != -1; i--) {
            if (cardRanks[i] >= 2) { // two of a kind
                if (i == blacklistedIndex) {
                    continue; // We talked about this during office hours
                }

                hasFullHouse = true;
            }
        }
        return hasFullHouse;
    }

    /**
     * Returns a string representation of the hand alongside the index values for
     * each of the cards in the hand
     * 
     * @return string representation of current hand.
     */
    @Override
    public String toString() {
        StringBuilder cardsString = new StringBuilder();
        cardsString.append("Cards: ( ");
        for (int i = 0; i < cards.length; i++) {
            Card card = cards[i];
            if (card != null) {
                cardsString.append(String.format("[%d] %s", i, card.toString()));
                if (i != cards.length - 1) {
                    cardsString.append(", ");
                }
            }
        }
        cardsString.append(" )");
        return cardsString.toString();
    }

    /**
     * This method will sort a hand into ascending order. Will only work in Java 8
     * or higher.
     * It makes the assumption that you have an array of N Cards named 'cards';
     * 'cards'
     * needs to be a field within this class.
     */
    public void sort() {
        Arrays.sort(cards, (Card u1, Card u2) -> u1.compareTo(u2));
    }

    /** Getter for the size of the hand */
    public int getHandSize() {
        return cards.length;
    }

}
