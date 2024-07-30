from helper import Queue
"""
Write a function, connected_components_count, that takes in the adjacency list of an undirected graph. The function should return the number of connected components within the graph.
{
  0: [8, 1, 5],
  1: [0],
  5: [0, 8],
  8: [0, 5],
  2: [3, 4],
  3: [2, 4],
  4: [3, 2]
}


"""

def main():
    assert(connected_components_count({
        0: [8, 1, 5],
        1: [0],
        5: [0, 8],
        8: [0, 5],
        2: [3, 4],
        3: [2, 4],
        4: [3, 2]
    })==2) # True
    assert(connected_components_count({
        1: [2],
        2: [1,8],
        6: [7],
        9: [8],
        7: [6, 8],
        8: [9, 7, 2]
    })==1) # True
    assert(connected_components_count({
        3: [],
        4: [6],
        6: [4, 5, 7, 8],
        8: [6],
        7: [6],
        5: [6],
        1: [2],
        2: [1]
    })==3) # True
    print("It worked!")


def connected_components_count(adjacency_list):
    visited = {}
    count = 0
    for element in adjacency_list:
        if bfs(adjacency_list,element,visited):
            count+=1
    return count


def recursive_dfs(adjacency_list,current,visited):
    if current in visited:
        return False
    visited[current]=True
    neighbours_list = adjacency_list[current]
    for element in neighbours_list:
        if element not in visited:
            recursive_dfs(adjacency_list,element,visited)
    return True

def bfs(adjacency_list,current,visited):
    if current in visited:
        return False
    queue = Queue()
    queue.enqueue(current)
    while not queue.isEmpty():
        first = queue.dequeue()
        visited[first]=True
        neighbours_list = adjacency_list[first]
        for element in neighbours_list:
            if element not in visited:
                queue.enqueue(element)
    return True

    
    

    
if __name__ == "__main__":
    main()