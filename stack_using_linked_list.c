#include <stdio.h>
#include <stdlib.h>

typedef struct node
{
    int data;
    struct node *next;
} Node;

typedef struct stack
{
    Node *top;
} Stack;

Node *create_new_node(int data)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

void initializeStack(Stack *s)
{
    s->top = NULL;
}

int isEmpty(Stack s)
{
    if (s.top == NULL)
    {
        return 1;
    }
    else
    {
        return 0;
    }
}

void push(Stack *s, int data)
{
    Node *temp = create_new_node(data);
    temp->next = s->top;
    s->top = temp;
}

int pop(Stack *s)
{
    if (isEmpty(*s))
    {
        printf("\nStack is already empty.\n");
        exit(1);
    }
    int to_return = s->top->data;
    Node *temp = NULL;
    temp = s->top;
    s->top = temp->next;
    free(temp);
    temp = NULL;
    return to_return;
}

int peek(Stack s)
{
    if (isEmpty(s))
    {
        printf("\nStack is already empty.\n");
        exit(1);
    }
    return s.top->data;
}

void print_stack(Stack s)
{
    Node *temp = NULL;
    temp = s.top;
    printf("\nThe elements of the stack are: ");
    while (temp != NULL)
    {
        printf("%d ", temp->data);
        temp = temp->next;
    }
}

int main()
{
    Stack s;
    initializeStack(&s);
    // push(&s, 1);
    // push(&s, 2);
    // push(&s, 3);
    // push(&s, 4);
    // push(&s, 5);
    printf("\nThe element that was popped is %d.", pop(&s));
    printf("\nThe top element is %d.", peek(s));
    print_stack(s);
    return 0;
}