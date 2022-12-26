#include <stdio.h>
#include <stdlib.h>

typedef struct Stack
{
    int *_;
    int topIndex;
    float currentSize;
} stack;

void initializeStack(stack *stack)
{
    stack->currentSize = 10;
    stack->_ = (int *)malloc((int)stack->currentSize * sizeof(int));
    stack->topIndex = -1;
}

void push(stack *stack)
{
    int number;
    for (int x = stack->topIndex + 1; x < stack->currentSize; x++)
    {

        printf("Enter a number (-999 to stop): ");
        scanf("%d", &number);
        if (number != -999)
        {
            stack->topIndex++;
            stack->_[stack->topIndex] = number;
        }
        else
        {
            return;
        }
    }
    printf("\nCannot push element anymore, stack is in over flow state.\n");
}

int pop(stack *stack)
{
    if (stack->topIndex >= 0)
    {
        int toReturn = stack->_[stack->topIndex];
        stack->topIndex--;
        return toReturn;
    }
    printf("\nNo element to pop from the stack, stack is in under flow state.\n");
    return -999;
}

void isEmpty(stack stack)
{
    if (stack.topIndex == -1)
    {
        printf("\nStack is empty.\n");
        return;
    }
    printf("\nStack is not empty.\n");
}

void isFull(stack stack)
{
    if (stack.topIndex > stack.currentSize - 2)
    {
        printf("\nStack is Full.\n");
        return;
    }
    printf("\nStack is not full.\n");
}

void increaseSize(stack *stack)
{
    if (stack->topIndex >= 0)
    {
        stack->currentSize *= 1.5;
        stack->_ = (int *)realloc(stack->_, stack->currentSize * sizeof(int));
        printf("\nStack size increased successfully.\n");
    }
}

void peek(stack stack)
{
    if (stack.topIndex >= 0)
    {
        printf("\nElement at the top of the stack is %d\n", stack._[stack.topIndex]);
    }
    printf("\nStack is empty\n");
}

void count(stack stack)
{
    if (stack.topIndex > -1)
    {
        printf("\nSize of the stack is %d.\n", stack.topIndex + 1);
        return;
    }
    else
    {
        printf("\nStack is empty\n");
    }
}

// Not necessary
void change(stack *stack)
{
    int indexToChange, newEle;
    printf("\nEnter the index to view element present at that index: ");
    scanf("%d", &indexToChange);
    if (indexToChange >= 0 && indexToChange <= stack->topIndex)
    {
        printf("\nEnter new element: ");
        scanf("%d", &newEle);
        stack->_[indexToChange] = newEle;
        return;
    }
    else
    {
        printf("\nInvalid index.\n");
    }
}

// Not necessary
void display(stack stack)
{
    if (stack.topIndex == -1)
    {
        printf("\nStack is empty.\n");
        return;
    }
    printf("\nStack: ");
    for (int x = 0; x < stack.currentSize; x++)
    {
        printf("%d ", stack._[x]);
    }
    printf("\n");
}

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
        printf("8. Increase the size of the stack\n");
        printf("9. To display all the elements of the stack\n");
        printf("10. To clear the terminal\n");
        printf("11. To exit\n");
        printf("Enter your choice: ");
        int choice;
        scanf("%d", &choice);
        switch (choice)
        {
        case 11:
        {
            cont = 0;
            break;
        }
        case 10:
        {
            system("clear");
            break;
        }
        case 9:
        {
            display(s);
            break;
        }
        case 8:
        {
            increaseSize(&s);
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
