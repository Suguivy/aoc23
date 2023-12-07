package sugui.aux;

import java.util.Comparator;

public class CardWithJokerComparator implements Comparator<Card> {
    private static String ORDERING = "J23456789TQKA";
    
    @Override
    public int compare(Card c1, Card c2) {
        return Integer.compare(ORDERING.indexOf(c1.label()), ORDERING.indexOf(c2.label()));
    }

}
