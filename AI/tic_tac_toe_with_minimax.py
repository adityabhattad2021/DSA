import unittest

class Node:
    def __init__(self,board:list,childrens:list|None,score:int|None) -> None:
        self.state = board.copy()
        self.childrens = childrens if childrens is not None else []
        self.parent = None
        self.score:int | None  = score
        self.selected_child = None

    def print_board(self):
        for x in range(0,9,3):
            for y in range(3):
                print(self.state[x+y],end=" ")
            print()

    def add_child(self,child):
        self.childrens.append(child)
        child.parent = self


class MinMaxAlgo:

    def return_score(self,board):
        # check horizontal
        for x in range(0,9,3):
            if board[x+0]=='X' and board[x+1]=='X' and board[x+2]=='X':
                return 1
            if board[x+0]=='O' and board[x+1]=='O' and board[x+2]=='O':
                return -1

        # check vertical
        for x in range(3):
            if board[x+0]=='X' and board[x+3]=='X' and board[x+6]=='X':
                return 1
            if board[x+0]=='O' and board[x+3]=='O' and board[x+6]=='O':
                return -1
        
        # check diagonal
        if board[0]=='X' and board[4]=='X' and board[8]=='X':
            return 1
        
        if board[2]=='X' and board[4]=='X' and board[6]=='X':
            return 1
        
        if board[0]=='O' and board[4]=='O' and board[8]=='O':
            return -1
        
        if board[2]=='O' and board[4]=='O' and board[6]=='O':
            return -1
        
        return 0
               

    def generate_tree(self,initial_board):
        _initial_board = initial_board.copy()
        root_node = Node(board=_initial_board,childrens=[],score=None)
        root_node.score=self.recursive_traverse(root_node,True)
        return root_node
        

    def recursive_traverse(self,node:Node,isMax:bool):
        board = node.state.copy()
        if self.return_score(node.state)!=0:
            return self.return_score(node.state)
        if isMax:
            ans = -2
            if '-' not in board:
                node.selected_child = Node(board=board,childrens=[],score=None)
                return self.return_score(node.state)
            for i in range(len(board)):
                if board[i]=='-':
                    new_board = board.copy()
                    new_board[i]='X'
                    new_node = Node(board=new_board,childrens=[],score=None)
                    node.add_child(new_node)
                    new_node.score = self.recursive_traverse(new_node,isMax=(not isMax))
                    # ans = max(new_node.score,ans)
                    if new_node.score>ans:
                        node.selected_child=new_node
                        ans = new_node.score
            return ans
        else:
            ans = 2
            if '-' not in board:
                node.selected_child = Node(board=board,childrens=[],score=None)
                return self.return_score(node.state)
            for i in range(len(board)):
                if board[i]=='-':
                    new_board = board.copy()
                    new_board[i]='O'
                    new_node = Node(board=new_board,childrens=[],score=None)
                    node.add_child(new_node)
                    new_node.score = self.recursive_traverse(new_node,isMax=(not isMax))
                    # ans = min(new_node.score,ans)
                    if new_node.score<ans:
                        node.selected_child=new_node
                        ans = new_node.score
            return ans
        
class TicTacToe:

    def __init__(self) -> None:
        self.board=['-']*9
        self.helper = MinMaxAlgo()

    def print_board(self):
        for x in range(0,9,3):
            for y in range(3):
                print(self.board[x+y],end=" ")
            print()


    def start_game(self):
        while True:
            if self.helper.return_score(self.board)!=0 or '-' not in self.board:
                score = self.helper.return_score(self.board)
                if score == 1:
                    print("Computer wins!")
                elif score==-1:
                    print("You win!")
                elif score==0:
                    print("Its a draw!") 
                self.print_board()
                try_again = input("Want to try again? ")
                if try_again.lower.startswith('y'):
                    self.board = ['-']*9
                    continue
                break
            print("Current game: ")
            self.print_board()
            user_input = int(input("Make a move: "))
            if self.board[user_input]=='-':
                self.board[user_input]='O'
            comp_move = self.helper.generate_tree(self.board)
            self.board = comp_move.selected_child.state.copy()


class TestMinMax(unittest.TestCase):

    def setUp(self):
        self.min_max_algo = MinMaxAlgo()

    def test_node_initialization(self):
        board = ['-', '-', '-', '-', 'X', '-', '-', '-', '-']
        node = Node(board=board, childrens=[], score=None)
        self.assertEqual(node.state, board)
        self.assertEqual(node.childrens, [])
        self.assertEqual(node.parent, None)
        self.assertEqual(node.score, None)

    def test_add_child(self):
        parent_node = Node(board=['-'] * 9, childrens=[], score=None)
        child_node = Node(board=['-'] * 9, childrens=[], score=None)
        parent_node.add_child(child_node)
        self.assertEqual(parent_node.childrens, [child_node])
        self.assertEqual(child_node.parent, parent_node)

    def test_return_score_x_win(self):
        board = ['X', 'X', 'X', '-', '-', '-', '-', '-', '-']
        self.assertEqual(self.min_max_algo.return_score(board), 1)

    def test_return_score_o_win(self):
        board = ['-', '-', '-', 'O', 'O', 'O', '-', '-', '-']
        self.assertEqual(self.min_max_algo.return_score(board), -1)

    def test_return_score_draw(self):
        board = ['O', 'X', 'O', 'X', 'O', 'X', 'X', 'O', 'X']
        self.assertEqual(self.min_max_algo.return_score(board), 0)

    def test_generate_tree(self):
        initial_board = ['-'] * 9
        root_node = self.min_max_algo.generate_tree(initial_board)
        self.assertIsNotNone(root_node)
        self.assertEqual(root_node.state, initial_board)
        self.assertEqual(root_node.score, 0) # Assuming a draw for an empty board

    def test_recursive_traverse_x_win(self):
        board = ['X', 'O', 'X', '-', '-', '-', '-', '-', '-']
        node = Node(board=board, childrens=[], score=None)
        score = self.min_max_algo.recursive_traverse(node, isMax=True)
        self.assertEqual(score, 1)

    def test_recursive_traverse_o_win(self):
        board = ['O', 'X', 'O', '-', '-', '-', '-', '-', '-']
        node = Node(board=board, childrens=[], score=None)
        score = self.min_max_algo.recursive_traverse(node, isMax=False)
        self.assertEqual(score, -1)


if __name__ == '__main__':
    game = TicTacToe()
    game.start_game()