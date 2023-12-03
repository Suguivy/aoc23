package sugui.util;

public record Pair<T1, T2>(T1 _1, T2 _2) {
    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair) {
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return _1.equals(pair._1) && _2.equals(pair._2);
        }
        return false;
    }
}
