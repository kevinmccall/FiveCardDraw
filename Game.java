import java.util.Random;
import java.util.Scanner;

public class Game {
    private Card[] deck;
    private Hand playerHand;
    private Hand computerHand;
    public static final int CARDS_IN_DECK = 52;

    public static void main(String[] args) {
        Game game = new Game();
        game.go();
    }

    public Game() {
        deck = new Card[CARDS_IN_DECK];
    }

    private void initializeDeck() {
        Face[] faceValues = Face.values();
        Suit[] suitValues = Suit.values();
        for (int i = 0; i < faceValues.length; i++) {
            for (int j = 0; j < suitValues.length; j++) {
                deck[i * suitValues.length + j] = new Card(faceValues[i], suitValues[j]);
            }
        }
    }

    public void go() {
        playerHand = new Hand();
        computerHand = new Hand();
        initializeDeck();
        shuffle();
        deal();
        /*
         * 
         * // ace of clubs, three of spades, six of hearts, queen of hearts, king of
         * // diamonds
         * playerHand.setCard(0, new Card(Face.ACE, Suit.CLUBS));
         * playerHand.setCard(1, new Card(Face.THREE, Suit.SPADES));
         * playerHand.setCard(2, new Card(Face.SIX, Suit.HEARTS));
         * playerHand.setCard(3, new Card(Face.QUEEN, Suit.HEARTS));
         * playerHand.setCard(4, new Card(Face.KING, Suit.DIAMONDS));
         * playerHand.updateHandValues();
         * // Ace of diamonds, four of spades, five of diamonds, jack of spades, queen
         * of
         * 
         * // clubs
         * computerHand.setCard(0, new Card(Face.ACE, Suit.DIAMONDS));
         * computerHand.setCard(1, new Card(Face.FOUR, Suit.SPADES));
         * computerHand.setCard(2, new Card(Face.FIVE, Suit.DIAMONDS));
         * computerHand.setCard(3, new Card(Face.JACK, Suit.SPADES));
         * computerHand.setCard(4, new Card(Face.QUEEN, Suit.CLUBS));
         * computerHand.updateHandValues();
         */

        /*
         * 
         * // Three of spades, six of spades, seven of diamonds, ten of hearts, king of
         * // spades
         * playerHand.setCard(0, new Card(Face.THREE, Suit.SPADES));
         * playerHand.setCard(1, new Card(Face.SIX, Suit.SPADES));
         * playerHand.setCard(2, new Card(Face.SEVEN, Suit.DIAMONDS));
         * playerHand.setCard(3, new Card(Face.TEN, Suit.HEARTS));
         * playerHand.setCard(4, new Card(Face.KING, Suit.SPADES));
         * playerHand.updateHandValues();
         * // Seven of spades, eight of hearts, nine of diamonds, ten of diamonds, ten
         * of
         * // spades
         * computerHand.setCard(0, new Card(Face.SEVEN, Suit.SPADES));
         * computerHand.setCard(1, new Card(Face.EIGHT, Suit.HEARTS));
         * computerHand.setCard(2, new Card(Face.NINE, Suit.DIAMONDS));
         * computerHand.setCard(3, new Card(Face.TEN, Suit.DIAMONDS));
         * computerHand.setCard(4, new Card(Face.TEN, Suit.SPADES));
         * computerHand.updateHandValues();
         * 
         */
        displayDeck("Player", playerHand);
        System.out.print("How many cards would you like to remove from your hand: ");
        Scanner scanner = new Scanner(System.in);
        int numCardsToSwitch = scanner.nextInt();
        if (numCardsToSwitch > 0) {
            System.out.print("Enter which cards you would like to remove: ");
            dealOne(playerHand, scanner.nextInt());
            for (int i = 1; i < numCardsToSwitch; i++) {
                dealOne(playerHand, scanner.nextInt()); // Would it be better to use scanner.nextString() here and to
                                                        // manually
            }
            playerHand.updateHandValues();
        }

        System.out.println("------------------------------");
        System.out.println("Reveal Hands!");
        displayDeck("Player", playerHand);
        displayDeck("AI", computerHand);
        System.out.println("------------------------------");

        printWinner();
        scanner.close();

    }

    public void shuffle() {
        // Creating a object for Random class
        Random r = new Random();

        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = deck.length - 1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i + 1);

            // Swap arr[i] with the element at random index
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
        // This code is contributed by Sumit Ghosh
    }

    public void displayDeck(String playerName, Hand hand) {
        System.out.println(playerName + ":");
        System.out.println(hand);
    }

    private void deal() {
        Hand[] hands = { playerHand, computerHand };
        int cardIndex = 0;
        for (int i = 0; i < Hand.DEFALT_POKER_CARDS; i++) {
            for (int j = 0; j < hands.length; j++) {
                Card card = deck[cardIndex];
                while (card == null) {
                    card = deck[++cardIndex]; // will crash if you don't shuffle before hitting the 52nd card
                    deck[cardIndex] = null;
                }
                deck[cardIndex] = null;
                hands[j].setCard(i, card);

                cardIndex++;
            }
        }
        // int handIndex = 0;
        // for (int i = 0; i < deck.length && handIndex < Hand.DEFALT_POKER_CARDS *
        // hands.length; i++) {
        // Card currentCard = deck[i];
        // if (currentCard != null) {
        // hands[i % hands.length].setCard(handIndex, currentCard);
        // handIndex++;
        // }
        // }
        for (int i = 0; i < hands.length; i++) {
            hands[i].updateHandValues();
        }
    }

    private void dealOne(Hand hand, int index) {
        boolean dealtCard = false;
        for (int i = 0; i < deck.length && !dealtCard; i++) {
            Card currentCard = deck[i];
            if (currentCard != null) {
                hand.setCard(index, currentCard);
                deck[i] = null;
            }
        }
    }

    private void printWinner() {
        WinHand playerHandType = playerHand.getHandType();
        WinHand computerHandType = computerHand.getHandType();
        int playerHandVal = playerHandType.ordinal();
        int computerHandVal = computerHandType.ordinal();
        String message;
        if (playerHandVal < computerHandVal) { // compare the enum values (lower means a better hand)
            message = String.format("The player has won with a hand of %s!", playerHandType.toString());
        } else if (playerHandVal == computerHandVal) {
            int playerHighCard = playerHand.getHighestCard();
            int computerHighCard = computerHand.getHighestCard();
            if (playerHighCard > computerHighCard) { // compare the rank of the card (Higher is better but ace is low)
                message = String.format("The player has won with a hand of %s!", playerHandType.toString());
            } else if (playerHighCard == computerHighCard) {
                message = String.format("You both split the pot! You both had a %s", playerHandType.toString());
            } else {
                message = String.format("The computer has won (Drats)! It had %s", playerHandType.toString());
            }
        } else {
            message = String.format("The computer has won (Drats)! It had %s", playerHandType.toString());
        }
        System.out.println(message);
    }

}
