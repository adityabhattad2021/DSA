"""
Write a function, undirected_path, that takes in a list of edges for an undirected graph and two nodes (node_A, node_B). The function should return a boolean indicating whether or not there exists a path between node_A and node_B.
edges = [
  ('i', 'j'),
  ('k', 'i'),
  ('m', 'k'),
  ('k', 'l'),
  ('o', 'n')
]
"""

def main():
    edges = [
        ('i', 'j'),
        ('k', 'i'),
        ('m', 'k'),
        ('k', 'l'),
        ('o', 'n')
    ]
    assert(undirected_path(edges, 'i', 'j')==True) # True
    assert(undirected_path(edges, 'i', 'm')==True) # True
    assert(undirected_path(edges, 'i', 'l')==True) # True
    assert(undirected_path(edges, 'i', 'o')==False) # False
    print("It worked!")


def convert_to_graph(edges):
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

def _recursive_checker(graph,current,destination,visited):
    if current==destination:
        return True
    if current not in visited:
        visited[current]=True
        neighbors_list = graph[current]
        for element in neighbors_list:
            if element not in visited:
                if _recursive_checker(graph,element,destination,visited):
                    return True
    return False


def recursive_checker(graph,current,destination):
    return _recursive_checker(graph,current,destination,visited={})



def undirected_path(edges,current,destination):
    graph = convert_to_graph(edges)
    return recursive_checker(graph,current,destination)

if __name__=="__main__":
    main()
