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
    1. Traversing a list.  ✅
    2. Append a new node(to the end) of the list.  ✅
    3. Prepen a new node(to the start) of the list.  ✅
    4. Inserting a new node to a specific position in the list.  ✅
    5. Deleting a node from the list.  ✅
    6. Updating a node in the list.  ✅


    Some applications of linked lists.
    1. Linked lists can be used to implement stacks and queues.
    2. Linked lists can also be used to implement graphs(Adjancency list representation of the graphs).
    3. Implementing hash tables: each bucket of the hash table can itself be a linked list(open chain hashing).
*/

#include"linked_list.h"




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
                head = create_new_node(element);
            }
            else
            {
                current = create_new_node(element);
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
                head = create_new_node(element);
            }
            else
            {
                current = create_new_node(element);
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
                head = create_new_node(element);
            }
            else
            {
                printf("\nEnter the position where you want to add a number: ");
                scanf("%d", &position);

                current = create_new_node(element);
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
    // delete_entire_list(&head);
    reversing_entire_linked_list(&head);
    print_the_list(head);
    printf("\nThe number of elements in the current list is %d.\n", count_of_nodes(head));

    return 0;
}
