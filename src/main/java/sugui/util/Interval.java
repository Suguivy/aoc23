package sugui.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Class for intervals. From is inclusive, to is exclusive.
 */
public class Interval implements Iterable<Long> {
    private record Node(long value, boolean canBeLess) {
    }

    LinkedList<Node> interval = new LinkedList<>();

    private Interval() {}

    public Interval(long from, long to) {
        Node fromNode = new Node(from, false);
        Node toNode = new Node(to, true);
        interval.add(fromNode);
        interval.add(toNode);
    }

    public boolean contains(long x) {
        for (Node node : interval) {
            if (x < node.value) {
                return node.canBeLess;
            }
        }
        return false;
    }

    private void addNode(Node node) {
        ListIterator<Node> iterator = interval.listIterator();
        while (iterator.hasNext()) {
            Node currNode = iterator.next();
            if (node.value < currNode.value) {
                iterator.previous();
                iterator.add(node);
                return;
            }
        }
        iterator.add(node);
    }

    /**
     * Adds the external interval to the current interval, modifying it.
     * @param extInterval the inverval to add to the current interval
     */
    public Interval union(Interval extInterval) {
        Interval toret = new Interval();
        for (Node node : interval) {
            toret.addNode(node);
        }
        for (Node node : extInterval.interval) {
            toret.addNode(node);
        }
        toret.normalize();
        return toret;
    }

    public void normalize() {
        ListIterator<Node> iterator = interval.listIterator();
        while (iterator.hasNext()) {
            Node currNode = iterator.next();
            if (iterator.hasNext()) {
                Node nextNode = iterator.next();
                iterator.previous();
                if (currNode.value == nextNode.value) {
                    iterator.remove();
                }            
            }
        }
    }

    @Override
    public Iterator<Long> iterator() {
        return new IntervalIterator(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : interval) {
            stringBuilder.append(node.canBeLess + " < " + node.value + "\n");
        }
        return stringBuilder.toString();
    }
}
