/*
    What is stack data structure?
    =>Stack is a linear data structure which operates in a LIFO(Last in First Out) or FILO(First in Last Out) pattern.

    - It is named stack as it behaves like a real-world stack, for example - a deck of cards or a plile of plates, etc.
    - Stack is a abstract data type without bounded (predefined) capacity.
    - It is a simple data structure that allows adding and removing elements in perticular order.
    - The order may be LIFO(Last in First Out) or FILO(First in Last Out).

*/

/*
    Standard Stack Operations
    0. Initialize - assign the top index as -1. ✅
    1. Push()  - place an item onto the stack. If there is no place for new item, stack is in overflow state. ✅
    2. Pop() - Return the item at the top of the stack and then remove it, if the pop is called when an stack is empty, it is in underflow state. ✅
    3. isEmpty() - Tells if the stack is empty or not. ✅
    4. isFull() - Tells if the stack is full or not. ✅
    5. Peek() - Access the item at i index. ✅
    6. Count() - Get the number of item in the stack. ✅
    7. Change() - Change the item at the i index. ✅
    8. Display() - Display all the items in the stack. ✅
*/

/*
    Some applications of the stack datastructure.
    1. Balancing of symbols.
    2. Infix to Postfix/Prefix conversion.
    3. Redo-undo feature at many places like editor, photoshop.
    4. Forward and backword feature in web browser.
    5. Used in many algorithms like tower of hanoi, tree traversal, stock spa problem, histogram problem.
    6. Used in Graph algorithm like topological sorting and strongly connected components.
*/

#include "stack.h"
#include <stdlib.h>

int main()
{
    int cont;
    cont = 1;
    stack s;
    initializeStack(&s);
    do
    {
        printf("\nHere are following choices \n");
        printf("1. To push element in the stack. \n");
        printf("2. To pop element out of the stack. \n");
        printf("3. To check if stack is empty.\n");
        printf("4. To check if stack is full. \n");
        printf("5. To peek at index of your choice in the stack\n");
        printf("6. To get the size of the stack \n");
        printf("7. To change element at index of choice in the stack \n");
        printf("8. To display all the elements of the stack\n");
        printf("9. To clear the terminal\n");
        printf("10. To exit\n");
        printf("Enter your choice: ");
        int choice;
        scanf("%d", &choice);
        switch (choice)
        {
            case 10:
            {
                cont = 0;
                break;
            }
            case 9:
            {
                system("clear");
                break;
            }
            case 8:
            {
                display(s);
                break;
            }
            case 7:
            {
                change(&s);
                break;
            }
            case 6:
            {
                count(s);
                break;
            }
            case 5:
            {
                peek(s);
                break;
            }
            case 4:
            {
                isFull(s);
                break;
            }
            case 3:
            {
                isEmpty(s);
                break;
            }
            case 2:
            {
                int ele = pop(&s);

                if (ele != -999)
                {
                    printf("Element poped is %d.", ele);
                }
                break;
            }
            case 1:
            {
                push(&s);
                break;
            }
            default:
            {
                break;
            }
        }

    } while (cont);

    return 0;
}