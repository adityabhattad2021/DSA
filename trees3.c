/*  
    What is heap?
    => A heap is a special tree based data structure in which the tree is a complete binary tree.
    (A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all the nodes are as far left as possible.)

    It follows the heap property:-
    Max-Heap: In a max heap the data present at the root node must be greatest among the data present at all of its children. The same property must be recursively true for all the sub-trees in that binary tree.
    Min-Heap: In a min heap the key present at the root node must be minimum among the keys present at all of its children. The same property must be true for all the sub-trees in that binary tree.

    Applications:
    => Heap sort algorithm
    => Graph algorithm like - Prims minimal-spanning-tree algorithm and Dijkstra's shortest path algorithm.
    =>A priority queue can be implemented with a heap or a varity of other methods.


    Heap Datas Structure can be implemented in 2 ways:
    1. Creating nodes in memory and link them in tree structure.
    2. Arrange the heap data structure in an array structure.
        The representation is done as:
        1. The root element will be at A[0]
        2. Below table shows indexes of other nodes for its i th node. i.e.
            1. A[(i-1)/2] Return the parent node.
            2. A[(2*i)+1] returns the left child node
            3. A[(2*i)+2]  returns the right child of the node.
            

    Operations using Heap Data Structures:
    1. getMini(): it returns the root of the min heap. Time complexity of this operation is O(1).
    2. getMax(): it returns the root element of the max heap. Time complexity of this operation is O(1).
    3. extractMin(): it removes the minimum element of the min heap.
    Time complexity of this operation is O(log(n)) as this operation needs to maintain the heap property (by calling heapify()) after removing the root.
    4. insert(): Inserting a new node takes O(log(n)) time. We add a new key at the end of the tree.
    If the new key is greater than its parent, then we don't need to do anything. Otherwise we need to traverse up to fix the voilated heap property.
    5. delete(): Deleting a key also takes O(log(n)) time. We replace the key to deleted with the minimum infinite by calling decreaceKey(). After decreaseKey(), the minus infinite value must reach the root, so we call extractMin() to remove the key.
    6. Heapify(): Heapify is the process of creating a heap datastructure from a binary tree. It is used to create a min-heap or a max-heap.
*/  