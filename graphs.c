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

int main()
{
    Node *head;
    head = NULL;
    insert_from_beginning(&head, 21);
    insert_from_beginning(&head, 32);
    insert_from_beginning(&head, 43);
    insert_from_beginning(&head, 25);
    remove_from_beginning(&head);
    insert_from_end(&head, 48);
    insert_from_end(&head, 85);
    insert_from_end(&head, 64);
    remove_from_end(&head);
    list_traversal(head);
}