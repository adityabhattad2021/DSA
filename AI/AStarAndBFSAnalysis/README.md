# A* vs BFS Analysis

## Overview

This Java application solves the sliding tile puzzle using A* search algorithm with Misplaced Tiles and Manhattan Distance heuristics, and Breadth-First Search (BFS). It evaluates and compares the efficiency of these methods by counting the number of states generated to reach the solution.

## Algorithms Used

- **A* Search with Misplaced Tiles Heuristic**: Estimates the cost to reach the goal by counting the number of tiles in the wrong position.
- **A* Search with Manhattan Distance Heuristic**: Estimates the cost to reach the goal by calculating the total distance of the tiles from their goal positions.
- **Breadth-First Search (BFS)**: Explores neighbor nodes level by level until the goal state is found.

## Observations

- **Efficiency**: A* with Manhattan Distance tends to be more efficient, generating fewer states compared to Misplaced Tiles and BFS.
- **Solvability**: The application checks the solvability of the puzzle before attempting to solve it, ensuring that only solvable instances are processed.
- **Performance**: The choice of heuristic significantly affects the performance and efficiency of the A* algorithm.

## Visualization
![output (1)](https://github.com/adityabhattad2021/DSA/assets/93488388/954bc8b8-9a14-42c7-895d-77988037d05b)
