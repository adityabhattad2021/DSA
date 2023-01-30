#include <stdio.h>
#include <stdlib.h>

typedef struct graph_edge
{
    int destination_vertex_ID;
    int weight;
} Edge;

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

DLLNode *create_new_doubly_node(Edge edge)
{
    DLLNode *newNode = (DLLNode *)malloc(sizeof(DLLNode));
    newNode->edge = edge;
    newNode->next = NULL;
    newNode->previous = NULL;
    return newNode;
}

void addNewDoublyNode(DLL *dll, Edge edge)
{
    DLLNode *newNode = create_new_doubly_node(edge);
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

/*---------------------------------------------------------*/

typedef struct graph_vertex
{
    int vertexId;
    char *vertexName;
    // Pointer to the head of the doubly linked list.
    DLL edgeList;
} Vertex;

typedef struct singly_linked_list_node
{
    Vertex *vertex;
    struct singly_linked_list_node *next;
} SLLNode;

typedef struct graph_graph
{
    // Pointer to head of a singly linked list.
    SLLNode *head;
} Graph;

SLLNode *create_new_singly_node(Vertex *vertex)
{
    SLLNode *newNode = (SLLNode *)malloc(sizeof(SLLNode));
    newNode->vertex = vertex;
    newNode->next = NULL;
    return newNode;
}
void initializeGraph(Graph *graph)
{
    graph->head = NULL;
}

// Here the graph will contain singly linked list which will contain doubly linkes list.
void addNewSinglyNode(Graph *sll, Vertex *vertex)
{
    SLLNode *newNode = create_new_singly_node(vertex);
    if (sll->head == NULL)
    {
        sll->head = newNode;
    }
    else
    {
        if (sll->head->next == NULL)
        {
            sll->head->next = newNode;
        }
        else
        {
            SLLNode *temp = NULL;
            temp = sll->head;
            while (temp->next != NULL)
            {
                temp = temp->next;
            }
            temp->next = newNode;
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

void setVertexId(Vertex *v, int vertex_id)
{
    v->vertexId = vertex_id;
}

void setVertexName(Vertex *v, char vertex_name[])
{
    v->vertexName = malloc(100 * sizeof(char));
    int vertexNameLen = 0;
    while (vertex_name[vertexNameLen] != '\0')
    {
        vertexNameLen++;
    }
    int x;
    for (x = 0; x < vertexNameLen; x++)
    {
        v->vertexName[x] = vertex_name[x];
    }
    v->vertexName[x] = '\0';
}

int getVertexId(Vertex v)
{
    return v.vertexId;
}

char *getVertexName(Vertex v)
{
    return v.vertexName;
}

void addVertexToGraph(Graph *graph){
    int vertex_id;
    char *vertex_name=(char*)malloc(sizeof(char)*100);
    printf("Enter the vertex id: ");
    scanf("%d",&vertex_id);
    printf("Enter vertex name: ");
    scanf("%s",vertex_name);
    Vertex *v=(Vertex *)malloc(sizeof(Vertex));
    setVertexId(v,vertex_id);
    setVertexName(v,vertex_name);
    if(graph->head!=NULL){
        SLLNode *temp=NULL;
        temp=graph->head;
        while(temp){
            if(temp->vertex->vertexId==vertex_id){
                printf("\nVertex already exists.\n");
                return;
            }
            temp=temp->next;
        }
    }
    addNewSinglyNode(graph,v);
}

int main()
{
    Graph graph;
    initializeGraph(&graph);
    int toStop = 0;
    while (!toStop)
    {
        printf("\nChoose from the following operations.");
        printf("\n1. Add a vertex.");
        printf("\n2. Update a vertex.");
        printf("\n3. Delete a vertex.");
        printf("\n4. Add an edge.");
        printf("\n5. Update an edge.");
        printf("\n6. Delete an edge.");
        printf("\n7. Check if two vertices are connected.");
        printf("\n8. Print all the connected vertices of the vertex.");
        printf("\n9. Print entire graph.");
        printf("\n10. Clear Terminal.");
        printf("\n11. Stop");
        printf("\nEnter your choice: ");
        int choice;
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
        {
            addVertexToGraph(&graph);
            break;
        }
        case 2:
        {
            break;
        }
        case 3:
        {
            break;
        }
        case 4:
        {
            break;
        }
        case 5:
        {
            break;
        }
        case 6:
        {
            break;
        }
        case 7:
        {
            break;
        }
        case 8:
        {
            break;
        }
        case 9:
        {
            break;
        }
        case 10:
        {
            system("clear");
            break;
        }
        case 11:
        {
            toStop = 1;
            break;
        }
        }
    }
    return 0;
}