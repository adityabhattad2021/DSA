import math


class BinaryHeap:
    """
    Binary Heap implementation that can work as either Min or Max heap.
    heap_type: str, either 'min' or 'max' to determine the type of heap
    """
    def __init__(self, heap_type='min',target = []):
        if heap_type not in ['min','max']:
            raise ValueError("Invalid heap type.")
        self.heap = target
        self.heap_type = heap_type
        self.build_heap(self.heap)

    def parent(self, index):
        return math.ceil(index/2) - 1

    def left_child(self, index):
        return 2*index + 1

    def right_child(self, index):
        return 2*index + 2

    def swap(self, index1, index2):
        self.heap[index1],self.heap[index2] = self.heap[index2],self.heap[index1]

    def compare(self, index1, index2):
        if self.heap_type == 'min':
            return self.heap[index1] > self.heap[index2]
        if self.heap_type == 'max':
            return self.heap[index1] < self.heap[index2]

    def heapify_up(self, index):
        parent = self.parent(index)
        if (parent >= 0) and (not self.compare(index,parent)):
            self.swap(index,parent)
            self.heapify_up(parent)
        
    def heapify_down(self, index):
        left_child = self.left_child(index)
        right_child = self.right_child(index)
        idx_to_rpls = index

        if left_child < self.size() and self.compare(index,left_child):
            idx_to_rpls = left_child
        if right_child < self.size() and self.compare(idx_to_rpls,right_child):
            idx_to_rpls = right_child
        
        if idx_to_rpls != index:
            self.swap(index,idx_to_rpls)
            self.heapify_down(idx_to_rpls)

    # O(logn)
    def insert(self, value):
        self.heap.append(value)
        self.heapify_up(self.size()-1)

    # O(logn)
    def extract(self):
        if self.is_empty():
            raise IndexError("Heap is empty")
        self.swap(0,self.size()-1)
        res = self.heap.pop()
        self.heapify_down(0)
        return res

    # O(1)
    def peek(self):
        if self.is_empty():
            raise IndexError("Heap is empty")
        return self.heap[0]

    def size(self):
        return len(self.heap)

    def is_empty(self):
        return len(self.heap) == 0

    # O(n)
    def build_heap(self,arr):
        last_leaf = (self.size()//2) - 1
        for idx in range(last_leaf,-1,-1):
            self.heapify_down(idx)


# Test cases
def test_binary_heap():
    # Test Min Heap
    min_heap = BinaryHeap(heap_type='min',target=[3, 7, 1, 4, 6])
    
    assert min_heap.size() == 5, "Size should be 5 after insertions"
    
    # Test 3: Extract minimum
    min_val = min_heap.extract()
    assert min_val == 1, "Minimum value should be 1"
    
    # Test 4: Peek
    assert min_heap.peek() == 3, "Next minimum value should be 3"
    
    # Test Max Heap
    max_heap = BinaryHeap(heap_type='max',target=[])
    
    # Test 5: Basic max heap operations
    values = [5, 3, 7, 1, 4, 6]
    for val in values:
        max_heap.insert(val)
    assert max_heap.extract() == 7, "Maximum value should be 7"
    assert max_heap.peek() == 6, "Next maximum value should be 6"

if __name__ == "__main__":
    test_binary_heap()
    print("All test cases passed!")