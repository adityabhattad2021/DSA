class Stack:
    def __init__(self):
        self.arr = []

    def push(self,ele):
        self.arr.append(ele)

    def pop(self):
        if self.isEmpty():
            return None
        return self.arr.pop()
    
    def top(self):
        if self.isEmpty():
            return None
        return self.arr[-1]
    
    def isEmpty(self):
        return len(self.arr) == 0
    

class Queue:
    def __init__(self):
        self.arr = []

    def enqueue(self,ele):
        self.arr.append(ele)

    def dequeue(self):
        if self.isEmpty():
            return None
        return self.arr.pop(0)
    
    def top(self):
        if self.isEmpty():
            return None
        return self.arr[0]
    
    def isEmpty(self):
        return len(self.arr)==0







