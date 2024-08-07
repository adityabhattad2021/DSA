
public class BST<T extends Comparable<T>> implements ITree<T> {

    private Node<T> root;
    private ITraversalType traversalType;

    public BST() {
        this.traversalType = new InOrderTraversal();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public ITree<T> insert(T data) {
        this.root = insert(data, root);
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) {
            node = new Node<>(data, null, null);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        } else {
            node.setRightChild(insert(data, node.getRightChild()));
        }
        return node;
    }

    public void delete(T data) {
        this.root = delete(this.root, data);
    }

    private Node<T> delete(Node<T> node, T data) {
        if (node == null) {
            return node;
        }
        if (node.getData().compareTo(data) > 0) {
            node.setLeftChild(delete(node.getLeftChild(), data));
        } else if (node.getData().compareTo(data) < 0) {
            node.setRightChild(delete(node.getRightChild(), data));
        } else {

            if (node.getLeftChild() == null) {
                return node.getRightChild();
            }
            if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            node.setData(findMax(node.getLeftChild()).getData());

            node.setLeftChild(delete(node.getLeftChild(), node.getData()));

        }

        return node;
    }

    private Node<T> findMax(Node<T> node) {
        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        return node;
    }

    public void traverse() {
        this.traversalType.traverse(root);
        System.out.println();
    }

    // ****strategy design pattern usage**** //
    public void setTraversalType(ITraversalType traversalType) {
        this.traversalType = traversalType;
    }

    public ITraversalType getPreOrderTraversal() {
        return new PreOrderTraversal();
    }

    public ITraversalType getPostOrderTraversal() {
        return new PostOrderTraversal();
    }

    public ITraversalType getInOrderTraversal() {
        return new InOrderTraversal();
    }

    private class PreOrderTraversal implements ITraversalType {

        public void traverse(Node<?> node) {
            if (node == null)
                return;
            System.out.print(node.getData() + " ");
            traverse(node.getLeftChild());
            traverse(node.getRightChild());
        }

    }

    public class PostOrderTraversal implements ITraversalType {
        public void traverse(Node<?> node) {
            if (node == null)
                return;
            traverse(node.getLeftChild());
            traverse(node.getRightChild());
            System.out.print(node.getData() + " ");
        }

    }

    public class InOrderTraversal implements ITraversalType {

        public void traverse(Node<?> node) {
            if (node == null)
                return;
            traverse(node.getLeftChild());
            System.out.print(node.getData() + " ");
            traverse(node.getRightChild());
        }
    }

}
