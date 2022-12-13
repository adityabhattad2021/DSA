#include <stdio.h>
#define SIZE 10

typedef struct Stack
{
    int _[SIZE];
    int topIndex;
} stack;

void initializeStack(stack *stack)
{
    stack->topIndex = -1;
}

void push(stack *stack)
{
    int number;
    for (int x = stack->topIndex + 1; x < SIZE; x++)
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
    if (stack.topIndex > SIZE - 2)
    {
        printf("\nStack is full.\n");
        return;
    }
    printf("\nStack is not full.\n");
}

void peek(stack stack)
{
    if (stack.topIndex >= 0)
    {
        printf("\nElement at the top of the stack is %d\n",stack._[stack.topIndex]);
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
    for (int x = 0; x < SIZE; x++)
    {
        printf("%d ", stack._[x]);
    }
    printf("\n");
}