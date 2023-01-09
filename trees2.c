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