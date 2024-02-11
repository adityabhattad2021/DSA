public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

    private Node<T> root;

    private boolean isBalanced(Node<T> node) {
        int heightOfLeftNode = node.getLeftChild() != null ? node.getLeftChild().getHeight() : 0;
        int heightOfRightChild = node.getRightChild() != null ? node.getRightChild().getHeight() : 0;
        return Math.abs(heightOfLeftNode - heightOfRightChild) > 1 ? false : true;
    }

    private int getDifference(Node<T> node) {
        int heightOfLeftNode = node.getLeftChild() != null ? node.getLeftChild().getHeight() : 0;
        int heightOfRightChild = node.getRightChild() != null ? node.getRightChild().getHeight() : 0;
        return heightOfLeftNode - heightOfRightChild;
    }

    private Node<T> makeRotations(Node<T> node) {
        if (isBalanced(node)) {
            return node;
        } else {
            if (getDifference(node) > 0) {
                if (getDifference(node.getLeftChild()) > 0) {
                    // Left heavy Case.
                    node = rightRotation(node);
                } else {
                    // Left - Right Case.
                    node.setLeftChild(rightRotation(node.getLeftChild()));
                    node = leftRotation(node);
                }
            } else {
                if (getDifference(node.getRightChild()) < 0) {
                    // Right heavy case
                    node = leftRotation(node);
                } else {
                    node.setRightChild(leftRotation(node.getRightChild()));
                    node = leftRotation(node);
                }
            }
        }
        return node;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> temp = node.getLeftChild();
        node.setLeftChild(temp.getRightChild());
        return temp;
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> temp = node.getRightChild();
        node.setRightChild(temp.getLeftChild());
        return temp;
    }

    @Override
    public IAVLTree<T> insert(T data) {
        this.root = insert(this.root, data);
        return this;
    }

    private Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            return new Node<T>(data, null, null);
        }
        if (node.getData().compareTo(data) > 0) {
            node.setLeftChild(insert(node.getLeftChild(), data));
            node.setHeight(node.getLeftChild().getHeight() + 1);
        } else if (node.getData().compareTo(data) < 0) {
            node.setRightChild(insert(node.getRightChild(), data));
            node.setHeight(node.getRightChild().getHeight() + 1);
        } else {
            System.out.println("Two equal data values not supported.");
        }
        node = makeRotations(node);
        return node;
    }

    @Override
    public void delete(T data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void traverse() {
        traverse(this.root);
    }

    private void traverse(Node<T> node) {
        if (node == null) {
            return;
        }
        traverse(node.getLeftChild());
        System.out.println(node + " ");
        traverse(node.getRightChild());
    }

    @Override
    public T getMax() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMax'");
    }

    @Override
    public T getMin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMin'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

}
