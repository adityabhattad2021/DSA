"""
    Simulate a genetic algoritm to solve 8 puzzle problem. Consider a pair of initial and final states. where the goal state is at minimul depth 10.

    Intial Population->Calculate Fitness->Select Parents->Crossover->Mutate->Repeat (until goal state is reached)

    initial_state = [1, 2, 3, 4, 0, 5, 6, 7, 8]

    final_state = [1, 2, 3, 4, 5, 8, 6, 0, 7]

""" 
# Functions for 8 Puzzle

def print_board(state):
    print("-------------")
    print(f"| {state[0]} | {state[1]} | {state[2]} |")
    print("-------------")
    print(f"| {state[3]} | {state[4]} | {state[5]} |")
    print("-------------")
    print(f"| {state[6]} | {state[7]} | {state[8]} |")
    print("-------------") 


def find_blank(state):
    return state.index(0)

def swap(state,index1,index2):
    temp=state[index1]
    state[index1]=state[index2]
    state[index2]=temp
    

def move_up(state):
    dup = state.copy()
    blank_index=find_blank(dup)
    if blank_index>2:
        new_blank_position = blank_index-3
        swap(dup,new_blank_position,blank_index)
    return dup

def move_down(state):
    dup = state.copy()
    blank_index=find_blank(dup)
    if blank_index<6:
        new_blank_position = blank_index+3
        swap(dup,new_blank_position,blank_index)
    return dup

def move_left(state):
    dup = state.copy()
    blank_index=find_blank(dup)
    if blank_index%3!=0:
        new_blank_position = blank_index-1
        swap(dup,new_blank_position,blank_index)
    return dup

def move_right(state):
    dup = state.copy()
    blank_index=find_blank(dup)
    if blank_index%3!=2:
        new_blank_position = blank_index+1
        swap(dup,new_blank_position,blank_index)
    return dup

def mhd(state,final_state):
    distance = 0
    for index in range(len(final_state)):
        current_val = state[index]
        if current_val!=0:
            goal_index = final_state.index(current_val)
            current_row = index/3
            current_col=  index%3
            goal_row = goal_index/3
            goal_col = goal_index%3
            distance+=abs(int(current_row-goal_row))+abs(int(current_col-goal_col))
    return distance



# Genetic Algo functions

import random

def calculate_fitness(state,goal_state):
    return mhd(state,goal_state)


def select_best_solutions(fitness_map):
    fitness_map = dict(sorted(fitness_map.items(), key=lambda item: item[1]))
    return list(fitness_map.keys())[:5]
    

def crossover(parents):
    parents = random.sample(parents,2)
    parent1 = parents[0]
    parent2 = parents[1]
    
    crossover_point = random.randint(0, len(parent1))
    child1 = parent1[:crossover_point] + parent2[crossover_point:]
    child2 = parent2[:crossover_point] + parent1[crossover_point:]
    return [child1,child2]

def mutate(children):
    mutated_children = []
    for child in children:
        child = list(child)
        child+=random.choice(['L','R','U','D'])
        mutated_children.append(''.join(child))
    return mutated_children

def run_genetic_algo():
    # generate initial population
    start_state=[1, 2, 3, 5, 4, 7, 6, 8, 0]
    goal_state=[0, 1, 2, 3, 4, 5, 6, 7, 8 ]

    directions = ['L','R','U','D']
    initial_population = []

    for x in directions:
        for y in directions:
            initial_population.append(x+y)

    random.shuffle(initial_population)

    for generation in range(100000):

        initial_states = []
        for move in initial_population:
            temp_state = start_state.copy()
            for turn in move:
                if turn == 'L':
                    temp_state=move_left(temp_state)
                elif turn == 'R':
                    temp_state=move_right(temp_state)
                elif turn == 'U':
                    temp_state=move_up(temp_state)
                elif turn == 'D':
                    temp_state=move_down(temp_state)
            if goal_state == temp_state:
                print("Goal State Reached")
                print("Start State: ")
                print_board(start_state)
                print("Goal State: ")
                print_board(goal_state)
                return
            initial_states.append(temp_state)
        initial_states=list(filter(lambda x:x!=None,initial_states))

        # calculate fitness
        fitness_values=[]
        fitness_values = [calculate_fitness(state,goal_state) for state in initial_states]
        fitness_map = dict(zip(initial_population,fitness_values))

        print("Generation: ",generation)
        print("Initial Population: ",initial_population)


        # select parents
        parents = select_best_solutions(fitness_map)

        # crossover
        children = crossover(parents)


        mutated_children = mutate(children)

        # replace last 2 elements of initial_population with mutated_children
        initial_population[-2:]=mutated_children
        random.shuffle(initial_population)

    

run_genetic_algo()
