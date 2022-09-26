public enum Face {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private String name;
    private int value;
    private int rank;

    Face(String name, int value, int rank) {
        this.name = name;
        this.value = value;
        this.rank = rank;
    }
}
