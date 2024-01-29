# Puzzle Solver

## Overview
This Puzzle Solver is a Java-based solution designed to solve sliding tile puzzle. It uses a breadth-first search algorithm to explore possible moves from an initial state to reach a defined final state.

## Features
- Breadth-first search algorithm for exploring possible moves.
- Ability to define custom start and end states for the puzzle.
- Debug mode for detailed logging of the puzzle-solving process.

## Setup and Execution
To run the Puzzle Solver:

1. Compile the Java files.
2. Run the `Puzzle` class with your desired start and end states.
3. Call the solvePuzzle() method with an optional debug flag.

## How It Works

### Class Descriptions

#### Puzzle Class
- **startState**: The initial state of the puzzle.
- **finalState**: The goal state of the puzzle.
- **Puzzle(Integer[] startState, Integer[] finalState)**: Constructor to initialize the puzzle.
- **solvePuzzle(boolean debug)**: Main method that solves the puzzle and returns the path from the start to the end state.
- **isSolvable(Integer[] initialState, Integer[] finalState)**: Checks if the puzzle is solvable.

#### Node Class
- **state**: Current state of the puzzle.
- **parent**: Reference to the parent node.
- **action**: The action taken to reach the current state.
- **level**: The depth level of the node in the search tree.
- **Node(Integer[] state, Node parent, String action, int level)**: Constructor for the Node class.
- **getMatrix(Integer[] state)**: Static method to format the puzzle state as a 3x3 matrix.
- **toString()**: Overridden method to return a string representation of the node.

### Algorithm
The solution utilizes a breadth-first search approach. It starts from the initial state, explores all possible moves, and gradually expands the search tree until it finds the solution or exhausts all possibilities.

## Limitations
- The performance might decrease for puzzles with a large number of states due to the breadth-first search approach.

## Future Enhancements
- Optimize the algorithm for larger and more complex puzzles.

## Author
- Aditya Bhattad

