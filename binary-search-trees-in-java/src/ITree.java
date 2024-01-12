public interface ITree<T extends Comparable<T>>{
    ITree<T> insert(T data);

    // void delete(T data);

    // void traverse();

    // T getMax();

    // T getMin();

    boolean isEmpty();
}