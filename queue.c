/*
    What is queue?
    => Queue is Linear data sturucture which operates in FIFO(FIrst in First Out) or  LILO(Last in Last Out).
    - It is named queue as it behaves like a real-world queue, for example - queue(line) of cars in a simgle lane, queue of people waiting at a food counter.
    - Queue is an abstract data type with a bounded(predefined) capacity.
    - It is a simple data structure that allows adding and removing elements in a perticular order.
*/

/*
    Standard Queue Operations
    - Enqueue() - Elements are added from one end (rear/back) ✅
    - Dequeue() - Elements are removed from one end (head/front) ✅
    - isFull() - Checks if the queue is full. ✅
    - isEmpty() - Checks if the queue is empty. ✅
    - Count() - Gets count of total items in queue. ✅
*/

/*
    Some application of queue data sturctures.
    - Queue is used when things but have to be processed in First in First Out order.
    - CPU sheduling, Disk sheduling.
    - Handling of interrupts in realtime systems. the interupts are handled in the same order as they arrive, first come first served.
    - In real life, Call Centers phone systems will use queues, to hold people calling them in an order, until a service representative is free.
    - When data is transferred asynchronously between two processes. Queue is used for synchronization.
*/

// #include"queue.h"
#include "circular_queue.h"
#include <stdlib.h>

int main()
{
    int cont,value;
    cont = 1;

    circualarQueue s;
    initializeQueue(&s);
    do
    {
        printf("\nHere are following choices \n");
        printf("1. To enqueue element in the queue. \n");
        printf("2. To dequeue element out of the queue. \n");
        printf("3. To check if queue is empty.\n");
        printf("4. To check if queue is full. \n");
        printf("5. To get the size of the stack \n");
        printf("6. To display all the elements of the queue\n");
        printf("7. To clear the terminal\n");
        printf("8. To exit\n");
        printf("Enter your choice: ");
        int choice;
        scanf("%d", &choice);
        switch (choice)
        {
        case 8:
        {
            cont = 0;
            break;
        }
        case 7:
        {
            system("clear");
            break;
        }
        case 6:
        {
            display(s);
            break;
        }
        case 5:
        {
            count(s);
            break;
        }
        case 4:
        {
            int result = isFull(s);
            if (result)
            {
                printf("\nQueue is full.\n");
                break;
            }
            printf("\nQueue is not full.\n");
            break;
        }
        case 3:
        {
            int result = isEmpty(s);
            if (result)
            {
                printf("\nQueue is empty.\n");
                break;
            }
            printf("\nQueue is not empty.\n");
            break;
        }
        case 2:
        {
            int ele = dequeue(&s);

            if (ele != -999)
            {
                printf("Element poped is %d.", ele);
            }
            break;
        }
        case 1:
        {
            printf("Enter the value to enqueue: ");
            scanf("%d", &value);
            enqueue(&s, value);
            break;
        }
        default:
        {
            break;
        }
        }

    } while (cont);

    return 0;
}