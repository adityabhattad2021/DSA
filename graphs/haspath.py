from helper import Queue

"""
    Write a function, hasPath, that takes in an object representing an adjacency list of a directed acyclic graph and two nodes (src, dst). The function should return a boolean indication whether or not there exists a directed path b/w the source and the destination nodes.
    const graph = {
        f : ['g','i'],
        g : ['h'],
        h : [],
        i : ['g','k'],
        j : ['i'],
        k : []
    }
"""

def main():
    adjacency_list = {
        'f': ['g', 'i'],
        'g': ['h'],
        'h': [],
        'i': ['g', 'k'],
        'j': ['i'],
        'k': []
    }
    print(hasPathWithQueue(adjacency_list, 'f', 'k')) # True
    print(hasPathWithQueue(adjacency_list, 'f', 'h')) # True
    print(hasPathWithQueue(adjacency_list, 'i', 'h')) # True
    print(hasPathWithQueue(adjacency_list, 'i', 'f')) # False


# time complexity O(e)
# space complexity O(n) 
def hasPath(adjacency_list,current,destination):
    if current==destination:
        return True

    neighbours_list = adjacency_list[current]
    for element in neighbours_list:
        if hasPath(adjacency_list,element,destination):
            return True

    return False    

def hasPathWithQueue(adjacency_list,current,destination):
    queue = Queue()
    queue.enqueue(current)
    while not queue.isEmpty():
        first = queue.dequeue()
        if first == destination:
            return True
        neighbour_list = adjacency_list[first]
        for element in neighbour_list:
            queue.enqueue(element)
    return False


if __name__=="__main__":
    main()