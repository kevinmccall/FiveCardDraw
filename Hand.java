import java.io.LineNumberInputStream;
import java.nio.file.attribute.FileStoreAttributeView;
import java.util.Arrays;

import javax.swing.plaf.metal.MetalBorders.Flush3DBorder;
import javax.swing.text.Highlighter.Highlight;

/**
 * Comment ME!
 *
 */
public class Hand {

    // FIELDS
    private Card[] cards;

    public Hand(int numberOfCards) {
        cards = new Card[numberOfCards];
        sort();
    }

    public Hand() {
        this(5);
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
        sort();
        Card highestCard = null;
        if (cards[0] != null) {
            highestCard = cards[0];
        }
        return highestCard;
    }

    /**
     * TODO: Find out if I am allowed use a break statement in a loop like this
     * Get the highest rank value of a pair of N cards
     * 
     * @return A rank value between 1 - 10 or -1 if no pair is found
     */
    private int getNPair(int pairN) {
        int cardRank = -1;
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

    }

    private boolean straight() {

    }

    private boolean flush() {

    }

    private boolean fourOfAKind() {

    }

    private boolean threeOfAKind() {

    }

    private boolean pair() {

    }

    private boolean twoPair() {

    }

    private boolean fullHouse() {

    }

    @Override
    public String toString() {
        String cardsString = "Cards: ( ";
        for (int i = 0; i < cards.length; i++) {
            Card card = cards[i];
            if (card != null) {
                cardsString += "[ " + i + " ]" + card.toString();
            }
            if (i != cards.length - 1) {
                cardsString += ", ";
            } else {
                cardsString += " )";
            }
        }
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
