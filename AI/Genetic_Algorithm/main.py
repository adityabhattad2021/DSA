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


def move_up(state):
    blank_index=find_blank(state)
    if blank_index>2:
        new_blank_position = blank_index-3
        temp = state[new_blank_position]
        state[new_blank_position]=state[blank_index]
        state[blank_index]=temp

def move_down(state):
    blank_index=find_blank(state)
    if blank_index<6:
        new_blank_position = blank_index+3
        temp = state[new_blank_position]
        state[new_blank_position]=state[blank_index]
        state[blank_index]=temp


def move_left(state):
    blank_index=find_blank(state)
    if blank_index%3!=0:
        new_blank_postion = blank_index-1
        temp = state[new_blank_postion]
        state[new_blank_postion]=state[blank_index]
        state[blank_index]=temp


def move_right(state):
    blank_index=find_blank(state)
    if blank_index%3!=2:
        new_blank_position = blank_index+2
        temp = state[new_blank_position]
        state[new_blank_position]=state[blank_index]
        state[blank_index]=temp


def mhd(state,final_state):
    distance = 0
    for index in range(len(final_state)):
        current_val = state[index]
        if current_val!=0:
            goal_index = state.index(current_val)
            current_row = index/3
            current_col=  index%3
            goal_row = goal_index/3
            goal_col = goal_index%3
            distance+=int(current_row-goal_row)+int(current_col-goal_col)
    return distance



# Genetic Algo functions

def calculate_fitness(state):
    return mhd(state)


def select_best_solutions(state):
    pass

def crossover(state):
    pass

def mutate(state):
    pass

def run_genetic_algo():
    pass


