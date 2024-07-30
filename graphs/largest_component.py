def main():
    adjacency_list = {
        0: [8, 1, 5],
        1: [0],
        5: [0, 8],
        8: [0, 5],
        2: [3, 4],
        3: [2, 4],
        4: [3, 2]
    }
    assert(largest_component(adjacency_list)==4)
    print("It worked!")
  



def recursive_dfs(graph,element,visited):
  if element in visited:
    return 0
  visited[element]=True
  neighbours_list = graph[element]
  count = 0
  for node in neighbours_list:
    count+=recursive_dfs(graph,node,visited)
  return count+1
      


def largest_component(graph):
  max_count = 0
  visited = {}
  for element in graph:
    count = recursive_dfs(graph,element,visited)
    if count>0:
      max_count = max(max_count,count)

  return max_count



if __name__=="__main__":
  main()