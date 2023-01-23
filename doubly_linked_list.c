#include "doubly_linked_list.h"

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
    int toStop = 0;
    while (!toStop)
    {
        printf("\n\nChoose among the following:");
        printf("\n1. To add a node at start.");
        printf("\n2. To add a node at end.");
        printf("\n3. To add a node at intermediate position.");
        printf("\n4. To delete a node from start.");
        printf("\n5. To delete a node from end.");
        printf("\n6. To delete a node from intermediate position.");
        printf("\n7. To print the doubly linked list.");
        printf("\n8. To print the doubly linked list in reverse order.");
        printf("\n9. To sort the list.");
        printf("\n10. To clear screen.");
        printf("\n11. To stop.");
        int choice;
        printf("\nEnter your choice: ");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
        {
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
                        add_node_at_begainning(&head, temporary_node);
                    }
                }
            }
            break;
        }
        case 2:
        {
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
            break;
        }
        case 3:
        {
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
                        add_after_position(&head, temporary_node);
                    }
                }
            }
            break;
        }
        case 4:
        {
            delete_first_node(&head);
            break;
        }
        case 5:
        {
            delete_last_node(head);
            break;
        }
        case 6:
        {
            delete_intermediate_node(&head);
            break;
        }
        case 7:
        {
            print_the_list(head);
            break;
        }
        case 8:
        {
            print_the_list_in_reverse(head);
            break;
        }
        case 9:
        {
            sort_the_list(head);
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

    // print_the_list(head);
    // // delete_first_node(&head);
    // // delete_last_node(head);
    // // delete_intermediate_node(&head);
    // reverse_the_list(&head);
    // count_number_of_nodes(head);
    // print_the_list(head);
    return 0;
}