"""
Write a function, island_count, that takes in a grid containing Ws and Ls. W represents water and L represents land. The function should return the number of islands on the grid. An island is a vertically or horizontally connected region of land.
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
    assert(island_count([
        ['W', 'L', 'W', 'W', 'W'],
        ['W', 'L', 'W', 'W', 'W'],
        ['W', 'W', 'W', 'L', 'W'],
        ['W', 'W', 'L', 'L', 'W'],
        ['L', 'W', 'W', 'L', 'L'],
        ['L', 'L', 'W', 'W', 'W'],
    ])==3)
    print("It worked!")

def get_neighbours(grid,row,column):
  neighbours = []
  if row>0 and grid[row-1][column]!='W':
    neighbours.append([row-1,column])

  if column<len(grid[row])-1 and grid[row][column+1]!='W':
    neighbours.append([row,column+1])

  if column>0 and grid[row][column-1]!='W':
    neighbours.append([row,column-1])

  if row<len(grid)-1 and grid[row+1][column]!='W':
    neighbours.append([row+1,column])
  return neighbours

def recursive_traversal(grid,row,column,visited):
  if (row,column) in visited:
    return False
  visited[(row,column)] = True
  neighbours = get_neighbours(grid,row,column)
  for neighbour in neighbours:
    recursive_traversal(grid,neighbour[0],neighbour[1],visited)
  return True

def island_count(grid):
  count = 0
  visited = {}
  for row in range(len(grid)):
    for column in range(len(grid[row])):
      if (grid[row][column] == 'L'):
        if recursive_traversal(grid,row,column,visited):
          count+=1
  return count


if __name__=="__main__":
    main()


"""
grid = [
  ['W', 'L', 'W', 'W', 'W'],
  ['W', 'L', 'W', 'W', 'W'],
  ['W', 'W', 'W', 'L', 'W'],
  ['W', 'W', 'L', 'L', 'W'],
  ['L', 'W', 'W', 'L', 'L'],
  ['L', 'L', 'W', 'W', 'W'],
]
"""