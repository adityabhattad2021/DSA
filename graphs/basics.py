from helper import Stack,Queue
from functools import partial



def main():
    adjacency_list = {
        'A': ['B', 'C'],
        'B': ['A', 'D'],
        'C': ['A', 'E'],
        'D': ['B', 'F'],
        'E': ['C', 'F'],
        'F':['D', 'E'],
        'G': ['A']
    }
    traverse_dfs(adjacency_list=adjacency_list)
    print("------------------------------------")
    recursive_dfs(adjacency_list,current='A')
    print("-------------------------------------")
    traverse_bfs(adjacency_list=adjacency_list)

def traverse_dfs(adjacency_list):
    stack = Stack()
    visited = {}
    stack.push('A')
    while not stack.isEmpty():
        top_element = stack.pop()
        if top_element not in visited:
            print(top_element)
            visited[top_element]=True
        neighbour_list = adjacency_list[top_element]
        for ele in neighbour_list:
            if ele not in visited:
                stack.push(ele)

def _recursive_dfs(adjacency_list,visited,current):
    if current not in visited:
        print(current)
        visited[current]=True
        neighbour_list = adjacency_list[current]
        for element in neighbour_list:
            if element not in visited:
                _recursive_dfs(adjacency_list,visited,element)
recursive_dfs = partial(_recursive_dfs,visited = {})

def traverse_bfs(adjacency_list):
    queue = Queue()
    visited = {}
    queue.enqueue('A')
    while not queue.isEmpty():
        first_element = queue.dequeue()
        if first_element not in visited:
            print(first_element)
            visited[first_element]=True
        neighbour_list = adjacency_list[first_element]
        for element in neighbour_list:
            if element not in visited:
                queue.enqueue(element)

if __name__=='__main__':
    main()