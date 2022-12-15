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
    Updating a node in the list.


    Some applications of linked lists.
    1. Linked lists can be used to implement stacks and queues.
    2. Linked lists can also be used to implement graphs(Adjancency list representation of the graphs).
    3. Implementing hash tables: each bucket of the hash table can itself be a linked list(open chain hashing).
*/


#include<stdio.h>
#include<stdlib.h>


typedef struct node{
    int data;
    struct node *next;
} Node;


Node *createNewNode(int data){
    Node *newNode=(Node *)malloc(sizeof(Node));
    newNode->data=data;
    newNode->next=NULL;
    return newNode;
}

int count_of_nodes(Node *head){
    int count=0;
    if(head==NULL){
        printf("\nThe linked list is empty.\n");
        return 0;
    }
    Node *ptr=NULL;
    ptr=head;
    while(ptr!=NULL){
        count++;
        ptr=ptr->next;
    }
    return count;
}

void print_the_list(Node *head){
    if(head==NULL){
        printf("\nThe linked list is empty.\n");
        return;
    }
    Node *ptr=NULL;
    ptr=head;
    printf("\nThe elements in the linked list are: ");
    printf("\n(Head,%p)",&(*head));
    while(ptr!=NULL){
        printf("\n(%d,  %p)",ptr->data,&(*ptr->next)); 
        ptr=ptr->next;
    }
}


int main(){

    Node *head=NULL;
    head=createNewNode(31);
       
    Node *current=NULL;
    current=createNewNode(25);
    head->next=current;
    
    current=createNewNode(52);
    head->next->next=current;

    current=createNewNode(48);
    head->next->next->next=current;

    current=createNewNode(38);
    head->next->next->next->next=current;

    current=createNewNode(62);
    head->next->next->next->next->next=current;

    current=createNewNode(84);
    head->next->next->next->next->next->next=current;

    print_the_list(head);
    printf("\nThe number of elements in the current list is %d.\n",count_of_nodes(head));
    return 0;
}

































