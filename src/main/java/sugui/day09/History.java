package sugui.day09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class History {
    private List<Integer> history;

    public History(List<Integer> history) {
        this.history = new ArrayList<>(history);
    }

    public int getNext() {
        if (isZeroes()) {
            return 0;
        } else {
            var differences = generateDifferences();
            var nextDiff = differences.getNext();
            return history.get(history.size() - 1) + nextDiff;
        }
    }

    public History reverse() {
        var revHistory = new ArrayList<>(history);
        Collections.reverse(revHistory);
        return new History(revHistory);
    }

    public boolean isZeroes() {
        return history.stream().allMatch(val -> val == 0);
    }

    private History generateDifferences() {
        var differences = IntStream.range(1, history.size()).mapToObj(i -> history.get(i) - history.get(i - 1))
                .collect(Collectors.toList());
        return new History(differences);
    }

    @Override
    public String toString() {
        return this.history.toString();
    }
}
