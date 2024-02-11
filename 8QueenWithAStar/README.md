# A* Algorithm for 8-Queen Problem

## Introduction
The 8-Queen problem is a classic puzzle where the goal is to place eight queens on an 8x8 chessboard so that no two queens threaten each other. This means that no two queens can share the same row, column, or diagonal. This implementation uses the A* search algorithm to find a solution, utilizing the number of attacking pairs of queens as a heuristic.

## How It Works
- **State Representation**: The state of the chessboard is represented as an array of integers, where each index corresponds to a column of the board, and its value represents the row position of a queen in that column.
- **Heuristic Function**: The heuristic function calculates the total number of pairs of queens that are attacking each other, either directly or diagonally.
- **A\* Algorithm**: Combines the actual cost to reach a given state and the heuristic cost to estimate the cost from the current state to the goal. The algorithm explores possible moves prioritizing those with the lowest estimated total cost.

## Features
- Efficient state representation for fast computations.
- Prioritizes exploration of states closer to the goal based on a heuristic.
- Debug mode for visualizing the search process.

## Debug Mode
The `solve` method accepts a boolean parameter `debug`. When set to `true`, the program prints each state as it's explored, providing insight into how the algorithm searches for a solution.
