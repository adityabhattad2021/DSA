#include <stdio.h>
#include <stdlib.h>

/*
    Binary Tree - A binary tree is a tree data structure in which each node has at most two children, which are referred to as the left child(LC) and right child(RC)

    Important Binary Tree Terms & Properties -
    1. A binary tree is called STRICT/PROPER binary tree, when each node has 2 or 0 children.
    2. A binary tree is called COMPLETE binary tree if all levels expect the leaf nodes are completely filled and and all nodes are as left as possible.
    3. A binary tree is called PERFECT binary tree if all levels are completely filled with two childrens.
    4. Max number of nodes at level 'x' = 2^x.
    5. For a binary tree, maximum number of nodes with height h = 2^0 + 2^1 + 2^2 + ... + 2^h.
       =>2^(h+1)-1
    6. A binary tree is called BALANCED binary tree, if the difference between the height of left and right subtree for every node is not more than k (usually 1).
*/

/*
    Binary Search Tree (BST) - It is a binary tree data structure, in which the values in the left subtrees of every node are smaller and the values in the right subtrees of every node are larger
    e.g
                    25
                   /   \
                  19   36
                 / \   / \
                14 23 29 52
    Time complexities:
    - Best cases (For balanced binary trees)
        Search/Access/Update = O(log(n)),
        Insert/Add = O(log(n)),
        Delete/Remove = O(log(n))
    - Worst cases (For unbalanced binary trees)
        Search/Access/Update = O(n)
        Insert/Add = O(n)
        Delete/Remove = O(n)
*/

typedef struct tree_node
{
    int data;
    struct tree_node *leftChild;
    struct tree_node *rightChild;
} TreeNode;

TreeNode *create_tree_node(int data)
{
    TreeNode *newNode = (TreeNode *)malloc(sizeof(TreeNode));
    newNode->data = data;
    newNode->leftChild = NULL;
    newNode->rightChild = NULL;

    return newNode;
}

// Insertion function for non duplicate elements only.
void insertNode(TreeNode **root, TreeNode *newNode)
{
    if (*root == NULL)
    {
        *root = newNode;
    }
    else
    {
        TreeNode *buffer = NULL;
        buffer = *root;
        while (buffer != NULL)
        {
            if (buffer->data == newNode->data)
            {
                printf("\nThis value already exists, enter another value.\n");
                return;
            }
            else if ((buffer->data > newNode->data) && (buffer->leftChild == NULL))
            {
                printf("Inserted at left side.");
                buffer->leftChild = newNode;
                return;
            }
            else if (buffer->data > newNode->data)
            {
                buffer = buffer->leftChild;
                continue;
            }
            else if ((buffer->data < newNode->data) && (buffer->rightChild == NULL))
            {
                printf("Inserted at right side.");
                buffer->rightChild = newNode;
                return;
            }
            else if (buffer->data < newNode->data)
            {
                buffer = buffer->rightChild;
                continue;
            }
        }
    }
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
    // Pass initial space count as 0
    print2DUtil(root, 0);
}

/*
    Binary Tree Traversal Techniques
    - Tree travesal (also known as tree search and walking tree) refers to the process of visiting (checking and/or updating) each node in the tree data structure, exactly once. Such traversals are classified by the order in which the nodes are visited.


    - Depth first search/traversal of binary tree (DFS):

    1. Pre Order (Node, Right, Left):
        - Access the data part of the current node.
        - Traverse the left subtree by recursively calling the pre-order function.
        - Traverse the right subtree by recursively calling the pre-order function.

    2. In Order (Left, Node, Right):
        - Traverse the left subtree by recursively calling the in-order function.
        - Access the data part of the current node.
        - Traverse the right subtree by recursively calling the in-order function.
        In BST in-order traversal retrives the key in ascending sorted order.

    3. Post Order (Left, Right, Node):
        - Traverse the left subtree by recursively calling the post-order function.
        - Traverse the right subtree by recursively calling the post-order function.
        - Access the data part of the current node.


    - Breadth first search/ level order (BFS)

    Trees can also be traversed in a level order, where we visit every node on a level before going to a lower level. This search is referred to as breadth-first-search (BFS), as the search tree is brodened as much as possible on each depth before going to the next depth.

    e.g        30
             /    \
            18    43
           /  \  /  \
          10  25 32  48

        DFS(Depth First Search):
        Pre-Order Search = 30 18 10 25 43 32 48
        In-Order Search = 10 18 25 30 32 43 48
        Post-Order Search = 10 25 18 32 48 43 30

        BFS(Breadth First Search): 30 18 43 10 25 32 48

*/

// Pre Order tree traversal
void preOrderTraversal(TreeNode *root)
{
    if (root == NULL)
    {
        return;
    }
    printf("%d ", root->data);
    preOrderTraversal(root->leftChild);
    preOrderTraversal(root->rightChild);
}

// In-Order tree traversal
void inOrderTraversal(TreeNode *root)
{
    if (root == NULL)
    {
        return;
    }
    inOrderTraversal(root->leftChild);
    printf("%d ", root->data);
    inOrderTraversal(root->rightChild);
}

// Post-Order tree traaversal
void postOrderTraversal(TreeNode *root)
{
    if (root == NULL)
    {
        return;
    }
    postOrderTraversal(root->leftChild);
    postOrderTraversal(root->rightChild);
    printf("%d ", root->data);
}

// Search Operation on Binary Tree
// Time Complexity: O(log(n))
// Iterative Approach
void search_element(TreeNode *root, int toFind)
{
    if (root == NULL)
    {
        printf("\nTree is empty.\n");
    }
    TreeNode *temp = NULL;
    temp = root;
    while (temp != NULL)
    {
        if (temp->data == toFind)
        {
            printf("\nElement is present in the binary search tree.\n");
            return;
        }
        else if (temp->data < toFind)
        {
            temp = temp->rightChild;
            continue;
        }
        else if (temp->data > toFind)
        {
            temp = temp->leftChild;
            continue;
        }
    }
    printf("\nElement not found.\n");
}

void recursive_search_element(TreeNode *root, int toFind)
{
    if (root == NULL)
    {
        printf("\nElement not found.\n");
        return;
    }
    else if (root->data == toFind)
    {
        printf("\nElement is present in the binary tree.\n");
        return;
    }
    else if (root->data < toFind)
    {
        recursive_search_element(root->rightChild, toFind);
    }
    else if (root->data > toFind)
    {
        recursive_search_element(root->leftChild, toFind);
    }
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
            temporary_node = create_tree_node(data);
            insertNode(&root, temporary_node);
        }
    }
    // root = create_tree_node(21);
    if (isEmpty(root))
    {
        printf("\nBinary Search Tree is currently empty.\n");
    }
    else
    {
        printf("\nBinary Search Tree is not empty.\n");
    }
    print2D(root);
    printf("\nBinary tree in pre-order traversal is: ");
    preOrderTraversal(root);
    printf("\nBinary tree in in-order traversal is: ");
    inOrderTraversal(root);
    printf("\nBinary tree in post-order traversal is: ");
    postOrderTraversal(root);
    int element;
    while (1)
    {
        printf("\nEnter the element to search(-999 to stop): ");
        scanf("%d", &element);
        if (element == -999)
        {
            break;
        }
        recursive_search_element(root, element);
    }

    return 0;
}