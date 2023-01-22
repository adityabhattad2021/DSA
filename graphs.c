/*
    Graph data structure
    => Graph consists of a finite set of vertices (or nodes) and set of edges (or links) which connects a pair of nodes.
    A graph is a non linear data structure consisting of nodes and edges.


    Difference between trees and graphs:
    Trees:
    1. Only one path or edge from one node.
    2. Has a root node.
    3. Have n-1 edges (N=Number of nodes).
    4. Hierarchical Model.

    Graphs:
    1. Multiple path/edges from one node.
    2. No root node.
    3. Number of edges not defined.
    4. Network Modal.

    (Tree is an undirected graph.)

    Definition:
     - Graph consists of a finite set of vertices (or nodes) and set of edges(or links) which connects the pair of nodes.
     - Graph is a non-linear data structure consisting of nodes and edges.

    Directed and Undirected Graphs:

    Directed Graphs (Digraphs):
    A directed graph is a set of vertices (nodes) connected by edges, with each node having a direction associated with it.
    Edges are usually represented by arrow pointing in the direction the graph can be traversed.

    Undirected Graphs:
    In an undirected graphs the edges are bidirectional, wit no direction associatd with them. Hence, the graph can be traversed in either direction. The absence of an arrow tells us that the graph is undirected.

    Weighted and Unweighted Graphs:

    Weighted Graphs:
    A weighted graph is a graph in which each branch is given a numerical weight.

    Unweighted Graphs:
    An unweighted graph is a group in which all the edges/paths are considered to have same weight.

    Real World use cases of graphs:
    - Social Network
    - Maps (Google Maps) / GPS / Nvigation / Flights
    - World Wide Web

*/

/*
    Adjancency Matrix:
    An adjancency matrix is a way of representing a graph as a matrix of boolean (Os and 1s). A finite graph can be represented in the form of a square matrix, where the boolean value of the matrix indicates if there is a connection between the nodes.
*/

// We will have to use queue and stack to perform Breadth first search and Depth first search on the adjacency matrix of the graph.
#include <stdio.h>
#include <stdlib.h>

typedef struct node
{
    int data;
    struct node *next;
} Node;

Node *create_new_node(int data)
{
    Node *newNode = (Node *)malloc(sizeof(Node));

    newNode->data = data;
    newNode->next = NULL;

    return newNode;
}

void insert_from_beginning(Node **head, int data)
{
    Node *newNode = create_new_node(data);
    if (*head == NULL)
    {
        *head = newNode;
        return;
    }
    Node *temp = NULL;
    temp = *head;
    (*head) = newNode;
    newNode->next = temp;
}

void remove_from_beginning(Node **head)
{
    if (*head == NULL)
    {
        printf("\nThe Linked List is empty.\n");
        exit(1);
    }
    if ((*head)->next == NULL)
    {
        free(*head);
        *head = NULL;
        return;
    }
    Node *temp = NULL;
    temp = *head;
    (*head) = (*head)->next;
    free(temp);
    temp = NULL;
}

void insert_from_end(Node **head, int data)
{
    Node *newNode = create_new_node(data);
    if (*head == NULL)
    {
        *head = newNode;
        return;
    }
    Node *temp = NULL;
    temp = *head;
    while (temp->next != NULL)
    {
        temp = temp->next;
    }
    temp->next = newNode;
}

void remove_from_end(Node **head)
{
    if (*head == NULL)
    {
        printf("\nLinked List is empty.\n");
        exit(1);
    }
    if ((*head)->next == NULL)
    {
        free(*head);
        *head = NULL;
        return;
    }
    Node *temp = NULL, *temp2 = NULL;
    temp = *head;
    while (temp->next != NULL)
    {
        temp2 = temp;
        temp = temp->next;
    }
    temp2->next = NULL;
    free(temp);
    temp = NULL;
}

void list_traversal(Node *head)
{
    if (head == NULL)
    {
        printf("\nLinked List is empty.\n");
        return;
    }
    printf("\nElements of the linked list are: ");
    while (head)
    {
        printf("%d ", head->data);
        head = head->next;
    }
}

typedef struct queue
{
    Node *head;
    Node *tail;
} Queue;

void initializeQueue(Queue *q)
{
    q->head = NULL;
    q->tail = NULL;
}

int isQueueEmpty(Queue q)
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
    if (q->head == NULL && q->tail == NULL)
    {
        q->head = newNode;
        q->tail = newNode;
    }
    else
    {
        if (q->head == q->tail)
        {
            q->tail = newNode;
            q->head->next = q->tail;
            return;
        }
        q->tail->next = newNode;
        q->tail = q->tail->next;
    }
}

int dequeue(Queue *q)
{
    int toReturn;
    if (isQueueEmpty(*q))
    {
        printf("\nQueue is already empty.\n");
        exit(1);
    }
    if (q->head == q->tail)
    {
        toReturn = q->head->data;
        free(q->head);
        q->head = NULL;
        q->tail = NULL;
    }
    else
    {
        Node *temp = NULL;
        temp = q->head;
        q->head = q->head->next;
        toReturn = temp->data;
        free(temp);
        temp = NULL;
    }
    return toReturn;
}

// Breadth first traversal
void BFT(int matrix[][7], int start, int number_of_ele)
{
    Queue q;
    initializeQueue(&q);
    int visited[7] = {0, 0, 0, 0, 0, 0, 0};
    int i;
    printf("%d ", start);
    enqueue(&q, start);
    visited[start] = 1;
    while (!isQueueEmpty(q))
    {
        i = dequeue(&q);
        for (int j = 1; j < number_of_ele; j++)
        {
            if (matrix[i][j] == 1 && visited[j] == 0)
            {
                printf("%d ", j);
                enqueue(&q, j);
                visited[j] = 1;
            }
        }
    }
}

// Depth First Trversal
void DFT(int matrix[][7], int start, int num_of_ele)
{
    static int visited[7] = {0};
    if (visited[start] == 0)
    {
        printf("%d ", start);
        visited[start] = 1;
        for (int j = 1; j < num_of_ele; j++)
        {
            if (matrix[start][j] == 1 && visited[j] == 0)
            {
                DFT(matrix,j,num_of_ele);
            }
        }
    }
}

int main()
{
    int adjencency_matrix[][7] = {{0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 1, 1, 0, 0, 0},
                                  {0, 1, 0, 0, 1, 0, 0},
                                  {0, 1, 0, 0, 1, 0, 0},
                                  {0, 0, 1, 1, 0, 1, 1},
                                  {0, 0, 0, 0, 1, 0, 0},
                                  {0, 0, 0, 0, 1, 0, 0}};

    // Breadth first traversal
    // BFT(adjencency_matrix, 1, 7);

    // Depth First Trversal
    DFT(adjencency_matrix, 2, 7);
}
