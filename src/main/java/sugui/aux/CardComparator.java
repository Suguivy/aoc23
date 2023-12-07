package sugui.aux;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    private static String ORDERING = "23456789TJQKA";

    @Override
    public int compare(Card c1, Card c2) {
        return Integer.compare(ORDERING.indexOf(c1.label()), ORDERING.indexOf(c2.label()));
    }
    
}
