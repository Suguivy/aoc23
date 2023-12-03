package sugui.util;

public class Ref<T> {
    private T value;
    
    public Ref(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }
}
