#include <stdio.h>
#include <stdlib.h>
#define print_N_and_return \
    printf("\n\n");        \
    return 0;

typedef struct node
{
    int data;
    struct node *next;
} Node;

Node *create_new_node(int data)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = newNode;
    return newNode;
}

void add_node_at_the_begainning(Node *tail, Node *newNode)
{
    newNode->next = tail->next;
    tail->next = newNode;
    // *tail=newNode;l
}

void add_node_at_the_end(Node **tail, Node *newNode)
{
    newNode->next = (*tail)->next;
    (*tail)->next = newNode;
    (*tail) = (*tail)->next;
}

void add_node_at_intermediate_position(Node **tail, Node *newNode)
{
    printf("\nEnter the position of the node after which you want to add the new node: ");
    int position;
    scanf("%d", &position);
    if (position < 1)
    {
        add_node_at_the_begainning(*tail, newNode);
        return;
    }
    Node *ptr = NULL;
    ptr = (*tail)->next;
    while (position > 1)
    {
        ptr = ptr->next;
        if (ptr == *tail)
        {
            add_node_at_the_end(&(*tail), newNode);
            return;
        }
        position--;
    }
    newNode->next = ptr->next;
    ptr->next = newNode;
    if (ptr == *tail)
    {
        (*tail) = (*tail)->next;
    }
}

void delete_first_node(Node **tail)
{
    if (*tail == NULL)
    {
        printf("\nNo element to delete.\n");
        return;
    }
    Node *temp = NULL;
    temp = (*tail)->next;
    if (temp == temp->next)
    {
        free(*tail);
        *tail = NULL;
        printf("\nDeleted the only element in the Circular Linked List successfully.\n");
        return;
    }
    (*tail)->next = (*tail)->next->next;
    free(temp);
    temp = NULL;
    printf("\nDeleted the element in the Circular Linked List successfully.\n");
}

void delete_last_node(Node **tail)
{
    if (*tail == NULL)
    {
        printf("\nNo element to delete.\n");
        return;
    }
    Node *temp = NULL;
    temp = (*tail)->next;
    if (temp == temp->next)
    {
        free(*tail);
        *tail = NULL;
        printf("\nDeleted the element in the Circular Linked List successfully.\n");
        return;
    }
    while (temp->next != *tail)
    {
        temp = temp->next;
    }
    temp->next = (*tail)->next;
    free(*tail);
    *tail = temp;
    temp = NULL;
    printf("\nDeleted the element in the Circular Linked List successfully.\n");
}

void delete_intermediate_node(Node **tail)
{
    if (*tail == NULL)
    {
        printf("\nNo element to delete.\n");
        return;
    }
    printf("\nEnter the position of the element to delete: ");
    int position;
    scanf("%d", &position);

    if (position < 2)
    {
        delete_first_node(&(*tail));
        return;
    }
    Node *buffer1 = NULL, *buffer2 = NULL;
    buffer2 = (*tail)->next;
    if (buffer2 == buffer2->next)
    {
        free(*tail);
        *tail = NULL;
        printf("\nDeleted the only element in the Circular Linked List successfully.\n");
        return;
    }
    while (position > 1)
    {
        buffer1 = buffer2;
        buffer2 = buffer2->next;
        if (buffer2->next == (*tail)->next)
        {
            delete_last_node(&(*tail));
            return;
        }
        position--;
    }
    buffer1->next = buffer2->next;
    free(buffer2);
    printf("\nDeleted the element in the Circular Linked List successfully.\n");
    buffer1 = NULL;
    buffer2 = NULL;
}

void count_number_of_nodes(Node *tail)
{
    if (tail == NULL)
    {
        printf("\nThe Circular Linked List is empty.\n");
        return;
    }
    Node *temp = NULL;
    temp = tail->next;
    int counter = 0;
    do
    {
        if (temp->next == temp)
        {
            counter++;
            break;
        }
        counter++;
        temp = temp->next;
    } while (temp != tail->next);
    printf("\nNumber of elements in the Circular Linked List is %d.\n", counter);
}

void search_for_node_with_data(Node *tail)
{
    printf("\nEnter the number to search in the Circular Linked List: ");
    int number;
    scanf("%d", &number);
    if (tail == NULL)
    {
        printf("\nThe linked list is empty.\n");
        return;
    }
    Node *temp = NULL;
    temp = tail->next;
    int position_of_element = 1;
    do
    {
        if (temp->data == number)
        {
            printf("\nThe number %d was found at position %d.\n",number, position_of_element);
            return;
        }
        else{
            position_of_element++;
            temp=temp->next;
        }
    } while (temp != tail->next);
    printf("\nElement not found.\n");
}

void print_the_list(Node *tail)
{
    if (tail == NULL)
    {
        printf("\nThe linked list is empty.\n");
        return;
    }
    Node *ptr = NULL;
    ptr = tail->next;
    printf("\nThe elements in the Circular Linked List are: ");
    do
    {
        printf("\n(%d, %p)", ptr->data, ptr->next);
        ptr = ptr->next;
    } while (ptr != tail->next);
}

int main()
{
    Node *tail = NULL, *temporary_node = NULL;
    while (1)
    {
        printf("Enter the number to add in the circular linked list(-999 to stop): ");
        int data;
        scanf("%d", &data);
        if (data == -999)
        {
            break;
        }
        if (tail == NULL)
        {
            tail = create_new_node(data);
        }
        else
        {
            temporary_node = create_new_node(data);
            add_node_at_the_end(&tail, temporary_node);
        }
    }
    print_the_list(tail);
    // delete_first_node(tail);
    delete_intermediate_node(&tail);
    print_the_list(tail);
    search_for_node_with_data(tail);
    // printf("\n");
    // return 0;
    print_N_and_return;
}