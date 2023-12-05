package sugui.util;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class for intervals. From is inclusive, to is exclusive.
 */
public class Interval {
    private record Node(long value, boolean canBeLess) {
    }
    LinkedList<Node> interval = new LinkedList<>();

    public Interval(long from, long to) {
        Node fromNode = new Node(from, false);
        Node toNode = new Node(to, true);
        interval.add(fromNode);
        interval.add(toNode);
    }

    public boolean contains(long x) {
        Iterator<Node> iterator = interval.iterator();
        while(iterator.hasNext()) {
            Node currNode = iterator.next();
            if (x < currNode.value) {
                return currNode.canBeLess;
            }
        }
        return false;
    }
}
