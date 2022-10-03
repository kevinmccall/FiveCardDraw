import java.util.Random;
import java.util.Scanner;

/**
 * Represents a Game of Five Card Draw. It is public so that the game can be
 * played.
 * 
 * @author Kevin McCall
 * @version 10/3/2022
 */
public class Game {
    /** Represents a deck of cards */
    private Card[] deck;
    /** Represents the Player's hand of cards */
    private Hand playerHand;
    /** Represents the Computers's hand of cards */
    private Hand computerHand;
    /** The default amount of cards in a deck */
    public static final int CARDS_IN_DECK = 52;

    /**
     * Runs a game of Five Card draw with user input
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.go();
    }

    /** Default constructor for a game of five card draw */
    public Game() {
        deck = new Card[CARDS_IN_DECK];
    }

    /** Initializes a deck with 52 cards */
    private void initializeDeck() {
        Face[] faceValues = Face.values();
        Suit[] suitValues = Suit.values();
        for (int i = 0; i < faceValues.length; i++) {
            for (int j = 0; j < suitValues.length; j++) {
                deck[i * suitValues.length + j] = new Card(faceValues[i], suitValues[j]);
            }
        }
    }

    /** Starts a game of Five Card Draw with default settings */
    public void go() {
        playerHand = new Hand();
        computerHand = new Hand();
        initializeDeck();
        shuffle();
        deal();
        switchPlayerCards();
        defualtRevealHands();
        printWinner();

    }

    /** Helper metod that Allows the player to switch their cards */
    private void switchPlayerCards() {
        displayDeck("Player", playerHand);
        System.out.print("How many cards would you like to remove from your hand: ");
        Scanner scanner = new Scanner(System.in);
        int numCardsToSwitch = scanner.nextInt();
        while (numCardsToSwitch < 0 || numCardsToSwitch > playerHand.getHandSize()) {
            System.err.println("Please provide a valid integer.");
            numCardsToSwitch = scanner.nextInt();
        }
        if (numCardsToSwitch > 0) {
            System.out.print("Enter which cards you would like to remove: ");
            for (int i = 0; i < numCardsToSwitch; i++) {
                int inputIndex = scanner.nextInt();
                while (inputIndex < 0 || inputIndex >= playerHand.getHandSize()) {
                    System.err.println("Please provide a valid integer position in the hand.");
                    inputIndex = scanner.nextInt();
                }
                dealOne(playerHand, inputIndex);
            }
            playerHand.updateHandValues();
        }
        scanner.close();
    }

    /** Helper method that prints the player's and the computer's hand */
    private void defualtRevealHands() {
        System.out.println("------------------------------");
        System.out.println("Reveal Hands!");
        displayDeck("Player", playerHand);
        displayDeck("AI", computerHand);
        System.out.println("------------------------------");
    }

    /**
     * Uses the fisher-gates shuffle method from Sumit Ghosh to shuffle the deck of
     * cards. PUblic because people may want to shuffle mid-game.
     */
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

    /**
     * Helper method that prints a deck of cards to stdout alongside the name of the
     * player who's deck is being shown
     * 
     * @param playerName The name of the player whose cards are being shown
     * @param hand       The hand that is to be printed
     */
    private void displayDeck(String playerName, Hand hand) {
        System.out.println(playerName + ":");
        System.out.println(hand);
    }

    /**
     * Deals cards to all players in a round robin type of way. Takes cards out of
     * deck
     */
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
        for (int i = 0; i < hands.length; i++) { // Inform each hand that their cards have been updated
            hands[i].updateHandValues();
        }
    }

    /**
     * Discards a single card from a hand at an index and deals a new card to that
     * index. Takes card from deck.
     * 
     * @param hand  The hand to deal a new card to
     * @param index The index of which card you are trying to access
     */
    private void dealOne(Hand hand, int index) {
        boolean dealtCard = false;
        for (int i = 0; i < deck.length && !dealtCard; i++) {
            Card currentCard = deck[i];
            if (currentCard != null) {
                hand.setCard(index, currentCard);
                deck[i] = null;
                dealtCard = true;
            }
        }
    }

    /**
     * Compares the hands of the player and the computer and prints the winner to
     * stdout. In the event of a tie of handType, the deck with the highest card
     * will win (Not realistic poker strategy).
     */
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
                message = String.format("The computer has won (Drats)! It had %s", computerHandType.toString());
            }
        } else {
            message = String.format("The computer has won (Drats)! It had %s", computerHandType.toString());
        }
        System.out.println(message);
    }

}
