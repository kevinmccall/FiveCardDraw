public class Tester {
    private Hand one;
    private Hand two;

    public Tester() {
        one = new Hand();
        two = new Hand(5);
    }

    public void makeSF(Hand hand) {
        hand.setCard(4, new Card(Face.KING, Suit.DIAMONDS));
        hand.setCard(3, new Card(Face.QUEEN, Suit.DIAMONDS));
        hand.setCard(2, new Card(Face.JACK, Suit.DIAMONDS));
        hand.setCard(1, new Card(Face.TEN, Suit.DIAMONDS));
        hand.setCard(0, new Card(Face.NINE, Suit.DIAMONDS));
    }

    public void make4K(Hand hand) {
        hand.setCard(0, new Card(Face.FIVE, Suit.DIAMONDS));
        hand.setCard(1, new Card(Face.FIVE, Suit.SPADES));
        hand.setCard(2, new Card(Face.FIVE, Suit.CLUBS));
        hand.setCard(3, new Card(Face.FIVE, Suit.HEARTS));
        hand.setCard(4, new Card(Face.THREE, Suit.DIAMONDS));
    }

    public void make3K(Hand hand) {
        hand.setCard(0, new Card(Face.ACE, Suit.SPADES));
        hand.setCard(1, new Card(Face.EIGHT, Suit.CLUBS));
        hand.setCard(2, new Card(Face.EIGHT, Suit.HEARTS));
        hand.setCard(3, new Card(Face.EIGHT, Suit.DIAMONDS));
        hand.setCard(4, new Card(Face.KING, Suit.DIAMONDS));
    }

    public void make3K2(Hand hand) {
        hand.setCard(0, new Card(Face.THREE, Suit.DIAMONDS));
        hand.setCard(1, new Card(Face.THREE, Suit.SPADES));
        hand.setCard(2, new Card(Face.THREE, Suit.CLUBS));
        hand.setCard(3, new Card(Face.FOUR, Suit.HEARTS));
        hand.setCard(4, new Card(Face.QUEEN, Suit.DIAMONDS));
    }

    public void makeFH(Hand hand) {
        hand.setCard(0, new Card(Face.THREE, Suit.DIAMONDS));
        hand.setCard(1, new Card(Face.THREE, Suit.SPADES));
        hand.setCard(2, new Card(Face.THREE, Suit.CLUBS));
        hand.setCard(3, new Card(Face.QUEEN, Suit.HEARTS));
        hand.setCard(4, new Card(Face.QUEEN, Suit.DIAMONDS));
    }

    public void makeS(Hand hand) {
        hand.setCard(0, new Card(Face.THREE, Suit.DIAMONDS));
        hand.setCard(1, new Card(Face.FOUR, Suit.SPADES));
        hand.setCard(2, new Card(Face.FIVE, Suit.CLUBS));
        hand.setCard(3, new Card(Face.SIX, Suit.HEARTS));
        hand.setCard(4, new Card(Face.SEVEN, Suit.DIAMONDS));
    }

    public void makeS2(Hand hand) {
        hand.setCard(0, new Card(Face.FIVE, Suit.DIAMONDS));
        hand.setCard(1, new Card(Face.SIX, Suit.SPADES));
        hand.setCard(2, new Card(Face.SEVEN, Suit.CLUBS));
        hand.setCard(3, new Card(Face.EIGHT, Suit.HEARTS));
        hand.setCard(4, new Card(Face.NINE, Suit.DIAMONDS));
    }

    public void make2P(Hand hand) {
        hand.setCard(0, new Card(Face.ACE, Suit.DIAMONDS));
        hand.setCard(1, new Card(Face.FOUR, Suit.SPADES));
        hand.setCard(2, new Card(Face.FOUR, Suit.CLUBS));
        hand.setCard(3, new Card(Face.TEN, Suit.HEARTS));
        hand.setCard(4, new Card(Face.TEN, Suit.DIAMONDS));
    }

    public void makeP(Hand hand) {
        hand.setCard(0, new Card(Face.ACE, Suit.DIAMONDS));
        hand.setCard(1, new Card(Face.THREE, Suit.SPADES));
        hand.setCard(2, new Card(Face.FOUR, Suit.CLUBS));
        hand.setCard(3, new Card(Face.TEN, Suit.CLUBS));
        hand.setCard(4, new Card(Face.TEN, Suit.SPADES));
    }

    public void makeH(Hand hand) {
        hand.setCard(0, new Card(Face.ACE, Suit.DIAMONDS));
        hand.setCard(1, new Card(Face.THREE, Suit.SPADES));
        hand.setCard(2, new Card(Face.FOUR, Suit.CLUBS));
        hand.setCard(3, new Card(Face.SEVEN, Suit.CLUBS));
        hand.setCard(4, new Card(Face.TEN, Suit.SPADES));
    }

    public void go() {
        Hand[] hand = new Hand[8];
        for (int i = 0; i < hand.length; i++)
            hand[i] = new Hand();

        makeSF(hand[0]);
        make4K(hand[1]);
        make3K(hand[2]);
        makeFH(hand[3]);
        makeS(hand[4]);
        make2P(hand[5]);
        makeP(hand[6]);
        makeH(hand[7]);
        for (int i = 0; i < hand.length; i++) {
            hand[i].updateHandValues();
            System.out.println(hand[i].toString());
        }

        System.out.println("SF: " + hand[0].getHandType());
        System.out.println("4K: " + hand[1].getHandType());
        System.out.println("3K: " + hand[2].getHandType());
        System.out.println("FH: " + hand[3].getHandType());
        System.out.println("S: " + hand[4].getHandType());
        System.out.println("2P: " + hand[5].getHandType());
        System.out.println("P: " + hand[6].getHandType());
        System.out.println("H: " + hand[7].getHandType());

    }

    public static void main(String[] args) {
        Tester driver = new Tester();
        driver.go();
    }
}
