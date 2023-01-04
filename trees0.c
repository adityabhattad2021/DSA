/*
    Tree - A tree is a non linear data structure that stimulates a hierarchical tree structure, with a root value and subtrees of children with a parent node, represented as a set of linked nodes.
      A
     / \
    B   C
       / \
      D   E


    Some key terms and their definitions in the context of trees:
    - Node: A node is a basic unit in a tree. It can have a value and zero or more child nodes.
    - Root: The root is the top node in a tree. It is the ancestor of all other nodes in the tree.
    - Child: A child is a node that is directly connected to another node when moving away from the root. For example, in the following tree, B, C, and D are children of A.
    - Parent: A parent is a node that has one or more child nodes. In the above example, A is the parent of B, C, and D.
    - Leaf: A leaf is a node that does not have any children. In the above example, B and E are leaf nodes.
    - Sibling: Siblings are nodes that have the same parent. In the above example, B and C are siblings.
    - Subtree: A subtree is a set of nodes and their descendants. For example, in the above tree, the subtree rooted at C consists of the nodes C, D, and E.
    - Depth: The depth of a node is the number of edges from the root to the node. For example, the depth of node A in the above tree is 0, the depth of nodes B and C is 1, and the depth of nodes D and E is 2.
    - Height: The height of a tree is the maximum depth of any node in the tree. In the above tree, the height is 2.
*/

/*
    Some key points about trees:
    - A tree is a collection of nodes organized in a hierarchical structure.
    - Each node in a tree has zero or more child nodes, which are below it in the hierarchy.
    -The top node in a tree is called the root.
    Nodes that do not have any children are called leaf nodes.
    - A tree can be either empty (null) or contain a root node.
*/


/*
    Some common types of trees:
    - Binary trees: Each node has at most two children.
    - Binary search trees: A binary tree in which the value of each node is greater than or equal to the values in its left subtree and less than or equal to the values in its right subtree.
    - Balanced trees: A tree in which the height of the left and right subtrees of every node differ by at most one.
    - Heap: A complete binary tree in which the value of each node is greater than or equal to the values in its children.
*/


/*
    Applications of trees:
    - Trees are used to store hierarchical data, such as the file system on a computer.
    - Trees (with some ordering e.g., BST) allows faster search, insert, and delete operations on sorted data.
    - Binary heaps are used in priority queues.
    - Used by compilers to build syntax trees.
    - Used to implement expression parser and expression solvers.
*/