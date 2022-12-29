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

int count_of_nodes(Node *head)
{
    int count = 0;
    if (head == NULL)
    {
        printf("\nThe linked list is empty.\n");
        return 0;
    }
    Node *ptr = NULL;
    ptr = head;
    while (ptr != NULL)
    {
        count++;
        ptr = ptr->next;
    }
    return count;
}

/*Time Complexity: O(n), but can be made O(1) by changing the design of the list i.e. maintaining a pointer to the last element of the list.*/
void add_at_end(Node *head, Node *newNode)
{
    Node *ptr;
    ptr = head;
    while (ptr->next != NULL)
    {
        ptr = ptr->next;
    }
    ptr->next = newNode;
}

/*Time Complexity: O(1).*/
void add_at_begainning(Node **head, Node *newNode)
{
    Node *ptr = NULL;
    ptr = newNode;
    newNode->next = *head;
    *head = ptr;
}

/*Time Complexity: O(n).*/
void add_at_position(Node *head, Node *newNode, int position)
{
    int end = count_of_nodes(head);
    if (position <= 0 || position >= end)
    {
        printf("\nInvalid position entered.\n");
        return;
    }
    Node *ptr = NULL;
    ptr = head;
    for (int x = 1; x < position - 1; x++)
    {
        ptr = ptr->next;
    }
    newNode->next = ptr->next;
    ptr->next = newNode;
}

/*Time Complexity: O(1).*/
void delete_first_element(Node **head)
{
    Node *temp = NULL;
    temp = *head;
    *head = (*head)->next;
    free(temp);
    temp = NULL;
}

/*Time Complexity: O(n).*/
void delete_last_element(Node *head)
{
    Node *temp = NULL, *temp2 = NULL;
    temp = head;
    while (temp->next != NULL)
    {
        temp2 = temp;
        temp = temp->next;
    }
    temp2->next = NULL;
    free(temp);
}

void delete_at_position(Node **head, int position)
{
    Node *nodeBeforeToDelete = NULL, *nodeToDelete = NULL;
    nodeBeforeToDelete = *head;
    nodeToDelete = *head;
    if (position == 1)
    {
        *head = (*head)->next;
    }
    else
    {
        for (int x = 1; x < position; x++)
        {
            nodeBeforeToDelete = nodeToDelete;
            nodeToDelete = nodeToDelete->next;
        }
        nodeBeforeToDelete->next = nodeToDelete->next;
    }
    free(nodeToDelete);
    printf("\n%d\n",nodeBeforeToDelete->data);
}

void delete_entire_list(Node **head){
    Node *buffer=*head;
    while(*head!=NULL){
        *head=(*head)->next;
        free(buffer);
        buffer=*head;
    }
    buffer=NULL;
    printf("\nLinked List deleted successfully!\n");
}


void reversing_entire_linked_list(Node **head){
    Node *firstElement=NULL,*buffer1=NULL,*buffer2=NULL;
    firstElement=(*head);
    buffer1=(*head)->next;
    buffer2=(*head)->next->next;
    firstElement->next=NULL;
    while(buffer1->next!=NULL){
        buffer1->next=*head;
        *head=buffer1;
        buffer1=buffer2;
        buffer2=buffer2->next;
    }
    buffer1->next=*head;
    *head=buffer1;
    // Insignificant.
    buffer1=NULL;
    buffer2=NULL;
    printf("\nLinked List reversed successfully.\n");
}

void print_the_list(Node *head)
{
    if (head == NULL)
    {
        printf("\nThe linked list is empty.\n");
        return;
    }
    Node *ptr = NULL;
    ptr = head;
    printf("\nThe elements in the linked list are: ");
    printf("\n(Head,%p)", &(*head));
    while (ptr != NULL)
    {
        printf("\n(%d,  %p)", ptr->data, (ptr->next));
        ptr = ptr->next;
    }
}

