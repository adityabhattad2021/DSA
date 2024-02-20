class Position:
    def __init__(self, score, moves=None):
        self.score = score
        self.moves = moves if moves else {}

    def evaluate(self):
        return self.score

    def is_game_over(self):
        return len(self.moves) == 0

    def legal_moves(self):
        return list(self.moves.keys())

    def push(self, move):
        return self.moves[move]


def minimax(position, depth, maxPlayer):
    if depth==0 or position.is_game_over():
        return position.score, None

    if maxPlayer:
        max_eval = float('-inf')
        best_move=None
        for move in position.legal_moves():
            next_position = position.push(move)
            eval,_ = minimax(next_position,depth-1,False) 
            if eval>max_eval:
                max_eval = eval
                best_move = move
        return max_eval,best_move
    
    else:
        min_eval = float('inf')
        best_move = None
        for move in position.legal_moves():
            next_position = position.push(move)
            eval,_ = minimax(next_position,depth-1,True)
            if eval< min_eval:
                min_eval = eval
                best_move = move
        return min_eval,best_move
    


def minimax_with_alpha_beta_pruning(position,depth,alpha,beta,maxPlayer):
    # for the leaf node:
    if depth==0 or position.is_game_over():
        return position.score,None
    
    if maxPlayer:
        max_eval = float('-inf')
        best_move = None
        for move in position.legal_moves():
            next_position = position.push(move)
            eval,_ = minimax_with_alpha_beta_pruning(next_position,depth-1,alpha,beta,False)
            if eval>max_eval:
                max_eval = eval
                best_move = move
            alpha = max(alpha,eval)
            if beta<=alpha:
                break
        return eval,best_move
    
    else:
        min_eval = float('inf')
        best_move = None
        for move in position.legal_moves():
            next_position = position.push(move)
            eval,_  = minimax_with_alpha_beta_pruning(next_position,depth-1,alpha,beta,True)
            if eval < min_eval:
                min_eval = eval
                best_move = move
            beta = min(beta,eval)
            if beta<=alpha:
                break
        return eval,best_move


def main():
    # Expanding the game tree
    # Leaf positions
    leaf_positions = [Position(score) for score in [3, 5, 2, -1, 4, 0, -2, 1, 3, 2, 6, -4, 5, -3, 2]]

    # Mid-level positions, linking to leaf positions
    mid_positions = [
        Position(0, {'m1': leaf_positions[0], 'm2': leaf_positions[1]}),
        Position(0, {'m3': leaf_positions[2], 'm4': leaf_positions[3]}),
        Position(0, {'m5': leaf_positions[4], 'm6': leaf_positions[5]}),
        Position(0, {'m7': leaf_positions[6], 'm8': leaf_positions[7]}),
        Position(0, {'m9': leaf_positions[8], 'm10': leaf_positions[9]}),
        Position(0, {'m11': leaf_positions[10], 'm12': leaf_positions[11]}),
        Position(0, {'m13': leaf_positions[12], 'm14': leaf_positions[13], 'm15': leaf_positions[14]})
    ]

    # Higher-level positions, linking to mid-level positions
    high_positions = [
        Position(0, {'h1': mid_positions[0], 'h2': mid_positions[1]}),
        Position(0, {'h3': mid_positions[2], 'h4': mid_positions[3]}),
        Position(0, {'h5': mid_positions[4], 'h6': mid_positions[5], 'h7': mid_positions[6]})
    ]

    # Starting position, linking to higher-level positions
    start_pos = Position(0, {'start1': high_positions[0], 'start2': high_positions[1], 'start3': high_positions[2]})

    # Run the minimax algorithm
    print(f"Starting position: {start_pos.moves}")
    score, best_move = minimax(start_pos, 3,True)
    print(f"Best score: {score}, Best move: {best_move}")

if __name__ == "__main__":
    main()
