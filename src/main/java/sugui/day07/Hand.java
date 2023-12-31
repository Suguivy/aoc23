package sugui.day07;

public record Hand(Card c1, Card c2, Card c3, Card c4, Card c5, int bid) {

    public enum HandType {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND,
    }
}
