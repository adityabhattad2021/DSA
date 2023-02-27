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

typedef struct queue
{
    Node *head;
    Node *tail;
} Queue;

Node *create_new_node(int data)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;

    return newNode;
}

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
    if (isQueueEmpty(*q))
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
        }
        else
        {
            q->tail->next = newNode;
            q->tail = newNode;
        }
    }
}

int dequeue(Queue *q)
{
    int toReturn;
    if (isQueueEmpty(*q))
    {
        printf("Queue is already empty.\n");
        exit(1);
    }
    else
    {
        if (q->head == q->tail)
        {
            toReturn = q->head->data;
            free(q->tail);
            q->tail = NULL;
            q->head = NULL;
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
    }
    return toReturn;
}

int peek(Queue q)
{
    return q.head->data;
}

// Breadth first traversal.
int BFT(Queue *q,int matrix[][7],int size,int start)
{
    static int visited[7]={0};
    printf("%d ",start);
    visited[start]=1;
    enqueue(q,start);
    while(!isQueueEmpty(*q)){
        int i=dequeue(q);
        for(int j=1;j<=size;j++){
            if(matrix[i][j]==1 && visited[j]==0){
                printf("%d ", j);
                enqueue(q, j);
                visited[j] = 1;
            }
        }
    }
}

int DFT(Queue *q,int matrix[][7],int size,int start){
    static int visited[7]={0};
    printf("%d ",start);
    visited[start]=7;
    for(int x=1;x<=size;x++){
        if(matrix[start][x]==1 && visited[x]==0){
            DFT(q,matrix,size,x);
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
    Queue q;
    initializeQueue(&q);

    printf("\nBreadth first traversal of the graph is: ");
    BFT(&q,adjencency_matrix,7,1);

    printf("\nDepth first traversal of the graph is: ");
    DFT(&q,adjencency_matrix,7,1);

    return 0;
}