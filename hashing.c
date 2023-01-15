// Hashing with open hashing collision resolution technique (Chaining).
#include <stdio.h>
#include <stdlib.h>

typedef struct node
{
    int data;
    struct node *next;
} Node;

typedef struct hash_table
{
    // Each element of array will contain head to a new linked list.
    Node **array;
    int size;
} HashTable;

void initializeHashTable(HashTable *table, int size)
{
    table->size = size;
    table->array = malloc(sizeof(Node) * table->size);

    for (int x = 0; x < table->size; x++)
    {
        table->array[x] = NULL;
    }
}

int hash(int key)
{
    return key % 10;
}

Node *create_new_node(int data)
{
    Node *newNode = (Node *)malloc(sizeof(Node));

    newNode->data = data;
    newNode->next = NULL;

    return newNode;
}

void insert_a_value(HashTable table, int value)
{
    int generatedHash = hash(value);
    Node *temp = NULL, *temp1 = NULL;
    temp = table.array[generatedHash];

    if (temp == NULL)
    {
        table.array[generatedHash] = create_new_node(value);
    }
    else
    {
        while (temp != NULL && temp->data < value)
        {
            temp1 = temp;
            temp = temp->next;
        }
        temp1->next = create_new_node(value);
        temp1->next->next = temp;
    }
}

int main()
{
    HashTable table;
    initializeHashTable(&table, 20);
    insert_a_value(table, 12);
    insert_a_value(table, 22);
    insert_a_value(table, 32);
    insert_a_value(table, 23);
    insert_a_value(table, 6);
    insert_a_value(table, 8);
    insert_a_value(table, 29);
    insert_a_value(table, 15);
    insert_a_value(table, 34);
    for (int x = 0; x < table.size; x++)
    {
        Node *temp=NULL;
        temp=table.array[x];
        printf("\nRow %d: ",x);
        while(temp!=NULL){
            printf("%d ",temp->data);
            temp=temp->next;
        }
    }
}