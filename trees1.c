

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