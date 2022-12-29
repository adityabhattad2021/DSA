#include"doubly_linked_list.h"

/*
    Why do we need a doubly linked list?
    A doubly linked list is a variation of a singly linked list where each node is also pointing to its previous node.
    1. Deletion operation is faster in doubly linked list, if the pointer to the intermediate node (node to delete) is given.
    2. Inserting a new node before a given node is easier in doubly linked list.
*/


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
            else
            {
                temporary_node = create_new_node(number);
                add_node_at_end(&head, temporary_node);
            }
        }
    }

    print_the_list(head);
    // delete_first_node(&head);
    // delete_last_node(head);
    // delete_intermediate_node(&head);
    reverse_the_list(&head);
    count_number_of_nodes(head);
    print_the_list(head);
    return 0;
}