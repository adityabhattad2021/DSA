public class Node<T extends Comparable<T>> {
    private T data;
    private int height;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T data, Node<T> leftChild, Node<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = 1;
    }
    
    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }



    public Node<T> getLeftChild() {
        return leftChild;
    }


    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }


    public Node<T> getRightChild() {
        return rightChild;
    }


    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public String toString(){
        return String.format("%d (Height: %d)", this.data,this.height);
    }

}
