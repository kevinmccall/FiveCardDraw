import java.util.Arrays;

/**
 * Comment ME!
 *
 */
public class Hand {

    // FIELDS
    private Card[] cards;

    public Hand(int numberOfCards) {
        cards = new Card[numberOfCards];
    }

    public Hand() {
        this(5);
    }

    public void setCard(int index, Card card) {
        cards[index] = card;
    }

    /**
     * TODO: Find out if i can use a break statement in a loop like this
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
        sort();
        Card highestCard = null;
        if (cards[0] != null) {
            highestCard = cards[0];
        }
        return highestCard;
    }

    /**
     * Get the rank value of the
     * 
     * @return
     */
    private int getNPair(int pairN) {
        int cardRank = -1;
        // hasNPair automatically sorts the list for us
        if (hasNPair(pairN)) {
            Face lastFaceValue = null;
            int pairCount = 0;
            for (int i = 0; i < cards.length; i++) {
                Card card = cards[i];
                if (card != null) {
                    if (lastFaceValue == card.getName()) {
                        pairCount++;
                        if (pairN == pairCount - 1) {
                            cardRank = card.getName().getRank();
                            break;
                        }
                    }
                }
                lastFaceValue = card.getName();
            }
            return cardRank;
        }
        return cardRank;
    }

    private boolean hasNPair(int pairN) {
        sort();
        boolean hasNpair = false;
        Face lastFaceValue = null;
        int pairCount = 0;
        for (int i = 0; i < cards.length; i++) {
            Card card = cards[i];
            if (card != null) {
                if (lastFaceValue == card.getName()) {
                    pairCount++;
                    if (pairN == pairCount - 1) {
                        hasNpair = true;
                        break;
                    }
                }
            }
            lastFaceValue = card.getName();
        }
        return hasNpair;
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
