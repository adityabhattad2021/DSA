"""
minimum island
Write a function, minimum_island, that takes in a grid containing Ws and Ls. W represents water and L represents land. The function should return the size of the smallest island. An island is a vertically or horizontally connected region of land.

You may assume that the grid contains at least one island.

grid = [
  ['W', 'L', 'W', 'W', 'W'],
  ['W', 'L', 'W', 'W', 'W'],
  ['W', 'W', 'W', 'L', 'W'],
  ['W', 'W', 'L', 'L', 'W'],
  ['L', 'W', 'W', 'L', 'L'],
  ['L', 'L', 'W', 'W', 'W'],
]

"""


def main():
    assert(minimum_island([
        ['W', 'L', 'W', 'W', 'W'],
        ['W', 'L', 'W', 'W', 'W'],
        ['W', 'W', 'W', 'L', 'W'],
        ['W', 'W', 'L', 'L', 'W'],
        ['L', 'W', 'W', 'L', 'L'],
        ['L', 'L', 'W', 'W', 'W'],
    ])==2)
    print("It worked!")

def get_neighbours(grid,row,column):
  neighbours = []
  
  if row>0 and grid[row-1][column]=='L':
    neighbours.append([row-1,column])
    
  if column>0 and grid[row][column-1]=='L':
    neighbours.append([row,column-1])
    
  if row<len(grid)-1 and grid[row+1][column]=='L':
    neighbours.append([row+1,column])
    
  if column<len(grid[row])-1 and grid[row][column+1]=='L':
    neighbours.append([row,column+1])
    
  return neighbours

def traverse_bfs(grid,row,column,visited):
  if (row,column) in visited:
    return 0
  count=0
  visited[(row,column)]=True
  neighbours = get_neighbours(grid,row,column)
  for neighbour in neighbours:
    count+=traverse_bfs(grid,neighbour[0],neighbour[1],visited)
  return count+1

def minimum_island(grid):
    min_count = 9999999999
    visited = {}
    for row in range(len(grid)):
      for column in range(len(grid[row])):
        if grid[row][column]!='W':
          count = traverse_bfs(grid,row,column,visited)
          if count>0:
            min_count = min(min_count,count)
    return min_count


if __name__=="__main__":
    main()