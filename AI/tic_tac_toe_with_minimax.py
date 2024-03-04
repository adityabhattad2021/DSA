from queue import LifoQueue


class Node:
    def __init__(self,board:list,childrens:list|None,score:int|None) -> None:
        self.state = board.copy()
        self.childrens = childrens if childrens is not None else []
        self.parent = None
        self.score:int | None  = score

    def print_board(self):
        for x in range(0,9,3):
            for y in range(3):
                print(self.state[x+y],end=" ")
            print()

    def add_child(self,child):
        self.childrens.append(child)
        child.parent = self


class MinMaxAlgo:
    def __init__(self) -> None:
        self.tree = None

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
               
    def make_move(self,index:int,turn:str):
        if self.board[index]=='-':
            self.board[index]=turn

    def generate_tree(self):
        initial_state = ['X','O','X','X','X','O','O','-','O']
        root_node = Node(board=initial_state,childrens=[],score=None)
        # root_node.print_board()
        root_node.state=self.recursive_traverse(root_node,True)
        print(root_node.state)
        

    def recursive_traverse(self,node:Node,isMax:bool):
        board = node.state.copy()
        node.print_board()
        print('---------------------------')
        if isMax:
            ans = -2
            if '-' not in board:
                return self.return_score(node.state)
            for i in range(len(board)):
                if board[i]=='-':
                    new_board = board.copy()
                    new_board[i]='X'
                    new_node = Node(board=new_board,childrens=[],score=None)
                    node.add_child(new_node)
                    new_node.state = self.recursive_traverse(new_node,isMax=(not isMax))
                    ans = max(new_node.state,ans)
            return ans
        else:
            ans = 2
            if '-' not in board:
                return self.return_score(node.state)
            for i in range(len(board)):
                if board[i]=='-':
                    new_board = board.copy()
                    new_board[i]='O'
                    new_node = Node(board=new_board,childrens=[],score=None)
                    node.add_child(new_node)
                    new_node.state = self.recursive_traverse(new_node,isMax=(not isMax))
                    ans = min(new_node.state,ans)
            return ans
        



min_max_algo = MinMaxAlgo()
min_max_algo.generate_tree()