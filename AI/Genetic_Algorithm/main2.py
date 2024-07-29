import random
from abc import abstractmethod

# for reproducibility
# random.seed(1000)

class DistanceCalculator:
    def evaluate(self,state,goal_state):
        raise NotImplementedError("Subclass must implement abstract method")
    

class ManhattanDistance(DistanceCalculator):
    def evaluate(self,state,goal_state):
        distance = 0
        for index in range(len(goal_state)):
            current_val = state[index]
            if current_val!=0:
                goal_index = goal_state.index(current_val)
                current_row = index/3
                current_col=  index%3
                goal_row = goal_index/3
                goal_col = goal_index%3
                distance+=abs(int(current_row-goal_row))+abs(int(current_col-goal_col))
        return distance

class MisplacedTiles(DistanceCalculator):
    def evaluate(self,state,goal_state):
        misplaced_tiles = 0
        for index in range(len(goal_state)):
            if state[index]!=goal_state[index]:
                misplaced_tiles+=1
        return misplaced_tiles


class EightPuzzle:

    def __init__(self,start_state,goal_state,distance_calculator:DistanceCalculator)->None:
        self.start_state = start_state
        self.goal_state = goal_state
        self.distance_calculator = distance_calculator

    def print_board(self,state):
        print("-------------")
        print(f"| {state[0]} | {state[1]} | {state[2]} |")
        print("-------------")
        print(f"| {state[3]} | {state[4]} | {state[5]} |")
        print("-------------")
        print(f"| {state[6]} | {state[7]} | {state[8]} |")
        print("-------------") 


    def find_blank(self,state):
        return state.index(0)

    def swap(self,state,index1,index2):
        temp=state[index1]
        state[index1]=state[index2]
        state[index2]=temp
        

    def move_up(self,state):
        dup = state.copy()
        blank_index=self.find_blank(dup)
        if blank_index>2:
            self.swap(dup,blank_index-3,blank_index)
        return dup

    def move_down(self,state):
        dup = state.copy()
        blank_index=self.find_blank(dup)
        if blank_index<6:
            self.swap(dup,blank_index+3,blank_index)
        return dup

    def move_left(self,state):
        dup = state.copy()
        blank_index=self.find_blank(dup)
        if blank_index%3!=0:
            self.swap(dup,blank_index-1,blank_index)
        return dup

    def move_right(self,state):
        dup = state.copy()
        blank_index=self.find_blank(dup)
        if blank_index%3!=2:
            self.swap(dup, blank_index+1,blank_index)
        return dup

    def evaluate(self,state,final_state):
        return self.distance_calculator.evaluate(state,final_state)
    



class GeneticAlgorithm:

    def __init__(self,eight_puzzle:EightPuzzle)->None:
        self.eight_puzzle = eight_puzzle


    def get_states(self,population,start_state):
        states = []
        for move in population:
            temp_state = start_state.copy()
            for turn in move:
                if turn == 'L':
                    temp_state=self.eight_puzzle.move_left(temp_state)
                elif turn == 'R':
                    temp_state=self.eight_puzzle.move_right(temp_state)
                elif turn == 'U':
                    temp_state=self.eight_puzzle.move_up(temp_state)
                elif turn == 'D':
                    temp_state=self.eight_puzzle.move_down(temp_state)
            
            states.append(temp_state)
        states=list(filter(lambda x:x!=None,states))
        return states

    def calculate_fitness(self,state,goal_state):
        return self.eight_puzzle.evaluate(state,goal_state)

    def get_inversion_count(self,state,goal_state):
        inversion_count = 0
        for i in range(len(state)-1):
            for j in range(i+1,len(state)):
                if goal_state.index(state[i])>goal_state.index(state[j]):
                    inversion_count+=1
        return inversion_count
    
    def is_solvable(self,state,goal_state):

        # there should be no repeating elements in the state
        if len(set(state))!=len(state):
            return False

        inversion_count = self.get_inversion_count(state,goal_state)
        grid_width = int(len(state)**0.5)
        if grid_width%2==1:
            return inversion_count%2==0
        else:
            blank_index = state.index(0)
            blank_row_from_bottom = grid_width - (blank_index/grid_width)
            return (inversion_count+blank_row_from_bottom)%2!=0

    def select_best_solutions(self, fitness_map):
        sorted_fitness_map = dict(sorted(fitness_map.items(), key=lambda item: item[1]))
        filtered_fitness_map = {}
        for key in sorted_fitness_map.keys():
            if self.is_solvable(key, self.eight_puzzle.goal_state):
                filtered_fitness_map[key] = sorted_fitness_map[key]
        # if len of fitness map is less then 5 add new elements to the fitness map
        while len(filtered_fitness_map)<5:
            new_population = self.generate_population(5-len(filtered_fitness_map))
            new_states = self.get_states(new_population,self.eight_puzzle.start_state)
            for state in new_states:
                if self.is_solvable(state,self.eight_puzzle.goal_state):
                    filtered_fitness_map[tuple(state)] = self.calculate_fitness(state,self.eight_puzzle.goal_state)
        return list(filtered_fitness_map.keys())[:5]
        
    def crossover(self,parents):
        parents = random.sample(parents,2)
        parent1 = parents[0]
        parent2 = parents[1]
        # smaller = min(len(parent1),len(parent2))
        crossover_point = random.randint(0, len(parent1))
        child1 = parent1[:crossover_point] + parent2[crossover_point:]
        child2 = parent2[:crossover_point] + parent1[crossover_point:]
        return [child1,child2]

    def mutate(self,children,mutation_rate=0.9):
        mutated_children = []
        for child in children:
            # swap two elements of the state
            if random.random() < mutation_rate:
                index1 = random.randint(0,len(child)-1)
                index2 = random.randint(0,len(child)-1)
                temp = list(child)
                temp[index1],temp[index2] = temp[index2],temp[index1]
                mutated_children.append(temp)
            else:
                mutated_children.append(child)
        mutated_children = [list(child) for child in mutated_children]
        return mutated_children

    def generate_population(self,size_of_population):
        directions = ['L','R','U','D']
        population = []
        while len(population)<size_of_population:
            choices = random.choices(directions,k=random.randint(1,len(directions)))
            random.shuffle(choices)
            population.append(''.join(choices))

        random.shuffle(population)
        return population

    def run(self,max_generations=1000,population_size=100):
        # generate initial population
        population = self.generate_population(population_size)
        states = self.get_states(population,self.eight_puzzle.start_state)

        for generation in range(max_generations):

            print("Generation: ",generation)
            print("Population: ",states)

            # check if goal state is reached
            if self.eight_puzzle.goal_state in states:
                print("Goal State Reached")
                print("Start State: ")
                self.eight_puzzle.print_board(self.eight_puzzle.start_state)
                print("Goal State: ")
                self.eight_puzzle.print_board(self.eight_puzzle.goal_state)
                break

            # calculate fitness
            fitness_values = [self.calculate_fitness(state,self.eight_puzzle.goal_state) for state in states]
            fitness_map = dict(zip(tuple(tuple(state) for state in states),fitness_values))

            # select parents
            parents = self.select_best_solutions(fitness_map)

            # crossover
            children = self.crossover(parents)

            # mutate
            mutated_children = self.mutate(children)

            # replace last 2 elements of population with mutated_children
            states[-2:]=mutated_children

            # if population size is less than expected, generate more children
            if len(states)<population_size:
                # while we do not get required solvable new states keep generating new children
                while True:
                    new_population = self.generate_population(population_size-len(states))
                    new_states = self.get_states(new_population,self.eight_puzzle.start_state)
                    for state in new_states:
                        if self.is_solvable(state,self.eight_puzzle.goal_state):
                            states.append(state)
                    if len(states)==population_size:
                        break

            random.shuffle(states)

    
if __name__ == "__main__":
    start_state = [0,1,2,3,4,5,6,7,8]
    goal_state = [1,2,5,3,4,8,6,7,0]
    eight_puzzle = EightPuzzle(start_state,goal_state,ManhattanDistance())
    genetic_algo = GeneticAlgorithm(eight_puzzle)
    genetic_algo.run()