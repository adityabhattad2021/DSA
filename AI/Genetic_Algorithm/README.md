# Eight Puzzle Solver using Genetic Algorithm

This folder contains a Python implementation of a solver for the Eight Puzzle problem using a Genetic Algorithm (GA).

## Overview

### What is the Eight Puzzle Problem?

The Eight Puzzle is a puzzle that consists of a 3x3 grid with 8 numbered tiles and a blank space. The goal is to rearrange the tiles so that they are in order, using as few moves as possible. The puzzle is solved by making sliding moves that use the blank space to move tiles into their correct positions.

### What is a Genetic Algorithm?

A Genetic Algorithm (GA) is a search heuristic that is inspired by Charles Darwin’s theory of natural evolution. This algorithm reflects the process of natural selection where the fittest individuals are selected for reproduction in order to produce the offspring of the next generation. GAs are used to find approximate solutions to optimization and search problems. They work by creating a population of candidate solutions and then iteratively selecting the best individuals from the population, applying crossover to generate new offspring, and mutating some individuals to maintain genetic diversity.

## How the Code Works


## Inputs

- **Goal State**: A list of 9 integers representing the target configuration of the puzzle.
- **Population Size**: The number of individuals in the population.
- **Max Generations**: The maximum number of generations the algorithm will run before terminating.
- **Mutation Rate**: The probability of a mutation occurring in the population.

## Example Usage

```python
if __name__=="__main__":
    goal_state=[1,2,5,3,4,8,6,7,0]
    genetic_algo_8_puzzle(100,1000000,0.9,goal_state)
```

