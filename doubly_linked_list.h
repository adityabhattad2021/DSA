#include <stdio.h>
#include <stdlib.h>




typedef struct node
{
    struct node *previous;
    int data;
    struct node *next;
} Node;

Node *create_new_node(int data)
{
    Node *new_node = (Node *)malloc(sizeof(Node));
    new_node->previous = NULL;
    new_node->data = data;
    new_node->next = NULL;
    return new_node;
}

void add_node_at_begainning(Node **head, Node *newNode)
{
    (*head)->previous = newNode;
    newNode->next = *head;
    *head = newNode;
}

void add_node_at_end(Node **head, Node *newNode)
{
    Node *ptr = NULL;
    if (*head == NULL)
    {
        *head = newNode;
    }
    ptr = *head;
    while (ptr->next != NULL)
    {
        ptr = ptr->next;
    }
    ptr->next = newNode;
    newNode->previous = ptr;
}

void add_after_position(Node **head, Node *newNode)
{
    if (*head == NULL)
    {
        printf("\nLinked List is empty.\n");
        return;
    }
    printf("\nEnter the position to add the new node: ");
    int position;
    scanf("%d", &position);
    if (position <= 0)
    {
        printf("\nPosition less than the minimum position, Inserting at the begainning of the list.\n");
        newNode->next = *head;
        (*head)->previous = newNode;
        *head = newNode;
    }
    else
    {
        Node *buffer = NULL, *buffer2 = NULL;
        buffer = *head;
        while (position != 1 && buffer->next != NULL)
        {
            buffer = buffer->next;
            position--;
        }
        // Pointer to next element of the element at position at which node is to be added.
        buffer2 = buffer->next;

        buffer->next = newNode;
        newNode->next = buffer2;
        newNode->previous = buffer;
        buffer2->previous = newNode;
    }
}

void delete_first_node(Node **head)
{
    (*head) = (*head)->next;
    free((*head)->previous);
    (*head)->previous = NULL;
}

void delete_last_node(Node *head)
{
    Node *buffer = NULL;
    buffer = head;
    while (buffer->next->next != NULL)
    {
        buffer = buffer->next;
    }
    free(buffer->next);
    buffer->next = NULL;
}

void delete_intermediate_node(Node **head)
{
    int position;
    printf("\nEnter the position of the node to delete: ");
    scanf("%d", &position);
    if (position < 2)
    {
        delete_first_node(&(*head));
        return;
    }
    Node *buffer1 = NULL, *buffer2 = NULL;
    buffer1 = *head;
    while (position > 1)
    {
        if (buffer1->next == NULL)
        {
            delete_last_node(*head);
            return;
        }
        else
        {
            buffer1 = buffer1->next;
            position--;
        }
    }
    if (buffer1->next != NULL)
    {
        buffer2 = buffer1->previous;
        buffer2->next = buffer1->next;
        buffer1->next->previous = buffer2;
        free(buffer1);
        buffer1 = NULL;
    }
    else
    {
        delete_last_node(*head);
    }
}

void reverse_the_list(Node **head)
{
    Node *buffer1 = NULL, *buffer2 = NULL;
    buffer1 = *head;
    buffer2 = buffer1->next;
    buffer1->previous = buffer2;
    buffer1->next = NULL;
    while (buffer2->next != NULL)
    {
        buffer1 = buffer2;
        buffer2 = buffer2->next;
        buffer1->next = buffer1->previous;
        buffer1->previous = buffer2;
    }
    buffer2->next = buffer1;
    buffer2->previous = NULL;
    (*head) = buffer2;
    // Insignificant.
    buffer1 = NULL;
    buffer2 = NULL;
    printf("\nDoubly Linked List reversed successfully.\n");
}

void print_the_list(Node *head)
{
    if (head == NULL)
    {
        printf("\nThe doubly linked list is empty.\n");
        return;
    }
    Node *ptr = NULL;
    ptr = head;
    printf("\nThe elements in the linked list are: ");
    printf("\n(Head,%p)", &(*head));
    while (ptr != NULL)
    {
        printf("\n(%p ,%d, %p)", ptr->previous, ptr->data, ptr->next);
        ptr = ptr->next;
    }
}

void count_number_of_nodes(Node *head){
    if(head==NULL){
        printf("\nThe doubly linked list is empty.\n");
        return;
    }
    int counter=0;
    Node *ptr=NULL;
    ptr=head;
    while(ptr!=NULL){
        counter++;
        ptr=ptr->next;
    }
    printf("\nNumber of elements in doubly linked list are: %d.\n",counter);
}

