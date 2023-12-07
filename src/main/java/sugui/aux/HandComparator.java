package sugui.aux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import sugui.aux.Hand.HandType;

public class HandComparator implements Comparator<Hand> {

    private HandType getHandType(Hand hand) {
        Map<String, Integer> cardCount = new HashMap<>();
        for (Card c : List.of(hand.c1(), hand.c2(), hand.c3(), hand.c4(), hand.c5())) {
            if (cardCount.containsKey(c.label()))
                cardCount.compute(c.label(), (k, v) -> v + 1);
            else
                cardCount.put(c.label(), 1);
        }
        List<Integer> counts = new ArrayList<>(cardCount.values());
        counts.sort(Comparator.reverseOrder());
        int fstCount = counts.get(0);
        int sndCount = counts.size() == 1 ? 0 : counts.get(1);
        if (fstCount == 5)
            return HandType.FIVE_OF_A_KIND;
        else if (fstCount == 4)
            return HandType.FOUR_OF_A_KIND;
        else if (fstCount == 3 && sndCount == 2)
            return HandType.FULL_HOUSE;
        else if (fstCount == 3 && sndCount == 1)
            return HandType.THREE_OF_A_KIND;
        else if (fstCount == 2 && sndCount == 2)
            return HandType.TWO_PAIR;
        else if (fstCount == 2 && sndCount == 1)
            return HandType.ONE_PAIR;
        else
            return HandType.HIGH_CARD;
    }

    @Override
    public int compare(Hand h1, Hand h2) {
        HandType h1HandType = getHandType(h1);
        HandType h2HandType = getHandType(h2);

        if (h1HandType.equals(h2HandType)) {
            var cardComparator = new CardComparator();
            int cmp1 = cardComparator.compare(h1.c1(), h2.c1());
            int cmp2 = cardComparator.compare(h1.c2(), h2.c2());
            int cmp3 = cardComparator.compare(h1.c3(), h2.c3());
            int cmp4 = cardComparator.compare(h1.c4(), h2.c4());
            int cmp5 = cardComparator.compare(h1.c5(), h2.c5());
            return Stream.of(cmp1, cmp2, cmp3, cmp4, cmp5)
                    .dropWhile(cmp -> cmp == 0)
                    .findFirst()
                    .orElse(0);
        } else {
            return h1HandType.compareTo(h2HandType);
        }
    }

}
