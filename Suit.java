public enum Suit {
    SPADES("Spades"),
    HEARTS("Hearts"),
    CLUBS("Clubs"),
    DIAMONDS("Diamonds");

    private String name;

    private Suit(String name) {
        this.name = name; 
    }

    public String getName() {
        return this.toString();
    }

    public String toString() {
        return name;
    }
}
