import random


class ManhattanDistance():
    @staticmethod
    def evaluate(state,goal_state):
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

class MisplacedTiles():
    @staticmethod
    def evaluate(state,goal_state):
        misplaced_tiles = 0
        for index in range(len(goal_state)):
            if state[index]!=goal_state[index]:
                misplaced_tiles+=1
        return misplaced_tiles


class Subject:
    
    def __init__(self,arr,goal_state) -> None:
        self.arr = arr
        self.fitness = MisplacedTiles.evaluate(self.arr,goal_state)
        
    def __lt__(self,other):
        return self.fitness > other.fitness

def generate_random_population(size,goal_state):
    populations=[]
    for _ in range(size):
        arr = [x for x in range(9)]
        random.shuffle(arr)
        subject = Subject(arr.copy(),goal_state)
        populations.append(subject)
    return populations


def select_best_two(population):
    tournament_size = 5
    p1 = max(random.sample(population, tournament_size), key=lambda x: x.fitness)
    p2 = max(random.sample(population, tournament_size), key=lambda x: x.fitness)
    return p1, p2

def crossover(p1,p2):
    random_index=random.randint(0,len(p1.arr)-1)
    offspring = p1.arr[:random_index]+p2.arr[random_index:]
    return offspring


def mutate(new_born,mutation_rate):
    if random.random()<mutation_rate:
        tile1=random.randint(0,len(new_born)-1)
        tile2=random.randint(0,len(new_born)-1)
        new_born[tile1],new_born[tile2]=new_born[tile2],new_born[tile1]

def genetic_algo_8_puzzle(population_size,max_generation,mutation_rate,goal_state):

    population = generate_random_population(population_size,goal_state)

    for generation in range(1,max_generation):
        p1,p2=select_best_two(population)

        newborn = crossover(p1,p2)
        mutate(newborn,mutation_rate)
        new_subject = Subject(newborn,goal_state)

        if new_subject.fitness < min(population).fitness:
            population[population.index(min(population))]=new_subject

        fittest = max(population)  

        print(f"Generation: {generation}, Fittest: {fittest.arr}, fitness: {fittest.fitness}")

        if fittest.fitness==0:
            print("Solution found.")
            return fittest
        
    print("Could not find the solution.")
    return fittest


if __name__=="__main__":
    goal_state=[1,2,5,3,4,8,6,7,0]
    genetic_algo_8_puzzle(100,1000000,0.9,goal_state)
