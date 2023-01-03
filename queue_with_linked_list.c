#include <stdio.h>
#include <stdlib.h>

// Queue implementation using linked list.

typedef struct node
{
    int data;
    struct node *next;
} Node;

typedef struct queue
{
    Node *head;
    Node *tail;
} Queue;

void initializeQueue(Queue *q);
Node *create_new_node(int data);
int isEmpty(Queue q);
void enqueue(Queue *q, int data);
int dequeue(Queue *q);
int count(Queue q);
void print_the_queue(Queue q);

int main()
{
    Queue q;
    initializeQueue(&q);

    enqueue(&q, 10);
    enqueue(&q, 20);
    enqueue(&q, 30);
    enqueue(&q, 40);
    enqueue(&q, 50);
    print_the_queue(q);
    int element = dequeue(&q);
    printf("\nThe element that was dequeued is %d.", element);
    element = dequeue(&q);
    printf("\nThe element that was dequeued is %d.", element);
    enqueue(&q, 60);
    print_the_queue(q);
    printf("\nNumber of elements in the queue is %d.\n", count(q));

    return 0;
}

void initializeQueue(Queue *q)
{
    q->head = NULL;
    q->tail = NULL;
}

Node *create_new_node(int data)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;

    return newNode;
}

int isEmpty(Queue q)
{
    if (q.head == NULL && q.tail == NULL)
    {
        return 1;
    }
    return 0;
}

void enqueue(Queue *q, int data)
{
    Node *newNode = create_new_node(data);
    if (isEmpty(*q))
    {
        q->head = newNode;
        q->tail = newNode;
    }
    else
    {
        if (q->head == q->tail)
        {
            q->head->next = newNode;
            q->tail = newNode;
            return;
        }
        q->tail->next = newNode;
        q->tail = q->tail->next;
    }
}

int dequeue(Queue *q)
{
    int data;
    if (isEmpty(*q))
    {
        printf("\nQueue is already empty.\n");
        exit(1);
    }
    else if (q->head == q->tail)
    {
        q->tail = NULL;
        data = q->head->data;
        free(q->head);
        q->head = NULL;
    }
    else
    {
        Node *temp = NULL;
        temp = q->head;
        q->head = q->head->next;
        data = temp->data;
        free(temp);
        temp = NULL;
    }
    return data;
}

int count(Queue q)
{
    Node *temp = NULL;
    temp = q.head;
    int counter = 0;
    while (temp)
    {
        counter++;
        temp = temp->next;
    }
    return counter;
}

void print_the_queue(Queue q)
{
    Node *temp = NULL;
    temp = q.head;
    if (isEmpty(q))
    {
        printf("\nQueue is empty.\n");
        return;
    }
    printf("\nThe elements of the queue are: ");
    while (temp)
    {
        printf("%d ", temp->data);
        temp = temp->next;
    }
}
