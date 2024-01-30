public interface ITree<T extends Comparable<T>>{
    ITree<T> insert(T data);

    void traverse();

    boolean isEmpty();
}
