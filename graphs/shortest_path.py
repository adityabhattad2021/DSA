from helper import Queue
"""
Write a function, shortest_path, that takes in a list of edges for an undirected graph and two nodes (node_A, node_B). The function should return the length of the shortest path between A and B. Consider the length as the number of edges in the path, not the number of nodes. If there is no path between A and B, then return -1. You can assume that A and B exist as nodes in the graph.
edges = [
  ['w', 'x'],
  ['x', 'y'],
  ['z', 'y'],
  ['z', 'v'],
  ['w', 'v']
]
"""

def main():
    assert(shortest_path([
  ['w', 'x'],
  ['x', 'y'],
  ['z', 'y'],
  ['z', 'v'],
  ['w', 'v']
], 'w', 'z')==2)
    assert(shortest_path([
  ['w', 'x'],
  ['x', 'y'],
  ['z', 'y'],
  ['z', 'v'],
  ['w', 'v']
], 'y', 'x')==1)
    print("It worked!")



def build_graph(edges):
    graph = {}
    for edge in edges:
        [node1,node2] = edge
        if node1 not in graph:
            graph[node1] = []
        if node2 not in graph:
            graph[node2] = []
        graph[node1].append(node2)
        graph[node2].append(node1)
    return graph


def bfs_traversal(graph,current,destination,visited):
    queue = Queue()
    queue.enqueue([current,0])
    while not queue.isEmpty():
        [first_element,its_distance] = queue.dequeue()
        if first_element==destination:
            return its_distance
        if first_element not in visited:
            visited[first_element]=True
            neighbours_list = graph[first_element]
            for node in neighbours_list:
                queue.enqueue([node,its_distance+1])
    return -1

def shortest_path(edges,node_a,node_b):
    graph = build_graph(edges)
    return bfs_traversal(graph,node_a,node_b,visited={})

if __name__=="__main__":
    main()