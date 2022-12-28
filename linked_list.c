/*

    What is linked list?
    => A linked list is a linear data structure, in which the elements are not stored at contigues memory locations. The elements in a linked list are linked using pointers(entity that points to the next element).
    In simple words, a linked list consists of nodes where each node contains a data field and a refrence(link) to the next node in the list.

    Working of linked list
    => Linked list consists of nodes contain a data field and a refrence(link) to the next node in the list.
    1. Node/Link/Element/Object -
        Each node in the linked list consists of two parts:
        a. link to the next node.
        b. data
    2. Next - This points to the next node/element in the linked list(since they are not stored in contiguous memory locations)

    Comparison between linked list and array:
    - Advantages of linked list over arrays.
    1. Dynamic Size.
    2. Ease of insertion/deletion.

    - Disadvantage of linked list over arrays.
    1. Random access is not allowed. we have to access elemnts sequentially starting from first node.
    2. Extra memory space for a pointer is required with each element of the list.
    3. Not cache friendly, Since array elements are contiguous locations, there is locality of refrence which is not there in case of linked lists.



    Types of linked list
    1. Singly Linked List
    2. Doubly Linked List
    3. Circular Linked List


    Operations of Linked List
    1. Traversing a list.
    2. Append a new node(to the end) of the list.
    3. Prepen a new node(to the start) of the list.
    4. Inserting a new node to a specific position in the list.
    5. Deleting a node from the list.
    6. Updating a node in the list.


    Some applications of linked lists.
    1. Linked lists can be used to implement stacks and queues.
    2. Linked lists can also be used to implement graphs(Adjancency list representation of the graphs).
    3. Implementing hash tables: each bucket of the hash table can itself be a linked list(open chain hashing).
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct node
{
    int data;
    struct node *next;
} Node;

Node *createNewNode(int data)
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
        printf("\n(%d,  %p)", ptr->data, &(*ptr->next));
        ptr = ptr->next;
    }
}

int main()
{

    Node *head = NULL;
    Node *current = NULL;
    while (1)
    {
        int element;
        printf("Enter a number to add to the linked list(-999 to quit): ");
        scanf("%d", &element);
        if (element == -999)
        {
            break;
        }
        else
        {
            if (head == NULL)
            {
                head = createNewNode(element);
            }
            else
            {
                current = createNewNode(element);
                add_at_end(head, current);
            }
        }
    }
    print_the_list(head);
    printf("\nThe number of elements in the current list is %d.\n", count_of_nodes(head));
    while (1)
    {
        int element;
        printf("Enter a number to add at the start of the already generated list linked list(-999 to quit): ");
        scanf("%d", &element);
        if (element == -999)
        {
            break;
        }
        else
        {
            if (head == NULL)
            {
                head = createNewNode(element);
            }
            else
            {
                current = createNewNode(element);
                add_at_begainning(&head, current);
            }
        }
    }
    print_the_list(head);
    printf("\nThe number of elements in the current list is %d.\n", count_of_nodes(head));
    while (1)
    {
        int element, position;
        printf("Enter a number to add at certain position of the already generated list linked list(-999 to quit): ");
        scanf("%d", &element);

        if (element == -999)
        {
            break;
        }
        else
        {
            if (head == NULL)
            {
                head = createNewNode(element);
            }
            else
            {
                printf("\nEnter the position where you want to add a number: ");
                scanf("%d", &position);

                current = createNewNode(element);
                add_at_position(head, current, position);
            }
        }
    }
    print_the_list(head);
    printf("\nThe number of elements in the current list is %d.\n", count_of_nodes(head));
    // delete_first_element(&head);
    // delete_first_element(&head);
    // delete_last_element(head);
    // delete_at_position(&head,4);
    print_the_list(head);
    printf("\nThe number of elements in the current list is %d.\n", count_of_nodes(head));

    return 0;
}