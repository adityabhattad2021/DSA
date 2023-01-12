/*
    What is balanced binary tree?
    A binary tree is called a balanced binary tree, if the difference between the height of the left and right subtree for every node is not more than k (usually k=1) height of a tree is the number of edges in the longest path from the root of the tree and a leaf node.
    e.g.:
                    25
                   /   \
                  19   36
                 / \   / \
                14 23 29 52

    Why balanced binary tree is important?
    An unbalanced bianry trees behaves like a linked list, and time complexity of linked list for searching an element is O(n) which is not at all ideal.
    Hence to improve the time complexity of binary tree from O(n) to O(log(n))
    we require binary tree to be balanced.`
*/

// AVL Tree is balanced binary tree

#include <stdio.h>
#include <stdlib.h>

typedef struct tree_node
{
    int data;
    struct tree_node *leftChild;
    struct tree_node *rightChild;
} TreeNode;

TreeNode *create_new_node(int data)
{
    TreeNode *newNode = NULL;
    newNode = (TreeNode *)malloc(sizeof(TreeNode));
    newNode->data = data;
    newNode->leftChild = NULL;
    newNode->rightChild = NULL;

    return newNode;
}

int find_height_of_binary_tree(TreeNode *root)
{
    if (root == NULL)
    {
        return -1;
    }
    int left_height = find_height_of_binary_tree(root->leftChild);
    int right_height = find_height_of_binary_tree(root->rightChild);
    if (left_height > right_height)
    {
        return left_height + 1;
    }
    else
    {
        return right_height + 1;
    }
}

int getBalancedFactor(TreeNode *n)
{
    if (n == NULL)
    {
        return -1;
    }
    else
    {
        return (find_height_of_binary_tree(n->leftChild) - find_height_of_binary_tree(n->rightChild));
    }
}

TreeNode *leftRotate(TreeNode *n)
{
    TreeNode *a = NULL, *b = NULL, *c = NULL;
    a = n->rightChild;
    b = a->leftChild;
    a->leftChild = n;
    n->rightChild = b;

    return a;
}

TreeNode *rightRotate(TreeNode *n)
{
    TreeNode *a = NULL, *b = NULL, *c = NULL;
    a = n->leftChild;
    b = a->rightChild;
    a->rightChild = n;
    n->leftChild = b;

    return a;
}

TreeNode *recursiveInsertNode(TreeNode *root, TreeNode *newNode)
{
    if (root == NULL)
    {
        return newNode;
    }
    else if (root->data > newNode->data)
    {
        root->leftChild = recursiveInsertNode(root->leftChild, newNode);
    }
    else if (root->data < newNode->data)
    {
        root->rightChild = recursiveInsertNode(root->rightChild, newNode);
    }
    else
    {
        printf("\nDuplicate element found, cannot insert.\n");
        return root;
    }

    int balanceFactor = getBalancedFactor(root);
    printf("\n Balance factor is %d.\n",balanceFactor);

    if (balanceFactor > 1 && newNode->data < root->leftChild->data)
    {
        return rightRotate(root);
    }
    else if (balanceFactor < -1 && newNode->data > root->rightChild->data)
    {
        return leftRotate(root);
    }
    else if (balanceFactor > 1 && newNode->data > root->leftChild->data)
    {
        root->leftChild = leftRotate(root);
        return rightRotate(root);
    }
    else if (balanceFactor < -1 && newNode->data < root->rightChild->data)
    {
        root->rightChild = rightRotate(root);
        return leftRotate(root);
    }
    return root;
}

int isEmpty(TreeNode *root)
{
    if (root == NULL)
    {
        return 1;
    }
    return 0;
}

// It basically follows reverse In-Order Treversal Techinique(Right, Node, Left) for printing.
void print2DUtil(TreeNode *root, int space)
{
    // Base case
    if (root == NULL)
        return;

    // Increase distance between levels
    space += 10;

    // Process right child first
    print2DUtil(root->rightChild, space);

    // Print current node after space
    // count
    printf("\n");
    for (int i = 10; i < space; i++)
    {
        printf(" ");
    }
    printf("%d\n", root->data);

    // Process left child
    print2DUtil(root->leftChild, space);
}

// Wrapper over print2DUtil()
void print2D(TreeNode *root)
{
    if (!isEmpty(root))
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }
    else
    {
        printf("\nBinary tree is empty.\n");
    }
}

void inOrderTraversal(TreeNode *root){
    if(root==NULL){
        return;
    }
    inOrderTraversal(root->leftChild);
    printf("%d ",root->data);
    inOrderTraversal(root->rightChild);
}

int main()
{
    TreeNode *root = NULL, *temporary_node = NULL;
    int data;
    while (1)
    {
        printf("\nEnter a number to add in a binary search tree(-999 to stop): ");
        scanf("%d", &data);
        if (data == -999)
        {
            break;
        }
        else
        {
            temporary_node = create_new_node(data);
            root = recursiveInsertNode(root, temporary_node);
        }
    }
    // root = create_tree_node(21);
    print2D(root);
    printf("\nThe in order sequence of the bianry tree is: ");
    inOrderTraversal(root);
    return 0;
}