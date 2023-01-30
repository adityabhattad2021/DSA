#include <stdio.h>

typedef struct graph_edge
{
    int destination_vertex_ID;
    int weight;
} Edge;

typedef struct graph_vertex
{
    int vertexId;
    char vertexName[100];
    // Pointer to the head of the doubly linked list.
} Vertex;

/*---------------------------------------------------------*/

/// @brief Doubly Linked List for graph implementation.

typedef struct doubly_linked_list_node
{
    Edge edge;
    struct doubly_linked_list_node *next;
    struct doubly_linked_list_node *previous;
} DLLNode;

typedef struct doubly_linked_list
{
    DLLNode *head;
} DLL;

void initializeDLL(DLL *dll)
{
    dll->head = NULL;
}

DLLNode *create_new_node(Edge edge)
{
    DLLNode *newNode = (DLLNode *)malloc(sizeof(DLLNode));
    newNode->edge = edge;
    newNode->next = NULL;
    newNode->previous = NULL;
    return newNode;
}

void addNewNode(DLL *dll, Edge edge)
{
    DLLNode *newNode = create_new_node(edge);
    if (dll->head == NULL)
    {
        dll->head = newNode;
    }
    else
    {
        if (dll->head->next == NULL)
        {
            newNode->previous = dll->head;
            dll->head->next = newNode;
        }
        else
        {
            DLLNode *temp = NULL;
            temp = dll->head;
            while (temp->next != NULL)
            {
                temp = temp->next;
            }
            temp->next = newNode;
            newNode->previous = temp;
        }
    }
}

/*---------------------------------------------------------*/

//

void setEdge(Edge *e, int destintion_vertex_id, int weight)
{
    e->destination_vertex_ID = destintion_vertex_id;
    e->weight = weight;
}

void setEdgeWeight(Edge *e, int weight)
{
    e->weight = weight;
}

int getEdgeDestinationVertexID(Edge e)
{
    return e.destination_vertex_ID;
}

int getEdgeWeight(Edge e)
{
    return e.weight;
}

int main()
{

    return 0;
}