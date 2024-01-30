public class RedBlackTree<T extends Comparable<T>> implements ITree<T> {

    private Node<T> root;

    @Override
    public ITree<T> insert(T data){
        Node<T> node = new Node<>(data);
        root = insert(root,node);
        recolorAndRotate(node);
        return this;
    }

    public Node<T> insert(Node<T> node,Node<T> nodeToInsert){
        if(node==null){
            return nodeToInsert;
        }
        if(nodeToInsert.getData().compareTo(node.getData())<0){
            node.setLeftChild(insert(node.getLeftChild(),nodeToInsert));
            node.getLeftChild().setParent(node);
        }else if(nodeToInsert.getData().compareTo(node.getData())>0){
            node.setRightChild(insert(node.getRightChild(),nodeToInsert));
            node.getRightChild().setParent(node);
        }
        return node;
    }

    private void recolorAndRotate(Node<T> node){
        Node<T> parent = node.getParent();
        if(node!=root && parent.getColor()==Color.RED){
            Node<T> grandParent = node.getParent().getParent();
            Node<T> uncle = parent.isLeftChild() ?
                    grandParent.getRightChild() : grandParent.getLeftChild();
            if(uncle!=null && uncle.getColor()==Color.RED){
                handleRecoloring(parent,uncle,grandParent);
            }else if(parent.isLeftChild()){
                handleLeftSituations(node,parent,grandParent);
            }else{
                // if parent is a right child
                handleRightSituations(node,parent,grandParent);
            }
        }
        root.setColor(Color.BLACK);
    }

    private void handleRightSituations(Node<T> node,Node<T> parent,Node<T> grandParent){
        if(node.isLeftChild()){
            rotateRight(parent);
        }
        parent.flipColor();
        grandParent.flipColor();
        rotateLeft(grandParent);
        recolorAndRotate(node.isLeftChild()?grandParent:parent);
    }

    private void handleLeftSituations(Node<T> node,Node<T> parent,Node<T> grandParent){
        if(!node.isLeftChild()){
            rotateLeft(parent);
        }
        parent.flipColor();
        grandParent.flipColor();
        rotateRight(grandParent);
        recolorAndRotate(!node.isLeftChild() ? grandParent:parent);
    }

    private void handleRecoloring(Node<T> parent,Node<T> uncle,Node<T> grandParent){
        uncle.flipColor();
        parent.flipColor();
        grandParent.flipColor();
        recolorAndRotate(grandParent);
    }

    private void rotateRight(Node<T> node){
        Node<T> leftNode = node.getLeftChild();
        node.setLeftChild(leftNode.getRightChild());
        if(node.getLeftChild()!=null){
            node.getLeftChild().setParent(node);
        }
        leftNode.setRightChild(node);
        leftNode.setParent(node.getParent());
        updateChildOfParent(node,leftNode);
        node.setParent(leftNode);
    }

    private void rotateLeft(Node<T> node){
        Node<T> rightNode = node.getRightChild();
        node.setRightChild((rightNode.getLeftChild()));
        if(node.getRightChild()!=null){
            node.getRightChild().setParent(node);
        }
        rightNode.setLeftChild(node);
        rightNode.setParent(node.getParent());
        updateChildOfParent(node,rightNode);
        node.setParent(rightNode);
    }

    private void updateChildOfParent(Node<T> node,Node<T> tempNode){
        if(node.getParent()==null){
            root=tempNode;
        }else if(node.isLeftChild()){
            node.getParent().setLeftChild(tempNode);
        }else{
            node.getParent().setRightChild(tempNode);
        }
    }

    @Override
    public void traverse(){
        traverseInOrder(root);
    }
    
    private void traverseInOrder(Node<T> node){
        if(node!=null){
            traverseInOrder(node.getLeftChild());
            System.out.print(node.getData()+" ");
            traverseInOrder(node.getRightChild());
        }
    }

    @Override 
    public boolean isEmpty(){
        return root==null;
    }
}
