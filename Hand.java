import java.util.Arrays;

/**
 * Comment ME!
 *
 */
public class Hand {

    // FIELDS
    private Card[] cards;
    private int[] values;
    private static int DEFALT_POKER_CARDS = 5;

    public Hand(int numberOfCards) {
        cards = new Card[numberOfCards];
        values = new int[13];
        for (int i = 0; i < cards.length; i++) {
            values[cards[i].getName().getRank() - 1] += 1; // create an array for each type of card 1 - 13
        }
        sort();
    }

    public Hand() {
        this(DEFALT_POKER_CARDS);
    }

    public void setCard(int index, Card card) {
        cards[index] = card;
        sort();
    }

    /**
     * 
     * 
     * @return Returns an integer of the highest ranked pair of cards.
     */
    public int getPair() {
        return getNPair(2);
    }

    public int getThreeKind() {
        return getNPair(3);
    }

    public int getFourKind() {
        return getNPair(4);
    }

    public Card getHighestCard() {
        for (int i = cards.length -1; i >= 0; i--) {
            if 
        }
    }

    /**
     * Get the highest rank value of a pair of N cards
     * 
     * @return A rank value between 1 - 10 or -1 if no pair is found
     */
    private int getNPair(int pairN) {
        int cardRank = -1;
        if (!hasNPair(pairN)) {
            return cardRank;
        }

        int pairCount = 0;
        for (int i = 0; i < cards.length - 1 && cardRank == -1; i++) {
            Card card = cards[i];
            Card nextCard = cards[i + 1];
            if (card != null) {
                if (card.value() == nextCard.value() - 1) {
                    pairCount++;
                    if (pairN == pairCount - 1) {
                        cardRank = nextCard.getName().getRank();
                    }
                } else if (card.value() == nextCard.value()) {

                } else {
                    pairCount = 0;
                }
            }
        }
        return cardRank;
    }

    private boolean hasNPair(int pairN) {
        boolean hasNpair = false;
        int pairCount = 0;
        for (int i = 0; i < cards.length - 1 && !hasNpair; i++) {
            Card card = cards[i];
            Card nextCard = cards[i + 1];
            if (card != null) {
                if (card.value() == nextCard.value()) {
                    pairCount++;
                    if (pairN == pairCount - 1) {
                        hasNpair = true;
                    }
                } else if (card.value() == nextCard.value()) {

                } else {
                    pairCount = 0;
                }
            }
        }
        return hasNpair;
    }

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
        } else if (getHighestCard() != null) {
            handType = WinHand.HIGH_CARD;
        } else {
            handType = WinHand.INVALID;
        }
        return handType;
    }

    private boolean straightFlush() {
        return flush() && straight();
    }

    private boolean straight() {
        boolean hasStraight = true;
        for (int i = 0; i < cards.length - 1 && !hasStraight; i++) {
            Card card = cards[i];
            Card nextCard = cards[i + 1];
            if (card != null) {
                if (!(card.value() == nextCard.value() - 1)) {
                    hasStraight = false;
                }
            }
        }
        return hasStraight;
    }

    private boolean flush() {
        boolean hasFlush = true;
        Suit suit = cards[0].getSuit();
        for (int i = 1; i < cards.length; i++) { // skip first iteration because we already set it in a variable
            if (cards[i].getSuit().equals(suit)) {
                hasFlush = false;
            }
        }
        return hasFlush;
    }

    private boolean fourOfAKind() {
        return hasNPair(4); // See if it has 4 of the same card
    }

    private boolean threeOfAKind() {
        return hasNPair(3); // See if it has 3 of the same card
    }

    private boolean pair() {
        return hasNPair(2); // See if it has 2 of the same card
    }

    private boolean twoPair() {
        boolean hasTwoPair = false;
        boolean foundFirst = false;
        for (int i = 0; i < cards.length - 1 && !hasTwoPair; i++) {
            Card currentCard = cards[i];
            Card nextCard = cards[i + 1];
            if (currentCard.value() == nextCard.value()) {
                if (!foundFirst) {
                    foundFirst = true;
                } else {
                    hasTwoPair = true;
                }
            }
        }
        return hasTwoPair;
    }

    private boolean fullHouse() {
        boolean hasFullHouse = false;
        int cardOneCount = 0;
        int cardTwoCount = 0;
        for (int i = 0; i < cards.length - 1 && hasFullHouse; i++) {
            Card currentCard = cards[i];
            Card nextCard = cards[i + 1];
            if (currentCard.value() == nextCard.value()) {
                cardOneCount += 1;
            } else if (currentCard.value() == nextCard.value() - 1) {
                cardTwoCount += 1;
            }
        }
        if (cardOneCount == 3 && cardTwoCount == 1) {
            hasFullHouse = true;
        }
        return hasFullHouse;
    }

    @Override
    public String toString() {
        String cardsString = "Cards: ( ";
        for (int i = 0; i < cards.length; i++) {
            Card card = cards[i];
            if (card != null) {
                cardsString += "[ " + i + " ]" + card.toString();
                if (i != cards.length - 1) {
                    cardsString += ", ";
                }
            }
        }
        cardsString += " )";
        return cardsString;
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

}
