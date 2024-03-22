# Eight Puzzle Solver using Genetic Algorithm

This folder contains a Python implementation of a solver for the Eight Puzzle problem using a Genetic Algorithm (GA).

## Overview

### What is the Eight Puzzle Problem?

The Eight Puzzle is a puzzle that consists of a 3x3 grid with 8 numbered tiles and a blank space. The goal is to rearrange the tiles so that they are in order, using as few moves as possible. The puzzle is solved by making sliding moves that use the blank space to move tiles into their correct positions.

### What is a Genetic Algorithm?

A Genetic Algorithm (GA) is a search heuristic that is inspired by Charles Darwin’s theory of natural evolution. This algorithm reflects the process of natural selection where the fittest individuals are selected for reproduction in order to produce the offspring of the next generation. GAs are used to find approximate solutions to optimization and search problems. They work by creating a population of candidate solutions and then iteratively selecting the best individuals from the population, applying crossover to generate new offspring, and mutating some individuals to maintain genetic diversity.

## How the Code Works

### EightPuzzle Class

The `EightPuzzle` class is responsible for the puzzle's logic. It includes methods for:
- Printing the current state of the puzzle.
- Finding the position of the blank tile.
- Swapping two tiles.
- Moving the blank tile up, down, left, or right.
- Calculating the fitness of a given state using either the Manhattan distance or Misplaced Tiles heuristic.

    **To implement the Manhattan distance and Misplaced Tiles heuristics as interchangeable fitness evaluation strategies,  Strategy design pattern has been used.** 

### GeneticAlgorithm Class

The `GeneticAlgorithm` class implements the GA framework. It includes methods for:
- Generating an initial population of random moves.
- Evaluating the fitness of each state in the population.
- Selecting the best solutions based on their fitness.
- Performing crossover and mutation operations to generate new populations.
- Running the GA until a solution is found or a maximum number of generations is reached.

### How the Code Works

1. **Initialization**: The `EightPuzzle` class is initialized with a start state and a goal state. The `GeneticAlgorithm` class is then initialized with an instance of the `EightPuzzle` class.

2. **Running the Solver**: The `run` method of the `GeneticAlgorithm` class is called to start the GA. It generates an initial population of random moves and enters a loop where it:
   - Evaluates the fitness of each state in the population.
   - Selects the best solutions.
   - Performs crossover and mutation operations to generate new populations.
   - Checks if the goal state has been reached.

3. **Termination**: The algorithm terminates when the goal state is reached or after a maximum number of generations.

## Inputs

- **Start State**: A list of 9 integers representing the initial configuration of the puzzle. The blank space is represented by 0.
- **Goal State**: A list of 9 integers representing the target configuration of the puzzle.
- **Population Size**: The number of individuals in the population.
- **Max Generations**: The maximum number of generations the algorithm will run before terminating.
- **Mutation Rate**: The probability of a mutation occurring in the population.

## Example Usage

```python
if __name__ == "__main__":
    start_state = [0,1,2,3,4,5,6,7,8]
    goal_state = [1,2,5,3,4,8,6,7,0]
    eight_puzzle = EightPuzzle(start_state,goal_state)
    genetic_algo = GeneticAlgorithm(eight_puzzle)
    genetic_algo.run()
```

This example initializes the puzzle with a start state and a goal state, creates an instance of the `EightPuzzle` class, and then runs the GA to find a solution.