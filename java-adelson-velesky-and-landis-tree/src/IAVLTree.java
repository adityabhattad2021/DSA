public interface IAVLTree<T extends Comparable<T>>{

    IAVLTree<T> insert(T data);

    void delete(T data);

    void traverse();

    T getMax();

    T getMin();

    boolean isEmpty();

}