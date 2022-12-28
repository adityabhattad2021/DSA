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

int main()
{
    Node *head = NULL;
    Node *temporary_node = NULL;
    int number;
    while (1)
    {
        printf("Enter a number to add in a doubly linked list(-999 to stop): ");
        scanf("%d", &number);
        if (number == -999)
        {
            break;
        }
        else
        {
            if (head == NULL)
            {
                head = create_new_node(number);
            }
            else{
                temporary_node=create_new_node(number);
                add_node_at_end(&head,temporary_node);
            }
        }
    }

    print_the_list(head);
    return 0;
}